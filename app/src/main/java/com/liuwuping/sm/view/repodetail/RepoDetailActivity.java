/*
 *  Copyright (c) 2016 [liuwuping1206@163.com | liuwuping1206@gmail.com]
 *
 *  Licensed under the Apache License, Version 2.0 (the "License”);
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.liuwuping.sm.view.repodetail;

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.liuwp.androidtoolkit.utils.L;
import com.liuwuping.sm.R;
import com.liuwuping.sm.model.Repo;
import com.liuwuping.sm.view.base.BaseActivity;
import com.liuwuping.sm.view.user.UserActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author:liuwuping
 * Date: 2016/5/18
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class RepoDetailActivity extends BaseActivity implements RepoDetailContract.View {

    @Bind(R.id.toolbar_repodetail)
    Toolbar toolbar;
    @Bind(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @Bind(R.id.wv_repodetail)
    WebView webView;
    @Bind(R.id.appbar_repodetail)
    AppBarLayout appBar;
    @Bind(R.id.iv_repodetail)
    ImageView imageView;
    @Bind(R.id.pb_repodetail_wv)
    ProgressBar webViewProgressBar;
    @Bind(R.id.tv_repodetail_star)
    TextView starTv;
    @Bind(R.id.tv_repodetail_fork)
    TextView forkTv;
    @Bind(R.id.tv_repodetail_issue)
    TextView issueTv;
    @Bind(R.id.fab_repodetail)
    FloatingActionButton fab;
    @Bind(R.id.iv_repodetail_logo)
    ImageView small;
    @Bind(R.id.tv_repodetail_repo)
    TextView repoNameTv;
    @Bind(R.id.tv_repodetail_owner)
    TextView repoOwnerTv;


    private RepoDetailPresenter presenter;
    private boolean isStar;
    private String login, repoName;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repodetail);
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setDisplayShowTitleEnabled(false);
        bar.setHomeButtonEnabled(true);

        Bundle bundle = getIntent().getExtras();
        final Repo repo = (Repo) bundle.get("repo");
        String[] names = repo.getFull_name().split("/");
        login = names[0];
        repoName = names[1];


        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(repoName);
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }

            }
        });


        repoNameTv.setText(repoName);
        SpannableString spannableString = new SpannableString(login);
        spannableString.setSpan(new UnderlineSpan(), 0, login.length(), 0);
        repoOwnerTv.setText(spannableString);
        starTv.setText(String.valueOf(repo.getStargazers_count()));
        forkTv.setText(String.valueOf(repo.getForks()));
        issueTv.setText(String.valueOf(repo.getOpen_issues()));


        presenter = new RepoDetailPresenter();
        presenter.attachView(this);
        presenter.getReadMeUrl(login, repoName);
        presenter.getAvatarUrl(login);
        presenter.isStar(login, repoName);

        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                webViewProgressBar.setVisibility(View.INVISIBLE);
            }
        });


    }

    @Override
    public void showHtml(String url) {
        webView.loadUrl(url);
//        webView.loadDataWithBaseURL(null, url, "text/html", "UTF-8", null);
    }

    @OnClick(R.id.fab_repodetail)
    public void fabClick() {
        if (isStar) {
            presenter.unStar(login, repoName);
        } else
            presenter.star(login, repoName);

    }

    @OnClick(R.id.tv_repodetail_owner)
    public void ownerTvClick() {
        Bundle bundle = new Bundle();
        bundle.putString(UserActivity.EXTRA_OWNER, login);
        switchActivity(UserActivity.class, bundle);
    }

    @Override
    public void showHeaderImage(String url) {
        Picasso.with(this)
                .load(url)
                .fit()
                .centerCrop()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Bitmap bitmap = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
                                Palette p = Palette.generate(bitmap);
                                Palette.Swatch swatch = p.getVibrantSwatch();
                                Palette.Swatch mutedSwatch = p.getMutedSwatch();
                                if (swatch != null) {
                                    collapsingToolbarLayout.setContentScrimColor(swatch.getRgb());
                                } else if (mutedSwatch != null) {
                                    collapsingToolbarLayout.setContentScrimColor(mutedSwatch.getRgb());
                                }

                            }
                        }, 100);

                    }

                    @Override
                    public void onError() {

                    }
                });

        Picasso.with(this)
                .load(url)
                .fit()
                .centerCrop()
                .into(small);
    }

    @Override
    public void showStarState(boolean isStar) {
        L.ii("isStar:" + isStar);
        this.isStar = isStar;
        if (isStar) {
            fab.setImageDrawable(getResources().getDrawable(R.drawable.star));
        } else
            fab.setImageDrawable(getResources().getDrawable(R.drawable.un_star));
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
