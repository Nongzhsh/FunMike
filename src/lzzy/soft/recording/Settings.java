package lzzy.soft.recording;

import android.os.Environment;

public class Settings
{
	
	public static String sdcardPath = Environment.getExternalStorageDirectory()
			.toString(); // ��ȡsd��·��
	public static String recordingPath = sdcardPath + "/FunMike/"; // ¼���ļ���λ��
	
	// ���徲̬���� �����ж�¼��״̬��¼���ļ��洢״̬
	public static final int MSG_RECORDING_START = 1;
	public static final int MSG_RECORDING_STOP = 2;
	public static final int MSG_RECORDING_STATE_ERROR = 3;
	public static final int MSG_RECORDING_EXCEPTION = 4;
	public static final int MSG_RECORDING_RELEASE = 5;
	public static final int MSG_FILE_SAVE_SUCCESS = 6;
	public static final int MSG_FILE_EXCEPTION = 7;
	
}
