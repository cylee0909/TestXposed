package com.cylee.testxpose;

import android.content.Context;
import android.os.Bundle;

import com.iflytek.readassistant.biz.broadcast.model.synthesize.SynthesizePath;
import com.iflytek.ys.common.speech.SynthesizerFactory;
import com.iflytek.ys.common.speech.interfaces.ISynthesizerListener;

import java.util.ArrayList;

public class Test {
   private static boolean executed = false;
   public static void test(Context context) {
      if (executed) {
         return;
      }
      executed = true;
      String content = "接下来几天会发布一篇文章，来做更好的总结，不喜欢看文章的可以先看这期视频~ 一些已经总结的（可能正确）小结论： 1. 如果第一次买电脑，需要实用性，那就必须买Windows，因为什么都可以做。 如果明确要做的事情macOS都能完成，可以买macOS ";

      handlePreSynthesize(content);

      String role= "xiaopei";
      String engine = "x";
      String engineType = "cloud";
      int speed = 50;
      Bundle bundle = new Bundle();
      bundle.putString(TtsParams.KEY_PARAM_TTS_ENGINE_TYPE, engineType);
      bundle.putString(
              TtsParams.kEY_PARAM_TTS_RES_PATH,
              SynthesizePath.getResourcePath(context, role)
      );
      bundle.putString(TtsParams.KEY_PARAM_CLOUD_TTS_ENGINE_TYPE, engine);
      bundle.putString("role", role);
      bundle.putInt("speed", speed);
      bundle.putLong("tts_id", System.currentTimeMillis());
      bundle.putBoolean(
              TtsParams.KEY_PARAM_ENABLE_SPEED_RATE,
              true
      );
      bundle.putInt(
              TtsParams.KEY_PARAM_SPEED_RATE_THRESHOLD,
              90
      );
      bundle.putFloat(TtsParams.KEY_PARAM_SPEED_RATE, 1.0f);
      if (TtsParams.TTS_ENGINE_TYPE_PTTS == engineType) {
         bundle.putString(TtsParams.KEY_PARAM_AUTH_ID, "null");
      }
      bundle.putString(TtsParams.KEY_SERVER_URL, "http://dz-x2.xf-yun.com:1028/msp.do");
      ArrayList arrayList = new ArrayList();
      String replacePolyphony = content;
//            var replacePolyphony: String =
//                PolyphonyManager.getInstance().replacePolyphony(content, arrayList)
//            val textPositionHelper = TextPositionHelper()
//            textPositionHelper.speechText = replacePolyphony
//            this.mPolyphonyQueue.offer(
//                Pair<Any?, Any?>(
//                    Pair<Any?, Any?>(
//                        content,
//                        textPositionHelper
//                    ), arrayList
//                )
//            )
//            if (speedRateEnabled(synthesizeParams)) {
//                replacePolyphony =
//                    com.iflytek.readassistant.biz.broadcast.model.synthesize.SynthesizeController.SPEED_INCREASE_FLAG + replacePolyphony
//            }
      SynthesizerFactory.getSynthesize().speak(replacePolyphony, bundle, new ISynthesizerListener() {
         @Override
         public void onMscSessionEnd(long j, String str) {

         }

         @Override
         public void onPlayBegin(long j) {

         }

         @Override
         public void onPlayCompleted(long j, String str) {

         }

         @Override
         public void onPlayInterrupted(long j) {

         }

         @Override
         public void onPlayPaused(long j) {

         }

         @Override
         public void onPlayResumed(long j) {

         }

         @Override
         public void onProgress(String str, long j, int i) {

         }
      });
   }


   static void handlePreSynthesize(String str) {
//        Logging.d(
//            com.iflytek.readassistant.biz.broadcast.model.synthesize.SynthesizeController.TAG,
//            "handlePreSynthesize() preSynthesizeText = $str"
//        )
      long currentTimeMillis = System.currentTimeMillis();
//        BroadcastInterruptCollectHelper.getInstance().handleSynthesizeBegin(currentTimeMillis, true)
//        val arrayList: java.util.ArrayList<*> = java.util.ArrayList<Any?>()
//        var replacePolyphony: String =
//            PolyphonyManager.getInstance().replacePolyphony(str, arrayList)
//        val textPositionHelper = TextPositionHelper()
//        textPositionHelper.speechText = replacePolyphony
//        this.mPolyphonyQueue.offer(
//            Pair<Any?, Any?>(
//                Pair<Any?, Any?>(str, textPositionHelper),
//                arrayList
//            )
//        )
//        if (speedRateEnabled(this.mSynthesizeParams)) {
//            replacePolyphony =
//                com.iflytek.readassistant.biz.broadcast.model.synthesize.SynthesizeController.SPEED_INCREASE_FLAG + replacePolyphony
//        }
      String replacePolyphony = str;
      SynthesizerFactory.getSynthesize().preSynthesize(currentTimeMillis, replacePolyphony);
   }
}
