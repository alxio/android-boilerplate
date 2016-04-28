package uk.co.ribot.androidboilerplate.bing.data.remote;

import android.util.Base64;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.GsonConverterFactory;
import retrofit2.Retrofit;
import retrofit2.RxJavaCallAdapterFactory;

/**
 * Created by user on 22.04.2016.
 */
public class RetrofitHelper {
    private static OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
      .baseUrl(BingApi.BASE_URL)
      .addConverterFactory(GsonConverterFactory.create())
      .addCallAdapterFactory(RxJavaCallAdapterFactory.create());

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        return createService(serviceClass, baseUrl, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String baseUrl, String username, String password) {
        if (username != null && password != null) {
            String credentials = username + ":" + password;
            final String basic =
              "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            httpClientBuilder.addInterceptor(new Interceptor() {
                @Override
                public Response intercept(Interceptor.Chain chain) throws IOException {
                    Request original = chain.request();

                    Request.Builder requestBuilder = original.newBuilder()
                      .header("Authorization", basic)
                      .header("Accept", "application/json")
                      .method(original.method(), original.body());

                    Request request = requestBuilder.build();
                    return chain.proceed(request);
                }
            });
        }

        OkHttpClient client = httpClientBuilder.build();
        Retrofit retrofit = retrofitBuilder.baseUrl(baseUrl).client(client).build();
        return retrofit.create(serviceClass);
    }
}
