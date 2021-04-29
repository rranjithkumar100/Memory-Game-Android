package com.agileinfoways.memorygame.game.fragments;

import android.animation.AnimatorSet;
import android.animation.AnimatorSet.Builder;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.agileinfoways.memorygame.R;
import com.agileinfoways.memorygame.game.common.Memory;
import com.agileinfoways.memorygame.game.common.Shared;
import com.agileinfoways.memorygame.game.events.ui.DifficultySelectedEvent;
import com.agileinfoways.memorygame.game.themes.Theme;
import com.agileinfoways.memorygame.game.ui.DifficultyView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DifficultySelectFragment extends Fragment {

    @BindView(R.id.star_1_1)
    ImageView star11;
    @BindView(R.id.star_1_2)
    ImageView star12;
    @BindView(R.id.star_1_3)
    ImageView star13;
    @BindView(R.id.star_4_1)
    ImageView star41;
    @BindView(R.id.star_4_2)
    ImageView star42;
    @BindView(R.id.star_4_3)
    ImageView star43;
    @BindView(R.id.star_2_1)
    ImageView star21;
    @BindView(R.id.star_2_2)
    ImageView star22;
    @BindView(R.id.star_2_3)
    ImageView star23;
    @BindView(R.id.star_5_1)
    ImageView star51;
    @BindView(R.id.star_5_2)
    ImageView star52;
    @BindView(R.id.star_5_3)
    ImageView star53;
    @BindView(R.id.star_3_1)
    ImageView star31;
    @BindView(R.id.star_3_2)
    ImageView star32;
    @BindView(R.id.star_3_3)
    ImageView star33;
    @BindView(R.id.star_6_1)
    ImageView star61;
    @BindView(R.id.star_6_2)
    ImageView star62;
    @BindView(R.id.star_6_3)
    ImageView star63;
    @BindView(R.id.select_difficulty_1)
    DifficultyView selectDifficulty1;
    @BindView(R.id.select_difficulty_4)
    DifficultyView selectDifficulty4;
    @BindView(R.id.select_difficulty_2)
    DifficultyView selectDifficulty2;
    @BindView(R.id.select_difficulty_5)
    DifficultyView selectDifficulty5;
    @BindView(R.id.select_difficulty_3)
    DifficultyView selectDifficulty3;
    @BindView(R.id.select_difficulty_6)
    DifficultyView selectDifficulty6;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(Shared.context).inflate(R.layout.difficulty_select_fragment, container, false);
        ButterKnife.bind(this, view);
        Theme theme = Shared.engine.getSelectedTheme();

        switch (theme.id) {
            case 1:
                selectDifficulty1.setBackgroundResource(R.drawable.animals_difficulty_bg);
                selectDifficulty2.setBackgroundResource(R.drawable.animals_difficulty_bg);
                selectDifficulty3.setBackgroundResource(R.drawable.animals_difficulty_bg);
                selectDifficulty4.setBackgroundResource(R.drawable.animals_difficulty_bg);
                selectDifficulty5.setBackgroundResource(R.drawable.animals_difficulty_bg);
                selectDifficulty6.setBackgroundResource(R.drawable.animals_difficulty_bg);
                break;
            case 2:
                selectDifficulty1.setBackgroundResource(R.drawable.vegitable_difficulty_bg);
                selectDifficulty2.setBackgroundResource(R.drawable.vegitable_difficulty_bg);
                selectDifficulty3.setBackgroundResource(R.drawable.vegitable_difficulty_bg);
                selectDifficulty4.setBackgroundResource(R.drawable.vegitable_difficulty_bg);
                selectDifficulty5.setBackgroundResource(R.drawable.vegitable_difficulty_bg);
                selectDifficulty6.setBackgroundResource(R.drawable.vegitable_difficulty_bg);
                break;
            case 3:
                selectDifficulty1.setBackgroundResource(R.drawable.clothe_difficulty_bg);
                selectDifficulty2.setBackgroundResource(R.drawable.clothe_difficulty_bg);
                selectDifficulty3.setBackgroundResource(R.drawable.clothe_difficulty_bg);
                selectDifficulty4.setBackgroundResource(R.drawable.clothe_difficulty_bg);
                selectDifficulty5.setBackgroundResource(R.drawable.clothe_difficulty_bg);
                selectDifficulty6.setBackgroundResource(R.drawable.clothe_difficulty_bg);
                break;
            case 4:
                selectDifficulty1.setBackgroundResource(R.drawable.vehicle_difficulty_bg);
                selectDifficulty2.setBackgroundResource(R.drawable.vehicle_difficulty_bg);
                selectDifficulty3.setBackgroundResource(R.drawable.vehicle_difficulty_bg);
                selectDifficulty4.setBackgroundResource(R.drawable.vehicle_difficulty_bg);
                selectDifficulty5.setBackgroundResource(R.drawable.vehicle_difficulty_bg);
                selectDifficulty6.setBackgroundResource(R.drawable.vehicle_difficulty_bg);
                break;
            case 5:
                selectDifficulty1.setBackgroundResource(R.drawable.emoji_difficulty_bg);
                selectDifficulty2.setBackgroundResource(R.drawable.emoji_difficulty_bg);
                selectDifficulty3.setBackgroundResource(R.drawable.emoji_difficulty_bg);
                selectDifficulty4.setBackgroundResource(R.drawable.emoji_difficulty_bg);
                selectDifficulty5.setBackgroundResource(R.drawable.emoji_difficulty_bg);
                selectDifficulty6.setBackgroundResource(R.drawable.emoji_difficulty_bg);
                break;
            case 6:
                selectDifficulty1.setBackgroundResource(R.drawable.sport_difficulty_bg);
                selectDifficulty2.setBackgroundResource(R.drawable.sport_difficulty_bg);
                selectDifficulty3.setBackgroundResource(R.drawable.sport_difficulty_bg);
                selectDifficulty4.setBackgroundResource(R.drawable.sport_difficulty_bg);
                selectDifficulty5.setBackgroundResource(R.drawable.sport_difficulty_bg);
                selectDifficulty6.setBackgroundResource(R.drawable.sport_difficulty_bg);
                break;
            default:
                selectDifficulty1.setBackgroundResource(R.drawable.animals_difficulty_bg);
                selectDifficulty2.setBackgroundResource(R.drawable.animals_difficulty_bg);
                selectDifficulty3.setBackgroundResource(R.drawable.animals_difficulty_bg);
                selectDifficulty4.setBackgroundResource(R.drawable.animals_difficulty_bg);
                selectDifficulty5.setBackgroundResource(R.drawable.animals_difficulty_bg);
                selectDifficulty6.setBackgroundResource(R.drawable.animals_difficulty_bg);
                break;
        }

        DifficultyView difficulty1 = (DifficultyView) view.findViewById(R.id.select_difficulty_1);
        difficulty1.setDifficulty(1, Memory.getHighStars(theme.id, 1));
        setOnClick(difficulty1, 1);

        if (Memory.getHighStars(theme.id, 1) == 1) {
            star11.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star12.setImageDrawable(getResources().getDrawable(R.drawable.star));
            star13.setImageDrawable(getResources().getDrawable(R.drawable.star));

        } else if (Memory.getHighStars(theme.id, 1) == 2) {
            star11.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star12.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star13.setImageDrawable(getResources().getDrawable(R.drawable.star));
        } else if (Memory.getHighStars(theme.id, 1) == 3) {
            star11.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star12.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star13.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        }

        DifficultyView difficulty2 = (DifficultyView) view.findViewById(R.id.select_difficulty_2);
        difficulty2.setDifficulty(2, Memory.getHighStars(theme.id, 2));
        setOnClick(difficulty2, 2);

        if (Memory.getHighStars(theme.id, 2) == 1) {
            star11.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star12.setImageDrawable(getResources().getDrawable(R.drawable.star));
            star13.setImageDrawable(getResources().getDrawable(R.drawable.star));

        } else if (Memory.getHighStars(theme.id, 2) == 2) {
            star21.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star22.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star23.setImageDrawable(getResources().getDrawable(R.drawable.star));
        } else if (Memory.getHighStars(theme.id, 2) == 3) {
            star21.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star22.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star23.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        }

        DifficultyView difficulty3 = (DifficultyView) view.findViewById(R.id.select_difficulty_3);
        difficulty3.setDifficulty(3, Memory.getHighStars(theme.id, 3));
        setOnClick(difficulty3, 3);

        if (Memory.getHighStars(theme.id, 3) == 1) {
            star31.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star32.setImageDrawable(getResources().getDrawable(R.drawable.star));
            star33.setImageDrawable(getResources().getDrawable(R.drawable.star));

        } else if (Memory.getHighStars(theme.id, 3) == 2) {
            star31.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star32.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star33.setImageDrawable(getResources().getDrawable(R.drawable.star));
        } else if (Memory.getHighStars(theme.id, 3) == 3) {
            star31.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star32.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star33.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        }

        DifficultyView difficulty4 = (DifficultyView) view.findViewById(R.id.select_difficulty_4);
        difficulty4.setDifficulty(4, Memory.getHighStars(theme.id, 4));
        setOnClick(difficulty4, 4);

        if (Memory.getHighStars(theme.id, 4) == 1) {
            star41.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star42.setImageDrawable(getResources().getDrawable(R.drawable.star));
            star43.setImageDrawable(getResources().getDrawable(R.drawable.star));

        } else if (Memory.getHighStars(theme.id, 4) == 2) {
            star41.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star42.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star43.setImageDrawable(getResources().getDrawable(R.drawable.star));
        } else if (Memory.getHighStars(theme.id, 4) == 3) {
            star41.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star42.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star43.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        }

        DifficultyView difficulty5 = (DifficultyView) view.findViewById(R.id.select_difficulty_5);
        difficulty5.setDifficulty(5, Memory.getHighStars(theme.id, 5));
        setOnClick(difficulty5, 5);

        if (Memory.getHighStars(theme.id, 5) == 1) {
            star51.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star52.setImageDrawable(getResources().getDrawable(R.drawable.star));
            star53.setImageDrawable(getResources().getDrawable(R.drawable.star));

        } else if (Memory.getHighStars(theme.id, 5) == 2) {
            star51.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star52.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star53.setImageDrawable(getResources().getDrawable(R.drawable.star));
        } else if (Memory.getHighStars(theme.id, 5) == 3) {
            star51.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star52.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star53.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        }

        DifficultyView difficulty6 = (DifficultyView) view.findViewById(R.id.select_difficulty_6);
        difficulty6.setDifficulty(6, Memory.getHighStars(theme.id, 6));
        setOnClick(difficulty6, 6);

        if (Memory.getHighStars(theme.id, 6) == 1) {
            star61.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star62.setImageDrawable(getResources().getDrawable(R.drawable.star));
            star63.setImageDrawable(getResources().getDrawable(R.drawable.star));

        } else if (Memory.getHighStars(theme.id, 6) == 2) {
            star61.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star62.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star63.setImageDrawable(getResources().getDrawable(R.drawable.star));
        } else if (Memory.getHighStars(theme.id, 6) == 3) {
            star61.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star62.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            star63.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
        }

        animate(difficulty1, difficulty2, difficulty3, difficulty4, difficulty5, difficulty6);

        TextView text1 = (TextView) view.findViewById(R.id.time_difficulty_1);
        text1.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        text1.setText(getBestTimeForStage(theme.id, 1));

        TextView text2 = (TextView) view.findViewById(R.id.time_difficulty_2);
        text2.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        text2.setText(getBestTimeForStage(theme.id, 2));

        TextView text3 = (TextView) view.findViewById(R.id.time_difficulty_3);
        text3.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        text3.setText(getBestTimeForStage(theme.id, 3));

        TextView text4 = (TextView) view.findViewById(R.id.time_difficulty_4);
        text4.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        text4.setText(getBestTimeForStage(theme.id, 4));

        TextView text5 = (TextView) view.findViewById(R.id.time_difficulty_5);
        text5.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        text5.setText(getBestTimeForStage(theme.id, 5));

        TextView text6 = (TextView) view.findViewById(R.id.time_difficulty_6);
        text6.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        text6.setText(getBestTimeForStage(theme.id, 6));

        return view;

    }

    private String getBestTimeForStage(int theme, int difficulty) {
        int bestTime = Memory.getBestTime(theme, difficulty);
        if (bestTime != -1) {
            int minutes = (bestTime % 3600) / 60;
            int seconds = (bestTime) % 60;
            String result = String.format("BEST: %02d:%02d", minutes, seconds);
            return result;
        } else {
            String result = "BEST:";
            return result;
        }
    }

    private void animate(View... view) {
        AnimatorSet animatorSet = new AnimatorSet();
        Builder builder = animatorSet.play(new AnimatorSet());
        for (int i = 0; i < view.length; i++) {
            ObjectAnimator scaleX = ObjectAnimator.ofFloat(view[i], "scaleX", 0.8f, 1f);
            ObjectAnimator scaleY = ObjectAnimator.ofFloat(view[i], "scaleY", 0.8f, 1f);
            builder.with(scaleX).with(scaleY);
        }
        animatorSet.setDuration(500);
        animatorSet.setInterpolator(new BounceInterpolator());
        animatorSet.start();
    }

    private void setOnClick(View view, final int difficulty) {
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new DifficultySelectedEvent(difficulty));
            }
        });
    }


}
