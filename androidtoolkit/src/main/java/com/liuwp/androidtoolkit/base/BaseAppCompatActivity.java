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
import android.support.annotation.LayoutRes;
import android.support.v7.app.AppCompatActivity;

import com.liuwp.androidtoolkit.R;

import butterknife.ButterKnife;

/**
 * Author:liuwuping
 * Date: 2016/5/13
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public abstract class BaseAppCompatActivity extends AppCompatActivity {

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
    }


    protected void switchActivity(Class<?> nextClass) {
        Intent it = new Intent(this, nextClass);
        startActivity(it);
        overridePendingTransition(R.anim.activity_right_enter, R.anim.activity_left_exit);
    }

    protected void switchThenFinish(Class<?> nextClass) {
        Intent it = new Intent(this, nextClass);
        startActivity(it);
        this.finish();
        overridePendingTransition(R.anim.activity_right_enter, R.anim.activity_left_exit);
    }

    protected void switchActivity(Intent it) {
        startActivity(it);
        overridePendingTransition(R.anim.activity_right_enter, R.anim.activity_left_exit);
    }
}
