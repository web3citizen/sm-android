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

package com.liuwuping.sm.view.tags;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

import com.liuwuping.sm.R;
import com.liuwuping.sm.view.base.BaseFragment;

import butterknife.Bind;
import io.codetail.animation.SupportAnimator;
import io.codetail.animation.ViewAnimationUtils;

/**
 * Author:liuwuping
 * Date: 16/4/24
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:所有的tag列表
 */
public class TagFragment extends BaseFragment {

    private static final Interpolator INTERPOLATOR = new AccelerateDecelerateInterpolator();

    @Bind(R.id.view_addtag)
    View addTagView;

    public static TagFragment newInstance() {
        Bundle args = new Bundle();
        TagFragment fragment = new TagFragment();
        fragment.setArguments(args);
        return fragment;
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.frag_tags, container, false);
        setHasOptionsMenu(true);
        return root;
    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_tags, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_tags:
                toggle();
                break;
        }
        return super.onOptionsItemSelected(item);

    }


    public void toggle() {
        if (addTagView.getVisibility() != View.VISIBLE) {
            revealOn();
        } else {
            revealOff();
        }
    }

    private void revealOn() {
        int cx = addTagView.getRight();
        int cy = addTagView.getTop();
        float radius = (float) Math.hypot(addTagView.getWidth(), addTagView.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(addTagView, cx, cy, 0, radius);
        animator.setInterpolator(INTERPOLATOR);
        animator.setDuration(getResources().getInteger(R.integer.view_reveal_mills));
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
                addTagView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationEnd() {
                // Do nothing
            }

            @Override
            public void onAnimationCancel() {
                // Do nothing
            }

            @Override
            public void onAnimationRepeat() {
                // Do nothing
            }
        });

        animator.start();
    }


    public void revealOff() {
        int cx = addTagView.getRight();
        int cy = addTagView.getTop();
        float radius = (float) Math.hypot(addTagView.getWidth(), addTagView.getHeight());
        SupportAnimator animator = ViewAnimationUtils.createCircularReveal(addTagView, cx, cy, radius, 0);
        animator.setInterpolator(INTERPOLATOR);
        animator.setDuration(getResources().getInteger(R.integer.view_reveal_mills));
        animator.addListener(new SupportAnimator.AnimatorListener() {
            @Override
            public void onAnimationStart() {
            }

            @Override
            public void onAnimationEnd() {
                addTagView.setVisibility(View.INVISIBLE);

            }

            @Override
            public void onAnimationCancel() {
                // Do nothing
            }

            @Override
            public void onAnimationRepeat() {
            }
        });
        animator.start();
    }

}
