package lzzy.soft.soundtouch;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import lzzy.soft.recording.RecordingThread;
import android.os.Handler;

public class SoundTouchClient
{
	private BlockingQueue<short[]> recordQueue = new LinkedBlockingQueue<short[]>();
	private RecordingThread recordingThread;
	private SoundTouchThread soundTouchThread;
	private SoundTouchPlay audioTrackPlay;
	
	private Handler handler = new Handler();
	
	public void start()
	{
		recordingThread = new RecordingThread(handler, recordQueue); // 实例化录音机RecordingThread类线程
		recordingThread.start(); // 开始录音线程
		
		soundTouchThread = new SoundTouchThread(handler, recordQueue); // 实例化SoundTouchThread线程
		soundTouchThread.start();// 开始线程、
		
		audioTrackPlay = new SoundTouchPlay();
		audioTrackPlay.start();
	}
	
	public void stop()
	{
		recordingThread.stopRecording(); // 停止线程
		soundTouchThread.stopSoundTounch();
		
		audioTrackPlay.stopAudioTrackPlay();
	}
}
