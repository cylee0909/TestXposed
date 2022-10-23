package com.cylee.testxpose;

import android.content.Context;

import com.cylee.testxpose.util.InjectUtil;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * Created by cylee on 2018/8/12.
 */

public class HookLogic implements IXposedHookLoadPackage {
    public Context mContext;
    IXposedHookLoadPackage[] mPackages;

    @Override
    public void handleLoadPackage(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        XposedBridge.log("package + " + lpparam.packageName + " launched");

        if (mPackages == null) {
            mPackages = new IXposedHookLoadPackage[] {
//            new MainThreadHookLogic(),
//            new HookLogicBaimiao(),
//                new PackageListLogic(mContext),
//                    new VMOSHook(mContext),
//                new PackageListLogic(mContext),
//                    new HookABook(mContext),
//                    new HookLogic5()
//                    new HookOnClickListener(mContext),
                    new HookLogicReadAssist(),
            };
        }

        for (IXposedHookLoadPackage p :
                mPackages) {
            InjectUtil.injectContext(p, mContext);
            p.handleLoadPackage(lpparam);
        }
    }
}
