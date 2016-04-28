package uk.co.ribot.androidboilerplate.bing.data.model;

import android.support.annotation.Nullable;

import java.util.Collection;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by user on 22.04.2016.
 */
public class SearchResult extends RealmObject {
    @PrimaryKey
    private String key;
    private RealmList<Result> results = new RealmList<>();
    @Nullable
    private String next;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public RealmList<Result> getResults() {
        return results;
    }

    public String getNext() {
        return next;
    }

    public void setNext(String next) {
        this.next = next;
    }

    public static SearchResult empty(String key){
        SearchResult that = new SearchResult();
        that.key = key;
        return that;
    }

    public static SearchResult create(String key, BingImageSearchResult r) {
        SearchResult that = new SearchResult();
        that.key = key;
        that.results.addAll(r.d.results);
        that.next = r.d.Next;
        return that;
    }
}
