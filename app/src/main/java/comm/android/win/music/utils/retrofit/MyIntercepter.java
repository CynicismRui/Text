package comm.android.win.music.utils.retrofit;

import android.util.Log;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class MyIntercepter implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {

        Request request = chain.request();
        //request.newBuilder().addHeader("User_Agent","");
        String url=request.url().toString()+"&source=android";
        Request build = request.newBuilder().url(url).build();
        Response response = chain.proceed(build);

        Log.i("==============", "intercept: "+response.toString());
        return response;


    }
}
