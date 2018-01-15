package comm.android.win.music.model;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;

import comm.android.win.music.contract.MainContract;
import comm.android.win.music.model.bean.DataInfo;
import comm.android.win.music.model.http.HttpInterceptor;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by win on 2017/12/27.
 */

public class MainModel  implements MainContract.Model {

    private static final String TAG = "MainModel";

    private static final String BASE_URL = "http://tingapi.ting.baidu.com/v1/restserver/ting";

    private static final String METHOD_GET_MUSIC_LIST = "?method=baidu.ting.billboard.billList&format=json";

    private static MainContract.Presenter mPresenter = null;



    @SuppressLint("HandlerLeak")
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            if (mPresenter!=null) {

                DataInfo dataInfo = (DataInfo) msg.obj;
                mPresenter.showData(dataInfo);

            }
        }
    };

    @Override
    public void getData(final MainContract.Presenter presenter, String type, int size, int offset) {

        mPresenter=presenter;
        OkHttpClient client = new OkHttpClient
                .Builder()
                .addInterceptor(new HttpInterceptor())
                .build();
        String url = BASE_URL + METHOD_GET_MUSIC_LIST + "&type=" + type + "&size=" + size + "&offset=" + offset;
        Log.i(TAG, "getData: " + url);
        Request request = new Request.Builder()
                .url(url)
                .build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {


                String info = response.body().string();
                DataInfo dataInfo = new Gson().fromJson(info, DataInfo.class);
                Log.i("xxx", "onResponse: ---------");
                Log.i("XXX", "onResponse: " + dataInfo.getSong_list().get(0).getTitle());
                Message message = handler.obtainMessage();
                message.obj = dataInfo;
                handler.sendMessage(message);

            }
        });


    }


}
