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

package com.liuwuping.sm.ui.user;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.liuwp.androidtoolkit.recyclerview.itemdecoration.SimplePaddingDecoration;
import com.liuwp.androidtoolkit.utils.L;
import com.liuwuping.sm.R;
import com.liuwuping.sm.model.Repo;
import com.liuwuping.sm.model.User;
import com.liuwuping.sm.ui.base.BaseFragment;
import com.liuwuping.sm.ui.trending.RepoAdapter;

import java.util.List;

import butterknife.Bind;

/**
 * Author:liuwuping
 * Date: 2016/5/23
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class UserTabFragment extends BaseFragment implements UserTabContract.View {

    @Bind(R.id.rv_commom)
    RecyclerView recyclerView;

    private static final String PARAM_TYPE = "type";
    private static final String PARAM_LOGIN = "login";
    private String type, login;

    private RepoAdapter repoAdapter;
    private UserAdapter userAdapter;
    private UserTagPresenter presenter;


    public static UserTabFragment newInstance(String login, String type) {
        Bundle args = new Bundle();
        args.putString(PARAM_TYPE, type);
        args.putString(PARAM_LOGIN, login);
        UserTabFragment fragment = new UserTabFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        type = getArguments().getString(PARAM_TYPE);
        login = getArguments().getString(PARAM_LOGIN);
        L.ii("login:" + login + ",tab:" + type);
    }

    @Override
    protected void initView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getActivity()));
        SimplePaddingDecoration decoration = new SimplePaddingDecoration(this.getActivity(),
                R.dimen.dp16, R.dimen.dp16,
                R.dimen.dp10, 0);
        recyclerView.addItemDecoration(decoration);
        recyclerView.setHasFixedSize(true);
        if (type.equals(UserActivity.TAG_REPOS)) {
            repoAdapter = new RepoAdapter(false);
        } else {
            userAdapter = new UserAdapter(getActivity());
        }
        if (repoAdapter != null) {
            recyclerView.setAdapter(repoAdapter);
        }
        if (userAdapter != null) {
            recyclerView.setAdapter(userAdapter);
        }


        presenter = new UserTagPresenter();
        presenter.attachView(this);
        if (type.equals(UserActivity.TAG_REPOS)) {
            presenter.loadRepos(login);
        } else if (type.equals(UserActivity.TAG_Followers)) {
            presenter.loadFollowers(login);
        } else {
            presenter.loadFollowing(login);
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.frag_recycler;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    public void showUserList(List<User> users) {
        userAdapter.setItems(users);
        userAdapter.notifyDataSetChanged();

    }

    @Override
    public void showRepoList(List<Repo> repos) {
        repoAdapter.setItems(repos);
        repoAdapter.notifyDataSetChanged();

    }
}
