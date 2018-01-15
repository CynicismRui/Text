package comm.android.win.music;

import android.Manifest;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import comm.android.win.music.base.BaseActivity;
import comm.android.win.music.executor.NavigationMenuExecutor;
import comm.android.win.music.ui.fragment.LocalMusicFragment;
import comm.android.win.music.ui.fragment.OnlineMusicFragment;

public class MusicActivity extends BaseActivity {

    @BindView(R.id.iv_menu)
    ImageView ivMenu;
    @BindView(R.id.music_tab)
    TabLayout musicTab;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.vp_musicPager)
    ViewPager vpMusicPager;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigation_view)
    NavigationView navigationView;

    private List<String> MusicTitles = Arrays.asList("在线音乐", "本地音乐");
    private LocalMusicFragment localFragment;
    private OnlineMusicFragment onlineFragment;

    @Override
    protected int setContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sdCardReadPermission();
    }

    @Override
    protected void initView() {

        localFragment = new LocalMusicFragment();
        onlineFragment = new OnlineMusicFragment();

        View mNavigationHeader = LayoutInflater.from(this).inflate(R.layout.nav_header, navigationView, false);
        navigationView.addHeaderView(mNavigationHeader);
        /**
         *  侧拉菜单
         */
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {

                        menuItem.setChecked(false);
                        drawerLayout.closeDrawers();
                        return NavigationMenuExecutor.onNavigationItemSelected(menuItem, MusicActivity.this);
                    }
                });

        musicTab.setupWithViewPager(vpMusicPager);

    }

    @Override
    protected void initData() {

        vpMusicPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                if (position == 1) {

                    return onlineFragment;
                }
                return localFragment;
            }

            @Override
            public int getCount() {
                return MusicTitles.size();
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return MusicTitles.get(position);
            }
        });

    }

    @OnClick({R.id.iv_menu, R.id.music_tab, R.id.iv_search, R.id.vp_musicPager})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_menu:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.iv_search:
                break;
            case R.id.vp_musicPager:
                break;
        }
    }


    public void sdCardWritePermission() {
        if (hasPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            //要申请权限
            requestPermission(458, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            doSdCardWritePermission();
        }

    }

    public void sdCardReadPermission() {
        if (hasPermission(Manifest.permission.READ_EXTERNAL_STORAGE)) {
            //要申请权限
            requestPermission(369, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        } else {
            doSdCardReadPermission();
        }
    }

    @Override
    public void doSdCardWritePermission() {
        sdCardWritePermission();
    }

    @Override
    public void doSdCardReadPermission() {

    }
}
