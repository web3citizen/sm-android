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
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.liuwuping.sm.R;
import com.liuwuping.sm.view.base.BaseFragment;
import com.liuwuping.sm.view.trending.TrendingTabFragment;

import butterknife.Bind;

/**
 * Author:liuwuping
 * Date: 2016/5/23
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class UserFragment extends BaseFragment {


    @Bind(R.id.tab_user)
    TabLayout tabLayout;
    @Bind(R.id.vp_user)
    ViewPager viewPager;

    public static UserFragment newInstance() {

        Bundle args = new Bundle();

        UserFragment fragment = new UserFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void initView() {
        viewPager.setAdapter(new UserFragPagerAdapter(getChildFragmentManager(), getActivity()));
        tabLayout.setupWithViewPager(viewPager);

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.frag_user;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }

    public class UserFragPagerAdapter extends FragmentPagerAdapter {
        private final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[]{"repos", "followers", "following"};
        private Context context;


        public UserFragPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
          /*  String title = tabTitles[position];
            String param = Character.toLowerCase(title.charAt(0)) + title.substring(1);*/
            return TrendingTabFragment.newInstance(tabTitles[position]);
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
