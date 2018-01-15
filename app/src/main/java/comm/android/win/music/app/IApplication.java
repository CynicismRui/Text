package comm.android.win.music.app;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

import comm.android.win.music.MusicActivity;

/**
 * Created by win on 2018/1/1.
 */
@SuppressLint("StaticFieldLeak")
public class IApplication extends Application {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

    }


    public static Context getContext() {
        return context;
    }
}
