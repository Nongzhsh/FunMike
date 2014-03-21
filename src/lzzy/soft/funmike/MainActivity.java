package lzzy.soft.funmike;

import java.io.File;

import lzzy.soft.audioplay.AudioPlay;
import lzzy.soft.hunmike.R;
import lzzy.soft.recording.Settings;
import lzzy.soft.soundtouch.SoundTouchClient;
import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener
{
	private Button btnStart;
	private Button btnFinish;
	private Button btnStop;
	private Button btnPlay;
	
	private SoundTouchClient soundTouchClient;
	private AudioPlay audioPlay;
	
	private Handler handler = new Handler()
	{
		public void handleMessage(Message msg)
		{
			btnStart.setVisibility(View.VISIBLE);
			btnFinish.setVisibility(View.INVISIBLE);
			
			btnPlay.setVisibility(View.VISIBLE);
			btnStop.setVisibility(View.INVISIBLE);
		}
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStart.setOnClickListener(this);
		
		btnFinish = (Button) findViewById(R.id.btnFinish);
		btnFinish.setOnClickListener(this);
		
		btnStop = (Button) findViewById(R.id.btnStop);
		btnStop.setOnClickListener(this);
		
		btnPlay = (Button) findViewById(R.id.btnPlay);
		btnPlay.setOnClickListener(this);
		
		soundTouchClient = new SoundTouchClient();
		
		String state = Environment.getExternalStorageState(); // 获取SD卡路径
		if (state.equals(Environment.MEDIA_MOUNTED)) // 判断SD卡是否可读写
		{
			File yzsPath = new File(Settings.recordingPath);
			
			if (!yzsPath.isDirectory()) // 判断是否存在文件文件夹
			{
				yzsPath.mkdir(); // 创建文件夹
			}
		}
		else
		{
			Toast.makeText(this, "sdcard error!", Toast.LENGTH_LONG).show();
		}
	}
	
	@Override
	public void onClick(View v)
	{
		switch (v.getId())
		{
			case R.id.btnStart:
				btnStart.setVisibility(View.INVISIBLE);
				btnFinish.setVisibility(View.VISIBLE);
				
				btnStop.setVisibility(View.INVISIBLE);
				btnPlay.setVisibility(View.INVISIBLE);
				
				soundTouchClient.start();
				break;
			
			case R.id.btnFinish:
				btnStart.setVisibility(View.VISIBLE);
				btnFinish.setVisibility(View.INVISIBLE);
				
				btnStop.setVisibility(View.INVISIBLE);
				btnPlay.setVisibility(View.VISIBLE);
				
				soundTouchClient.stop();
				break;
			
			case R.id.btnStop:
				btnStart.setVisibility(View.VISIBLE);
				btnFinish.setVisibility(View.INVISIBLE);
				
				btnStop.setVisibility(View.INVISIBLE);
				btnPlay.setVisibility(View.VISIBLE);
				
				audioPlay.stop();
				break;
			
			case R.id.btnPlay:
				btnStart.setVisibility(View.INVISIBLE);
				btnFinish.setVisibility(View.INVISIBLE);
				
				btnStop.setVisibility(View.VISIBLE);
				btnPlay.setVisibility(View.INVISIBLE);
				
				audioPlay = new AudioPlay(handler);
				audioPlay.play();
				break;
		}
	}
}
