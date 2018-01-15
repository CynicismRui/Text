package comm.android.win.music.ui.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import comm.android.win.music.MusicActivity;
import comm.android.win.music.R;

public class StartActivity extends AppCompatActivity {
    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {

                Intent intent = new Intent();
                intent.setClass(StartActivity.this, MusicActivity.class);
                startActivity(intent);
                finish();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_start);

        startMusicActivity();
    }

    private void startMusicActivity() {
        handler.sendEmptyMessageDelayed(1, 3000);
    }
}
