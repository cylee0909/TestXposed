package com.iflytek.ys.common.speech;

import android.content.Context;

import com.iflytek.ys.common.speech.interfaces.ISynthesizer;

/* loaded from: dump_dex_com.iflytek.readassistant/class6.dex */
public final class SynthesizerFactory {
    public static final int PLAY_MSC = 2;
    private static ISynthesizer mSynthesizer;

    public static ISynthesizer createSynthesize(Context context, Object synthesizeConfig) {
        return mSynthesizer;
    }

    public static ISynthesizer getSynthesize() {
        ISynthesizer iSynthesizer;
        synchronized (SynthesizerFactory.class) {
            iSynthesizer = mSynthesizer;
        }
        return iSynthesizer;
    }
}
