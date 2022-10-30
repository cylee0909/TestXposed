package com.cylee.testxpose;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by cylee on 2018/8/12.
 */

public class HookLogicReadAssist implements IXposedHookLoadPackage {
    public Context mContext;
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("HookLogicReadAssist package + "+lpparam.packageName+" launched");
        if(lpparam.packageName.contains("com.iflytek.readassistant")) {

            XposedHelpers.findAndHookMethod("com.iflytek.ys.core.util.log.Logging", lpparam.classLoader, "setDebugLogging", boolean.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    param.args[0] = true;
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });

//            String process = ProcessUtils.getCurrentProcessName(mContext);
//
//            if (!process.contains(":")) {
//                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
////                        ClassLoader current = getClass().getClassLoader();
////                        while (current != null && !current.getParent().toString().contains("BootClassLoader")) {
////                            current = current.getParent();
////                        }
////                        try {
////                            Field field = ClassLoader.class.getDeclaredField("parent");
////                            field.setAccessible(true);
////                            field.set(current, lpparam.classLoader);
////                        } catch (Throwable e) {
////                            e.printStackTrace();
////                        }
//                        XposedBridge.log("HookLogicReadAssist Test run ....");
//                        try {
//                            Class clazz = getClass().getClassLoader().loadClass(Test.class.getName());
//                            Method method = clazz.getDeclaredMethod("test", Context.class);
//                            method.invoke(null, mContext);
//                        } catch (Throwable e) {
//                            e.printStackTrace();
//                        }
//                    }
//                }, 5000);
//            }
//            XposedHelpers.findAndHookMethod("com.iflytek.readassistant.route.common.entities.j0", lpparam.classLoader, "t", new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("HookLogicReadAssist getVip return 2");
//                    param.setResult("2");
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                }
//            });
//
//            XposedHelpers.findAndHookMethod("com.iflytek.readassistant.biz.broadcast.d.j.c", lpparam.classLoader, "a", "com.iflytek.readassistant.biz.broadcast.d.j.d",  new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("HookLogicReadAssist SynthesizeParams"+param.args[0].toString());
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                }
//            });


        }
    }

}
