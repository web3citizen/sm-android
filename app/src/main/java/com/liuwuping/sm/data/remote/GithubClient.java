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
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Author:liuwuping
 * Date: 16/4/25
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class GithubClient {

    private static final String API_URL = "";

    private static volatile GithubClient instance;
    private final GithubApi githubApi;

    private GithubClient() {

        OkHttpClient client = new OkHttpClient();
//        client.setConnectTimeout(15, TimeUnit.SECONDS);
//        client.setReadTimeout(15, TimeUnit.SECONDS);
//        client.setWriteTimeout(15, TimeUnit.SECONDS);
        //auto login
        //logging
        //client
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        githubApi = retrofit.create(GithubApi.class);
    }


    public static GithubClient getInstance() {
        if (instance == null) {
            synchronized (GithubClient.class) {
                if (instance == null) {
                    instance = new GithubClient();
                }
            }
        }
        return instance;
    }


    public GithubApi get() {
        return githubApi;
    }


}
