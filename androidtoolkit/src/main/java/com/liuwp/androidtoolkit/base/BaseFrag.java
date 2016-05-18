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

package com.liuwp.androidtoolkit.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.liuwp.androidtoolkit.R;
import com.liuwp.androidtoolkit.uistack.UIStackHelper;

import butterknife.ButterKnife;

/**
 * Author:liuwuping
 * Date: 2016/5/13
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public abstract class BaseFrag extends Fragment {
    private UIStackHelper uiStackHelper;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutId() != 0) {
            return inflater.inflate(getContentViewLayoutId(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        if (null != getLoadingTargetView()) {
            uiStackHelper = new UIStackHelper(getLoadingTargetView());
        }
        initView();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }

    protected abstract void initView();

    protected abstract int getContentViewLayoutId();

    protected abstract View getLoadingTargetView();


    protected void toggleShowLoading(boolean toggle, String msg) {
        if (null == uiStackHelper) {
            throw new IllegalArgumentException("You must return a right target view for loading");
        }
        if (toggle) {
            uiStackHelper.showLoadingState(msg);
        } else {
            uiStackHelper.restore();
        }
    }

    protected void switchActivity(Class<?> nextClass) {
        Intent it = new Intent(getActivity(), nextClass);
        startActivity(it);
    }

    protected void switchActivity(Class<?> nextClass, Bundle bundle) {
        Intent it = new Intent(getActivity(), nextClass);
        if (null != bundle) {
            it.putExtras(bundle);
        }
        startActivity(it);
    }


}
