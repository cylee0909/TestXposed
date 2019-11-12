package com.cylee.testxpose;

import java.util.concurrent.Executors;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by cylee on 2018/8/12.
 */

public class HookLogic implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("package + "+lpparam.packageName+" launched");
        if (lpparam.packageName.equals("com.baidu.homework")) { //作业帮
            XposedBridge.log("com.baidu.homework run  hhhh2222333444");
//            XposedHelpers.findAndHookConstructor(Thread.class, String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
//                    XposedBridge.log("before thread construct "+param.args[0]);
////                    if (param.args[0].toString().contains("Step_xxx")) {
////                        throw new RuntimeException("Step_xxx finded");
////                    }
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                }
//            });

//            XposedHelpers.findAndHookMethod(Executors.class, "newCachedThreadPool", new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
////                    throw new RuntimeException("newCachedThreadPool finded");
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    super.afterHookedMethod(param);
//                }
//            });
//            XposedHelpers.findAndHookMethod(ClassLoader.class, "loadClass", String.class, new XC_MethodHook() {
//                @Override
//                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
////                    super.beforeHookedMethod(param);
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    if (param.args != null && param.args.length >= 1 && param.args[0] instanceof String) {
//                        XposedBridge.log("DexclassLoader loadclass call "+param.args[0]);
//                        if ("com.qq.e.comm.plugin.b.b.a$2".equals(param.args[0])) {
//                            Class clazz = (Class) param.getResult();
//                            XposedHelpers.findAndHookMethod(clazz, "run", new XC_MethodHook() {
//                                @Override
//                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
//                                    XposedBridge.log("com.qq.e.comm.plugin.b.b.a$2#run befor");
//                                }
//
//                                @Override
//                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                    XposedBridge.log("com.qq.e.comm.plugin.b.b.a$2#run after");
//                                }
//                            });
//                        }
//                    }
//                }
//            });

        }
    }
}
