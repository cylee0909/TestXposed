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

public class PopupWindowLogic implements IXposedHookLoadPackage {
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
                    if (clazz.getName().contains("android.widget.PopupWindow")) {
                        if (!hookedClass.containsKey(clazz.getName())) {
                            hookedClass.put(clazz.getName(), clazz);
                            XposedBridge.log("METHOD_DEBUG load "+clazz.getName());
                            XposedHelpers.findAndHookMethod(clazz, "update", int.class, int.class, int.class, int.class, boolean.class, new XC_MethodHook() {
                                @Override
                                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                                    XposedBridge.log("METHOD_DEBUG befor update");
                                }

                                @Override
                                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                                }
                            });
                        }
//                        XposedBridge.log("METHOD_DEBUG load "+clazz.getName());
                    }
                }
            });
        }
    }
}
