package com.cylee.testxpose;

import android.content.Context;
import android.util.Log;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by cylee on 2018/8/12.
 */

public class VMOSHook implements IXposedHookLoadPackage {
    private Context mContext;

    public VMOSHook(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.vmos.app")) {
            XposedHelpers.findAndHookMethod("com.vmos.app.DataModel.AdModel", lpparam.classLoader, "loadAd", String.class, new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("cylee meet-> "+"com.vmos.app.DataModel.AdModel.loadAd");
                    param.setResult(null);
                }
            });

            XposedHelpers.findAndHookMethod("com.vmos.app.view.MyFullDialog", lpparam.classLoader, "showAd", new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    XposedBridge.log("cylee meet-> "+"com.vmos.app.view.MyFullDialog.showAd");
                    param.setResult(null);
                }
            });
        }
    }
}
