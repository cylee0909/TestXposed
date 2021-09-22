package com.cylee.testxpose;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.view.View;

import java.util.WeakHashMap;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class HookOnClickListener implements IXposedHookLoadPackage {
    public Context mContext;
    public WeakHashMap<View.OnClickListener, String> onclickStack = new WeakHashMap<>();
    public HookOnClickListener(Context context) {
        mContext = context;
    }

    volatile boolean hookComplete = false;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.ss.android.article.news")) { //头条
            XposedHelpers.findAndHookMethod(Application.class, "onCreate",  new XC_MethodHook() {
                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    String processName = ProcessUtils.getCurrentProcessName(mContext);
                    XposedBridge.log("cylee attach context process onCreate" + processName);
                    if (processName.equals("com.ss.android.article.news")) {
                        realHook(lpparam);
                    }
                }
            });
        }
    }

    private void realHook(XC_LoadPackage.LoadPackageParam lpparam) throws Exception {
        if (!hookComplete) {
            XposedHelpers.findAndHookMethod("android.view.View", lpparam.classLoader, "setOnClickListener",
                    "android.view.View$OnClickListener", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            String stack = Log.getStackTraceString(new RuntimeException("cylee"));
                            onclickStack.put((View.OnClickListener)(param.args[0]), stack);
                            XposedBridge.log( "cylee hook setOnClickListener");
                        }

                        @Override
                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                            super.afterHookedMethod(param);
                        }
                    });

            XposedHelpers.findAndHookMethod("android.view.View", lpparam.classLoader, "performClick", new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            View view = (View)param.thisObject;
                            Object mListenerInfo = XposedHelpers.getObjectField(view, "mListenerInfo");
                            if (mListenerInfo != null) {
                                View.OnClickListener clickListener = (View.OnClickListener) XposedHelpers.getObjectField(mListenerInfo, "mOnClickListener");
                                String stack = onclickStack.get(clickListener);
                                XposedBridge.log( "cylee hook onClick stack : \n"+stack);
                            }
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
