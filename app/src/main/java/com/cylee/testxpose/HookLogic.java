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
    IXposedHookLoadPackage[] mPackages = {
            new MainThreadHookLogic(),
            new HookLogicBaimiao(),
    };

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("package + " + lpparam.packageName + " launched");
        for (IXposedHookLoadPackage p :
                mPackages) {
            p.handleLoadPackage(lpparam);
        }
    }
}
