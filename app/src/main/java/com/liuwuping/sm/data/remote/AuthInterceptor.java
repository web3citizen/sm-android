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

package com.liuwuping.sm.data.remote;

import android.text.TextUtils;

import com.liuwuping.sm.Constants;
import com.liuwuping.sm.data.local.SharedPrefManager;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Author:liuwuping
 * Date: 16/4/25
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class AuthInterceptor implements Interceptor {

    private String token = "aa5d43b66a41e59927978257a5e68f8851acf080";

    public AuthInterceptor() {
//        token= SharedPrefManager.getInstance().getStringValue(Constants.SHARED_TOKEN);
        token="aa5d43b66a41e59927978257a5e68f8851acf080";
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        if (!TextUtils.isEmpty(token)) {
            Request.Builder requestBuilder = original.newBuilder()
                    .header("Authorization", String.format("token %s", token))
                    .method(original.method(), original.body());
            Request request = requestBuilder.build();
            return chain.proceed(request);
        }
        return chain.proceed(original);
    }

}
