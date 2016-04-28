package uk.co.ribot.androidboilerplate.bing.data.remote;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import uk.co.ribot.androidboilerplate.bing.data.model.BingImageSearchResult;

/**
 * Created by user on 22.04.2016.
 */
public interface BingApi {
    String BASE_URL = "https://api.datamarket.azure.com/";
    String API_KEY = "aXw/v4E+kar1uxlkbZ6Vl3BRfMy6cQ0r1XK8XYkFi9M";

    @GET("Bing/Search/v1/Image?$format=json")
    Observable<BingImageSearchResult> queryImages(@Query("Query") String query);

    @GET("Bing/Search/v1/Image?$format=json")
    Call<BingImageSearchResult> queryImagesBlocking(@Query("Query") String query);

    class Creator {
        public static BingApi create() {
            return RetrofitHelper
              .createService(BingApi.class, BingApi.BASE_URL, BingApi.API_KEY, BingApi.API_KEY);
        }
    }
}
