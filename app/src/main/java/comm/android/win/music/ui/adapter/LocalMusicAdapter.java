package comm.android.win.music.ui.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import comm.android.win.music.R;
import comm.android.win.music.app.IApplication;
import comm.android.win.music.bean.Song;
import comm.android.win.music.utils.MusicUtils;

/**
 *
 * Created by win on 2018/1/7.
 */

public class LocalMusicAdapter extends RecyclerView.Adapter implements View.OnClickListener {

    private Context mContext;
    private OnItemClickListener mOnItemClickListener = null;
    private OnMoreClickListener mListener=null;


    public LocalMusicAdapter() {

        mContext = IApplication.getContext();
    }

    private List<Song> songList = new ArrayList<>();

    //拿到本地音乐数据
    public void getData(List<Song> songList) {
        if (songList != null) {
            this.songList = songList;
        }

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_localmusic, parent, false);
        view.setOnClickListener(this);
        return new SongViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, @SuppressLint("RecyclerView") final int position) {

        SongViewHolder songViewHolder = (SongViewHolder) holder;
        String albumArt = MusicUtils.getAlbumArt(mContext, songList.get(position).getAlbum_id());
        Bitmap bm = null;
        bm = BitmapFactory.decodeFile(albumArt);
//        songViewHolder.ivCover.setImageBitmap(bm);
        songViewHolder.tvTitle.setText(songList.get(position).getSong());
        songViewHolder.tvArtist.setText(songList.get(position).getSinger());
        songViewHolder.vDivider.setVisibility(isShowDivider(position) ? View.VISIBLE : View.GONE);

        songViewHolder.ivMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mListener!=null){
                    Toast.makeText(mContext,"第"+position+"dialog",Toast.LENGTH_SHORT).show();

                    mListener.onMoreClick(position);
                }
            }
        });
        songViewHolder.itemView.setTag(position);
    }

    public boolean isShowDivider(int position) {

        return position != songList.size() - 1;
    }


    @Override
    public int getItemCount() {
        return songList.size();
    }

    @Override
    public void onClick(View view) {

        if (mOnItemClickListener!=null){
            mOnItemClickListener.onItemClick(view, (Integer) view.getTag());
        }
    }


    static class SongViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.v_playing)
        View vPlaying;
        @BindView(R.id.iv_cover)
        ImageView ivCover;
        @BindView(R.id.tv_title)
        TextView tvTitle;
        @BindView(R.id.tv_artist)
        TextView tvArtist;
        @BindView(R.id.iv_more)
        ImageView ivMore;
        @BindView(R.id.v_divider)
        View vDivider;

        SongViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }
    /**
     * 音乐列表“更多”按钮监听器
     * Created by hzwangchenyan on 2015/12/17.
     */
    public interface OnMoreClickListener {
        void onMoreClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {

        mOnItemClickListener = onItemClickListener;
    }

}
