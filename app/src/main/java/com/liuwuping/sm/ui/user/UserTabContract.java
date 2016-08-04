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

package com.liuwuping.sm.ui.user;

import com.liuwuping.sm.model.Repo;
import com.liuwuping.sm.model.User;
import com.liuwuping.sm.ui.base.MvpView;

import java.util.List;

/**
 * Author:liuwuping
 * Date: 2016/5/24
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public interface UserTabContract {

    interface View extends MvpView {
        void showUserList(List<User> users);

        void showRepoList(List<Repo> repos);
    }

    interface Presenter {

        void loadRepos(String username);

        void loadFollowers(String username);

        void loadFollowing(String username);
    }
}
