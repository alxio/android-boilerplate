package uk.co.ribot.androidboilerplate.bing.data;

import io.realm.Realm;
import uk.co.ribot.androidboilerplate.bing.data.model.SearchResult;

/**
 * Created by user on 27.04.2016.
 */
public class RealmHelper {

    public static void saveSearchResult(SearchResult result) {
        Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(result);
        realm.commitTransaction();
    }

    public static void createSearchResultIfNeeded(String key) {
        Realm realm = Realm.getDefaultInstance();
        long count = realm.where(SearchResult.class).equalTo("key", key).count();
        if(count == 0){
            realm.beginTransaction();
            realm.copyToRealmOrUpdate(SearchResult.empty(key));
            realm.commitTransaction();
        }
    }
}
