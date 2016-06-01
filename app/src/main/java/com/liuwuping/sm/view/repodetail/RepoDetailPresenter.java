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

package com.liuwuping.sm.view.repodetail;

import android.text.TextUtils;

import com.google.gson.JsonObject;
import com.liuwp.androidtoolkit.utils.L;
import com.liuwuping.sm.data.DataManager;
import com.liuwuping.sm.model.User;
import com.liuwuping.sm.view.base.BasePresenter;
import com.liuwuping.sm.view.repodetail.RepoDetailContract.Presenter;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:liuwuping
 * Date: 2016/5/18
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class RepoDetailPresenter extends BasePresenter<RepoDetailContract.View> implements Presenter {
    private Subscription subscription, subscription2, subscription3;

    @Override
    public void detachView() {
        super.detachView();
        if (subscription != null) {
            subscription.unsubscribe();
        }
        if (subscription2 != null) {
            subscription2.unsubscribe();
        }
        if (subscription3 != null) {
            subscription3.unsubscribe();
        }
    }

    @Override
    public void getReadMeUrl(String owner, String repo) {
        checkViewAttached();
        subscription = DataManager.getReadme(owner, repo)
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
                        String htmlUrl = result.get("html_url").getAsString();
                        L.ii("html_url:" + htmlUrl);
                        getMvpView().showHtml(htmlUrl);
                    }
                });

    }

    @Override
    public void getAvatarUrl(String username) {
        checkViewAttached();
        subscription2 = DataManager.getUser(username)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<User>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(User user) {
                        getMvpView().showHeaderImage(user.getAvatar_url());
                    }
                });

    }

    @Override
    public void isStar(String owner, String repo) {
        checkViewAttached();
        subscription3 = DataManager.isStar(owner, repo)
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
                    public void onNext(JsonObject jsonObject) {
                        L.ii(jsonObject.toString());
                        String msg = jsonObject.get("message").getAsString();
                        if (TextUtils.isEmpty(msg)) {
                            getMvpView().showStarState(false);
                        } else
                            getMvpView().showStarState(true);

                    }
                });

    }

    @Override
    public void star(String owner, String repo) {

    }

    @Override
    public void unStar(String owner, String repo) {

    }
}
