package com.cylee.testxpose;

import android.content.Context;
import android.util.Log;

import com.stardust.autojs.script.JavaScriptFileSource;

import org.mozilla.javascript.ast.AstRoot;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.Reader;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by cylee on 2018/8/12.
 */

public class HookLogic5 implements IXposedHookLoadPackage {
    public Context mContext;
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("package + "+lpparam.packageName+" launched");
        if(lpparam.packageName.contains("cc.midlgd.com")) {
            XposedHelpers.findAndHookConstructor("com.stardust.autojs.script.StringScriptSource", lpparam.classLoader, String.class, String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.d("cylee", "StringScriptSource a1 = "+param.args[0]
                    + " a2 = "+param.args[1]);
                    BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream("/sdcard/raw.js"));
                    bos.write(param.args[1].toString().getBytes());
                    bos.flush();
                    bos.close();
                    Log.d("cylee", "StringScriptSource " + param.thisObject.getClass().getName());
                }
            });

            Log.d("cylee", "start process "+ProcessUtils.getCurrentProcessName(mContext));
            XposedHelpers.findAndHookMethod("com.stardust.autojs.engine.RhinoJavaScriptEngine", lpparam.classLoader, "doExecution", "com.stardust.autojs.script.JavaScriptSource", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Log.d("cylee", "doExecution "+param.args[0]);
                    JavaScriptFileSource fileSource = (JavaScriptFileSource) param.args[0];
                    Log.d("cylee", "source = "+fileSource.getScript());
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });

            XposedHelpers.findAndHookMethod("org.mozilla.javascript.Kit", lpparam.classLoader, "readReader", Reader.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.d("cylee", "reader = "+param.getResult());
                }
            });

            XposedHelpers.findAndHookMethod("org.mozilla.javascript.Parser", lpparam.classLoader, "parse", String.class, String.class, int.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    Log.d("cylee", "Parser = "+param.args[0] +" "+param.args[1]+" "+param.args[2]);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    AstRoot root = (AstRoot) param.getResult();
                    Log.d("cylee", "Parser result = "+root.toSource(2) + root.debugPrint());
                }
            });

            XposedHelpers.findAndHookMethod("com.stardust.autojs.script.StringScriptSource", lpparam.classLoader, "f",  new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.d("cylee", "JavaScriptFileSource = FileReader "+param.getResult());
                }
            });

            XposedHelpers.findAndHookMethod("com.stardust.autojs.script.StringScriptSource", lpparam.classLoader, "e",  new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                    Log.d("cylee", "JavaScriptFileSource = String " +param.getResult());
                }
            });
        }
    }
}
