package lzzy.soft.utils;

import android.util.Log;

public class Utils
{
	
	/*
	 * public static RecordingText convert(String s){
	 * 
	 * RecordingText text = new RecordingText();
	 * 
	 * int index = s.indexOf(" "); text.fileNum = s.substring(0, index);
	 * 
	 * // String content = s.substring(index+1, s.length()); text.fileContent =
	 * content.replaceAll("\t", "\n");
	 * 
	 * return text; }
	 */
	
	public static byte[] shortToByteSmall(short[] buf)
	{
		
		byte[] bytes = new byte[buf.length * 2];
		for (int i = 0, j = 0; i < buf.length; i++, j += 2)
		{
			short s = buf[i];
			
			byte b1 = (byte) (s & 0xff);
			byte b0 = (byte) ((s >> 8) & 0xff);
			
			bytes[j] = b1;
			bytes[j + 1] = b0;
		}
		return bytes;
		
	}
	
	// public static short byteToShort(byte[] b, int index)
	// {
	// return (short) (((b[index + 1] << 8) | b[index + 0] & 0xff));
	// }
	
	public static short[] byteArrayToShortArray(byte[] bytes)
	{
		int items = bytes.length / 2;
		
		short[] shorts = new short[items * 2];
		try
		{
			for (int i = 0; i < shorts.length - 1; i++)
			{
				shorts[i] = (short) ((bytes[i * 2] & 0xff) | (bytes[i * 2 + 1] & 0xff) << 8);
			}
		}
		
		catch (Exception e)
		{
			e.printStackTrace();
			Log.d("byteArrayToShortArray", "转换错误");
		}
		return shorts;
	}
}
