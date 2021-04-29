package com.agileinfoways.memorygame;

import com.agileinfoways.memorygame.utils.AdsWrapper;
import com.google.android.gms.ads.AdRequest;

import timber.log.Timber;


public class Application extends android.app.Application {
    private static Application sInstance;
    private AdsWrapper adsWrapper;

    @Override
    public void onCreate() {
        super.onCreate();
        Timber.plant(new Timber.DebugTree());

        sInstance = this;
        adsWrapper = new AdsWrapper.Builder().with(this)
                .addTestDeviceIds(new String[]{"FC0135B6D6269BE7C5D5669065FBF72F", AdRequest.DEVICE_ID_EMULATOR})
                .build();
    }

    /**
     * Gets ads wrapper.
     *
     * @return the ads wrapper
     */
    public AdsWrapper getAdsWrapper() {
        return adsWrapper;
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Application getInstance() {
        return sInstance;
    }
}
