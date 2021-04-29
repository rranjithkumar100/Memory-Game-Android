package com.agileinfoways.memorygame.activities;


import android.app.Dialog;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.widget.AppCompatImageView;
import androidx.fragment.app.Fragment;

import com.agileinfoways.memorygame.R;
import com.agileinfoways.memorygame.base.AppBaseActivity;
import com.agileinfoways.memorygame.game.common.Shared;
import com.agileinfoways.memorygame.game.engine.Engine;
import com.agileinfoways.memorygame.game.engine.ScreenController;
import com.agileinfoways.memorygame.game.events.EventBus;
import com.agileinfoways.memorygame.game.events.ui.BackGameEvent;
import com.agileinfoways.memorygame.game.fragments.GameFragment;
import com.agileinfoways.memorygame.game.fragments.MenuFragment;
import com.agileinfoways.memorygame.game.ui.PopupManager;
import com.agileinfoways.memorygame.game.utils.Utils;


public class MainActivity extends AppBaseActivity {

    private ImageView mBackgroundImage;
    private Dialog mExitDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Shared.context = getApplicationContext();
        Shared.engine = Engine.getInstance();
        Shared.eventBus = EventBus.getInstance();

        setContentView(R.layout.activity_main);
        mBackgroundImage = (ImageView) findViewById(R.id.background_image);

        Shared.activity = this;
        Shared.engine.start();
        Shared.engine.setBackgroundImageView(mBackgroundImage);

        // set background
        //setBackgroundImage();

        // set menu
        ScreenController.getInstance().openScreen(ScreenController.Screen.MENU);


    }

    @Override
    protected void onDestroy() {
        Shared.engine.stop();
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        Fragment mCurrentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_container);

        if (PopupManager.isShown()) {
            PopupManager.closePopup();
            if (ScreenController.getLastScreen() == ScreenController.Screen.GAME) {
                Shared.eventBus.notify(new BackGameEvent());
            }
        } else if (mCurrentFragment instanceof GameFragment) {
            showExitDialog(false);
        } else if (mCurrentFragment instanceof MenuFragment) {
            showExitDialog(true);
        } else if (ScreenController.getInstance().onBack()) {
            super.onBackPressed();
        }
    }


    /**
     * Show Exit Dialog
     */
    private void showExitDialog(boolean isExit) {

        mExitDialog = new Dialog(MainActivity.this);

        if (mExitDialog.getWindow() != null) {
            mExitDialog.getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
            Window window = mExitDialog.getWindow();
            WindowManager.LayoutParams wlp = window.getAttributes();
            mExitDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            wlp.gravity = Gravity.CENTER;
            window.setAttributes(wlp);
            mExitDialog.setContentView(R.layout.dialog_exit);
            mExitDialog.setCancelable(false);
            mExitDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            AppCompatImageView llExit = mExitDialog.findViewById(R.id.iv_exit);
            AppCompatImageView llNo = mExitDialog.findViewById(R.id.iv_no);

            llNo.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mExitDialog.isShowing())
                        mExitDialog.dismiss();
                }
            });

            llExit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mExitDialog.isShowing())
                        mExitDialog.dismiss();

                    if (isExit) {
                        finish();
                    } else {
                        ScreenController.getInstance().onBack();
                    }
                }
            });

            mExitDialog.show();
        }
    }

    private void setBackgroundImage() {
        Bitmap bitmap = Utils.scaleDown(R.drawable.home_screen_bg, Utils.screenWidth(), Utils.screenHeight());
        bitmap = Utils.crop(bitmap, Utils.screenHeight(), Utils.screenWidth());
        bitmap = Utils.downscaleBitmap(bitmap, 2);
        mBackgroundImage.setImageBitmap(bitmap);
    }

}
