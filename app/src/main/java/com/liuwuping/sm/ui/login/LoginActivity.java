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

package com.liuwuping.sm.ui.login;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.liuwp.androidtoolkit.utils.L;
import com.liuwuping.sm.Constants;
import com.liuwuping.sm.R;
import com.liuwuping.sm.data.local.SharedPrefHelper;
import com.liuwuping.sm.ui.base.BaseActivity;
import com.liuwuping.sm.ui.main.MainActivity;

import butterknife.Bind;

/**
 * Author:liuwuping
 * Date: 16/4/25
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class LoginActivity extends BaseActivity implements LoginContract.View {

    private static final String AUTH_TOKEN = "https://github.com/login/oauth/authorize";
    private static String CLIENT_ID = "6c93a6c4ecabd657cffb";
    private static String CLIENT_SECRET = "a79c132f78e0497bef72f2ba4b6bf2db40314344";
    private static String SCOPE = "user,repo,public_repo";


    @Bind(R.id.wv_login)
    WebView webView;
    @Bind(R.id.pb_login)
    ProgressBar progressBar;
    private LoginPresenter presenter;
    ProgressDialog loading = null;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        String accessToken = SharedPrefHelper.getInstance().getStringValue(Constants.ACCESS_TOKEN);
        if (!TextUtils.isEmpty(accessToken)) {
            enterMain();
            return;
        }
        presenter = new LoginPresenter();
        presenter.attachView(this);
        initWebView();
    }

    private void initWebView() {
        webView.getSettings().setJavaScriptEnabled(true);
        String url = AUTH_TOKEN + "?client_id=" + CLIENT_ID + "&scope=" + SCOPE;
        webView.setWebViewClient(new WebViewClient() {
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                L.ii("url:" + url);
                String fragment = "?code=";
                if (url.contains(fragment)) {
                    webView.stopLoading();
                    Uri uri = Uri.parse(url);
                    String code = uri.getQueryParameter("code");
                    L.ii("code:" + code);
                    presenter.auth(CLIENT_ID, CLIENT_SECRET, code);
                }
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });
        webView.loadUrl(url);
        webView.setBackgroundColor(Color.TRANSPARENT);
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
    public void enterMain() {
        switchActivity(MainActivity.class);
    }

    @Override
    public void showLoadingDialog() {
        loading = ProgressDialog.show(this, "", getResources().getString(R.string.loading));

    }

    @Override
    public void hideLoadingDialog() {
        loading.dismiss();
    }

}
