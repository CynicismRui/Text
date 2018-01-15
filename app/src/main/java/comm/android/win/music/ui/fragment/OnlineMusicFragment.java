package comm.android.win.music.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import comm.android.win.music.MusicActivity;
import comm.android.win.music.R;
import comm.android.win.music.base.BaseFragment;
import comm.android.win.music.ui.activity.MusicListActivity;
import comm.android.win.music.ui.adapter.MusicListAdapter;

/**
 * 在线音乐
 */
public class OnlineMusicFragment extends BaseFragment {

    @BindView(R.id.onlineRecyclerView)
    RecyclerView onlineRecyclerView;
    Unbinder unbinder;
    private MusicListAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_online_music, container, false);
        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    protected void init() {
        onlineRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity(),LinearLayoutManager.VERTICAL,false));
        adapter = new MusicListAdapter(getActivity());
        onlineRecyclerView.setAdapter(adapter);

    }

    @Override
    protected void initData() {
        adapter.setOnItemClickListener(new MusicListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                gainIntent();

            }


        });
    }

    private void gainIntent() {
        startActivity(new Intent(getActivity(), MusicListActivity.class));
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
