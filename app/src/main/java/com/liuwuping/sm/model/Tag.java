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

package com.liuwuping.sm.model;

import io.realm.RealmObject;

/**
 * Author:liuwuping
 * Date: 2016/4/27
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:标签
 */
/*
Realm realm = Realm.getDefaultInstance();
        GitHubService api = retrofit.create(GitHubService.class);
        Observable observable= realm.where(Person.class).isNotNull("username").findAllAsync().asObservable()
*/

public class Tag extends RealmObject{
    private String name;
    private String desc;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
