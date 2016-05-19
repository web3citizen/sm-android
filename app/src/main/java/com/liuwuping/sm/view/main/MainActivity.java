

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
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;

import com.liuwuping.sm.R;
import com.liuwuping.sm.util.ActivityUtils;
import com.liuwuping.sm.view.base.BaseActivity;
import com.liuwuping.sm.view.tags.TagsFragment;
import com.liuwuping.sm.view.stars.StarsFragment;
import com.liuwuping.sm.view.trending.TrendingFragment;

import butterknife.Bind;


public class MainActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private static final long DRAWER_CLOSE_DELAY_MILLS = 300L;


    @Bind(R.id.toolbar_main)
    Toolbar toolbar;
    @Bind(R.id.drawer_layout)
    DrawerLayout drawer;
    @Bind(R.id.nav_view)
    NavigationView navigationView;

    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        imageView = (ImageView) navigationView.findViewById(R.id.iv_navheader);


        toolbar.setTitle(R.string.nav_trending);
        ActivityUtils.replaceFragment(getSupportFragmentManager(), TrendingFragment.newInstance(), R.id.content_main);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        drawer.closeDrawer(GravityCompat.START);
        int id = item.getItemId();
        int title = 0;
        Fragment fragment = null;
        if (id == R.id.nav_trending) {
            title = R.string.nav_trending;
            fragment = TrendingFragment.newInstance();
        } else if (id == R.id.nav_untag) {
            title = R.string.nav_untag;
            fragment = StarsFragment.newInstance();
        } else if (id == R.id.nav_tag) {
            title = R.string.nav_tag;
            fragment = TagsFragment.newInstance();
        }
        final int finalTitle = title;
        final Fragment finalFragment = fragment;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                toolbar.setTitle(finalTitle);
                ActivityUtils.replaceFragment(getSupportFragmentManager(), finalFragment, R.id.content_main);
            }
        }, DRAWER_CLOSE_DELAY_MILLS);
        return true;
    }
}
