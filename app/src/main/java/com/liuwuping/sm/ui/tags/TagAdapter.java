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

package com.liuwuping.sm.ui.tags;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuwuping.sm.R;
import com.liuwuping.sm.model.Tag;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:liuwuping
 * Date: 2016/4/28
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class TagAdapter extends RecyclerView.Adapter<TagAdapter.ItemView> {
    private List<Tag> items;


    public TagAdapter() {
        this.items=new ArrayList<>();
    }

    public void setItems(List<Tag> items) {
        this.items = items;
    }

    @Override
    public ItemView onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_tags, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(ItemView holder, int position) {
        Tag data = items.get(position);
        holder.nameTv.setText(data.getName());
        holder.descTv.setText(data.getDesc());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class ItemView extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_tags_item_name)
        TextView nameTv;
        @Bind(R.id.tv_tags_item_desc)
        TextView descTv;

        public ItemView(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }


    }

}
