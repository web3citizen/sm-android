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

package com.liuwuping.sm.data;

import com.google.gson.JsonObject;
import com.liuwuping.sm.data.remote.GithubClient;
import com.liuwuping.sm.model.Repo;

import java.util.List;

import io.realm.Realm;
import rx.Observable;

/**
 * Author:liuwuping
 * Date: 16/4/24
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class DataManager {

    public static Observable<JsonObject> login(String auth, JsonObject login) {
        return GithubClient.getInstance().get().login(auth, login);
    }


    public static Observable<List<Repo>> getUserStars() {
        return GithubClient.getInstance().get().getUserStars();
    }

    public static Observable<List<JsonObject>> getStarsByUser(String username) {
        return GithubClient.getInstance().get().getStarsByUser(username);
    }

    public static Observable<List<Repo>> getTrendingRepos(String language) {
        return GithubClient.getInstance().get().getTrendingRepos(language);
    }
}
