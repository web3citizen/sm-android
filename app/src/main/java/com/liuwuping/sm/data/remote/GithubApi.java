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

import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Author:liuwuping
 * Date: 16/4/24
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public interface GithubApi {

    public static final String CLIENT_ID="";
    public static final String CLIENT_SECRET="";

//    https://api.github.com/authorizations

    @POST("/authorizations")
    Observable<JsonObject> login(@Body JsonObject request);


    @POST("/user")
    Observable<JsonObject> getUserInfo(@Header("Authorization") String authorization);


    //auth=token 6fcc300a8d89e373754c8395a514f1846e2f029b
    @GET("/user/starred")
    Observable<List<JsonObject>> getUserStars(@Header("Authorization") String authorization);

//    /users/:username/starred

    @GET("/users/{username}/starred")
    Observable<List<JsonObject>> getStarsByUser(@Path("username") String username);








}
