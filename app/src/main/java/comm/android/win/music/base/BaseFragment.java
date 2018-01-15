package comm.android.win.music.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;

import org.greenrobot.eventbus.EventBus;

/**
 * Fragment基类
 * Created by win on 2017/12/31.
 */

public abstract class BaseFragment extends Fragment {

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        init();
        initData();

    }



    protected abstract void init();

    protected abstract void initData();
}
