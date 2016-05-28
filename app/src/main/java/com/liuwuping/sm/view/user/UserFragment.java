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

package com.liuwuping.sm.view.user;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.graphics.Palette;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuwuping.sm.Constants;
import com.liuwuping.sm.R;
import com.liuwuping.sm.data.local.SharedPrefManager;
import com.liuwuping.sm.model.User;
import com.liuwuping.sm.view.base.BaseFragment;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.Bind;

/**
 * Author:liuwuping
 * Date: 2016/5/23
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class UserFragment extends BaseFragment implements UserContract.View {

    public static final String TAG_REPOS = "Repos";
    public static final String TAG_Followers = "Followers";
    public static final String TAG_Following = "Following";
    public static final String PARAM_USERNAME = "username";


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
    @Bind(R.id.collapse_user)
    CollapsingToolbarLayout collapsingToolbarLayout;


    private UserPresenter presenter;


    public static UserFragment newInstance() {

        Bundle args = new Bundle();

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        viewPager.setOffscreenPageLimit(2);
        viewPager.setAdapter(new UserFragPagerAdapter(getChildFragmentManager(), getActivity()));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    public void onResume() {
        super.onResume();
        presenter = new UserPresenter();
        presenter.attachView(this);
        presenter.loadLoginUser();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.frag_user;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    @Override
    public void showUserInfo(User user) {
        loginTv.setText(user.getLogin());
        nameTv.setText(user.getName());
        locationTv.setText(user.getLocation());
        emailTv.setText(user.getEmail());
        Picasso.with(getActivity())
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
        private final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[]{UserFragment.TAG_REPOS, UserFragment.TAG_Followers, UserFragment.TAG_Following};


        public UserFragPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            String login = SharedPrefManager.getInstance().getStringValue(Constants.LOGIN);
            return UserTabFragment.newInstance(login, tabTitles[position]);
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
