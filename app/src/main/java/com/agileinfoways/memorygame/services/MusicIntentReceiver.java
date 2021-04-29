package com.agileinfoways.memorygame.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MusicIntentReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals(android.media.AudioManager.ACTION_AUDIO_BECOMING_NOISY)) {
            Toast.makeText(context, "Headphones disconnected.", Toast.LENGTH_SHORT).show();
        }
    }
}