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

package com.liuwuping.sm;

import android.app.Application;

import com.liuwuping.sm.data.local.SharedPrefManager;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Author:liuwuping
 * Date: 16/4/21
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class SmApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        SharedPrefManager.init(this);
        //realm
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this).build();
        Realm.setDefaultConfiguration(realmConfiguration);
    }
}
