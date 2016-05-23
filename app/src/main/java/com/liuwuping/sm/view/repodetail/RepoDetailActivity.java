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

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
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
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

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
    @Bind(R.id.pb_repodetail)
    ProgressBar progressBar;
    @Bind(R.id.pb_repodetail_wv)
    ProgressBar webViewProgressBar;
    @Bind(R.id.tv_repodetail_star)
    TextView starTv;
    @Bind(R.id.tv_repodetail_fork)
    TextView forkTv;

    private RepoDetailPresenter presenter;


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


        appBar.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            boolean isShow = false;
            int scrollRange = -1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                L.ii("滑动：" + verticalOffset);
                if (scrollRange == -1) {
                    scrollRange = appBarLayout.getTotalScrollRange();
                }
                if (scrollRange + verticalOffset == 0) {
                    collapsingToolbarLayout.setTitle(repo.getFull_name());
                    isShow = true;
                } else if (isShow) {
                    collapsingToolbarLayout.setTitle("");
                    isShow = false;
                }

            }
        });


        starTv.setText(String.valueOf(repo.getStargazers_count()));
        forkTv.setText(String.valueOf(repo.getForks()));

        String[] names = repo.getFull_name().split("/");
        presenter = new RepoDetailPresenter();
        presenter.attachView(this);
        presenter.getReadMeUrl(names[0], names[1]);
        presenter.getAvatarUrl(names[0]);

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

    }

    @Override
    public void showHeaderImage(String url) {
        progressBar.setVisibility(View.VISIBLE);
        Picasso.with(this)
                .load(url)
                .fit()
                .centerCrop()
                .into(imageView, new Callback() {
                    @Override
                    public void onSuccess() {
                        progressBar.setVisibility(View.INVISIBLE);
                    }

                    @Override
                    public void onError() {

                    }
                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
