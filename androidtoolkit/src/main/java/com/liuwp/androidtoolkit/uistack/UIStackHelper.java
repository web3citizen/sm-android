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

package com.liuwp.androidtoolkit.uistack;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.liuwp.androidtoolkit.R;


/**
 * Author:liuwuping
 * Date: 2016/5/13
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:参考设计http://scotthurff.com/posts/why-your-user-interface-is-awkward-youre-ignoring-the-ui-stack
 */
public class UIStackHelper {

    private IViewHelper helper;


    public UIStackHelper(View view) {
        helper = new ViewHelper(view);
    }

    public void showErrorState() {

    }

    public void showBlankState() {

    }

    public void showLoadingState(String msg) {
        View layout = helper.inflate(R.layout.state_loading);
        if (!TextUtils.isEmpty(msg)) {
            TextView textView = (TextView) layout.findViewById(R.id.loading_msg);
            textView.setText(msg);
        }
        helper.showLayout(layout);
    }

    public void restore() {
        helper.restoreView();
    }
}