package lzzy.soft.soundtouch;

import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioTrack;

public class SoundTouchPlay extends Thread
{
	private volatile boolean setToStopped = false;
	
	private int bufferSize = AudioTrack.getMinBufferSize(44100,
			AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT);// 获得缓冲区字节大小
	
	private short[] stAudioData;
	
	private AudioTrack audioTrack;
	
	public void stopAudioTrackPlay()
	{
		setToStopped = true;
	}
	
	@Override
	public void run()
	{
		audioTrack = new AudioTrack(AudioManager.STREAM_MUSIC, 44100,
				AudioFormat.CHANNEL_OUT_MONO, AudioFormat.ENCODING_PCM_16BIT,
				bufferSize * 2, AudioTrack.MODE_STREAM);
		
		try
		{
			audioTrack.play();
			
			while (true)
			{
				stAudioData = SoundTouchThread.stAudioData;
				
				if (stAudioData != null)
				{
					audioTrack.write(stAudioData, 0, stAudioData.length);
				}
				
				if (setToStopped && stAudioData.length == 0)
				{
					break;
				}
			}
			
			audioTrack.stop();
			audioTrack.release();
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
