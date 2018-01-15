package comm.android.win.music.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.util.DisplayMetrics;
import android.util.Log;


/**
 * Created by hzwangchenyan on 2016/11/23.
 */
public class AppCache {
    private Context mContext;

    private AppCache() {
    }

    private static class SingletonHolder {
        private static AppCache sAppCache = new AppCache();
    }

    private static AppCache getInstance() {
        return SingletonHolder.sAppCache;
    }

    public static void init(Application application) {
        getInstance().onInit(application);
    }

    private void onInit(Application application) {
        mContext = application.getApplicationContext();

        application.registerActivityLifecycleCallbacks(new ActivityLifecycle());
    }

    public static Context getContext() {
        return getInstance().mContext;
    }


    public static void updateNightMode(boolean on) {
        if (on) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }


//        Resources resources = getContext().getResources();
//        DisplayMetrics dm = resources.getDisplayMetrics();
//        Configuration config = resources.getConfiguration();
//        config.uiMode &= ~Configuration.UI_MODE_NIGHT_MASK;
//        config.uiMode |= on ? Configuration.UI_MODE_NIGHT_YES : Configuration.UI_MODE_NIGHT_NO;
//        resources.updateConfiguration(config, dm);

    }

    private static class ActivityLifecycle implements Application.ActivityLifecycleCallbacks {
        private static final String TAG = "Activity";

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
            Log.i(TAG, "onCreate: " + activity.getClass().getSimpleName());
        }

        @Override
        public void onActivityStarted(Activity activity) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }

        @Override
        public void onActivityStopped(Activity activity) {
        }

        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
            Log.i(TAG, "onDestroy: " + activity.getClass().getSimpleName());
        }
    }
}
