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

package com.liuwuping.sm.view.stars;

import com.liuwuping.sm.data.DataManager;
import com.liuwuping.sm.model.Repo;
import com.liuwuping.sm.view.base.BasePresenter;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Author:liuwuping
 * Date: 2016/5/3
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class StarsPresenter extends BasePresenter<StarsContract.View> implements StarsContract.Presenter {

    private Subscription subscription;

    @Override
    public void detachView() {
        super.detachView();
        if (subscription != null)
            subscription.unsubscribe();
    }

    @Override
    public void loadRepos() {
        subscription = DataManager.getUserStars()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(new Subscriber<List<Repo>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(List<Repo> repos) {
                        getMvpView().showRepos(repos);
                    }
                });


    }
}
