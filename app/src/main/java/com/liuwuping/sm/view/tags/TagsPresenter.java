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

import com.liuwuping.sm.model.Tag;
import com.liuwuping.sm.view.base.BasePresenter;


import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;

/**
 * Author:liuwuping
 * Date: 2016/5/3
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class TagsPresenter extends BasePresenter<TagsContract.View> implements TagsContract.Presenter {

    private Realm realm;
    private Subscription subscription;


    @Override
    public void attachView(TagsContract.View view) {
        super.attachView(view);
        if (realm == null)
            realm = Realm.getDefaultInstance();
    }

    @Override
    public void detachView() {
        super.detachView();
        if (realm != null) {
            realm.close();
        }
        if (subscription != null) {
            subscription.unsubscribe();
        }
    }

    @Override
    public void loadTags() {
        getMvpView().showLoading("");
        subscription = realm.where(Tag.class).findAllAsync().asObservable()
                .map(new Func1<RealmResults<Tag>, List<Tag>>() {
                    @Override
                    public List<Tag> call(RealmResults<Tag> tags) {
                        final List<Tag> tagList = new ArrayList<>(tags.size());
                        for (Tag tag : tags) {
                            tagList.add(tag);
                        }
                        return tagList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Tag>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(List<Tag> tags) {
                        getMvpView().hideLoading();
                        getMvpView().showTags(tags);

                    }
                });
    }

    @Override
    public void saveTag(Tag tag) {
        realm.beginTransaction();
        realm.copyToRealm(tag);
        realm.commitTransaction();
        getMvpView().toggleAddTagView();
    }
}
