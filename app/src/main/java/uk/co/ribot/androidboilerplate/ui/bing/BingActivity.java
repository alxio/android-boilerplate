package uk.co.ribot.androidboilerplate.ui.bing;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.Random;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import uk.co.ribot.androidboilerplate.R;
import uk.co.ribot.androidboilerplate.ui.base.BaseActivity;

public class BingActivity extends BaseActivity implements BingMvpView {

    private static final String EXTRA_TRIGGER_SYNC_FLAG =
      "uk.co.ribot.androidboilerplate.ui.bing.BingActivity.EXTRA_TRIGGER_SYNC_FLAG";

    @Inject
    BingPresenter mBingPresenter;

    @Bind(R.id.recycler_view)
    RecyclerView mRecyclerView;

    /**
     * Return an Intent to start this Activity.
     * triggerDataSyncOnCreate allows disabling the background sync service onCreate. Should
     * only be set to false during testing.
     */
    public static Intent getStartIntent(Context context, boolean triggerDataSyncOnCreate) {
        Intent intent = new Intent(context, BingActivity.class);
        intent.putExtra(EXTRA_TRIGGER_SYNC_FLAG, triggerDataSyncOnCreate);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getActivityComponent().inject(this);
        setContentView(R.layout.activity_bing);
        ButterKnife.bind(this);

        mRecyclerView.setAdapter(null);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBingPresenter.attachView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mBingPresenter.detachView();
    }

    public void onButtonClicked(View v){
        mBingPresenter.search(""+System.currentTimeMillis() % 10);
    }

    /*****
     * MVP View methods implementation
     *****/
    @Override
    public Context getContext() {
        return this;
    }

    @Override
    public void setContentLoading() {

    }

    @Override
    public void setTitle(String query) {

    }
}
