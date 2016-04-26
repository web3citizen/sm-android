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
import retrofit2.http.POST;
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
  /*  {
        "client_id":"6c93a6c4ecabd657cffb",
            "client_secret":"a79c132f78e0497bef72f2ba4b6bf2db40314344",
            "note":"Sm Android App",
            "scopes":["user","repo","public_repo"]
    }*/
/*  {
      "id": 31278267,
          "url": "https://api.github.com/authorizations/31278267",
          "app": {
      "name": "sm",
              "url": "https://github.com/thinkSky1206/sm-android",
              "client_id": "6c93a6c4ecabd657cffb"
  },
      "token": "7ebeb15885b63deca815febf56fa62530e27a829",
          "hashed_token": "8e9dd11423e1752eedd2abb3d0bc8946211baa2a960cca0fcdec87dc68fabf9e",
          "token_last_eight": "0e27a829",
          "note": "Sm Android App",
          "note_url": null,
          "created_at": "2016-04-26T06:08:44Z",
          "updated_at": "2016-04-26T06:08:44Z",
          "scopes": [
      "user",
              "repo"
      ],
      "fingerprint": null
  }*/
    @POST("/authorizations")
    Observable<JsonObject> login(@Body JsonObject request);


    @POST("/user")
    Observable<JsonObject> getUserInfo(@Header("Authorization") String authorization);


    //auth=token 6fcc300a8d89e373754c8395a514f1846e2f029b
    @GET("/user/starred")
    Observable<List<JsonObject>> getUserStars(@Header("Authorization") String authorization);








}
