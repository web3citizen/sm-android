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

package com.liuwp.androidtoolkit.recyclerview.itemdecoration;

import android.content.Context;
import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;


/**
 * Author:  liuwp
 * Email:   liuwuping1206@163.com
 * Date:    2016/2/23
 * Description:类似margin效果
 */
public class SimplePaddingDecoration extends RecyclerView.ItemDecoration {

    private int left, right, top, bottom = 0;


    public SimplePaddingDecoration(Context context, int leftDimen, int rightDimen, int topDimen, int bottomDimen) {
        if (leftDimen > 0) {
            left = context.getResources().getDimensionPixelSize(leftDimen);
        }
        if (rightDimen > 0) {
            right = context.getResources().getDimensionPixelSize(rightDimen);
        }
        if (topDimen > 0) {
            top = context.getResources().getDimensionPixelSize(topDimen);
        }
        if (bottomDimen > 0) {
            bottom = context.getResources().getDimensionPixelSize(bottomDimen);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = left;
        outRect.right = right;
        outRect.top = top;
        outRect.bottom = bottom;
    }
}
