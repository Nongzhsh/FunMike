package lzzy.soft.audioplay;

import lzzy.soft.recording.Settings;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Handler;
import android.util.Log;

public class AudioPlay implements OnCompletionListener
{
	private MediaPlayer mediaPlayer;
	private Handler minHandler;
	
	public AudioPlay(Handler minHandler)
	{
		this.minHandler = minHandler;
		mediaPlayer = new MediaPlayer();
	}
	
	public void stop()
	{
		if (mediaPlayer != null && mediaPlayer.isPlaying())
		{
			mediaPlayer.stop();
			onCompletion(mediaPlayer);
		}
	}
	
	public void play() // 播放录音文件
	{
		try
		{
			mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
			mediaPlayer.setOnCompletionListener(this);
			mediaPlayer.setDataSource(Settings.recordingPath + "funmike.wav");
			mediaPlayer.prepare();
			mediaPlayer.start();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			Log.e("soundtouch", e.getMessage());
		}
	}
	
	@Override
	public void onCompletion(MediaPlayer mp) // 播放完成
	{
		minHandler.sendEmptyMessage(0);
		mediaPlayer.release(); // 释放内存
	}
}
