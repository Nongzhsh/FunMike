package lzzy.soft.recording;

import android.os.Environment;

public class Settings
{
	
	public static String sdcardPath = Environment.getExternalStorageDirectory()
			.toString(); // 获取sd卡路径
	public static String recordingPath = sdcardPath + "/FunMike/"; // 录音文件的位置
	
	// 定义静态常量 便于判读录音状态，录音文件存储状态
	public static final int MSG_RECORDING_START = 1;
	public static final int MSG_RECORDING_STOP = 2;
	public static final int MSG_RECORDING_STATE_ERROR = 3;
	public static final int MSG_RECORDING_EXCEPTION = 4;
	public static final int MSG_RECORDING_RELEASE = 5;
	public static final int MSG_FILE_SAVE_SUCCESS = 6;
	public static final int MSG_FILE_EXCEPTION = 7;
	
}
