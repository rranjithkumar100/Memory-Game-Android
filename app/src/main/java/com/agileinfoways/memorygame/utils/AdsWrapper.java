package com.agileinfoways.memorygame.utils;

import android.content.Context;
import android.location.Location;
import android.util.Log;

import androidx.annotation.IntDef;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.agileinfoways.memorygame.R;
import com.agileinfoways.memorygame.game.common.Shared;
import com.agileinfoways.memorygame.game.events.ui.NextGameEvent;
import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;

/**
 * <p>
 * Usage :
 * <li>1. include layout_ad_container.xml to your layout</li>
 * <li>2. add your admob id and ad unit id in strings.xml under admob_app_id & banner_ad_unit_id </li>
 * <li>3. bind views</li>
 * <pre>
 * AdView adView = (AdView)findViewById(R.id.adView);
 * </pre>
 * <li>4. get instance using builder</li>
 * <pre>
 *  AdsWrapper adsWrapper = new Builder()
 *      .with(this)
 *      .addTestDeviceIds(new String[]{AdRequest.DEVICE_ID_EMULATOR,"D2YYYYYYYYYYYYYYYYYYYYYYYY"})
 *      .build();
 * </pre>
 * <li>5. call {@link #loadBannerAd} to load banner ads.</li>
 * <pre>
 * adsWrapper.requestBannerAd(adView);
 * </pre>
 * <li>5. call loadBannerAd(...) to load banner ads.</li>
 * <pre>
 * adsWrapper.loadBannerAd("ca-app-pub-XXXXXXXXXXXXXXXX~NNNNNNNNNN",adView);
 * </pre>
 */
public class AdsWrapper {

    private static final String TAG = "Ads";

    @Nullable
    private Context mContext;

    @Nullable
    private Location mLocation;

    private int mGender = -1;

    @Nullable
    private String[] mTestDeviceIds;

    @Nullable
    private Date mBirthdayDate;
    private InterstitialAd mInterstitialAd;

    private AdsWrapper() {
        //no direct instances. use builder instead.
    }

    private AdsWrapper(Context context, @Nullable String[] testDeviceIds, @Nullable Location location, @Builder.Gender int gender, @Nullable Date birthdayDate) {
        this.mContext = context.getApplicationContext();
        this.mLocation = location;
        this.mGender = gender;
        this.mTestDeviceIds = testDeviceIds;
        this.mBirthdayDate = birthdayDate;
        MobileAds.initialize(context, context.getString(R.string.ad_mob_id));
    }

    public void loadBannerAd(final @NonNull AdView adView) {
        AdRequest adRequest = generateAdRequest();
        adView.setAdListener(getBannerAdListener(adView));
        adView.loadAd(adRequest);
    }


    public void loadInterstitialAd(@Nullable InterstitialAdCallback interstitialAdCallback) {
        if (mContext == null) return;
        AdRequest adRequest = generateAdRequest();
        if (mInterstitialAd == null) {
            mInterstitialAd = new InterstitialAd(mContext);
            mInterstitialAd.setAdUnitId(mContext.getString(R.string.interstitial_id));
        }
        mInterstitialAd.setAdListener(getInterstitialAdListener(interstitialAdCallback));
        mInterstitialAd.loadAd(adRequest);
    }


    private AdRequest generateAdRequest() {
        AdRequest.Builder adRequestBuilder = new AdRequest.Builder();
        if (mTestDeviceIds != null) {
            for (String testDevice : mTestDeviceIds) {
                adRequestBuilder.addTestDevice(testDevice);
            }
        }
        if (mGender != -1) {
            adRequestBuilder.setGender(mGender);
        }
        if (mLocation != null) {
            adRequestBuilder.setLocation(mLocation);
        }
        if (mBirthdayDate != null) {
            adRequestBuilder.setBirthday(mBirthdayDate);
        }
        return adRequestBuilder.build();
    }

    @NonNull
    private AdListener getInterstitialAdListener(@Nullable final InterstitialAdCallback adCallback) {
        return new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.e(TAG, "AD CLOSED!");
                Log.e(TAG, "\n loading new ad now!");
                // loadInterstitialAd(adCallback);
                Shared.eventBus.notify(new NextGameEvent());

            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                logAdError(i);

                AdRequest adRequest = generateAdRequest();
                if (mInterstitialAd != null)
                    mInterstitialAd.loadAd(adRequest);
                //Shared.eventBus.notify(new NextGameEvent());
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.e(TAG, "AD : User left the application!");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.e(TAG, "AD OPENED! Paisa aaya lya!");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e(TAG, "AD LOADED SUCCESSFULLY!");
                if (adCallback != null) {
                    adCallback.whenLoaded(mInterstitialAd);
                }
            }
        };
    }

    @NonNull
    private AdListener getBannerAdListener(final AdView adView) {
        return new AdListener() {
            @Override
            public void onAdClosed() {
                super.onAdClosed();
                Log.e(TAG, "AD CLOSED!");
                Log.e(TAG, "\n loading new ad now!");
                loadBannerAd(adView);
            }

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                logAdError(i);
            }

            @Override
            public void onAdLeftApplication() {
                super.onAdLeftApplication();
                Log.e(TAG, "AD : User left the application!");
            }

            @Override
            public void onAdOpened() {
                super.onAdOpened();
                Log.e(TAG, "AD OPENED! Paisa aaya lya!");
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                Log.e(TAG, "AD LOADED SUCCESSFULLY!");
            }
        };
    }

    private void logAdError(int i) {
        Log.d(TAG, "logAdError() called with: code = [" + i + "]");
        switch (i) {
            case AdRequest.ERROR_CODE_INTERNAL_ERROR:
                Log.e(TAG, "AD FAILED TO LOAD. Error Code : " + "ERROR_CODE_INTERNAL_ERROR");
                break;
            case AdRequest.ERROR_CODE_NETWORK_ERROR:
                Log.e(TAG, "AD FAILED TO LOAD. Error Code : " + "ERROR_CODE_NETWORK_ERROR");
                break;
            case AdRequest.ERROR_CODE_INVALID_REQUEST:
                Log.e(TAG, "AD FAILED TO LOAD. Error Code : " + "ERROR_CODE_INVALID_REQUEST");
                break;
            case AdRequest.ERROR_CODE_NO_FILL:
                Log.e(TAG, "AD FAILED TO LOAD. Error Code : " + "ERROR_CODE_NO_FILL");
                break;
        }
    }

    public static class Builder {

        private Context context;
        private String[] testDeviceIds;
        private Location targetedLocation;
        private Date birthdayDate;
        @Gender
        private int targetedGender = AdRequest.GENDER_UNKNOWN;

        public Builder with(@NonNull Context context) {
            this.context = context;
            return this;
        }

        public Builder addTestDeviceIds(@NonNull String[] testDeviceIds) {
            this.testDeviceIds = testDeviceIds;
            return this;
        }

        public Builder targetGender(@Gender int targetedGender) {
            this.targetedGender = targetedGender;
            return this;
        }

        public Builder targetLocation(@NonNull Location targetedLocation) {
            this.targetedLocation = targetedLocation;
            return this;
        }

        public Builder targetAge(@NonNull Date birthdayDate) {
            this.birthdayDate = birthdayDate;
            return this;
        }

        public AdsWrapper build() {
            if (context == null)
                throw new IllegalStateException("context is null. use with() and pass context");
            return new AdsWrapper(context, testDeviceIds, targetedLocation, targetedGender, birthdayDate);
        }

        @Retention(RetentionPolicy.SOURCE)
        @IntDef({AdRequest.GENDER_MALE, AdRequest.GENDER_FEMALE, AdRequest.GENDER_UNKNOWN})
        private @interface Gender {
        }
    }
}
