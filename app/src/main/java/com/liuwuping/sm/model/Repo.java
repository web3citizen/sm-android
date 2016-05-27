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

import java.io.Serializable;

/**
 * Author:liuwuping
 * Date: 2016/4/29
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class Repo implements Serializable{

    private String full_name;
    private String description;
    private String language;
    private int stargazers_count;
    private int forks;
    private int open_issues;


    public Repo() {
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getStargazers_count() {
        return stargazers_count;
    }

    public void setStargazers_count(int stargazers_count) {
        this.stargazers_count = stargazers_count;
    }


    public int getForks() {
        return forks;
    }

    public void setForks(int forks) {
        this.forks = forks;
    }


    public int getOpen_issues() {
        return open_issues;
    }

    public void setOpen_issues(int open_issues) {
        this.open_issues = open_issues;
    }
}
