package uk.co.ribot.androidboilerplate.bing.data;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Timer;

import javax.inject.Inject;
import javax.inject.Singleton;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;
import rx.Observable;
import rx.Observer;
import rx.functions.Func1;
import rx.subjects.BehaviorSubject;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.bing.data.model.Result;
import uk.co.ribot.androidboilerplate.bing.data.model.SearchResult;
import uk.co.ribot.androidboilerplate.data.DataManager;

/**
 * Created by user on 26.04.2016.
 */
@Singleton
public class BingDataManager {
    @Inject
    public BingDataManager() {
    }

    public Observable<List<Result>> getResults(Realm realm, String query) {
        return realm.asObservable()
          .map((r) -> r.where(SearchResult.class).equalTo("key", query).findFirst())
          .filter((o) -> o != null)
          .map((searchResult) -> searchResult.getResults());
    }
}
