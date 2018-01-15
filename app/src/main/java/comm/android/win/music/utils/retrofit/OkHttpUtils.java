package comm.android.win.music.utils.retrofit;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;



public class OkHttpUtils {


    private static OkHttpClient client = null ;


    public static OkHttpClient getInstance(){

        if(client == null){
            synchronized (OkHttpUtils.class){  //是为了线程不拥挤

                if(client == null){
                    client = new OkHttpClient.Builder()
                            .connectTimeout(20000, TimeUnit.SECONDS)
                            .writeTimeout(20000,TimeUnit.SECONDS)
                            .readTimeout(20000,TimeUnit.SECONDS)  //在这里添加;拦截器
                            .addInterceptor(new LoggingInterceptor())
                            .build();

                   }
            }
        }
        return client;
    }


}
