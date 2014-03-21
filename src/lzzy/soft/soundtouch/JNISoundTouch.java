package lzzy.soft.soundtouch;

public class JNISoundTouch
{
	
	public native void setSampleRate(int sampleRate);// ���ط��� ���������Ĳ���Ƶ��
	
	public native void setChannels(int channel);// ���ط��� ��������������
	
	public native void setTempoChange(float newTempo);// ���ط��� ���ٲ����
	
	public native void setPitchSemiTones(int newPitch);// ���ط��� ��������������
	
	public native void setRateChange(float newRate);// ���ط��� ��������������
	
	public native void putSamples(short[] samples, int len);
	
	public native short[] receiveSamples();
	
	static
	{
		System.loadLibrary("soundtouch");
	}
}
