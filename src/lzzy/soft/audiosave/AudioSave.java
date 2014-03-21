package lzzy.soft.audiosave;

import java.io.FileOutputStream;
import java.util.LinkedList;

import lzzy.soft.recording.Settings;
import android.os.Handler;

public class AudioSave
{
	public static void audioSave(Handler handler, LinkedList<byte[]> audioDatas)
	{
		int fileLength = 0;
		for (byte[] bytes : audioDatas)
		{
			fileLength += bytes.length;
		}
		try
		{
			WaveHeader header = new WaveHeader(fileLength);
			byte[] headers = header.getHeader();
			
			// 创建录音文件funmike.wav
			FileOutputStream out = new FileOutputStream(Settings.recordingPath
					+ "funmike.wav");
			out.write(headers);
			
			for (byte[] bytes : audioDatas)
			{
				out.write(bytes);
			}
			
			out.close();
			handler.sendEmptyMessage(Settings.MSG_FILE_SAVE_SUCCESS);
		}
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{   
			
		}
	}
}
