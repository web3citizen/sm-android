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

package com.liuwuping.sm.ui.repodetail;

import com.liuwuping.sm.ui.base.MvpView;

/**
 * Author:liuwuping
 * Date: 2016/5/18
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public interface RepoDetailContract {

    interface View extends MvpView {
        void showHtml(String url);

        void showHeaderImage(String url);

        void showStarState(boolean isStar);
    }

    interface Presenter {

        void getReadMeUrl(String owner, String repo);

        void getAvatarUrl(String username);

        void isStar(String owner, String repo);

        void star(String owner, String repo);

        void unStar(String owner, String repo);
    }
}
