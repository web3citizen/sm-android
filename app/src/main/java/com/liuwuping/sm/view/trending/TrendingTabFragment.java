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

package com.liuwuping.sm.view.trending;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuwuping.sm.R;
import com.liuwuping.sm.model.Repo;
import com.liuwuping.sm.view.base.BaseFragment;
import com.liuwuping.sm.widget.SimplePaddingDecoration;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:liuwuping
 * Date: 2016/4/29
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class TrendingTabFragment extends BaseFragment implements TrendingTabContract.View {

    @Bind(R.id.rv_trengdingtab)
    RecyclerView recyclerView;


    private static final String LANGUAGE = "language";
    private String language;
    private RepoAdapter adapter;
    private TrendingTabPresenter presenter;

    public static TrendingTabFragment newInstance(String language) {
        Bundle args = new Bundle();
        args.putString(LANGUAGE, language);
        TrendingTabFragment fragment = new TrendingTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        language = getArguments().getString(LANGUAGE);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new TrendingTabPresenter();
        presenter.attachView(this);
        presenter.loadRepos(language);
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        SimplePaddingDecoration decoration = new SimplePaddingDecoration(this.getActivity(),
                R.dimen.dp16, R.dimen.dp16,
                R.dimen.dp10, 0);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setHasFixedSize(true);
        adapter = new RepoAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.frag_trending_tab;
    }

    @Override
    protected View getLoadingTargetView() {
        return recyclerView;
    }


    @Override
    public void showRepos(List<Repo> repos) {
        adapter.setItems(repos);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        presenter.detachView();
    }

}
