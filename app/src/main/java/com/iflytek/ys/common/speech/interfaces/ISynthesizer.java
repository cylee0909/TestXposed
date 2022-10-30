package com.iflytek.ys.common.speech.interfaces;

import android.os.Bundle;

/* loaded from: dump_dex_com.iflytek.readassistant/class6.dex */
public interface ISynthesizer {
    public static final int ERROR = -1;
    public static final int SUCCESS = 0;

    void destroy();

    void init();

    boolean isSpeaking(ISynthesizerListener iSynthesizerListener);

    void onApnChange(Object apnAccessorType);

    int pauseSpeak(ISynthesizerListener iSynthesizerListener);

    void preSynthesize(long j, String str);

    int resumeSpeak(ISynthesizerListener iSynthesizerListener);

    void setLogCallback(Object iBlcLogCallback);

    void speak(String str, Bundle bundle, ISynthesizerListener iSynthesizerListener);

    int stopSpeak(ISynthesizerListener iSynthesizerListener);
}
