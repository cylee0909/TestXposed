package com.cylee.testxpose;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by cylee on 2018/8/12.
 */

public class HookStartActivity implements IXposedHookLoadPackage {
    public Context mContext;
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("HookLogicReadAssist package + "+lpparam.packageName+" launched");
        if(lpparam.packageName.contains("com.ss.android.ugc.aweme")) {

            try {
                XposedHelpers.findAndHookMethod("android.app.Activity", lpparam.classLoader, "startActivityForResult", Intent.class, int.class, new XC_MethodHook() {
                    @Override
                    protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                        super.beforeHookedMethod(param);
                        Log.d("cylee", "cylee-> startActivityForResult" + param);
                    }

                    @Override
                    protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                        super.afterHookedMethod(param);
                    }
                });
            } catch (Throwable e) {

            }

        }
    }

}
