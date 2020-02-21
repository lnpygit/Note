package com.example.note;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class ListAdapter extends BaseAdapter {

    private List<Constants> mConstants;
    private Context mContext;

    public ListAdapter(List<Constants> Constants, Context Context){
        mConstants = Constants;
        mContext = Context;
    }

    @Override
    public int getCount() {
        return mConstants.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        ViewHolder holder = null;
//        if(convertView == null){
//            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_list,parent,false);
//            holder = new ViewHolder();
//            holder.img_icon = (ImageView) convertView.findViewById(R.id.img_icon);
//            holder.txt_content = (TextView) convertView.findViewById(R.id.txt_content);
//            convertView.setTag(holder);
//        }else{
//            holder = (ViewHolder) convertView.getTag();
//        }
//        holder.img_icon.setImageResource(mData.get(position).getImgId());
//        holder.txt_content.setText(mData.get(position).getContent());
//        return convertView;
//
//        return tv;
//    }
//
//    private class ViewHolder{
//        ImageView img_icon;
//        TextView txt_content;
        return convertView;
    }





}
