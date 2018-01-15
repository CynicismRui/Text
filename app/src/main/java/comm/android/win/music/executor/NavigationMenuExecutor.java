package comm.android.win.music.executor;

import android.app.ProgressDialog;
import android.os.Handler;
import android.view.MenuItem;

import comm.android.win.music.MusicActivity;
import comm.android.win.music.R;
import comm.android.win.music.app.AppCache;
import comm.android.win.music.utils.Preferences;

/**
 * Created by win on 2018/1/3.
 */

public class NavigationMenuExecutor {
    public static boolean onNavigationItemSelected(MenuItem menuItem, MusicActivity activity) {

        switch (menuItem.getItemId()) {
            case R.id.action_setting:
                break;
            case R.id.action_night:
                nightMode(activity);
                break;
            case R.id.action_timer:
                break;
            case R.id.action_exit:
                break;
            case R.id.action_about:
                break;
        }
        return false;
    }

    private static void nightMode(final MusicActivity activity) {
        final boolean on = !Preferences.isNightMode();
        final ProgressDialog dialog = new ProgressDialog(activity);
        dialog.setCancelable(false);
        dialog.show();
        AppCache.updateNightMode(on);

        Handler handler = new Handler(activity.getMainLooper());
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                dialog.cancel();
                activity.recreate();
                Preferences.saveNightMode(on);
            }
        }, 500);



    }
}
