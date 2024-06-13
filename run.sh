# 需要关闭LSPosed中的 Xposed API 调用保护
./gradlew installDebug
adb logcat -c && adb logcat | grep 'LSPosed-Bridge'