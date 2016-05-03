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

package com.liuwuping.sm.data.local;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Author:liuwuping
 * Date: 16/4/25
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class SharedPrefManager {
    private static final String PREF_NAME = "com.liuwp.sm";
    private static SharedPrefManager instance;
    private final SharedPreferences pref;


    private SharedPrefManager(Context context) {
        pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void init(Context context) {
        if (context == null) {
            instance = new SharedPrefManager(context);
        }
    }

    public static synchronized SharedPrefManager getInstance() {
        if (instance == null) {
            throw new IllegalStateException(
                    "SharedPrefManager is not initialized, call init(..) method first.");
        }
        return instance;
    }


    public void putStringValue(String key, String value) {
        pref.edit().putString(key, value).commit();
    }

    public String getStringValue(String key) {
        return pref.getString(key, "");
    }

    public void remove(String key) {
        pref.edit().remove(key).commit();
    }

    public boolean clear() {
        return pref.edit().clear().commit();
    }
}
