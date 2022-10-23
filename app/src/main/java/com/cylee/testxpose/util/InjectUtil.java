package com.cylee.testxpose.util;

import android.content.Context;

import java.lang.reflect.Field;

import de.robv.android.xposed.XposedBridge;

public class InjectUtil {
    public static void injectContext(Object object, Context context) {
        try {
            Field field = object.getClass().getDeclaredField("mContext");
            if (field != null) {
                field.setAccessible(true);
                field.set(object, context);
                XposedBridge.log("inject context field");
            } else {
                XposedBridge.log("mContext field not found");
            }
        } catch (Throwable e) {
        }
    }
}
