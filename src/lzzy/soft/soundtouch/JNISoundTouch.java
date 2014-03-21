package lzzy.soft.soundtouch;

public class JNISoundTouch
{
	
	public native void setSampleRate(int sampleRate);// 本地方法 设置声音的采样频率
	
	public native void setChannels(int channel);// 本地方法 设置声音的声道
	
	public native void setTempoChange(float newTempo);// 本地方法 变速不变调
	
	public native void setPitchSemiTones(int newPitch);// 本地方法 设置声音的音调
	
	public native void setRateChange(float newRate);// 本地方法 设置声音的速率
	
	public native void putSamples(short[] samples, int len);
	
	public native short[] receiveSamples();
	
	static
	{
		System.loadLibrary("soundtouch");
	}
}
