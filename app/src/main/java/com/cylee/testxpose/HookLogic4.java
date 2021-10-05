package com.cylee.testxpose;

import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by cylee on 2018/8/12.
 */

public class HookLogic4 implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("package + "+lpparam.packageName+" launched");
        if(lpparam.packageName.contains("com.aiyouni")) {
            XposedHelpers.findAndHookMethod("com.hex.wanshiwu.v2.nidongde.videoplay.VideoDetailsActivityV2", lpparam.classLoader, "g", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.setResult(false);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.d("cylee", "VideoDetailsActivityV2 g" + param.getResult());
                }
            });
        }
    }
}
