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


import com.liuwuping.sm.view.base.BaseMvpPresenter;

import rx.Subscription;

/**
 * Author:liuwuping
 * Date: 16/4/25
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class LoginPresenter extends BaseMvpPresenter<LoginContract.View> implements LoginContract.Presenter {

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
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void login(String username, String password) {
        checkViewAttached();
    /*    String credentials = username + ":" + password;
        String basic = "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

        JsonArray scopes = new JsonArray();
        scopes.add("user");
        scopes.add("repo");
        scopes.add("public_repo");

        JsonObject request = new JsonObject();
        request.addProperty("note", "Sm Android App 12");
        request.add("scopes", scopes);
        subscription = DataManager.login(basic, request)
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
                        String token = result.get("token").getAsString();
                        Log.e("token", "获取toke:" + token);
                        SharedPrefManager.getInstance().putStringValue(Constants.SHARED_TOKEN, token);
                        getMvpView().show(token);
                    }
                });*/
//        getMvpView().show("hehe");
    }
}
