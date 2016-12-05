package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.appfoodpie.BaseActivity;
import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.beans.LibraryMoreBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by dllo on 16/11/30.
 */

public class LibraryMoreAdapter extends RecyclerView.Adapter<LibraryMoreAdapter.LibraryViewHolder> {

    private List<LibraryMoreBean.FoodsBean> data;
    private Context context;

    public LibraryMoreAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<LibraryMoreBean.FoodsBean> data) {
//        this.data.clear();
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clean(){
        data.clear();
    }

    @Override
    public LibraryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_lib_more, parent, false);
        LibraryViewHolder holder = new LibraryViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(LibraryViewHolder holder, int position) {
        Picasso.with(context).load(data.get(position).getThumb_image_url()).into(holder.img);
        holder.tvName.setText(data.get(position).getName());
        holder.tvCalory.setText(data.get(position).getCalory());
    }

    @Override
    public int getItemCount() {
        return data != null ? data.size() : 0;
    }

    class LibraryViewHolder extends RecyclerView.ViewHolder {
        private ImageView img;
        private TextView tvName,tvCalory;

        public LibraryViewHolder(View itemView) {
            super(itemView);
            img = (CircleImageView) itemView.findViewById(R.id.item_lib_more_img);
            tvName = (TextView) itemView.findViewById(R.id.item_lib_more_tv_name);
            tvCalory = (TextView) itemView.findViewById(R.id.item_lib_more_tv_calory);
        }
    }
}
