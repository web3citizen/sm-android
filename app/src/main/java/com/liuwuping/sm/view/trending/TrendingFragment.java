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
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuwuping.sm.R;
import com.liuwuping.sm.view.base.BaseFragment;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:liuwuping
 * Date: 16/4/24
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class TrendingFragment extends BaseFragment {

    @Bind(R.id.tab_trending)
    TabLayout tabLayout;
    @Bind(R.id.vp_trending)
    ViewPager viewPager;


    public static TrendingFragment newInstance() {
        Bundle args = new Bundle();
        TrendingFragment fragment = new TrendingFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_trending, container, false);
        ButterKnife.bind(this, root);
        viewPager.setAdapter(new TrendingFragPagerAdapter(getFragmentManager(), getActivity()));
        tabLayout.setupWithViewPager(viewPager);
        return root;
    }

    public class TrendingFragPagerAdapter extends FragmentPagerAdapter {
        private final int PAGE_COUNT = 3;
        private String tabTitles[] = new String[]{"all", "java", "swift"};
        private Context context;


        public TrendingFragPagerAdapter(FragmentManager fm, Context context) {
            super(fm);
            this.context = context;
        }

        @Override
        public Fragment getItem(int position) {
            Log.e("frag pos:", tabTitles[position]);
            return TrendingTabFragment.newInstance(tabTitles[position]);
        }

        @Override
        public int getCount() {
            return PAGE_COUNT;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            Log.e("title pos:", tabTitles[position]);
            return tabTitles[position];
        }
    }


}
