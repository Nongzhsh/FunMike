package lzzy.soft.recording;

import java.util.concurrent.BlockingQueue;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Handler;

public class RecordingThread extends Thread
{
	
	public static int FREQUENCY = 44100; // 采样率
	private static int CHANNEL = AudioFormat.CHANNEL_IN_MONO; // 单声道（立体为
															  // CHANNEL_IN_STEREO）
	private static int ENCODING = AudioFormat.ENCODING_PCM_16BIT;// 编码制式(PCM)和采样大小(16bit)
	
	private volatile boolean setToStopped = false; // 设置录制状态
	
	private Handler handler;
	
	public static int bufferSize = AudioRecord.getMinBufferSize(FREQUENCY,
			CHANNEL, ENCODING);// 获得缓冲区字节大小
	
	private BlockingQueue<short[]> recordQueue; // recordQueue设置线程容器
	
	public RecordingThread(Handler handler, BlockingQueue<short[]> recordQueue)
	{
		this.handler = handler;
		this.recordQueue = recordQueue;
	}
	
	public void stopRecording()
	{
		this.setToStopped = true;
	}
	
	@Override
	public void run() // 执行录音线程
	{
		AudioRecord audioRecord = null;
		try
		{
			short[] buffer = new short[bufferSize];
			audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC,
					FREQUENCY, CHANNEL, ENCODING, bufferSize); // 实例化录音机
			
			int state = audioRecord.getState(); // 录音机状态
			if (state == AudioRecord.STATE_INITIALIZED) // 是否准备就绪(STATE_INITIALIZED
														// 返回值1)
			{
				audioRecord.startRecording(); // 开始录音
				
				handler.obtainMessage(Settings.MSG_RECORDING_START)
						.sendToTarget(); // 向handler发送开始录音的消息
				
				boolean flag = true;
				
				while (!setToStopped)
				{
					int len = audioRecord.read(buffer, 0, buffer.length);// 从音频硬件录制缓冲区读取数据。
					if (flag)
					{
						
						double sum = 0.0;
						for (int i = 0; i < len; i++)
						{
							sum += buffer[i];
						}
						if (sum == 0.0)
						{
							continue;
						}
						else
						{
							handler.obtainMessage(Settings.MSG_RECORDING_START)
									.sendToTarget();
							flag = false;
						}
					}
					
					short[] data = new short[len];
					System.arraycopy(buffer, 0, data, 0, len);
					recordQueue.add(data);
				}
				handler.sendEmptyMessage(Settings.MSG_RECORDING_STOP);// 构建录音停止消息
				audioRecord.stop(); // 停止录音
			}
			else
			{
				handler.sendEmptyMessage(Settings.MSG_RECORDING_STATE_ERROR);// 构建录音错误消息
			}
		}
		catch (Exception e)
		{
			handler.sendEmptyMessage(Settings.MSG_RECORDING_EXCEPTION);// 构建录音异常消息
		}
		finally
		{
			try
			{
				audioRecord.release(); // 释放录音机内存
				audioRecord = null;
				
				handler.sendEmptyMessage(Settings.MSG_RECORDING_RELEASE);// 构建释放录音机内存消息
			}
			catch (Exception e)
			{}
		}
	}
}
