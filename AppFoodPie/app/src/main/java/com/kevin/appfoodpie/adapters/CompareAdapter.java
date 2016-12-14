package com.kevin.appfoodpie.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kevin.appfoodpie.CompareActivity;
import com.kevin.appfoodpie.R;
import com.kevin.appfoodpie.beans.CompareBean;
import com.squareup.picasso.Picasso;


/**
 * Created by dllo on 16/12/12.
 */

public class CompareAdapter extends BaseAdapter {

    private CompareBean data;
    private static CompareBean data1_ori;
    private Context context;
    private CompareBean data2;
    private static CompareBean data2_ori;

    private int i;

    public void setI(int i) {
        this.i = i;
    }


    public CompareAdapter(Context context) {
        this.context = context;
    }

    public void setData(CompareBean data) {
        if (i == 1){
            Log.d("CompareAdapter", "111111111:" + data);
            this.data = data;
            data1_ori = data;
        }else if (i == 2){
            this.data2 = data;
            data2_ori = data;
            Log.d("CompareAdapter", "222222222:" + data);
        }


        notifyDataSetChanged();
    }

//    public void setData2(CompareBean data2) {
//        this.data2 = data2;
//        notifyDataSetChanged();
//    }



    @Override
    public int getCount() {
        if (data != null || data2 != null){
            return 23;
        }else {
            return 0;
        }
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        CompareViewHolder viewHolder = null;
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_compare, parent, false);
            viewHolder = new CompareViewHolder(convertView);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (CompareViewHolder) convertView.getTag();
        }
        switch (position) {
            case 0:
                if (data1_ori != null){
                    viewHolder.tv1.setText(data1_ori.getIngredient().getCalory());
                }
                viewHolder.tv2.setText("热量");
                if (data2_ori != null){
                viewHolder.tv3.setText(data2_ori.getIngredient().getCalory());
                }
                break;
            case 1:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getProtein());
                }
                viewHolder.tv2.setText("蛋白质");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getProtein());
                }
                break;
            case 2:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getFat());
                }
                viewHolder.tv2.setText("脂肪");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getFat());
                }
                break;
            case 3:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getCarbohydrate());
                }
                viewHolder.tv2.setText("碳水化合物");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getCarbohydrate());
                }
                break;
            case 4:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getFiber_dietary());
                }
                viewHolder.tv2.setText("膳食纤维");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getFiber_dietary());
                }
                break;
            case 5:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getVitamin_a());
                }
                viewHolder.tv2.setText("维生素A");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getVitamin_a());
                }
                break;
            case 6:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getVitamin_c());
                }
                viewHolder.tv2.setText("维生素C");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getVitamin_c());
                }
                break;
            case 7:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getVitamin_e());
                }
                viewHolder.tv2.setText("维生素E");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getVitamin_e());
                }
                break;
            case 8:
                if (data1_ori != null){
                    viewHolder.tv1.setText(data1_ori.getIngredient().getProtein());
                }
                viewHolder.tv2.setText("蛋白质");
                if (data2_ori != null){
                viewHolder.tv3.setText(data2_ori.getIngredient().getProtein());
                }
                break;
            case 9:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getCarotene());
                }
                viewHolder.tv2.setText("胡萝卜素");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getCarotene());
                }
                break;
            case 10:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getThiamine());
                }
                viewHolder.tv2.setText("维生素B1");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getThiamine());
                }
                break;
            case 11:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getLactoflavin());
                }
                viewHolder.tv2.setText("维生素B2");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getLactoflavin());
                }
                break;
            case 12:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getNiacin());
                }
                viewHolder.tv2.setText("烟酸");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getNiacin());
                }
                break;
            case 13:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getMagnesium());
                }
                viewHolder.tv2.setText("镁");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getMagnesium());
                }
                break;
            case 14:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getCalcium());
                }
                viewHolder.tv2.setText("钙");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getCalcium());
                }
                break;
            case 15:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getIron());
                }
                viewHolder.tv2.setText("铁");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getIron());
                }
                break;
            case 16:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getNiacin());
                }
                viewHolder.tv2.setText("锌");
                if (data2_ori != null){
                viewHolder.tv3.setText(data2_ori.getIngredient().getNiacin());

                }
                break;
            case 17:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getThiamine());
                }
                viewHolder.tv2.setText("铜");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getThiamine());
                }
                break;
            case 18:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getManganese());
                }
                viewHolder.tv2.setText("锰");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getManganese());
                }
                break;
            case 19:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getKalium());
                }
                viewHolder.tv2.setText("钾");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getKalium());
                }
                break;
            case 20:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getPhosphor());
                }
                viewHolder.tv2.setText("磷");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getPhosphor());
                }
                break;
            case 21:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getNatrium());
                }
                viewHolder.tv2.setText("钠");
                if (data2_ori != null){

                viewHolder.tv3.setText(data2_ori.getIngredient().getNatrium());
                }
                break;
            case 22:
                if (data1_ori != null){

                    viewHolder.tv1.setText(data1_ori.getIngredient().getSelenium());
                }
                viewHolder.tv2.setText("硒");
                if (data2_ori != null){
                viewHolder.tv3.setText(data2_ori.getIngredient().getSelenium());
                }
                break;
        }

        return convertView;
    }

    class CompareViewHolder {
        TextView tv1, tv2, tv3;

        public CompareViewHolder(View view) {
            tv1 = (TextView) view.findViewById(R.id.tv_one);
            tv2 = (TextView) view.findViewById(R.id.tv_two);
            tv3 = (TextView) view.findViewById(R.id.tv_three);
        }
    }

}
