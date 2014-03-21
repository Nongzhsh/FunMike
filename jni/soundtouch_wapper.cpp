/* DO NOT EDIT THIS FILE - it is machine generated */
#include <jni.h>
/* Header for class lzzy_soft_soundtouch_JNISoundTouch */

#ifndef _Included_lzzy_soft_soundtouch_JNISoundTouch
#define _Included_lzzy_soft_soundtouch_JNISoundTouch
#ifdef __cplusplus
extern "C" {
#endif
#include "SoundTouch/SoundTouch.h"
#define BUFFER_SIZE 4096

soundtouch::SoundTouch mSoundTouch;
/*
 * Class:     lzzy_soft_soundtouch_JNISoundTouch
 * Method:    setSampleRate
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_lzzy_soft_soundtouch_JNISoundTouch_setSampleRate
(JNIEnv *env, jobject obj, jint sampleRate)
{
	mSoundTouch.setSampleRate(sampleRate);
}

/*
 * Class:     lzzy_soft_soundtouch_JNISoundTouch
 * Method:    setChannels
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_lzzy_soft_soundtouch_JNISoundTouch_setChannels
(JNIEnv *env, jobject obj, jint channel)
{
	mSoundTouch.setChannels(channel);
}

/*
 * Class:     lzzy_soft_soundtouch_JNISoundTouch
 * Method:    setTempoChange
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_lzzy_soft_soundtouch_JNISoundTouch_setTempoChange
(JNIEnv *env, jobject obj, jfloat newTempo)
{
	mSoundTouch.setTempoChange(newTempo);
}

/*
 * Class:     lzzy_soft_soundtouch_JNISoundTouch
 * Method:    setPitchSemiTones
 * Signature: (I)V
 */
JNIEXPORT void JNICALL Java_lzzy_soft_soundtouch_JNISoundTouch_setPitchSemiTones
(JNIEnv *env, jobject obj, jint pitch)
{
	mSoundTouch.setPitchSemiTones(pitch);
}

/*
 * Class:     lzzy_soft_soundtouch_JNISoundTouch
 * Method:    setRateChange
 * Signature: (F)V
 */
JNIEXPORT void JNICALL Java_lzzy_soft_soundtouch_JNISoundTouch_setRateChange
(JNIEnv *env, jobject obj, jfloat newRate)
{
	mSoundTouch.setRateChange(newRate);
}

/*
 * Class:     lzzy_soft_soundtouch_JNISoundTouch
 * Method:    putSamples
 * Signature: ([SI)V
 */
JNIEXPORT void JNICALL Java_lzzy_soft_soundtouch_JNISoundTouch_putSamples
(JNIEnv *env, jobject obj, jshortArray samples, jint len)
{
	jshort *input_samples = env->GetShortArrayElements(samples, NULL);
	mSoundTouch.putSamples(input_samples, len);
	env->ReleaseShortArrayElements(samples, input_samples, 0);
}
/*
 * Class:     lzzy_soft_soundtouch_JNISoundTouch
 * Method:    receiveSamples
 * Signature: ()[S
 */
JNIEXPORT jshortArray JNICALL Java_lzzy_soft_soundtouch_JNISoundTouch_receiveSamples
(JNIEnv *env, jobject obj)
{
	short buffer[BUFFER_SIZE];
	int nSamples = mSoundTouch.receiveSamples(buffer, BUFFER_SIZE);

	jshortArray receiveSamples = env->NewShortArray(nSamples);
	env->SetShortArrayRegion(receiveSamples, 0, nSamples, buffer);

	return receiveSamples;
}

#ifdef __cplusplus
}
#endif
#endif