package com.cylee.testxpose;

import android.util.Log;

import java.util.concurrent.atomic.AtomicBoolean;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by cylee on 2018/8/12.
 */

public class HookLogic3 implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("package + "+lpparam.packageName+" launched");
        if (lpparam.packageName.equals("com.baidu.homework")) { //作业帮
//            XposedBridge.log("com.baidu.homework run  hhhh2222333444");
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
            final AtomicBoolean log = new AtomicBoolean(true);

//            XposedHelpers.findAndHookMethod(Class.class, "forName", String.class, boolean.class, ClassLoader.class, new XC_MethodHook() {
//                long current = 0;
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                    Log.d("cylee", "loadclasstest "+param.args[0]);
//                    current = System.currentTimeMillis();
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    if (log.get() && param.getResult() != null) {
//                        Log.d("cylee", "findClassCylee forName "+param.args[0] +" time = " + (System.currentTimeMillis() - current));
//                    }
//                }
//            });
//
//
//            XposedHelpers.findAndHookMethod(ClassLoader.class, "loadClass", String.class, boolean.class, new XC_MethodHook() {
//                long current = 0;
//                @Override
//                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
////                    Log.d("cylee", "loadclasstest "+param.args[0]);
//                    current = System.currentTimeMillis();
//                }
//
//                @Override
//                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                    if (log.get() && param.getResult() != null) {
//                        Log.d("cylee", "findClassCylee "+param.args[0] +" time = " + (System.currentTimeMillis() - current));
//                    }
//                }
//            });

            XposedHelpers.findAndHookMethod(System.class, "load", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if (log.get()) {
                        Log.d("cylee", "loadHook, load "+param.args[0]);
                    }
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                }
            });

            XposedHelpers.findAndHookMethod(System.class, "loadLibrary", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if (log.get()) {
                        Log.d("cylee", "loadHook, loadLibrary "+param.args[0]);
                        if (param.args[0].toString().contains("tnet") || param.args[0].toString().contains("webviewchromium")) {
                            try {
                                throw new RuntimeException("crash");
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                }
            });

            XposedHelpers.findAndHookMethod("com.baidu.homework.base.LaunchLog", lpparam.classLoader, "d", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    if (param.args[0].toString().contains("Draw Time Total")) {
                        log.set(false);
                        Log.d("cylee", "loadHook ======================================end===========================" + param.args[0]);
                    }
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                }
            });

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
