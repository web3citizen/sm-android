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

import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuwuping.sm.Constants;
import com.liuwuping.sm.R;
import com.liuwuping.sm.data.local.SharedPrefHelper;
import com.liuwuping.sm.model.User;
import com.liuwuping.sm.ui.base.BaseActivity;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**
 * Author:liuwuping
 * Date: 2016/6/3
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class UserActivity extends BaseActivity implements UserContract.View {

    public static final String EXTRA_OWNER = "owner";
    public static final String TAG_REPOS = "Repos";
    public static final String TAG_Followers = "Followers";
    public static final String TAG_Following = "Following";
    private String owner;
    private UserPresenter presenter;

    @Bind(R.id.tab_user)
    TabLayout tabLayout;
    @Bind(R.id.vp_user)
    ViewPager viewPager;
    @Bind(R.id.iv_user)
    ImageView avatar;
    @Bind(R.id.tv_user_login)
    TextView loginTv;
    @Bind(R.id.tv_user_name)
    TextView nameTv;
    @Bind(R.id.tv_user_location)
    TextView locationTv;
    @Bind(R.id.tv_user_email)
    TextView emailTv;
    @Bind(R.id.toolbar_user)
    Toolbar toolbar;
    @Bind(R.id.collapse_user)
    CollapsingToolbarLayout collapsingToolbarLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            owner = bundle.getString(EXTRA_OWNER);
        }
        if (TextUtils.isEmpty(owner)) {
            owner = SharedPrefHelper.getInstance().getStringValue(Constants.LOGIN);
        }
        initView();
        presenter = new UserPresenter();
        presenter.attachView(this);
        if (TextUtils.isEmpty(owner)) {
            presenter.loadLoginUser();
        } else
            presenter.loadUser(owner);

    }

    private void initView() {
        setSupportActionBar(toolbar);
        ActionBar bar = getSupportActionBar();
        bar.setDisplayHomeAsUpEnabled(true);
        bar.setDisplayShowHomeEnabled(true);
        bar.setHomeButtonEnabled(true);
        bar.setTitle(owner);

        collapsingToolbarLayout.setTitleEnabled(false);

        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new UserFragPagerAdapter(getSupportFragmentManager(), owner));
        tabLayout.setupWithViewPager(viewPager);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void showUserInfo(User user) {
        loginTv.setText(user.getLogin());
        nameTv.setText(user.getName());
        locationTv.setText(user.getLocation());
        emailTv.setText(user.getEmail());
        Picasso.with(this)
                .load(user.getAvatar_url())
                .fit()
                .into(avatar, new Callback() {
                    @Override
                    public void onSuccess() {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Bitmap bitmap = ((BitmapDrawable) avatar.getDrawable()).getBitmap();
                                Palette p = Palette.generate(bitmap);
                                Palette.Swatch swatch = p.getVibrantSwatch();
                                Palette.Swatch mutedSwatch = p.getMutedSwatch();
                                if (swatch != null) {
                                    collapsingToolbarLayout.setBackgroundColor(swatch.getRgb());
                                    collapsingToolbarLayout.setContentScrimColor(swatch.getRgb());
                                } else if (mutedSwatch != null) {
                                    collapsingToolbarLayout.setBackgroundColor(mutedSwatch.getRgb());
                                    collapsingToolbarLayout.setContentScrimColor(mutedSwatch.getRgb());
                                }

                            }
                        }, 100);
                    }

                    @Override
                    public void onError() {

                    }
                });

    }


    public class UserFragPagerAdapter extends FragmentPagerAdapter {
        private String owner;
        private final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[]{UserActivity.TAG_REPOS, UserActivity.TAG_Followers, UserActivity.TAG_Following};


        public UserFragPagerAdapter(FragmentManager fm, String owner) {
            super(fm);
            this.owner = owner;
        }

        @Override
        public Fragment getItem(int position) {
            return UserTabFragment.newInstance(owner, tabTitles[position]);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
