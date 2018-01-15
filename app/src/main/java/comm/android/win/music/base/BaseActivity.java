package comm.android.win.music.base;

import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import butterknife.ButterKnife;

/**
 * Activity基类
 * Created by win on 2017/12/27.
 */

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    //    private MainPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setContentViewId());
        ButterKnife.bind(this);
        initView();
//        presenter=setPresenter();
//        presenter.attachView(this);
        setSystemBarTransparent();
        initData();

    }

    /**
     * 系统沉浸式
     */
    private void setSystemBarTransparent() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // LOLLIPOP解决方案
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // KITKAT解决方案
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    }

    protected abstract int setContentViewId();

    protected abstract void initView();

    protected abstract void initData();
//    protected abstract MainPresenter setPresenter();


    /**
     * 为子类提供一个权限检查方法
     * String... permission表示不定参数，也就是调用这个方法的时候这里可以传入多个String对象(JDK5新特性)
     *
     * @return
     */
    public boolean hasPermission(String... permissions) {
        for (String permission : permissions) {
            if (ContextCompat.checkSelfPermission(this, permission) != PackageManager.PERMISSION_GRANTED) {
                return true;
            }
        }
        return false;
    }

    /**
     * 为子类提供一个权限申请方法
     */
    public void requestPermission(int code, String... permissions) {
        ActivityCompat.requestPermissions(this, permissions, code);
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 458:
                //判断读取申请权限是否成功,成功就执行写入的逻辑
                //注意:因为集合里只有一个权限申请,所以参数为0代表写入入权限
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    //用户拒绝了权限请求,给用户提示权限的功能
                    Toast.makeText(this, "权限没有授予", Toast.LENGTH_SHORT).show();
                }
                break;
            case 369:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                } else {
                    //用户拒绝了权限请求,给用户提示权限的功能
                    Toast.makeText(this, "权限没有授予", Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    public void doSdCardWritePermission() {
    }

    public void doSdCardReadPermission() {
    }


}
