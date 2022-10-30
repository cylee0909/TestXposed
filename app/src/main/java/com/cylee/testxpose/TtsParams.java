package com.cylee.testxpose;

import android.os.Bundle;
import android.text.TextUtils;

import java.util.HashMap;

/* loaded from: dump_dex_com.iflytek.readassistant/class6.dex */
public final class TtsParams {
    public static final String CLOUD_TTS_ENGINE_TYPE_AISOUND = "aisound";
    public static final String CLOUD_TTS_ENGINE_TYPE_INTP65 = "intp65";
    public static final String CLOUD_TTS_ENGINE_TYPE_NEW = "x";
    public static final String CLOUD_TTS_METHOD_OF_READ_NUMBER_AS_STRING = "2";
    public static final String CLOUD_TTS_METHOD_OF_READ_NUMBER_AS_STRING_IF_NOT_SURE = "3";
    public static final String CLOUD_TTS_METHOD_OF_READ_NUMBER_AS_VALUE = "1";
    public static final String CLOUD_TTS_METHOD_OF_READ_NUMBER_AS_VALUE_IF_NOT_SURE = "0";
    public static final String CLOUD_TTS_METHOD_OF_READ_NUMBER_NONE = "0";
    public static final String CLOUD_TTS_ROLE_KANGHUI = "aisxhui";
    public static final String CLOUD_TTS_ROLE_XIAOXUE = "xiaoxue";
    public static final int CLOUD_TTS_TIMEOUT = 0;
    public static final int DEFAULT_PITCH = 50;
    public static final int DEFAULT_SCENE = 0;
    public static final int DEFAULT_SPEED = 55;
    public static final int DEFAULT_STREAM = 3;
    public static final int DEFAULT_VOLUME = 100;
    public static final String KEY_PARAM_AUTH_ID = "auth_id";
    public static final String KEY_PARAM_CLOUD_TTS_AUDIO_CACHE_COUNT = "audio_cache_count";
    public static final String KEY_PARAM_CLOUD_TTS_AUDIO_NEED_CACHE = "audio_need_cache";
    public static final String KEY_PARAM_CLOUD_TTS_DELAY = "cloud_tts_delay";
    public static final String KEY_PARAM_CLOUD_TTS_ENGINE_TYPE = "cloud_tts_engine_type";
    public static final String KEY_PARAM_CLOUD_TTS_METHOD_OF_READ_NUMBER = "cloud_tts_method_of_read_number";
    public static final String KEY_PARAM_CLOUD_TTS_NEEDPLAY = "audio_need_play";
    public static final String KEY_PARAM_ENABLE_SPEED_RATE = "enable_speed_rate";
    public static final String KEY_PARAM_ID = "tts_id";
    public static final String KEY_PARAM_LOCAL_TTS_METHOD_OF_READ_CHINESE_NUMBER_1 = "method_of_read_chinese_number_1";
    public static final String KEY_PARAM_LOCAL_TTS_VOICE_MODE = "voice_mode";
    public static final String KEY_PARAM_PITCH = "pitch";
    public static final String KEY_PARAM_ROLE = "role";
    public static final String KEY_PARAM_SCENE = "scene";
    public static final String KEY_PARAM_SPEED = "speed";
    public static final String KEY_PARAM_SPEED_RATE = "speed_rate";
    public static final String KEY_PARAM_SPEED_RATE_THRESHOLD = "speed_rate_threshold";
    public static final String KEY_PARAM_STREAM = "stream";
    public static final String KEY_PARAM_TTP = "ttp";
    public static final String KEY_PARAM_TTS_ENGINE_TYPE = "tts_engine_type";
    public static final String KEY_PARAM_VOLUME = "volume";
    public static final String KEY_SERVER_URL = "server_url";
    public static final String LOCAL_TTS_CHINESE_NUMBER_1_READ_YAO = "yao";
    public static final String LOCAL_TTS_CHINESE_NUMBER_1_READ_YI = "yi";
    public static final String LOCAL_TTS_ROLE_JIAJIA = "jiajia";
    public static final String LOCAL_TTS_ROLE_MINGMA = "maming";
    public static final String LOCAL_TTS_ROLE_NANNAN = "nannan";
    public static final String LOCAL_TTS_ROLE_XIAOFENG = "xiaofeng";
    public static final String LOCAL_TTS_ROLE_XIAOKUN = "xiaokun";
    public static final String LOCAL_TTS_ROLE_XIAOLIN = "xiaolin";
    public static final String LOCAL_TTS_ROLE_XIAOMEI = "xiaomei";
    public static final String LOCAL_TTS_ROLE_XIAOQIAN = "xiaoqian";
    public static final String LOCAL_TTS_ROLE_XIAOQIANG = "xiaoqiang";
    public static final String LOCAL_TTS_ROLE_XIAORONG = "xiaorong";
    public static final String LOCAL_TTS_ROLE_XIAOYAN = "xiaoyan";
    public static final String LOCAL_TTS_ROLE_XIAOYUAN = "xiaoyuan";
    public static final String LOCAL_TTS_ROLE_XUDUO = "xuduo";
    public static final String LOCAL_TTS_VOICE_MODE_CHROUS = "chorus";
    public static final String LOCAL_TTS_VOICE_MODE_ECCENTRIC = "eccentric";
    public static final String LOCAL_TTS_VOICE_MODE_ECHO = "echo";
    public static final String LOCAL_TTS_VOICE_MODE_NONE = "none";
    public static final String LOCAL_TTS_VOICE_MODE_REVERB = "reverb";
    public static final String LOCAL_TTS_VOICE_MODE_ROBERT = "robert";
    public static final String LOCAL_TTS_VOICE_MODE_UNDERWATER = "underwater";
    public static final String LOCAL_TTS_VOICE_MODE_WANDER = "wander";
    public static final int MAX_PITCH = 100;
    public static final int MAX_SPEED = 100;
    public static final int MAX_VOLUME = 100;
    public static final int MIN_PITCH = 0;
    public static final int MIN_SPEED = 0;
    public static final int MIN_VOLUME = 0;
    public static final String TTS_ENGINE_TYPE_CLOUD = "cloud";
    public static final String TTS_ENGINE_TYPE_LOCAL = "purextts";
    public static final String TTS_ENGINE_TYPE_PTTS = "ptts";
    public static final String TTS_TEXT = "text";
    public static final String kEY_PARAM_TTS_RES_PATH = "tts_res_path";
    private String mAuthId;
    private int mCloudTTSAudioCacheCount;
    private String mCloudTTSEngineType;
    private String mCloudTTSMethodOfReadNumber;
    private String mCloudTTSRole;
    private int mCloudTTSTimeout;
    private boolean mEnableSpeedRate;
    private boolean mIsCloudTTSAudioNeedCache;
    private boolean mIsCloudTTSNeedPlay;
    private String mLocalTTSMethodOfReadNumber1;
    private String mLocalTTSRole;
    private int mPitch;
    private String mServerUrl;
    private int mSpeed;
    private int mSpeedRateThreshold;
    private String mTTSEngineType;
    private long mTTSId;
    private int mTTSScene;
    private String mTtsResPath;
    private String mTtsTtp;
    private String mVoiceMode;
    private int mVolume;
    private int mStreamType = 3;
    private float mSpeedRate = 1.0f;

    public final void setParams(Bundle bundle) {
        setDefault();
        if (bundle == null) {
            return;
        }
        setTTSEngineType(bundle.getString(KEY_PARAM_TTS_ENGINE_TYPE));
        setCloudTTSEngineType(bundle.getString(KEY_PARAM_CLOUD_TTS_ENGINE_TYPE));
        setCloudTTSAudioNeedCache(bundle.getBoolean(KEY_PARAM_CLOUD_TTS_AUDIO_NEED_CACHE, false));
        setCloudTTSAudioNeedPlay(bundle.getBoolean(KEY_PARAM_CLOUD_TTS_NEEDPLAY, true));
        setCloudTTSAudioCacheCount(bundle.getInt(KEY_PARAM_CLOUD_TTS_AUDIO_CACHE_COUNT, 1));
        setCloudTTSDelayTime(bundle.getInt(KEY_PARAM_CLOUD_TTS_DELAY, 0));
        setCloudTTSMethodOfReadNumber(bundle.getString(KEY_PARAM_CLOUD_TTS_METHOD_OF_READ_NUMBER));
        setCloudTTSRole(bundle.getString("role"));
        setVoiceMode(bundle.getString(KEY_PARAM_LOCAL_TTS_VOICE_MODE));
        setLocalTTSMethodOfReadNumber1(bundle.getString(KEY_PARAM_LOCAL_TTS_METHOD_OF_READ_CHINESE_NUMBER_1));
        setSpeed(bundle.getInt("speed", 55));
        setPitch(bundle.getInt("pitch", 50));
        setVolume(bundle.getInt("volume", 100));
        setStreamType(bundle.getInt("stream", 3));
        setTTSScene(bundle.getInt("scene"));
        setCloudTTSttp(bundle.getString(KEY_PARAM_TTP));
        setTTSId(bundle.getLong("tts_id"));
        setEnableSpeedRate(bundle.getBoolean(KEY_PARAM_ENABLE_SPEED_RATE, this.mEnableSpeedRate));
        setSpeedRateThreshold(bundle.getInt(KEY_PARAM_SPEED_RATE_THRESHOLD, this.mSpeedRateThreshold));
        setSpeedRate(bundle.getFloat(KEY_PARAM_SPEED_RATE, this.mSpeedRate));
        setTtsResPath(bundle.getString(kEY_PARAM_TTS_RES_PATH));
        setAuthId(bundle.getString(KEY_PARAM_AUTH_ID));
        setServerUrl(bundle.getString(KEY_SERVER_URL));
    }

    private void setDefault() {
        this.mTTSEngineType = TTS_ENGINE_TYPE_LOCAL;
        this.mCloudTTSEngineType = "x";
        this.mIsCloudTTSAudioNeedCache = false;
        this.mIsCloudTTSNeedPlay = true;
        this.mCloudTTSAudioCacheCount = 1;
        this.mCloudTTSMethodOfReadNumber = "0";
        this.mCloudTTSTimeout = 0;
        this.mCloudTTSRole = CLOUD_TTS_ROLE_XIAOXUE;
        this.mSpeed = 55;
        this.mPitch = 50;
        this.mVolume = 100;
        this.mStreamType = 3;
        this.mLocalTTSRole = "xiaoyuan";
        this.mLocalTTSMethodOfReadNumber1 = LOCAL_TTS_CHINESE_NUMBER_1_READ_YAO;
        this.mVoiceMode = "none";
        this.mTTSScene = 0;
        this.mTtsTtp = "text";
    }

    private void setTTSEngineType(String str) {
        if (!TextUtils.isEmpty(str) && (TTS_ENGINE_TYPE_LOCAL.equals(str) || TTS_ENGINE_TYPE_CLOUD.equals(str) || TTS_ENGINE_TYPE_PTTS.equals(str))) {
            this.mTTSEngineType = str;
        } else {
            this.mTTSEngineType = TTS_ENGINE_TYPE_LOCAL;
        }
    }

    private void setCloudTTSEngineType(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mCloudTTSEngineType = str;
        } else {
            this.mCloudTTSEngineType = CLOUD_TTS_ENGINE_TYPE_INTP65;
        }
    }

    private void setCloudTTSAudioNeedCache(boolean z) {
        this.mIsCloudTTSAudioNeedCache = z;
    }

    private void setCloudTTSAudioNeedPlay(boolean z) {
        this.mIsCloudTTSNeedPlay = z;
    }

    private void setCloudTTSAudioCacheCount(int i) {
        if (i >= 0) {
            this.mCloudTTSAudioCacheCount = i;
        }
    }

    private void setCloudTTSMethodOfReadNumber(String str) {
        if (!TextUtils.isEmpty(str) && ("0".equals(str) || "1".equals(str) || "0".equals(str) || "2".equals(str) || "3".equals(str))) {
            this.mCloudTTSMethodOfReadNumber = str;
        } else {
            this.mCloudTTSMethodOfReadNumber = "0";
        }
    }

    private void setLocalTTSMethodOfReadNumber1(String str) {
        if (!TextUtils.isEmpty(str) && (LOCAL_TTS_CHINESE_NUMBER_1_READ_YAO.equals(str) || LOCAL_TTS_CHINESE_NUMBER_1_READ_YI.equals(str))) {
            this.mLocalTTSMethodOfReadNumber1 = str;
        } else {
            this.mLocalTTSMethodOfReadNumber1 = LOCAL_TTS_CHINESE_NUMBER_1_READ_YAO;
        }
    }

    private void setCloudTTSDelayTime(int i) {
        if (i >= 0) {
            this.mCloudTTSTimeout = i;
        }
    }

    public final void setCloudTTSRole(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mCloudTTSRole = CLOUD_TTS_ROLE_KANGHUI;
        } else {
            this.mCloudTTSRole = str;
        }
    }

    public final void setCloudTTSttp(String str) {
        if (TextUtils.isEmpty(str)) {
            this.mTtsTtp = "text";
        } else {
            this.mTtsTtp = str;
        }
    }

    public final void setLocalTTSRole(String str) {
        if (!TextUtils.isEmpty(str)) {
            this.mLocalTTSRole = str;
        } else {
            this.mLocalTTSRole = "xiaoyuan";
        }
    }

    private void setVoiceMode(String str) {
        if (!TextUtils.isEmpty(str) && ("none".equals(str) || LOCAL_TTS_VOICE_MODE_WANDER.equals(str) || LOCAL_TTS_VOICE_MODE_UNDERWATER.equals(str) || LOCAL_TTS_VOICE_MODE_ROBERT.equals(str) || LOCAL_TTS_VOICE_MODE_REVERB.equals(str) || LOCAL_TTS_VOICE_MODE_ECHO.equals(str) || LOCAL_TTS_VOICE_MODE_ECCENTRIC.equals(str) || LOCAL_TTS_VOICE_MODE_CHROUS.equals(str))) {
            this.mVoiceMode = str;
        } else {
            this.mVoiceMode = "none";
        }
    }

    private void setSpeed(int i) {
        if (i < 0 || i > 100) {
            return;
        }
        this.mSpeed = i;
    }

    private void setTTSScene(int i) {
        if (i >= 0) {
            this.mTTSScene = i;
        } else {
            this.mTTSScene = 0;
        }
    }

    private void setPitch(int i) {
        if (i < 0 || i > 100) {
            return;
        }
        this.mPitch = i;
    }

    private void setVolume(int i) {
        if (i < 0 || i > 100) {
            return;
        }
        this.mVolume = i;
    }

    private void setStreamType(int i) {
        if (i >= 0) {
            this.mStreamType = i;
        } else {
            this.mStreamType = 3;
        }
    }

    public final boolean getCloudTTSAudioNeedPlay() {
        return this.mIsCloudTTSNeedPlay;
    }

    public final String getCloudTTSttp() {
        return this.mTtsTtp;
    }

    public final int getCloudTTSAudioCacheCount() {
        return this.mCloudTTSAudioCacheCount;
    }

    public final int getCloudTTSDelay() {
        return this.mCloudTTSTimeout;
    }

    public final String getLocalTTSMethodOfReadNumber1() {
        return this.mLocalTTSMethodOfReadNumber1;
    }

    public final String getCloudTTSMethodOfReadNumber() {
        return this.mCloudTTSMethodOfReadNumber;
    }

    public final String getCloudTTSRole() {
        return this.mCloudTTSRole;
    }

    public final String getLocalTTSRole() {
        return this.mLocalTTSRole;
    }

    public final String getVoiceMode() {
        return this.mVoiceMode;
    }

    public final int getSpeed() {
        return this.mSpeed;
    }

    public final int getPitch() {
        return this.mPitch;
    }

    public final int getVolume() {
        return this.mVolume;
    }

    public final int getStreamType() {
        return this.mStreamType;
    }

    public final String getTTSEngineType() {
        return this.mTTSEngineType;
    }

    public final int getTTSScene() {
        return this.mTTSScene;
    }

    public final String getCloudTTSEngineType() {
        return this.mCloudTTSEngineType;
    }

    public final boolean isCloudTTSAudioNeedCache() {
        return this.mIsCloudTTSAudioNeedCache;
    }

    public final long getTTSId() {
        return this.mTTSId;
    }

    public final void setTTSId(long j) {
        this.mTTSId = j;
    }

    public final boolean isEnableSpeedRate() {
        return this.mEnableSpeedRate;
    }

    public final void setEnableSpeedRate(boolean z) {
        this.mEnableSpeedRate = z;
    }

    public final float getSpeedRate() {
        return this.mSpeedRate;
    }

    public final void setSpeedRate(float f) {
        this.mSpeedRate = f;
    }

    public final int getSpeedRateThreshold() {
        return this.mSpeedRateThreshold;
    }

    public final void setSpeedRateThreshold(int i) {
        this.mSpeedRateThreshold = i;
    }

    public final String getTtsResPath() {
        return this.mTtsResPath;
    }

    public final void setTtsResPath(String str) {
        this.mTtsResPath = str;
    }

    public final String getAuthId() {
        return this.mAuthId;
    }

    public final void setAuthId(String str) {
        this.mAuthId = str;
    }

    public final String getServerUrl() {
        return this.mServerUrl;
    }

    public final void setServerUrl(String str) {
        this.mServerUrl = str;
    }

    /* loaded from: dump_dex_com.iflytek.readassistant/class6.dex */
    public static class TtsParamMap {
        private static final HashMap<String, Integer> mTTSParamMap;

        static {
            HashMap<String, Integer> hashMap = new HashMap<>();
            mTTSParamMap = hashMap;
            hashMap.put(TtsParams.LOCAL_TTS_CHINESE_NUMBER_1_READ_YAO, 0);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_CHINESE_NUMBER_1_READ_YI, 1);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_XIAOYAN, 3);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_JIAJIA, 9);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_MINGMA, 58);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_XUDUO, 58);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_XIAOFENG, 4);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_XIAOKUN, 25);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_XIAOMEI, 15);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_XIAOQIAN, 11);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_XIAOQIANG, 24);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_XIAORONG, 14);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_NANNAN, 7);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_ROLE_XIAOLIN, 22);
            mTTSParamMap.put("none", 0);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_VOICE_MODE_WANDER, 1);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_VOICE_MODE_ECHO, 2);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_VOICE_MODE_ROBERT, 3);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_VOICE_MODE_CHROUS, 4);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_VOICE_MODE_UNDERWATER, 5);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_VOICE_MODE_REVERB, 6);
            mTTSParamMap.put(TtsParams.LOCAL_TTS_VOICE_MODE_ECCENTRIC, 7);
        }

        public static Integer get(String str) {
            return mTTSParamMap.get(str);
        }
    }
}
