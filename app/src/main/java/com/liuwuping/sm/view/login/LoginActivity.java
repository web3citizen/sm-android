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

package com.liuwuping.sm.view.login;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.view.View;
import android.widget.Toast;

import com.liuwuping.sm.R;
import com.liuwuping.sm.view.base.BaseActivity;
import com.liuwuping.sm.view.main.MainActivity;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * Author:liuwuping
 * Date: 16/4/25
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    @Bind(R.id.til_login_name)
    TextInputLayout username;
    @Bind(R.id.til_login_pw)
    TextInputLayout pass;

    private LoginPresenter presenter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new LoginPresenter();
        presenter.attachView(this);
    }


    @OnClick(R.id.bt_login)
    public void OnClick(View view) {
        String name = username.getEditText().getText().toString();
        String pwd = pass.getEditText().getText().toString();
        switchActivity(MainActivity.class);
//        presenter.login(name, pwd);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    /***
     * MPV View interface implementation
     ***/

    @Override
    public void show(String size) {
        Toast.makeText(LoginActivity.this, size, Toast.LENGTH_SHORT).show();
        switchActivity(MainActivity.class);
    }


}
