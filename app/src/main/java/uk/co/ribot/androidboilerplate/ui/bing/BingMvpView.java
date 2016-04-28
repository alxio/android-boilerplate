package uk.co.ribot.androidboilerplate.ui.bing;

import android.content.Context;

import uk.co.ribot.androidboilerplate.ui.base.MvpView;

public interface BingMvpView extends MvpView {
    Context getContext();
    void setContentLoading();
    void setTitle(String query);
}
