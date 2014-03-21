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
		recordingThread = new RecordingThread(handler, recordQueue); // ʵ����¼����RecordingThread���߳�
		recordingThread.start(); // ��ʼ¼���߳�
		
		soundTouchThread = new SoundTouchThread(handler, recordQueue); // ʵ����SoundTouchThread�߳�
		soundTouchThread.start();// ��ʼ�̡߳�
		
		audioTrackPlay = new SoundTouchPlay();
		audioTrackPlay.start();
	}
	
	public void stop()
	{
		recordingThread.stopRecording(); // ֹͣ�߳�
		soundTouchThread.stopSoundTounch();
		
		audioTrackPlay.stopAudioTrackPlay();
	}
}
