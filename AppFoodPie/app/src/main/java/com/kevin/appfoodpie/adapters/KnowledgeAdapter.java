package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.beans.KnowledgeBean;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by dllo on 16/11/25.
 */

public class KnowledgeAdapter extends BaseAdapter{

//    private KnowledgeBean bean;
    private List<KnowledgeBean.FeedsBean> data;
    private Context context;

    public static final int TYPE_ONE = 0;
    public static final int TYPE_TWO = 1;
    public static final int TYPE_COUNT = 2;

    public KnowledgeAdapter(Context context) {
        this.context = context;
        data = new ArrayList<>();
    }

    public void setData(List<KnowledgeBean.FeedsBean> data) {
        this.data.addAll(data);
        notifyDataSetChanged();
    }

    public void clean(){
        data.clear();
    }

    @Override
    public int getItemViewType(int position) {
        if (data.get(position).getContent_type() == 1){
            return TYPE_ONE;
        }else {
            return TYPE_TWO;
        }
    }

    @Override
    public int getViewTypeCount() {
        return TYPE_COUNT;
    }

    @Override
    public int getCount() {
        return data.size() > 0 ? data.size() : 0;
    }

    @Override
    public Object getItem(int position) {
        return data.get(position) != null ? data.get(position) : null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        OneViewHolder oneViewHolder = null;
        TwoViewHolder twoViewHolder = null;
        if (convertView == null){
            switch (getItemViewType(position)){
                case TYPE_ONE:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_knowledge,parent,false);
                    oneViewHolder = new OneViewHolder(convertView);
                    convertView.setTag(oneViewHolder);
                    break;
                case TYPE_TWO:
                    convertView = LayoutInflater.from(context).inflate(R.layout.item_knowledge_two,parent,false);
                    twoViewHolder = new TwoViewHolder(convertView);
                    convertView.setTag(twoViewHolder);
                    break;
            }
        }else {
            switch (getItemViewType(position)){
                case TYPE_ONE:
                    oneViewHolder = (OneViewHolder) convertView.getTag();
                    break;
                case TYPE_TWO:
                    twoViewHolder = (TwoViewHolder) convertView.getTag();
                    break;
            }
        }
        switch (getItemViewType(position)){
            case TYPE_ONE:
                oneViewHolder.tv_item_one_title.setText(data.get(position).getTitle());
                oneViewHolder.tv_item_one_soure.setText(data.get(position).getSource());
                oneViewHolder.tv_item_one_tail.setText(data.get(position).getTail());
                Picasso.with(context).load(data.get(position).getImages().get(0)).into(oneViewHolder.img_item_one);
                break;
            case TYPE_TWO:
//                if (!data.get(position).getImages().get(0).isEmpty()){
//                }
                Picasso.with(context).load(data.get(position).getImages().get(0)).into(twoViewHolder.item_two_imgOne);
                Picasso.with(context).load(data.get(position).getImages().get(1)).into(twoViewHolder.item_two_imgTwo);
                Picasso.with(context).load(data.get(position).getImages().get(2)).into(twoViewHolder.item_two_imgThree);
                twoViewHolder.item_two_tvTitle.setText(data.get(position).getTitle());
                twoViewHolder.item_two_tvSoure.setText(data.get(position).getSource());
                twoViewHolder.item_two_tvTail.setText(data.get(position).getTail());
                break;
            default:
                break;
        }
        return convertView;
    }
    class OneViewHolder{
        private ImageView img_item_one;
        private TextView tv_item_one_title;
        private TextView tv_item_one_soure;
        private TextView tv_item_one_tail;
        public OneViewHolder(View view) {
            img_item_one = (ImageView) view.findViewById(R.id.knowledge_images);
            tv_item_one_title = (TextView) view.findViewById(R.id.knowledge_title);
            tv_item_one_soure = (TextView) view.findViewById(R.id.knowledge_source);
            tv_item_one_tail = (TextView) view.findViewById(R.id.knowledge_tail);
        }
    }
    class TwoViewHolder{
        private ImageView item_two_imgOne;
        private ImageView item_two_imgTwo;
        private ImageView item_two_imgThree;
        private TextView item_two_tvTitle;
        private TextView item_two_tvSoure;
        private TextView item_two_tvTail;

        public TwoViewHolder(View view) {
            item_two_imgOne = (ImageView) view.findViewById(R.id.knowledge_imgOne);
            item_two_imgTwo = (ImageView) view.findViewById(R.id.knowledge_imgTwo);
            item_two_imgThree = (ImageView) view.findViewById(R.id.knowledge_imgThree);
            item_two_tvTitle = (TextView) view.findViewById(R.id.knowledge_title_two);
            item_two_tvSoure = (TextView) view.findViewById(R.id.knowledge_source_two);
            item_two_tvTail = (TextView) view.findViewById(R.id.knowledge_tail_two);
        }
    }

}
