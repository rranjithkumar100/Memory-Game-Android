package com.agileinfoways.memorygame.game.fragments;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.agileinfoways.memorygame.R;
import com.agileinfoways.memorygame.activities.MainActivity;
import com.agileinfoways.memorygame.activities.PrivacyPolicyActivity;
import com.agileinfoways.memorygame.activities.TermsAndConditionsActivity;
import com.agileinfoways.memorygame.base.AppBaseFragment;
import com.agileinfoways.memorygame.game.common.Music;
import com.agileinfoways.memorygame.game.common.Shared;
import com.agileinfoways.memorygame.game.events.ui.StartEvent;
import com.agileinfoways.memorygame.game.utils.Utils;
import com.agileinfoways.memorygame.utils.Consts;

public class MenuFragment extends AppBaseFragment {

    private ImageView mStartGameButton;
    private ImageView mIvMusic;
    private ImageView mIvsettings;
    private Dialog mSettingDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.menu_fragment, container, false);
        mStartGameButton = (ImageView) view.findViewById(R.id.start_game_button);
        mIvMusic = (ImageView) view.findViewById(R.id.iv_music);
        mIvsettings = view.findViewById(R.id.iv_settings);

        boolean isMusic = ((MainActivity) getActivity()).mPrefUtils.getBoolean(Consts.SharedPrefs.IS_MUSIC, true);

        //Set Music Icon
        if (isMusic) {
            mIvMusic.setImageDrawable(getResources().getDrawable(R.drawable.music_on_icon));
        } else {
            mIvMusic.setImageDrawable(getResources().getDrawable(R.drawable.music_off_icon));
        }

        mIvMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (((MainActivity) getActivity()).mPrefUtils.checkKeyAvailable(Consts.SharedPrefs.IS_MUSIC)) {
                    if (((MainActivity) getActivity()).mPrefUtils.getBoolean(Consts.SharedPrefs.IS_MUSIC, true)) {
                        ((MainActivity) getActivity()).doUnbindService();
                        ((MainActivity) getActivity()).mPrefUtils.setBoolean(Consts.SharedPrefs.IS_MUSIC, false);
                        mIvMusic.setImageDrawable(getResources().getDrawable(R.drawable.music_off_icon));
                    } else {
                        ((MainActivity) getActivity()).doBindService();
                        ((MainActivity) getActivity()).mPrefUtils.setBoolean(Consts.SharedPrefs.IS_MUSIC, true);
                        mIvMusic.setImageDrawable(getResources().getDrawable(R.drawable.music_on_icon));
                    }
                } else {
                    ((MainActivity) getActivity()).doUnbindService();
                    ((MainActivity) getActivity()).mPrefUtils.setBoolean(Consts.SharedPrefs.IS_MUSIC, false);
                    mIvMusic.setImageDrawable(getResources().getDrawable(R.drawable.music_off_icon));
                }
            }
        });


        mIvsettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showSettingDialog();
            }
        });

        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // animate title from place and navigation buttons from place
                animateAllAssetsOff(new AnimatorListenerAdapter() {
                    @Override
                    public void onAnimationEnd(Animator animation) {
                        Shared.eventBus.notify(new StartEvent());
                    }
                });
            }
        });

        // play background music
        Music.playBackgroundMusic();
        return view;
    }

    /**
     * Show setting Dialog
     */
    private void showSettingDialog() {

        mSettingDialog = new Dialog(getActivity());

        if (mSettingDialog.getWindow() != null) {
            mSettingDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            Window window = mSettingDialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            mSettingDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            wlp.gravity = Gravity.CENTER;
            window.setAttributes(wlp);
            mSettingDialog.setContentView(R.layout.dialog_settings);
            mSettingDialog.setCancelable(true);
            mSettingDialog.getWindow().setLayout(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            AppCompatImageView ivRate = mSettingDialog.findViewById(R.id.iv_rate);
            AppCompatImageView ivShare = mSettingDialog.findViewById(R.id.iv_share);
           // AppCompatImageView ivMusic = mSettingDialog.findViewById(R.id.iv_music);
            AppCompatImageView ivSound = mSettingDialog.findViewById(R.id.iv_sound);
            AppCompatTextView tvPrivacyPolicy = mSettingDialog.findViewById(R.id.tv_privacy_policy);
            AppCompatTextView tvTermsAndConditions = mSettingDialog.findViewById(R.id.tv_terms_and_conditions);

/*            boolean isMusic = ((MainActivity) getActivity()).mPrefUtils.getBoolean(Consts.SharedPrefs.IS_MUSIC, true);

            //Set Music Icon
            if (isMusic) {
                ivMusic.setImageDrawable(getResources().getDrawable(R.drawable.music_on_icon));
            } else {
                ivMusic.setImageDrawable(getResources().getDrawable(R.drawable.music_off_icon));
            }*/

            //Set sound icon
            if (Music.OFF) {
                ivSound.setImageResource(R.drawable.volume_off);
            } else {
                ivSound.setImageResource(R.drawable.volume_on);
            }

            ivRate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSettingDialog.isShowing())
                        mSettingDialog.dismiss();

                    final String appPackageName = Shared.context.getPackageName();
                    try {
                        Shared.activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName)));
                    } catch (android.content.ActivityNotFoundException anfe) {
                        Shared.activity.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
                    }
                }
            });

            ivShare.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSettingDialog.isShowing())
                        mSettingDialog.dismiss();

                    Intent sendIntent = new Intent();
                    sendIntent.setAction(Intent.ACTION_SEND);
                    sendIntent.putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app));
                    sendIntent.setType("text/plain");
                    startActivity(sendIntent);
                }
            });

 /*           ivMusic.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (((MainActivity) getActivity()).mPrefUtils.checkKeyAvailable(Consts.SharedPrefs.IS_MUSIC)) {
                        if (((MainActivity) getActivity()).mPrefUtils.getBoolean(Consts.SharedPrefs.IS_MUSIC, true)) {
                            ((MainActivity) getActivity()).doUnbindService();
                            ((MainActivity) getActivity()).mPrefUtils.setBoolean(Consts.SharedPrefs.IS_MUSIC, false);
                            ivMusic.setImageDrawable(getResources().getDrawable(R.drawable.music_off_icon));
                        } else {
                            ((MainActivity) getActivity()).doBindService();
                            ((MainActivity) getActivity()).mPrefUtils.setBoolean(Consts.SharedPrefs.IS_MUSIC, true);
                            ivMusic.setImageDrawable(getResources().getDrawable(R.drawable.music_on_icon));
                        }
                    } else {
                        ((MainActivity) getActivity()).doUnbindService();
                        ((MainActivity) getActivity()).mPrefUtils.setBoolean(Consts.SharedPrefs.IS_MUSIC, false);
                        ivMusic.setImageDrawable(getResources().getDrawable(R.drawable.music_off_icon));
                    }
                }
            });*/

            ivSound.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Music.OFF = !Music.OFF;

                    //Set sound icon
                    if (Music.OFF) {
                        ivSound.setImageResource(R.drawable.volume_off);
                    } else {
                        ivSound.setImageResource(R.drawable.volume_on);
                    }
                }
            });

            tvPrivacyPolicy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSettingDialog.isShowing())
                        mSettingDialog.dismiss();

                    if (mNetworkUtils.isConnected()) {
                        startActivity(new Intent(getActivity(), PrivacyPolicyActivity.class));
                    } else {
                        showAlert(getString(R.string.internet_not_available));
                    }
                }
            });

            tvTermsAndConditions.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mSettingDialog.isShowing())
                        mSettingDialog.dismiss();

                    if (mNetworkUtils.isConnected()) {
                        startActivity(new Intent(getActivity(), TermsAndConditionsActivity.class));
                    } else {
                        showAlert(getString(R.string.internet_not_available));
                    }
                }
            });
            mSettingDialog.show();
        }
    }

    protected void animateAllAssetsOff(AnimatorListenerAdapter adapter) {

        // settings button
        ObjectAnimator settingsAnimator = ObjectAnimator.ofFloat(mIvMusic, "translationY", Utils.px(120));
        settingsAnimator.setInterpolator(new AccelerateInterpolator(2));
        settingsAnimator.setDuration(300);

        // setting  button
        ObjectAnimator volumeAnimator = ObjectAnimator.ofFloat(mIvsettings, "translationY", Utils.px(120));
        volumeAnimator.setInterpolator(new AccelerateInterpolator(2));
        volumeAnimator.setDuration(300);

        // start button
        ObjectAnimator startButtonAnimator = ObjectAnimator.ofFloat(mStartGameButton, "translationY", Utils.px(130));
        startButtonAnimator.setInterpolator(new AccelerateInterpolator(2));
        startButtonAnimator.setDuration(300);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(settingsAnimator, volumeAnimator, startButtonAnimator);
        animatorSet.addListener(adapter);
        animatorSet.start();
    }

}
