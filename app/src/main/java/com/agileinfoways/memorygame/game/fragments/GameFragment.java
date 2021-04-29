package com.agileinfoways.memorygame.game.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agileinfoways.memorygame.Application;
import com.agileinfoways.memorygame.R;
import com.agileinfoways.memorygame.game.common.Shared;
import com.agileinfoways.memorygame.game.events.engine.FlipDownCardsEvent;
import com.agileinfoways.memorygame.game.events.engine.GameWonEvent;
import com.agileinfoways.memorygame.game.events.engine.HidePairCardsEvent;
import com.agileinfoways.memorygame.game.model.Game;
import com.agileinfoways.memorygame.game.ui.BoardView;
import com.agileinfoways.memorygame.game.ui.PopupManager;
import com.agileinfoways.memorygame.game.utils.Clock;
import com.agileinfoways.memorygame.utils.AdsWrapper;
import com.agileinfoways.memorygame.utils.InterstitialAdCallback;
import com.google.android.gms.ads.InterstitialAd;

public class GameFragment extends BaseFragment {

    private BoardView mBoardView;
    private TextView mTime;
    private ImageView mTimeImage;
    private LinearLayout llTimer;
    private InterstitialAd mInterstitialAd;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup view = (ViewGroup) inflater.inflate(R.layout.game_fragment, container, false);
        view.setClipChildren(false);
        ((ViewGroup) view.findViewById(R.id.game_board)).setClipChildren(false);
        mTime = (TextView) view.findViewById(R.id.time_bar_text);
        mTimeImage = (ImageView) view.findViewById(R.id.time_bar_image);
        llTimer = view.findViewById(R.id.ll_timer);
        mBoardView = BoardView.fromXml(getActivity().getApplicationContext(), view);
        FrameLayout frameLayout = (FrameLayout) view.findViewById(R.id.game_container);
        frameLayout.addView(mBoardView);
        frameLayout.setClipChildren(false);

        // build board
        buildBoard();
        Shared.eventBus.listen(FlipDownCardsEvent.TYPE, this);
        Shared.eventBus.listen(HidePairCardsEvent.TYPE, this);
        Shared.eventBus.listen(GameWonEvent.TYPE, this);

        AdsWrapper adsWrapper = Application.getInstance().getAdsWrapper();
        adsWrapper.loadInterstitialAd(new InterstitialAdCallback() {
            @Override
            public void whenLoaded(InterstitialAd interstitialAd) {
                mInterstitialAd = interstitialAd;
            }
        });
        return view;
    }

    @Override
    public void onDestroy() {
        Shared.eventBus.unlisten(FlipDownCardsEvent.TYPE, this);
        Shared.eventBus.unlisten(HidePairCardsEvent.TYPE, this);
        Shared.eventBus.unlisten(GameWonEvent.TYPE, this);
        super.onDestroy();
    }

    private void buildBoard() {
        Game game = Shared.engine.getActiveGame();
        int time = game.boardConfiguration.time;
        setTime(time);
        mBoardView.setBoard(game);

        startClock(time);
    }

    private void setTime(int time) {
        int min = time / 60;
        int sec = time - min * 60;
        mTime.setText(" " + String.format("%02d", min) + ":" + String.format("%02d", sec));
    }

    private void startClock(int sec) {
        Clock clock = Clock.getInstance();
        clock.startTimer(sec * 1000, 1000, new Clock.OnTimerCount() {

            @Override
            public void onTick(long millisUntilFinished) {
                setTime((int) (millisUntilFinished / 1000));
            }

            @Override
            public void onFinish() {
                setTime(0);
            }
        });
    }

    @Override
    public void onEvent(GameWonEvent event) {
        mTime.setVisibility(View.GONE);
        mTimeImage.setVisibility(View.GONE);
        llTimer.setVisibility(View.GONE);
        PopupManager.showPopupWon(event.gameState,mInterstitialAd);
    }

    @Override
    public void onEvent(FlipDownCardsEvent event) {
        mBoardView.flipDownAll();
    }

    @Override
    public void onEvent(HidePairCardsEvent event) {
        mBoardView.hideCards(event.id1, event.id2);
    }

}
