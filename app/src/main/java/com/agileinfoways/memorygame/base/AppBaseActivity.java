package com.agileinfoways.memorygame.base;

import android.app.Activity;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.agileinfoways.memorygame.R;
import com.agileinfoways.memorygame.activities.MusicPlayerActivity;
import com.agileinfoways.memorygame.utils.NetworkUtils;

/**
 * Created on 8/8/2017.
 */

public class AppBaseActivity extends MusicPlayerActivity {

    protected InputMethodManager mInputMethodManager;
    public NetworkUtils mNetworkUtils;

    public SoundPool spSound;
    public int imove;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mInputMethodManager = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        mNetworkUtils = new NetworkUtils(AppBaseActivity.this);
        spSound = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        imove = spSound.load(this, R.raw.click, 1);
    }

    /**
     * to replace fragment in container
     * tag will be same as class name of fragment
     *
     * @param containerId        id of fragment container
     * @param isAddedToBackStack should be added to back stack?
     */
    public void replaceFragment(Fragment fragment, int containerId, boolean isAddedToBackStack) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerId, fragment);
        if (isAddedToBackStack) {
            fragmentTransaction.addToBackStack(fragment.getClass().getSimpleName());
        }
        fragmentTransaction.commitAllowingStateLoss();
    }

    public void hideKeyBoard(View view) {
        if (view != null) {
            mInputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    /**
     * to show common alert dialogs and messages
     * change it with yur own dialogs/views/any preferred method for showing alerts
     */
    public void showAlert(String msg) {
        if (msg == null) return;
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    public void soundMove() {
        spSound.play(imove, 1.0f, 1.0f, 1, 0, 1);
    }

}

