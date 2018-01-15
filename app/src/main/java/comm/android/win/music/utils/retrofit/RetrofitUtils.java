package comm.android.win.music.utils.retrofit;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitUtils {
    private static APIService service  = null ;
    public static APIService getInstance(){
        if(service == null){
            synchronized (RetrofitUtils.class){
                Retrofit retrofit = new Retrofit.Builder()
                          //这里写接口的拼接
                        .baseUrl("http://tingapi.ting.baidu.com/")
                        .addConverterFactory(ScalarsConverterFactory.create())
                        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                        .client(OkHttpUtils.getInstance())
                        .build();
                service = retrofit.create(APIService.class);
            }
        }
        return service;
    }

}
