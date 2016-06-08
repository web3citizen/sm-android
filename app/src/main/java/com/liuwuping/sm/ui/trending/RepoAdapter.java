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

package com.liuwuping.sm.ui.trending;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liuwuping.sm.R;
import com.liuwuping.sm.model.Repo;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Author:liuwuping
 * Date: 2016/4/29
 * Email:liuwuping1206@163.com|liuwuping1206@gmail.com
 * Description:
 */
public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.RepoItem> {

    private List<Repo> items;
    private boolean isDescShow = true;


    public RepoAdapter() {
        this(true);
    }

    public RepoAdapter(boolean isDesc) {
        items = new ArrayList<>();
        isDescShow = isDesc;
    }


    public void setItems(List<Repo> items) {
        this.items = items;
    }

    @Override
    public RepoItem onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_repo, parent, false);
        return new RepoItem(view);
    }

    @Override
    public void onBindViewHolder(RepoItem holder, int position) {
        Repo repo = items.get(position);
        holder.nameTv.setText(repo.getFull_name());
        holder.descTv.setText(repo.getDescription());
        holder.starsTv.setText(String.valueOf(repo.getStargazers_count()));
        holder.languageTv.setText(repo.getLanguage());
        if (!isDescShow) {
            holder.descTv.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public static class RepoItem extends RecyclerView.ViewHolder {

        @Bind(R.id.tv_repo_name)
        TextView nameTv;
        @Bind(R.id.tv_repo_desc)
        TextView descTv;
        @Bind(R.id.tv_repo_star)
        TextView starsTv;
        @Bind(R.id.tv_repo_language)
        TextView languageTv;


        public RepoItem(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


    }
}
