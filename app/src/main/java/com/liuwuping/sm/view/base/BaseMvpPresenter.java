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

package com.liuwuping.sm.view.base;

/**
 * Author:liuwuping
 * Date: 16/4/24
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class BaseMvpPresenter<T extends MvpView> implements MvpPresenter<T> {

    private T mvpView;

    @Override
    public void attachView(T view) {
        mvpView = view;
    }

    @Override
    public void detachView() {
        mvpView = null;
    }




    public boolean isViewAttached() {
        return mvpView != null;
    }

    public T getMvpView() {
        return mvpView;
    }

    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call MvpPresenter.attachView(MvpView) before" +
                    " requesting data to the MvpPresenter");
        }
    }
}