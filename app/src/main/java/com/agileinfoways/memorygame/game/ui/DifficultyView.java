package com.agileinfoways.memorygame.game.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.agileinfoways.memorygame.R;

public class DifficultyView extends LinearLayout {

    private TextView tvDifficulty;
    private Context context;

    public DifficultyView(Context context) {
        this(context, null);
        this.context = context;
    }

    public DifficultyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        LayoutInflater.from(context).inflate(R.layout.difficult_view, this, true);
        setOrientation(LinearLayout.VERTICAL);

        tvDifficulty = findViewById(R.id.tv_difficulty);
    }

    public void setDifficulty(int difficulty, int stars) {

        if (difficulty == 1) {
            tvDifficulty.setText(context.getString(R.string.level_beginner));

        } else if (difficulty == 2) {
            tvDifficulty.setText(context.getString(R.string.level_easy));

        } else if (difficulty == 3) {
            tvDifficulty.setText(context.getString(R.string.level_medium));

        } else if (difficulty == 4) {
            tvDifficulty.setText(context.getString(R.string.level_hard));

        } else if (difficulty == 5) {
            tvDifficulty.setText(context.getString(R.string.level_hardest));

        } else if (difficulty == 6) {
            tvDifficulty.setText(context.getString(R.string.level_master));
        }

/*		String titleResource = String.format(Locale.US, "button_difficulty_%d_star_%d", difficulty, stars);
		int drawableResourceId = Shared.context.getResources().getIdentifier(titleResource, "drawable", Shared.context.getPackageName());
		mTitle.setImageResource(drawableResourceId);*/
    }

}
