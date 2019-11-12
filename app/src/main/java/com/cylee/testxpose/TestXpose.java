package com.cylee.testxpose;


import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Debug;

import java.io.File;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.util.Arrays;

import dalvik.system.PathClassLoader;
import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * @author DX
 * 这种方案建议只在开发调试的时候使用，因为这将损耗一些性能(需要额外加载apk文件)，调试没问题后，直接修改xposed_init文件为正确的类即可
 * 可以实现免重启，由于存在缓存，需要杀死宿主程序以后才能生效
 * 这种免重启的方式针对某些特殊情况的hook无效
 * 例如我们需要implements IXposedHookZygoteInit,并将自己的一个服务注册为系统服务，这种就必须重启了
 * Created by DX on 2017/10/4.
 */

/**
 * Created by cylee on 2018/8/12.
 */

public class TestXpose implements IXposedHookLoadPackage {
    /**
     * 当前Xposed模块的包名,方便寻找apk文件
     */
    private final String modulePackage = "com.cylee.testxpose";

    /**
     * 实际hook逻辑处理类
     */
    private final String handleHookClass = HookLogic.class.getName();
    /**
     * 实际hook逻辑处理类的入口方法
     */
    private final String handleHookMethod = "handleLoadPackage";

    private long apkLastModify = 0L;

    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        XposedBridge.log("package + "+loadPackageParam);
        //将loadPackageParam的classloader替换为宿主程序Application的classloader,解决宿主程序存在多个.dex文件时,有时候ClassNotFound的问题
        XposedHelpers.findAndHookMethod(Application.class, "attach", Context.class, new XC_MethodHook() {
            @Override
            protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                Context context = (Context) param.args[0];
                XposedBridge.log("attach context process "+ProcessUtils.getCurrentProcessName(context));
                loadPackageParam.classLoader = context.getClassLoader();
                invokeHandleHookMethod(context, modulePackage, handleHookClass, handleHookMethod, loadPackageParam);
            }
        });
    }

    /**
     * 安装app以后，系统会在/data/app/下备份了一份.apk文件，通过动态加载这个apk文件，调用相应的方法
     * 这样就可以实现，只需要第一次重启，以后修改hook代码就不用重启了
     *
     * @param context           context参数
     * @param modulePackageName 当前模块的packageName
     * @param handleHookClass   指定由哪一个类处理相关的hook逻辑
     * @param loadPackageParam  传入XC_LoadPackage.LoadPackageParam参数
     * @throws Throwable 抛出各种异常,包括具体hook逻辑的异常,寻找apk文件异常,反射加载Class异常等
     */
    private void invokeHandleHookMethod(Context context, String modulePackageName, String handleHookClass, String handleHookMethod, XC_LoadPackage.LoadPackageParam loadPackageParam) throws Throwable {
        //原来的两种方式不是很好,改用这种新的方式
        File apkFile = findApkFile(context, modulePackageName);
        if (apkFile == null) {
            throw new RuntimeException("寻找模块apk失败");
        }
        long newLastModify = apkFile.lastModified();
        XposedBridge.log("apk file last modified : "+newLastModify);
        if (apkLastModify != newLastModify || newLastModify == 0) {
            apkLastModify = newLastModify;
            ClassLoader xposedClassLoader = XC_LoadPackage.LoadPackageParam.class.getClassLoader();
            XposedBridge.log("classloader = "+context.getClassLoader() +"  o = " + XC_LoadPackage.LoadPackageParam.class.getClassLoader());
            PathClassLoader pathClassLoader = new PathClassLoader(apkFile.getAbsolutePath(), xposedClassLoader);
            Class<?> cls = null;
            try {
                cls = Class.forName(handleHookClass, true, pathClassLoader);
            } catch (Exception e) {
                XposedBridge.log("apk file " + apkFile.getAbsolutePath()+" find "+handleHookClass+" not found. just ignore it");
                // ignore
            }
            if (cls == null) {
                File folder = apkFile.getParentFile();
                File[] apkFiles = folder.listFiles(new FileFilter() {
                    @Override
                    public boolean accept(File pathname) {
                        return pathname.getAbsolutePath().endsWith("apk");
                    }
                });
                for (File f : apkFiles) {
                    if (!f.getName().equals(apkFile.getName())) {
                        try {
                            PathClassLoader otherPathClassLoader = new PathClassLoader(f.getAbsolutePath(), xposedClassLoader);
                            cls = Class.forName(handleHookClass, true, otherPathClassLoader);
                            if (cls != null) {
                                break;
                            }
                        } catch (Exception e) {
                            XposedBridge.log("apk file " + f.getAbsolutePath()+" find "+handleHookClass+" not found. just ignore it");
                            // ignore
                        }
                    }
                }
            }
            Object instance = cls.newInstance();
//            XposedBridge.log("instance = "+ instance + instance.getClass().getName());
//            Method[]  methods = instance.getClass().getDeclaredMethods();
//            for (Method m : methods) {
//                XposedBridge.log("method : "+ m.getName()+" "+ Arrays.toString(m.getParameterTypes()));
//            }
            Method method = cls.getDeclaredMethod(handleHookMethod, XC_LoadPackage.LoadPackageParam.class);
            method.invoke(instance, loadPackageParam);
        }
    }

    /**
     * 根据包名构建目标Context,并调用getPackageCodePath()来定位apk
     *
     * @param context           context参数
     * @param modulePackageName 当前模块包名
     * @return return apk file
     */
    private File findApkFile(Context context, String modulePackageName) {
        if (context == null) {
            return null;
        }
        try {
            ApplicationInfo info = context.getPackageManager().getApplicationInfo(modulePackageName, 0);
            if (info != null) {
                return new File(info.sourceDir);
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
