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

package com.liuwuping.sm.view.about;

import android.view.View;
import android.widget.TextView;

import com.liuwuping.sm.BuildConfig;
import com.liuwuping.sm.R;
import com.liuwuping.sm.view.base.BaseFragment;

import butterknife.Bind;

/**
 * Author:liuwuping
 * Date: 2016/5/23
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class AboutFragment extends BaseFragment {

    @Bind(R.id.tv_about)
    TextView versionTv;


    public static AboutFragment newInstance() {
        AboutFragment fragment = new AboutFragment();
        return fragment;
    }


    @Override
    protected void initView() {
        versionTv.setText(BuildConfig.VERSION_NAME);

    }

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.frag_about;
    }

    @Override
    protected View getLoadingTargetView() {
        return null;
    }
}