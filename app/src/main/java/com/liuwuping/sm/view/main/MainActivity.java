

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

package com.liuwuping.sm.view.main;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.liuwuping.sm.R;
import com.liuwuping.sm.util.CircleTransform;
import com.liuwuping.sm.view.about.AboutFragment;
import com.liuwuping.sm.view.base.BaseActivity;
import com.liuwuping.sm.view.tags.TagsFragment;
import com.liuwuping.sm.view.stars.StarsFragment;
import com.liuwuping.sm.view.trending.TrendingFragment;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainContract.View {

    private static final long DRAWER_CLOSE_DELAY_MILLS = 300L;
    private static final int INDEX_TRENDING = 1;
    private static final int INDEX_STAR = 2;
    private static final int INDEX_TAG = 3;
    private static final int INDEX_USER = 4;
    private static final int INDEX_ABOUT = 5;
    private static final String LAST_INDEX = "lastIndex";

    private MainPresenter presenter;
    private TrendingFragment trendingFragment;
    private StarsFragment starsFragment;
    private TagsFragment tagsFragment;
    private AboutFragment aboutFragment;


    @Bind(R.id.toolbar_main)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    private ImageView imageView;
    private int lastIndex = 1;
    private int lastTitle = R.string.nav_trending;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(R.string.nav_trending);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        imageView = (ImageView) navigationView.findViewById(R.id.iv_navheader);

        presenter = new MainPresenter();
        presenter.attachView(this);
        presenter.loadAvatar();


        if (savedInstanceState != null) {
            trendingFragment = (TrendingFragment) getSupportFragmentManager().findFragmentByTag(trendingFragment.getClass().getSimpleName());
            tagsFragment = (TagsFragment) getSupportFragmentManager().findFragmentByTag(tagsFragment.getClass().getSimpleName());
            starsFragment = (StarsFragment) getSupportFragmentManager().findFragmentByTag(starsFragment.getClass().getSimpleName());
            aboutFragment = (AboutFragment) getSupportFragmentManager().findFragmentByTag(aboutFragment.getClass().getSimpleName());
            lastIndex = savedInstanceState.getInt(LAST_INDEX);
            showFragment(lastIndex);
        } else {
            navigationView.setCheckedItem(R.id.nav_trending);
            showFragment(lastIndex);
        }
//        ActivityUtils.replaceFragment(getSupportFragmentManager(), TrendingFragment.newInstance(), R.id.content_main);

    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(LAST_INDEX, lastIndex);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        int id = item.getItemId();
        if (id == R.id.nav_trending) {
            lastTitle = R.string.nav_trending;
            lastIndex = INDEX_TRENDING;
        } else if (id == R.id.nav_star) {
            lastTitle = R.string.nav_untag;
            lastIndex = INDEX_STAR;
        } else if (id == R.id.nav_tag) {
            lastTitle = R.string.nav_tag;
            lastIndex = INDEX_TAG;
        } else if (id == R.id.nav_me) {
            lastTitle = R.string.nav_me;
            lastIndex = INDEX_USER;
        } else if (id == R.id.nav_about) {
            lastTitle = R.string.nav_about;
            lastIndex = INDEX_ABOUT;
        }
        navigationView.setCheckedItem(id);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(lastTitle);
                showFragment(lastIndex);
            }
        }, DRAWER_CLOSE_DELAY_MILLS);
        return true;
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @Override
    public void showUserAvatar(String imageUrl) {
        Picasso.with(this)
                .load(imageUrl)
                .transform(new CircleTransform())
                .into(imageView);
    }

    /**
     * 显示Fragment
     *
     * @param position
     */
    private void showFragment(int position) {
        Class target = TrendingFragment.class;
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.setCustomAnimations(R.anim.fragment_fade_enter, R.anim.fragment_fade_exit);
        switch (position) {
            case INDEX_TRENDING:
                target = TrendingFragment.class;
                if (trendingFragment == null) {
                    trendingFragment = TrendingFragment.newInstance();
                    ft.add(R.id.container_main, trendingFragment, trendingFragment.getClass().getSimpleName());
                }
                break;
            case INDEX_STAR:
                target = StarsFragment.class;
                if (starsFragment == null) {
                    starsFragment = StarsFragment.newInstance().newInstance();
                    ft.add(R.id.container_main, starsFragment, starsFragment.getClass().getSimpleName());
                }
                break;
            case INDEX_TAG:
                target = TagsFragment.class;
                if (tagsFragment == null) {
                    tagsFragment = TagsFragment.newInstance();
                    ft.add(R.id.container_main, tagsFragment, tagsFragment.getClass().getSimpleName());
                }
                break;
           /* case INDEX_USER:
                target = UserFragment.class;
                if (userFragment == null) {
                    userFragment = UserFragment.newInstance();
                    ft.add(R.id.container_main, userFragment, userFragment.getClass().getSimpleName());
                }
                break;*/
            case INDEX_ABOUT:
                target = AboutFragment.class;
                if (aboutFragment == null) {
                    aboutFragment = AboutFragment.newInstance();
                    ft.add(R.id.container_main, aboutFragment, aboutFragment.getClass().getSimpleName());
                }
                break;
        }
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null && fragments.size() > 0) {
            for (Fragment fragment : fragments) {
                if (target.isInstance(fragment)) {
                    ft.show(fragment);
                } else {
                    ft.hide(fragment);
                }
            }
        }
        ft.commit();
    }

}
