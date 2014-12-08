package com.brandonjf.smsreader;

import android.content.Context;
import android.media.AudioManager;
import android.speech.tts.TextToSpeech;

import java.util.HashMap;
import java.util.Locale;

/**
 * Created by brandon on 12/7/14.
 */
public class Speaker implements TextToSpeechBeta.OnInitListener {

    private TextToSpeech tts;
    private boolean ready = false;
    private boolean allowed = false;
    public Speaker(Context context){
        tts = new TextToSpeech(context,this);
    }
    public boolean isAllowed(){
        return allowed;
    }
    public void allow(boolean allowed){
        this.allowed = allowed;
    }

    public void onInit(int status){
        if (status == TextToSpeech.SUCCESS){
            tts.setLanguage(Locale.US);
            ready = true;
        } else {
            ready = false;
        }
    }

    public void speak(){
        if (ready && allowed){
            HashMap<String, String> hash = new HashMap<String, String>();
            hash.put(TextToSpeech.Engine.KEY_PARAM_STREAM, String.valueOf(AudioManager.STREAM_NOTIFICATION));
            tts.speak(text, TextToSpeech.QUEUE_ADD, hash);
        }
    }


}
