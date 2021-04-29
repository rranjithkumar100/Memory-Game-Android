package com.agileinfoways.memorygame.utils;

/**
 * Created by agile-01 on 6/10/2017.
 * <p>
 * put all constants here
 */
public class Consts {

    public interface SharedPrefs {
        String IS_SOUND = "is_sound";
        String IS_MUSIC = "is_music";
        String IS_VIBRATOR = "is_vibrator";
    }

    /**
     * other constant must be put here
     */
    public interface OtherConstant {
        String IS_FIRST_TIME = "is_first_time";

        String LEVEL_COMPLETE = "complete";
        String LEVEL_NOT_COMPLETE = "not_complete";
        String LEVEL_LOCK = "locked";

        String PRIVACY_URL = "https://firebasestorage.googleapis.com/v0/b/agile-projects-6b727.appspot.com/o/memory-game%2Fmemory_game_privacy_policy_v2.html?alt=media&token=c127cc29-59aa-47d9-be7f-cd471b6c75b6";
        String TERMS_AND_CONDITION_URL = "https://firebasestorage.googleapis.com/v0/b/agile-projects-6b727.appspot.com/o/memory-game%2Fterms_and_conditions.html?alt=media&token=2ff53aed-29c3-463a-abdd-7ab56217b673";

    }

    /**
     * all common formats
     */
    public interface CommonFormat {
    }


    /**
     * all keys for bundle/intent extras must be put here
     */
    public interface BundleExtras {

    }

    /**
     * all type of request codes must put here
     */
    public interface RequestCode {
    }

    /**
     * all date time formats must be put here
     */
    public interface DateTimeFormats {

    }

    /**
     * all time delays must be put here
     */
    public interface Delays {
    }
}