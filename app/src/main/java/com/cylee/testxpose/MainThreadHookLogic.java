package com.cylee.testxpose;

import android.os.Looper;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by cylee on 2018/8/12.
 */

public class MainThreadHookLogic implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.baidu.homework")) { //作业帮
            final HashMap<String, Class> hookedClass = new HashMap<>();
            XposedHelpers.findAndHookMethod(ClassLoader.class, "loadClass", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);

                    // 参数的检查
                    if (param.hasThrowable()) {
                        return;
                    }

                    // 获取指定名称的类加载之后的Class<?>
                    final Class<?> clazz = (Class<?>) param.getResult();
                    if (clazz.getName().contains("android.view.View") || clazz.getName().contains("android.view.ViewGroup") ) {
                        if (!hookedClass.containsKey(clazz.getName())) {
                            hookedClass.put(clazz.getName(), clazz);
                            XposedBridge.log("METHOD_DEBUG load " + clazz.getName());
                            // 获取加载的指定类的名称
                            final String strClazz = clazz.getName();
//                            XposedBridge.log("METHOD_DEBUG load: "+strClazz);
                            Method[] m = clazz.getDeclaredMethods();
                            // 打印获取到的所有的类方法的信息
                            for (int i = 0; i < m.length; i++) {
                                int modifiers = m[i].getModifiers();
                                if (!Modifier.isPrivate(modifiers) && !Modifier.isAbstract(modifiers)) {
                                    // 对指定名称类中声明的非抽象方法进行java Hook处理
                                    XposedBridge.hookMethod(m[i], new XC_MethodHook() {

                                        @Override
                                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                            // 打印被java Hook的类方法的名称和参数类型等信息
//                                            if (Looper.getMainLooper() != Looper.myLooper()) {
//                                                String methodName = param.method.getName();
//                                                param.setThrowable(new RuntimeException("in background"));
////                                                if (!methodName.startsWith("get")
////                                                        && !methodName.equals("hasIdentityMatrix")
////                                                        && !methodName.equals("setAccessibilityDelegate")) {
////                                                    XposedBridge.log("METHOD_DEBUG invoke: "+strClazz+"-"+param.method.toString());
//////                                                    param.setThrowable(new RuntimeException("in background"));
//////                                                    param.setResult(0);
////                                                }
//                                            }
                                        }

                                        // 被java Hook的类方法执行完毕之后，打印log日志
                                        @Override
                                        protected void afterHookedMethod(MethodHookParam param) throws Throwable {
//                                             打印被java Hook的类方法的名称和参数类型等信息
                                            if (Looper.getMainLooper() != Looper.myLooper()) {
                                                String methodName = param.method.getName();
                                                if (!methodName.contains("get")) {
                                                    XposedBridge.log("METHOD_DEBUG invoke:after " + strClazz + "-" + param.method.toString());
                                                }
//                                                if (!methodName.startsWith("getID")
//                                                        && !methodName.equals("hasIdentityMatrix")
//                                                        && !methodName.equals("setAccessibilityDelegate")) {

//                                                }
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }
                }
            });
        }
    }
}
