package uk.co.ribot.androidboilerplate.ui.bing;

import java.util.List;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmQuery;
import rx.Subscriber;
import rx.Subscription;
import timber.log.Timber;
import uk.co.ribot.androidboilerplate.bing.data.BingSearchService;
import uk.co.ribot.androidboilerplate.bing.data.model.Result;
import uk.co.ribot.androidboilerplate.bing.data.model.SearchResult;
import uk.co.ribot.androidboilerplate.ui.base.BasePresenter;

public class BingPresenter extends BasePresenter<BingMvpView> {
    private Subscription mSubscription;
    private String currentQuery;
    private Realm realm;

    @Inject
    public BingPresenter() {
    }

    public void setRealm(Realm realm) {
        this.realm = realm;
    }

    @Override
    public void attachView(BingMvpView mvpView) {
        super.attachView(mvpView);
        realm = Realm.getDefaultInstance();
        currentQuery = null;
    }

    @Override
    public void detachView() {
        super.detachView();
        cancelSubscription();
        realm.close();
        currentQuery = null;
    }

    public void search(String query) {
        if (!setCurrentQuery(query)) {
            return;
        }
        if (!existsInRealm(query)) {
            BingSearchService.startActionSearch(getMvpView().getContext(), query);
            getMvpView().setContentLoading();
        }
        cancelSubscription();
        mSubscription = realm.asObservable()
          .map((r) -> queryRealm(r, query).findFirst())
          .filter((o) -> o != null)
          .map((searchResult) -> searchResult.getResults())
          .subscribe(new Subscriber<List<Result>>() {
              @Override
              public void onCompleted() {
                  Timber.i("onCompleted");
              }

              @Override
              public void onError(Throwable e) {
                  Timber.i("onError: %s", e.toString());
              }

              @Override
              public void onNext(List<Result> results) {
                  if (results.isEmpty()) {
                      Timber.e("onNext: Empty");
                  } else {
                      Timber.e("onNext: Have %d Results", results.size());
                  }
              }
          });
    }

    private boolean existsInRealm(String query) {
        return queryRealm(realm, query).count() != 0;
    }

    private RealmQuery<SearchResult> queryRealm(Realm realm, String query) {
        return realm.where(SearchResult.class).equalTo("key", query);
    }

    private void cancelSubscription() {
        if (mSubscription != null) mSubscription.unsubscribe();
    }

    private boolean setCurrentQuery(String query) {
        if (query != null && query != currentQuery) {
            this.currentQuery = query;
            getMvpView().setTitle(query);
            return true;
        } else {
            return false;
        }
    }
}
