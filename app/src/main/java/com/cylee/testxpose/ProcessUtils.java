package com.cylee.testxpose;

import android.app.ActivityManager;
import android.content.Context;
import android.text.TextUtils;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;

/**
 * Created by nkzhangsn on 14/8/14.
 */
public class ProcessUtils {
    public static String getCurrentProcessName(Context context){
        String currentProcName = "";
        try{
            int pid = android.os.Process.myPid();
            ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
            for (ActivityManager.RunningAppProcessInfo processInfo : manager.getRunningAppProcesses())
            {
                if (processInfo.pid == pid)
                {
                    currentProcName = processInfo.processName;
                    break;
                }
            }
            if(TextUtils.isEmpty(currentProcName)){
                BufferedReader cmdlineReader = null;
                try {
                    cmdlineReader = new BufferedReader(new InputStreamReader(
                            new FileInputStream("/proc/" + android.os.Process.myPid() + "/cmdline"),"iso-8859-1"));
                    int c;
                    StringBuilder processName = new StringBuilder();
                    while ((c = cmdlineReader.read()) > 0) {
                        processName.append((char) c);
                    }
                    currentProcName = processName.toString();
                } finally {
                    if (cmdlineReader != null) {
                        cmdlineReader.close();
                    }
                }
            }
        }catch (Exception e){
            return "";
        }
        return currentProcName;
    }
}
