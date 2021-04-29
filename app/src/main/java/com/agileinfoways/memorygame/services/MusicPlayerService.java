package com.agileinfoways.memorygame.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Binder;
import android.os.IBinder;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import androidx.annotation.Nullable;

import com.agileinfoways.memorygame.R;

public class MusicPlayerService extends Service implements MediaPlayer.OnCompletionListener,
        MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnSeekCompleteListener,
        MediaPlayer.OnInfoListener, MediaPlayer.OnBufferingUpdateListener, AudioManager.OnAudioFocusChangeListener {

    public static final String ACTION_START_MUSIC = "START_MUSIC";
    public static final String ACTION_STOP_MUSIC = "STOP_MUSIC";
    public static final String ACTION_PLAY_SOUND = "PLAY_SOUND";
    public static final String ACTION_STOP_SOUND = "STOP_SOUND";

    // Binder given to clients
    private final IBinder iBinder = new LocalBinder();
    //MediaSession
    private MediaPlayer mediaPlayer;
    private MediaPlayer mSoundPlayer;
    //Used to pause/resume MediaPlayer
    private int resumePosition;
    private AudioManager audioManager;
    private boolean ongoingCall = false;
    private PhoneStateListener phoneStateListener;
    private TelephonyManager telephonyManager;
    private MusicIntentReceiver becomingNoisyReceiver;

    public MusicPlayerService() {
        super();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        // Perform one-time setup procedures
        requestAudioFocus();
        // Manage incoming phone calls during playback.
        // Pause MediaPlayer on incoming call,
        // Resume on hangup.
        callStateListener();
        //ACTION_AUDIO_BECOMING_NOISY -- change in audio outputs -- BroadcastReceiver
        registerBecomingNoisyReceiver();

        if (mSoundPlayer == null) {
            createSoundPlayer();
        }
    }

    public void sendCommand(@Nullable Intent intent) {
        if (intent != null) {
            String action = intent.getAction();
            if (ACTION_START_MUSIC.equals(action)) {
                if (mediaPlayer == null)
                    initMediaPlayer();

                handleActionPlayMusic();
            } else if (ACTION_STOP_MUSIC.equals(action)) {
                handleActionStopMusic();
            } else if (ACTION_PLAY_SOUND.equals(action)) {
                if (mSoundPlayer == null)
                    createSoundPlayer();

                handleActionPlaySound();
            }
        }
    }

    /**
     * To initialize media mService
     */
    private void initMediaPlayer() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.oniku);
        //Set up MediaPlayer event listeners
        mediaPlayer.setOnCompletionListener(this);
        mediaPlayer.setOnErrorListener(this);
        mediaPlayer.setOnPreparedListener(this);
        mediaPlayer.setOnBufferingUpdateListener(this);
        mediaPlayer.setOnSeekCompleteListener(this);
        mediaPlayer.setOnInfoListener(this);

        mediaPlayer.setLooping(true);
        mediaPlayer.setVolume(100, 100);

    }

    //to pause the media
    private void pauseMedia() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            resumePosition = mediaPlayer.getCurrentPosition();
        }
    }

    //to resume the media
    private void resumeMedia() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.seekTo(resumePosition);
            mediaPlayer.start();
        }
    }

    //to pause the sound
    private void pauseSound() {
        if (mSoundPlayer.isPlaying()) {
            mSoundPlayer.pause();
            resumePosition = mSoundPlayer.getCurrentPosition();
        }
    }

    //to resume the sound
    private void resumeSound() {
        if (!mSoundPlayer.isPlaying()) {
            mSoundPlayer.seekTo(resumePosition);
            mSoundPlayer.start();
        }
    }

    /**
     * To create media player
     */
    private void createMediaPlayer() {
        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.oniku);
        if (mediaPlayer != null) {
            mediaPlayer.setLooping(true);
            mediaPlayer.setVolume(100, 100);
        }
    }

    /**
     * To create sound player
     */
    private void createSoundPlayer() {
        mSoundPlayer = MediaPlayer.create(getApplicationContext(), R.raw.click);
        if (mSoundPlayer != null) {
            mSoundPlayer.setLooping(false);
            mSoundPlayer.setVolume(100, 100);
        }
    }

    /**
     * To handle the play action for music
     */
    private void handleActionPlayMusic() {
        if (mediaPlayer == null) {
            createMediaPlayer();
        } else {
            mediaPlayer.start();
        }
    }

    /**
     * To handle the play action for sound
     */
    private void handleActionPlaySound() {
        if (mSoundPlayer == null) {
            createSoundPlayer();
        } else {
            mSoundPlayer.start();
        }
    }

    /**
     * To handle the stop action for music
     */
    private void handleActionStopMusic() {
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                mediaPlayer.reset();
                mediaPlayer.release();
            } finally {
                mediaPlayer = null;
            }
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        if (intent != null) {
            //Timber.e("onBind: " + intent.toString());
            if (intent.getAction() != null && intent.getAction().equals(ACTION_START_MUSIC)) {
                //Timber.d("onBind action music: " + intent.getAction());
                handleActionPlayMusic();
            } else if (intent.getAction() != null && intent.getAction().equals(ACTION_PLAY_SOUND)) {
                //Timber.d("onBind action sound: " + intent.getAction());
                handleActionPlaySound();
            }
        }

        return iBinder;
    }

    @Override
    public void onDestroy() {

        if (mediaPlayer != null) {
            handleActionStopMusic();
            removeAudioFocus();
            //unregister BroadcastReceivers
        }

        if (mSoundPlayer != null) {
            removeAudioFocus();
        }

        //unregisterReceiver(becomingNoisyReceiver);
        super.onDestroy();

    }

    @Override
    public void onBufferingUpdate(MediaPlayer mp, int percent) {
        //Invoked indicating buffering status of
        //a media resource being streamed over the network.
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        //Invoked when playback of a media source has completed.
        //stop the service
        stopSelf();
    }

    @Override
    public boolean onError(MediaPlayer mp, int what, int extra) {
        //Invoked when there has been an error during an asynchronous operation
        switch (what) {
            case MediaPlayer.MEDIA_ERROR_NOT_VALID_FOR_PROGRESSIVE_PLAYBACK:
                //Timber.e("MEDIA ERROR NOT VALID FOR PROGRESSIVE PLAYBACK " + extra);
                break;
            case MediaPlayer.MEDIA_ERROR_SERVER_DIED:
                //Timber.e("MEDIA ERROR SERVER DIED " + extra);
                break;
            case MediaPlayer.MEDIA_ERROR_UNKNOWN:
                //Timber.e("MEDIA ERROR UNKNOWN " + extra);
                break;
        }
        return false;
    }

    @Override
    public boolean onInfo(MediaPlayer mp, int what, int extra) {
        //Invoked to communicate some info.
        return false;
    }

    @Override
    public void onPrepared(MediaPlayer mp) {
        //Invoked when the media source is ready for playback.
        if (mp.equals(mediaPlayer)) {
            handleActionPlayMusic();
        } else if (mp.equals(mSoundPlayer)) {
            handleActionPlaySound();
        }

    }

    @Override
    public void onSeekComplete(MediaPlayer mp) {
        //Invoked indicating the completion of a seek operation.

    }

    @Override
    public void onAudioFocusChange(int focusChange) {
        //Invoked when the audio focus of the system is updated.
        switch (focusChange) {
            case AudioManager.AUDIOFOCUS_GAIN:
                // resume playback
                // Timber.e("Audio focus gain");
                if (mediaPlayer == null) {
                    return;
                } else {
                    mediaPlayer.start();
                }

                //handleActionPlayMusic();
                break;
            case AudioManager.AUDIOFOCUS_LOSS:
                //Timber.e("Audio focus loss");
                // Lost focus for an unbounded amount of time: stop playback and release media mService
                handleActionStopMusic();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT:
                //Timber.e("Audio focus loss transient");
                // Lost focus for a short time, but we have to stop
                // playback. We don't release the media mService because playback
                // is likely to resume
                if (mediaPlayer == null) return;
                pauseMedia();
                break;
            case AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK:
                // Timber.e("Audio focus loss transient ducking");
                // Lost focus for a short time, but it's ok to keep playing
                // at an attenuated level
                if (mediaPlayer == null) return;
                if (mediaPlayer.isPlaying()) mediaPlayer.setVolume(0.1f, 0.1f);
                break;
        }
    }

    /**
     * To check and request audio focus
     *
     * @return it resturns true or false
     */
    private boolean requestAudioFocus() {
        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int result = audioManager.requestAudioFocus(this, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN);
        if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
            //Focus gained
            return true;
        }
        //Could not gain focus
        return false;
    }

    /**
     * it will remove the audio focus
     *
     * @return it returns true or false
     */
    private boolean removeAudioFocus() {
        return AudioManager.AUDIOFOCUS_REQUEST_GRANTED ==
                audioManager.abandonAudioFocus(this);
    }

    //Handle incoming phone calls
    private void callStateListener() {
        // Get the telephony manager
        telephonyManager = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);
        //Starting listening for PhoneState changes
        phoneStateListener = new PhoneStateListener() {
            @Override
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    //if at least one call exists or the phone is ringing
                    //pause the MediaPlayer
                    case TelephonyManager.CALL_STATE_OFFHOOK:
                    case TelephonyManager.CALL_STATE_RINGING:
                        if (mediaPlayer != null) {
                            pauseMedia();
                            ongoingCall = true;
                        }
                        break;
                    case TelephonyManager.CALL_STATE_IDLE:
                        // Phone idle. Start playing.
                        if (mediaPlayer != null) {
                            if (ongoingCall) {
                                ongoingCall = false;
                                resumeMedia();
                            }
                        }
                        break;
                }
            }
        };
        // Register the listener with the telephony manager
        // Listen for changes to the device call state.
        telephonyManager.listen(phoneStateListener,
                PhoneStateListener.LISTEN_CALL_STATE);


    }

    /**
     * To register Becoming noisy receiver
     */
    private void registerBecomingNoisyReceiver() {
        //register after getting audio focus
        IntentFilter intentFilter = new IntentFilter(AudioManager.ACTION_AUDIO_BECOMING_NOISY);
        intentFilter.addAction(Intent.ACTION_HEADSET_PLUG);
        registerReceiver(becomingNoisyReceiver, intentFilter);
    }


    //use to bind the service. it contains the method to get service instance
    public class LocalBinder extends Binder {
        public MusicPlayerService getService() {
            return MusicPlayerService.this;
        }
    }

}
