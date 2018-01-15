package comm.android.win.music.ui.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.HashMap;
import java.util.Map;

import comm.android.win.music.R;
import comm.android.win.music.bean.MusicListBean;
import comm.android.win.music.utils.retrofit.APIFactory;
import comm.android.win.music.utils.retrofit.AbstractObserver;

/**
 * 在线音乐列表
 * Created by win on 2018/1/1.
 */

public class MusicListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private Context context;

    public MusicListAdapter(Context context) {

        this.context = context;
    }

    private static final int type_head = 0;
    private static final int type_list = 1;

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == type_head) {
            View view = LayoutInflater.from(context).inflate(R.layout.item_headline, parent, false);
            return new MusicTitleViewHolder(view);
        } else {
            View view = LayoutInflater.from(context).inflate(R.layout.item_musiclist, parent, false);
            view.setOnClickListener(this);
            return new MusicListViewHolder(view);

        }
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {

        if (holder instanceof MusicTitleViewHolder) {
            MusicTitleViewHolder MusicTitleholder1 = (MusicTitleViewHolder) holder;
            if (position == 0) {
                MusicTitleholder1.tvProfile1.setText("主打榜单");

            } else if (position == 3) {
                MusicTitleholder1.tvProfile1.setText("分类榜单");

            } else if (position == 11) {
                MusicTitleholder1.tvProfile1.setText("媒体榜单");
            }
        }

        //1、新歌榜，2、热歌榜，
        //11、摇滚榜，12、爵士，16、流行
        //21、欧美金曲榜，22、经典老歌榜，23、情歌对唱榜，24、影视金曲榜，25、网络歌曲榜
        else if (holder instanceof MusicListViewHolder) {
            final MusicListViewHolder musicListHolder = (MusicListViewHolder) holder;
            if (position == 1 || position == 2) {
                Map<String, String> map = new HashMap<>();
                map.put("method", "baidu.ting.billboard.billList");
                map.put("type", position + "");
                map.put("size", "15");
                map.put("offset", "offset");
                APIFactory.getInstance().get("v1/restserver/ting", map, new AbstractObserver<MusicListBean>() {

                    @Override
                    public void onSuccess(MusicListBean songbean) {

                        Glide.with(context).load(songbean.getBillboard().getPic_s192()).into(musicListHolder.ivCover);
                        musicListHolder.tvMusic1.setText(songbean.getSong_list().get(0).getTitle());
                        musicListHolder.tvMusic2.setText(songbean.getSong_list().get(1).getTitle());
                        musicListHolder.tvMusic3.setText(songbean.getSong_list().get(2).getTitle());
                    }

                    @Override
                    public void onFailure(int code) {}
                });
            }

            if (position == 4 || position == 5) {
                Map<String, String> map = new HashMap<>();
                map.put("method", "baidu.ting.billboard.billList");
                map.put("type", position + 7 + "");
                map.put("size", "15");
                map.put("offset", "offset");
                APIFactory.getInstance().get("v1/restserver/ting", map, new AbstractObserver<MusicListBean>() {
                    @Override
                    public void onSuccess(MusicListBean songbean) {

                        Glide.with(context).load(songbean.getBillboard().getPic_s192()).into(musicListHolder.ivCover);
                        musicListHolder.tvMusic1.setText(songbean.getSong_list().get(0).getTitle());
                        musicListHolder.tvMusic2.setText(songbean.getSong_list().get(1).getTitle());
                        musicListHolder.tvMusic3.setText(songbean.getSong_list().get(2).getTitle());
                    }

                    @Override
                    public void onFailure(int code) {}
                });

            }

            if (position == 6) {
                Map<String, String> map = new HashMap<>();
                map.put("method", "baidu.ting.billboard.billList");
                map.put("type", position + 10 + "");
                map.put("size", "15");
                map.put("offset", "offset");
                APIFactory.getInstance().get("v1/restserver/ting", map, new AbstractObserver<MusicListBean>() {
                    @Override
                    public void onSuccess(MusicListBean songbean) {

                        Glide.with(context).load(songbean.getBillboard().getPic_s192()).into(musicListHolder.ivCover);
                        musicListHolder.tvMusic1.setText(songbean.getSong_list().get(0).getTitle());
                        musicListHolder.tvMusic2.setText(songbean.getSong_list().get(1).getTitle());
                        musicListHolder.tvMusic3.setText(songbean.getSong_list().get(2).getTitle());
                    }

                    @Override
                    public void onFailure(int code) {}
                });

            }
            if (position == 7 || position == 8 || position == 9 || position == 10 || position == 11) {
                Map<String, String> map = new HashMap<>();
                map.put("method", "baidu.ting.billboard.billList");
                map.put("type", position + 14 + "");
                map.put("size", "15");
                map.put("offset", "offset");
                APIFactory.getInstance().get("v1/restserver/ting", map, new AbstractObserver<MusicListBean>() {
                    @Override
                    public void onSuccess(MusicListBean songbean) {

                        Glide.with(context).load(songbean.getBillboard().getPic_s192()).into(musicListHolder.ivCover);
                        musicListHolder.tvMusic1.setText(songbean.getSong_list().get(0).getTitle());
                        musicListHolder.tvMusic2.setText(songbean.getSong_list().get(1).getTitle());
                        musicListHolder.tvMusic3.setText(songbean.getSong_list().get(2).getTitle());
                    }

                    @Override
                    public void onFailure(int code) {}
                });
            }

            musicListHolder.itemView.setTag(position);

        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0 || position == 3 || position == 11) {
            return type_head;
        } else {
            return type_list;
        }
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    class MusicTitleViewHolder extends RecyclerView.ViewHolder {


        private final TextView tvProfile1;

        public MusicTitleViewHolder(View itemView) {
            super(itemView);

            tvProfile1 = itemView.findViewById(R.id.tv_profile);
        }
    }

    class MusicListViewHolder extends RecyclerView.ViewHolder {

        private final ImageView ivCover;
        private final TextView tvMusic1;
        private final TextView tvMusic2;
        private final TextView tvMusic3;

        public MusicListViewHolder(View itemView) {

            super(itemView);
            ivCover = itemView.findViewById(R.id.iv_cover);
            tvMusic1 = itemView.findViewById(R.id.tv_music_1);
            tvMusic2 = itemView.findViewById(R.id.tv_music_2);
            tvMusic3 = itemView.findViewById(R.id.tv_music_3);
        }
    }

    private OnItemClickListener mOnItemClickListener = null;

    public interface OnItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    @Override
    public void onClick(View view) {
        if (mOnItemClickListener != null) {
            //注意这里使用getTag方法获取position
            mOnItemClickListener.onItemClick(view, (int) view.getTag());
        }
    }

}
