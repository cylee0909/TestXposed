package com.iflytek.ys.common.speech.interfaces;

/* loaded from: dump_dex_com.iflytek.readassistant/class6.dex */
public interface ISynthesizerListener {
    void onMscSessionEnd(long j, String str);

    void onPlayBegin(long j);

    void onPlayCompleted(long j, String str);

    void onPlayInterrupted(long j);

    void onPlayPaused(long j);

    void onPlayResumed(long j);

    void onProgress(String str, long j, int i);
}
