package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.beans.SearchBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/12/5.
 */

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder>{

    private List<SearchBean.ItemsBean> data;
    private Context context;

    public SearchAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<SearchBean.ItemsBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void Clean(){
        data.clear();
    }

    @Override
    public SearchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_search_details,parent,false);
        SearchViewHolder holder = new SearchViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(SearchViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getThumb_image_url()).into(holder.img);
        holder.name.setText(data.get(position).getName());
        holder.num.setText(data.get(position).getCalory());
        int num = data.get(position).getHealth_light();
        switch (num){
            case 1:
                holder.imgC.setImageResource(R.mipmap.ic_food_light_green);
                break;
            case 2:
                holder.imgC.setImageResource(R.mipmap.ic_food_light_yellow);
                break;
            case 3:
                holder.imgC.setImageResource(R.mipmap.ic_food_light_red);
                break;

        }
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder{
        private ImageView img,imgC;
        private TextView name,num;
        public SearchViewHolder(View itemView) {
            super(itemView);
            img = (ImageView) itemView.findViewById(R.id.details_img);
            name = (TextView) itemView.findViewById(R.id.details_name);
            num = (TextView) itemView.findViewById(R.id.details_kc);
            imgC = (ImageView) itemView.findViewById(R.id.img_color_more);
        }
    }
}
