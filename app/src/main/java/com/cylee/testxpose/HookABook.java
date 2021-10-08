package com.cylee.testxpose;

import android.content.Context;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookABook implements IXposedHookLoadPackage {
    public Context mContext;
    public HookABook(Context context) {
        mContext = context;
    }

    volatile boolean hookComplete = false;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        realHook(lpparam);
    }

    private void realHook(XC_LoadPackage.LoadPackageParam lpparam) throws Exception {
        if (lpparam.packageName.equals("com.ellabook") && !hookComplete) {
            XposedHelpers.findAndHookMethod("com.businesslogiclayer.reader.ReaderControl", lpparam.classLoader, "onClick",
                    "android.view.View", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            XposedBridge.log( "cylee hook ReaderControl 2");
                            XposedHelpers.setBooleanField(param.thisObject, "tryMode", false);
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                        }
                    });
            hookComplete = true;
        }
    }
}
