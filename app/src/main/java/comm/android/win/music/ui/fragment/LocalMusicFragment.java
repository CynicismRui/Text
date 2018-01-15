package comm.android.win.music.ui.fragment;


import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import comm.android.win.music.R;
import comm.android.win.music.base.BaseFragment;
import comm.android.win.music.bean.Song;
import comm.android.win.music.ui.adapter.LocalMusicAdapter;
import comm.android.win.music.utils.MusicUtils;

/**
 * 本地音乐
 */
public class LocalMusicFragment extends BaseFragment implements LocalMusicAdapter.OnMoreClickListener{

    private static final String TAG = "LocalMusicFragment";

    @BindView(R.id.localRecyclerView)
    RecyclerView localRecyclerView;
    Unbinder unbinder;
    private View view;

    private LocalMusicAdapter adapter;
    private List<Song> musicData;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = LayoutInflater.from(getActivity()).inflate(R.layout.fragment_local_music, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void init() {
        localRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new LocalMusicAdapter();
        localRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {

        musicData = MusicUtils.getMusicData(getActivity());
        if (musicData != null) {
            adapter.getData(musicData);
        }
        adapter.notifyDataSetChanged();
        Log.e(TAG, "initData: " );

        adapter.setOnItemClickListener(new LocalMusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

                Toast.makeText(getActivity(),"第"+position+"条目",Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @Override
    public void onMoreClick(int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(getContext());
        dialog.setTitle(musicData.get(position).getSong());
        dialog.setItems(R.array.local_music_dialog, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:// 分享
//                        shareMusic(music);
                        break;
                    case 1:// 设为铃声
//                        requestSetRingtone(music);
                        break;
                    case 2:// 查看歌曲信息
//                        musicInfo(music);
                        break;
                    case 3:// 删除
//                        deleteMusic(music);
                        break;
                }
            }
        });
        dialog.show();
    }
}
