package com.cylee.testxpose;

import android.content.Context;
import android.util.Log;

import com.stardust.autojs.script.JavaScriptFileSource;

import org.mozilla.javascript.ast.AstRoot;

import java.io.BufferedOutputStream;
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

public class HookLogicReadAssist implements IXposedHookLoadPackage {
    public Context mContext;
    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("HookLogicReadAssist package + "+lpparam.packageName+" launched");
        if(lpparam.packageName.contains("com.iflytek.readassistant")) {
            XposedHelpers.findAndHookMethod("com.iflytek.readassistant.route.common.entities.j0", lpparam.classLoader, "t", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    super.beforeHookedMethod(param);
                    XposedBridge.log("HookLogicReadAssist getVip return 2");
                    param.setResult("2");
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {
                    super.afterHookedMethod(param);
                }
            });
        }
    }
}
