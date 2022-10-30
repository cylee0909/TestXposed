package com.iflytek.readassistant.biz.broadcast.model.synthesize;

import android.content.Context;

/* loaded from: dump_dex_com.iflytek.readassistant/class5.dex */
public class SynthesizePath {
    public static String getResourcePath(Context context, String str) {
//        StringBuffer stringBuffer = new StringBuffer();
//        stringBuffer.append(parseCommonResourcePath());
//        stringBuffer.append(";");
//        stringBuffer.append(parseResourcePath(str));
        return "";
    }

//    private static String parseAssetsPath(Context context, String str) {
//        AssetFileDescriptor assetFileDescriptor;
//        Throwable th;
//        if (!TextUtils.isEmpty(str)) {
//            String packageResourcePath = context.getPackageResourcePath();
//            try {
//                assetFileDescriptor = context.getAssets().openFd(str);
//            } catch (Exception unused) {
//                assetFileDescriptor = null;
//            } catch (Throwable th2) {
//                th = th2;
//                assetFileDescriptor = null;
//            }
//            try {
//                String str2 = "fo|" + packageResourcePath + "|" + assetFileDescriptor.getStartOffset() + "|" + assetFileDescriptor.getLength();
//                if (assetFileDescriptor == null) {
//                    return str2;
//                }
//                try {
//                    assetFileDescriptor.close();
//                    return str2;
//                } catch (Exception unused2) {
//                    return str2;
//                }
//            } catch (Exception unused3) {
//                if (assetFileDescriptor != null) {
//                    try {
//                        assetFileDescriptor.close();
//                    } catch (Exception unused4) {
//                    }
//                }
//                return null;
//            } catch (Throwable th3) {
//                th = th3;
//                if (assetFileDescriptor != null) {
//                    try {
//                        assetFileDescriptor.close();
//                    } catch (Exception unused5) {
//                    }
//                }
//            }
//        }
//        return null;
//    }
//
//    private static String parseResourcePath(String str) {
//        if (TextUtils.isEmpty(str)) {
//            return null;
//        }
//        String offlineResourcePath = OfflineSpeakerResManager.getOfflineResourcePath(str);
//        File file = new File(offlineResourcePath);
//        if (!file.exists() || !file.isFile()) {
//            return null;
//        }
//        return "fo|" + offlineResourcePath + "|0|" + file.length();
//    }
//
//    private static String parseCommonResourcePath() {
//        String offlineCommonResourcePath = OfflineCommonResUtils.getOfflineCommonResourcePath();
//        File file = new File(offlineCommonResourcePath);
//        if (!file.exists() || !file.isFile()) {
//            return null;
//        }
//        return "fo|" + offlineCommonResourcePath + "|0|" + file.length();
//    }
}
