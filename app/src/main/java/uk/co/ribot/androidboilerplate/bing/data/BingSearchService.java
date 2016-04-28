package uk.co.ribot.androidboilerplate.bing.data;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.bing.data.model.BingImageSearchResult;
import uk.co.ribot.androidboilerplate.bing.data.model.SearchResult;
import uk.co.ribot.androidboilerplate.bing.data.remote.BingApi;

public class BingSearchService extends IntentService {
    private static final String ACTION_SEARCH = "uk.co.ribot.androidboilerplate.bing.data.model.action.ACTION_SEARCH";
    private static final String EXTRA_QUERY = "uk.co.ribot.androidboilerplate.bing.data.model.extra.EXTRA_QUERY";

    public BingSearchService() {
        super("BingSearchService");
    }

    public static void startActionSearch(Context context, String query) {
        Intent intent = new Intent(context, BingSearchService.class);
        intent.setAction(ACTION_SEARCH);
        intent.putExtra(EXTRA_QUERY, query);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            switch (action){
                case ACTION_SEARCH:
                    final String query = intent.getStringExtra(EXTRA_QUERY);
                    handleActionSearch(query);
                    break;
                default:
                    Timber.w("Unknown action: %s", intent.getAction());
                    break;
            }
        }
    }

    // Internal implementation

    private BingApi api = BingApi.Creator.create();

    private void handleActionSearch(String query) {
        SearchResult result = performSearch(query);
        Timber.w("Search for %s successfull, have %d results.", query, result.getResults().size());
        RealmHelper.saveSearchResult(result);
    }

    private SearchResult performSearch(String query){
        return api.queryImages(wrap(query))
          .map((r) -> SearchResult.create(query, r))
          .toBlocking()
          .first();
    }

    //Wraps query in '' to match format requested by Bing Api
    private String wrap(String query) {
        return "'" + query + "'";
    }
}
