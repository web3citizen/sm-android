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
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;

import com.liuwuping.sm.R;
import com.liuwuping.sm.model.Repo;
import com.liuwuping.sm.view.base.BaseActivity;

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
        Repo repo = (Repo) bundle.get("repo");

        collapsingToolbarLayout.setTitle(repo.getFull_name());

        String[] names = repo.getFull_name().split("/");
        presenter = new RepoDetailPresenter();
        presenter.attachView(this);
        presenter.getReadMeUrl(names[0], names[1]);

        webView.getSettings().setJavaScriptEnabled(true);


    }

    @Override
    public void loadUrl(String url) {
        webView.loadUrl(url);

    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
