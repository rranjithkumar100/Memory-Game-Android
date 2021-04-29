package com.agileinfoways.memorygame.game.fragments;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.agileinfoways.memorygame.Application;
import com.agileinfoways.memorygame.R;
import com.agileinfoways.memorygame.activities.MainActivity;
import com.agileinfoways.memorygame.game.common.Memory;
import com.agileinfoways.memorygame.game.common.Shared;
import com.agileinfoways.memorygame.game.events.ui.ThemeSelectedEvent;
import com.agileinfoways.memorygame.game.themes.Theme;
import com.agileinfoways.memorygame.game.themes.Themes;
import com.google.android.gms.ads.AdView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ThemeSelectFragment extends Fragment {

    @BindView(R.id.star_1_1)
    ImageView star11;
    @BindView(R.id.star_1_2)
    ImageView star12;
    @BindView(R.id.star_1_3)
    ImageView star13;
    @BindView(R.id.star_2_1)
    ImageView star21;
    @BindView(R.id.star_2_2)
    ImageView star22;
    @BindView(R.id.star_2_3)
    ImageView star23;
    @BindView(R.id.star_3_1)
    ImageView star31;
    @BindView(R.id.star_3_2)
    ImageView star32;
    @BindView(R.id.star_3_3)
    ImageView star33;
    @BindView(R.id.star_4_1)
    ImageView star41;
    @BindView(R.id.star_4_2)
    ImageView star42;
    @BindView(R.id.star_4_3)
    ImageView star43;
    @BindView(R.id.star_5_1)
    ImageView star51;
    @BindView(R.id.star_5_2)
    ImageView star52;
    @BindView(R.id.star_5_3)
    ImageView star53;
    @BindView(R.id.star_6_1)
    ImageView star61;
    @BindView(R.id.star_6_2)
    ImageView star62;
    @BindView(R.id.star_6_3)
    ImageView star63;
    @BindView(R.id.adView)
    AdView adView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(Shared.context).inflate(R.layout.theme_select_fragment, container, false);

        ButterKnife.bind(this, view);

        ImageView animals = view.findViewById(R.id.theme_animals);
        ImageView vegetables = view.findViewById(R.id.theme_vegetables);
        ImageView clothes = view.findViewById(R.id.theme_clothes);
        ImageView vehicles = view.findViewById(R.id.theme_vehicles);
        ImageView emojis = view.findViewById(R.id.theme_emojis);
        ImageView sports = view.findViewById(R.id.theme_sports);
        ImageView photos = view.findViewById(R.id.theme_photos);

        final Theme themeAnimals = Themes.createAnimalsTheme();
        setStars(themeAnimals);
        final Theme themeVegitables = Themes.createVegetablesTheme();
        setStars(themeVegitables);
        final Theme themeClothes = Themes.createClothesTheme();
        setStars(themeClothes);
        final Theme themeVehicles = Themes.createVehiclesTheme();
        setStars(themeVehicles);
        final Theme themeEmoji = Themes.createEmojiTheme();
        setStars(themeEmoji);
        final Theme themeSports = Themes.createSportsTheme();
        setStars(themeSports);
        final Theme themePhotos = Themes.createPhotoTheme();
        setStars(themePhotos);

        animals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themeAnimals));
            }
        });

        vegetables.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themeVegitables));
            }
        });

        clothes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themeClothes));
            }
        });
        vehicles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themeVehicles));
            }
        });
        emojis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themeEmoji));
            }
        });
        sports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themeSports));
            }
        });
        photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Shared.eventBus.notify(new ThemeSelectedEvent(themePhotos));
            }
        });

        /**
         * Imporove performance first!!!
         */
        animateShow(animals);
        animateShow(vegetables);
        animateShow(clothes);
        animateShow(vehicles);
        animateShow(emojis);
        animateShow(sports);

        //Load Banner Add
        if (((MainActivity)getActivity()).mNetworkUtils.isConnected()) {
            adView.setVisibility(View.VISIBLE);
            Application.getInstance().getAdsWrapper().loadBannerAd(adView);
        } else {
            adView.setVisibility(View.GONE);
        }
        return view;
    }

    private void animateShow(View view) {
        ObjectAnimator animatorScaleX = ObjectAnimator.ofFloat(view, "scaleX", 0.5f, 1f);
        ObjectAnimator animatorScaleY = ObjectAnimator.ofFloat(view, "scaleY", 0.5f, 1f);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(300);
        animatorSet.playTogether(animatorScaleX, animatorScaleY);
        animatorSet.setInterpolator(new DecelerateInterpolator(2));
        view.setLayerType(View.LAYER_TYPE_HARDWARE, null);
        animatorSet.start();
    }

    private void setStars(Theme theme) {
        int sum = 0;
        for (int difficulty = 1; difficulty <= 6; difficulty++) {
            sum += Memory.getHighStars(theme.id, difficulty);
        }
        int num = sum / 6;

        if (theme.id == 1) {
            if (num == 1) {
                star11.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star12.setImageDrawable(getResources().getDrawable(R.drawable.star));
                star13.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 2) {
                star11.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star12.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star13.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 3) {
                star11.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star12.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star13.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            }
        } else if (theme.id == 2) {
            if (num == 1) {
                star21.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star22.setImageDrawable(getResources().getDrawable(R.drawable.star));
                star23.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 2) {
                star21.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star22.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star23.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 3) {
                star21.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star22.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star23.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            }
        } else if (theme.id == 3) {
            if (num == 1) {
                star31.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star32.setImageDrawable(getResources().getDrawable(R.drawable.star));
                star33.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 2) {
                star31.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star32.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star33.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 3) {
                star31.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star32.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star33.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            }
        } else if (theme.id == 4) {
            if (num == 1) {
                star41.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star42.setImageDrawable(getResources().getDrawable(R.drawable.star));
                star43.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 2) {
                star41.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star42.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star43.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 3) {
                star41.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star42.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star43.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            }
        } else if (theme.id == 5) {
            if (num == 1) {
                star51.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star52.setImageDrawable(getResources().getDrawable(R.drawable.star));
                star53.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 2) {
                star51.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star52.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star53.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 3) {
                star51.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star52.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star53.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            }
        } else if (theme.id == 6) {
            if (num == 1) {
                star61.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star62.setImageDrawable(getResources().getDrawable(R.drawable.star));
                star63.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 2) {
                star61.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star62.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star63.setImageDrawable(getResources().getDrawable(R.drawable.star));
            } else if (num == 3) {
                star61.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star62.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
                star63.setImageDrawable(getResources().getDrawable(R.drawable.star_fill));
            }
        }


    }
}
