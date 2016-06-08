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

package com.liuwuping.sm.ui.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.liuwuping.sm.R;
import com.liuwuping.sm.model.User;
import com.liuwuping.sm.util.CircleTransform;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:liuwuping
 * Date: 2016/5/25
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserItem> {

    private Context context;
    private List<User> items;

    public UserAdapter(Context context) {
        items = new ArrayList<>();
        this.context = context;
    }


    public void setItems(List<User> items) {
        this.items = items;
    }

    @Override
    public UserItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new UserItem(view);
    }

    @Override
    public void onBindViewHolder(UserItem holder, int position) {
        User user = items.get(position);
        holder.nameTv.setText(user.getLogin());
        Picasso.with(context)
                .load(user.getAvatar_url())
                .transform(new CircleTransform())
                .into(holder.imageView);

    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class UserItem extends RecyclerView.ViewHolder {

        @Bind(R.id.iv_item_user)
        ImageView imageView;
        @Bind(R.id.tv_item_user)
        TextView nameTv;


        public UserItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
