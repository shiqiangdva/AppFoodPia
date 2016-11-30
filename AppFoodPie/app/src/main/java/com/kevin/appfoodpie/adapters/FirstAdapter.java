package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.beans.FirstBean;
import com.kevin.appfoodpie.values.MyListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by dllo on 16/11/24.
 */

public class FirstAdapter extends RecyclerView.Adapter{

//    private FirstBean firstBean;
    private Context context;
    public static final int TYPE_ONE = 1;
    public static final int TYPE_TWO = 2;
    private List<FirstBean.FeedsBean> data;
    private MyListener myListener;

    public void setMyListener(MyListener myListener) {
        this.myListener = myListener;
    }

    public FirstAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<FirstBean.FeedsBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clean(){
        data.clear();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getContent_type() == 6){
            return TYPE_ONE;
        }else{
            return TYPE_TWO;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder holder = null;
        switch (viewType){
            case TYPE_ONE:
                View viewOne = LayoutInflater.from(context).inflate(R.layout.item_first_fragment_two,parent,false);
                holder = new MyOneViewHolder(viewOne);
                break;
            case TYPE_TWO:
                View viewTwo = LayoutInflater.from(context).inflate(R.layout.item_first_fragment,parent,false);
                holder = new MyTwoViewHolder(viewTwo);
                break;
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        int type = getItemViewType(position);
        switch (type){
            case TYPE_ONE:
                MyOneViewHolder myOneViewHolder = (MyOneViewHolder) holder;
                Picasso.with(context).load(data.get(position).getCard_image()).into(myOneViewHolder.img_itemOne);
                break;
            case TYPE_TWO:
                MyTwoViewHolder myTwoViewHolder = (MyTwoViewHolder) holder;
                Picasso.with(context).load(data.get(position).getCard_image()).into(myTwoViewHolder.img_big);
                myTwoViewHolder.tv_title.setText(data.get(position).getTitle());
                myTwoViewHolder.tv_content.setText(data.get(position).getDescription());
                Picasso.with(context).load(data.get(position).getPublisher_avatar()).into(myTwoViewHolder.img_small);
                myTwoViewHolder.tv_name.setText(data.get(position).getPublisher());
                myTwoViewHolder.tv_num.setText(data.get(position).getLike_ct() +"");
                break;
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myListener.FirstClick(data.get(position).getLink(),data.get(position).getCard_image(),
                        data.get(position).getTitle(),data.get(position).getPublisher(),
                        data.get(position).getPublisher_avatar(),data.get(position).getDescription(),
                        data.get(position).getLike_ct(),data.get(position).getContent_type());
            }
        });

    }

    @Override
    public int getItemCount() {
        return data.size() != 0 ? data.size() : 0;
    }

    class MyOneViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_itemOne;
        public MyOneViewHolder(View itemView) {
            super(itemView);
            img_itemOne = (ImageView) itemView.findViewById(R.id.item_first_fragment_two_iv);
        }
    }
    class MyTwoViewHolder extends RecyclerView.ViewHolder{
        private ImageView img_big;
        private TextView tv_title;
        private TextView tv_content;
        private ImageView img_small;
        private TextView tv_name;
        private TextView tv_num;
        public MyTwoViewHolder(View itemView) {
            super(itemView);
            img_big = (ImageView) itemView.findViewById(R.id.item_first_fragment_iv_big);
            tv_title = (TextView) itemView.findViewById(R.id.item_first_fragment_tv_title);
            tv_content = (TextView) itemView.findViewById(R.id.item_first_fragment_tv_content);
            img_small = (ImageView) itemView.findViewById(R.id.item_first_fragment_iv_small);
            tv_name = (TextView) itemView.findViewById(R.id.item_first_fragment_tv_name);
            tv_num = (TextView) itemView.findViewById(R.id.item_first_fragment_tv_agree);
        }
    }
}
