package com.cylee.testxpose;

import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import java.util.HashMap;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class PackageListLogic implements IXposedHookLoadPackage {
    public Context mContext;

    public PackageListLogic(Context context) {
        mContext = context;
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.baidu.homework")) { //作业帮

            PackageManager packageManager = mContext.getPackageManager();
//            XposedHelpers.findAndHookMethod("com.baidu.mobstat.ed", lpparam.classLoader, "a", int.class, String.class, new XC_MethodHook(){
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    XposedBridge.log("METHOD_DEBUG com.baidu.mobstat.ed "+param.args[0] +" "+param.args[1]);
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                }
//            });
//
//            XposedHelpers.findAndHookMethod("com.baidu.mobstat.ar", lpparam.classLoader, "h", new XC_MethodHook(){
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    XposedBridge.log("METHOD_DEBUG com.baidu.mobstat.ar.h ");
//                    param.setResult(0);
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//
//                }
//            });
            XposedHelpers.findAndHookMethod(packageManager.getClass(), "getInstalledPackages", int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("METHOD_DEBUG PackageListLogic ");
                    try {
                        throw new RuntimeException("crash");
                    } catch (Throwable e) {
                        e.printStackTrace();
                    }
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                }
            });
        }
    }
}
