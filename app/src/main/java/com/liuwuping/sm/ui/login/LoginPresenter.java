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


import com.google.gson.JsonObject;
import com.liuwp.androidtoolkit.utils.L;
import com.liuwuping.sm.Constants;
import com.liuwuping.sm.data.DataManager;
import com.liuwuping.sm.data.local.SharedPrefManager;
import com.liuwuping.sm.ui.base.BasePresenter;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:liuwuping
 * Date: 16/4/25
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class LoginPresenter extends BasePresenter<LoginContract.View> implements LoginContract.Presenter {

    private Subscription subscription;

    public LoginPresenter() {
    }

    @Override
    public void attachView(LoginContract.View view) {
        super.attachView(view);
    }

    @Override
    public void detachView() {
        super.detachView();
        if (subscription != null)
            subscription.unsubscribe();
    }

    @Override
    public void auth(String clientId, String clientSecret, String code) {
        checkViewAttached();
        getMvpView().showLoadingDialog();
        subscription = DataManager.getAccessToken(clientId, clientSecret, code)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<JsonObject>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(JsonObject result) {
                        getMvpView().hideLoadingDialog();
                        String token = result.get("access_token").getAsString();
                        L.ii("access token:" + token);
                        SharedPrefManager.getInstance().putStringValue(Constants.ACCESS_TOKEN, token);
                        getMvpView().enterMain();
                    }
                });
    }
}
