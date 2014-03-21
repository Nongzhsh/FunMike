package lzzy.soft.soundtouch;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import lzzy.soft.audiosave.AudioSave;
import lzzy.soft.utils.Utils;
import android.os.Handler;

public class SoundTouchThread extends Thread
{
	private BlockingQueue<short[]> recordQueue;
	private Handler handler;
	private static final long TIME_WAIT_RECORDING = 100;
	private volatile boolean setToStopped = false;
	private JNISoundTouch soundtouch = new JNISoundTouch();
	
	public LinkedList<byte[]> audioDatas = new LinkedList<byte[]>();
	public static short[] stAudioData;
	
	public SoundTouchThread(Handler handler, BlockingQueue<short[]> recordQueue)
	{
		this.handler = handler;
		this.recordQueue = recordQueue;
	}
	
	public void stopSoundTounch()
	{
		setToStopped = true;
	}
	
	@Override
	public void run()
	{
		audioDatas.clear();
		
		soundtouch.setSampleRate(44100);// 设置声音的采样频率8000~48000Hz
		soundtouch.setChannels(1);// 声道 1和2
		soundtouch.setPitchSemiTones(7);// 音调 -60 .. + 60 音程
		soundtouch.setRateChange(0.0f);// 音速 -95.0 .. +5000.0 %
		soundtouch.setTempoChange(-5.0f);// 节奏 -95.0 .. +5000.0 %
		
		short[] recordingData;
		while (true)
		{
			try
			{
				recordingData = recordQueue.poll(TIME_WAIT_RECORDING,
						TimeUnit.MILLISECONDS);
				
				if (recordingData != null)
				{
					soundtouch.putSamples(recordingData, recordingData.length);// soundtouch对录音数据进行处理
					
					stAudioData = soundtouch.receiveSamples(); // 输出处理后的音频
					
					short[] buffer;
					do
					{
						buffer = soundtouch.receiveSamples();
						byte[] bytes = Utils.shortToByteSmall(buffer);
						audioDatas.add(bytes); // 用于保存录音文件
					}
					while (buffer.length > 0);
				}
				
				if (setToStopped && recordQueue.size() == 0)
				{
					break;
				}
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
		}
		AudioSave.audioSave(handler, audioDatas);
	}
}
