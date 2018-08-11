package com.cylee.testxpose;

import android.widget.TextView;

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
        if (lpparam.packageName.equals("com.iflytek.inputmethod")) { //讯飞输入法
            XposedHelpers.findAndHookMethod(TextView.class, "setText", CharSequence.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(XC_MethodHook.MethodHookParam param) throws Throwable {
//                    super.beforeHookedMethod(param);
                    if (param.args != null && param.args.length == 1 && param.args[0] instanceof CharSequence) {
                        XposedBridge.log("raw args = "+param.args[0]);
                        param.args[0] = "HAHA5";
                    } else {
                        super.beforeHookedMethod(param);
                    }
                }

                @Override
                protected void afterHookedMethod(MethodHookParam param) throws Throwable {

                }
            });

        }
    }
}
