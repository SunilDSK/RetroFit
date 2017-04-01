package com.sunilkumar.android.retrofit;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Sunilkumar on 01-04-2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.MyViewholder>{

    private List<User.ItemsBean> itemsBeanList;

    public UserAdapter(List<User.ItemsBean> itemsBeanList) {
        this.itemsBeanList = itemsBeanList;
    }

    @Override
    public MyViewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.each_row_layout,parent,false);
        return new MyViewholder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewholder holder, int position) {

        holder.title.setText(itemsBeanList.get(position).getAvatar_url());
        holder.score.setText(itemsBeanList.get(position).getScore()+"");

    }

    @Override
    public int getItemCount() {
        return itemsBeanList.size();
    }

    public class MyViewholder extends RecyclerView.ViewHolder{

        public TextView title,score;

        public MyViewholder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            score = (TextView) itemView.findViewById(R.id.count);
        }
    }
}
