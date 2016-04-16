package com.liuyahan.ticketsearch_liuyahan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.liuyahan.ticketsearch_liuyahan.R;
import com.liuyahan.ticketsearch_liuyahan.entity.SearchEntity;

import java.util.List;

public class SearchResultAdapter extends BaseAdapter{
    private List<SearchEntity.dataEntity.trainListEntity> trainList;
    private LayoutInflater inflater;
    private List<SearchEntity.dataEntity.trainListEntity.SeatInfosEntity> seatInfos;

    public SearchResultAdapter(Context context,List<SearchEntity.dataEntity.trainListEntity> trainList) {
        this.trainList = trainList;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return trainList.size();
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
        ViewHolder holder;
        if (convertView==null){
            convertView = inflater.inflate(R.layout.layout_listview_item,parent,false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        holder.trainNo.setText(trainList.get(position).getTrainNo());
        holder.trainType.setText(trainList.get(position).getTrainType());
        holder.startTime.setText(trainList.get(position).getStartTime());
        holder.endTime.setText(trainList.get(position).getEndTime());
        holder.from.setText(trainList.get(position).getFrom());
        holder.to.setText(trainList.get(position).getTo());
        holder.duration.setText(trainList.get(position).getDuration());
        seatInfos = trainList.get(position).getSeatInfos();
        if (seatInfos.size()>0) {
            holder.seat.setText(seatInfos.get(0).getSeat());
            if (seatInfos.get(0).getRemainNum() >= 0) {
                holder.remainNum.setText(seatInfos.get(0).getRemainNum() + "张");
            } else {
                holder.remainNum.setText("0张");
            }
            holder.seatPrice.setText("¥" + seatInfos.get(0).getSeatPrice());
        } else {
            holder.seat.setText("无席别");
            holder.remainNum.setText("0张");

            holder.seatPrice.setText("¥0");
        }
        return convertView;
    }

    private class ViewHolder {
        TextView trainNo;
        TextView trainType;
        TextView remainNum;
        TextView startTime;
        TextView endTime;
        TextView from;
        TextView to;
        TextView seatPrice;
        TextView duration;
        TextView seat;

        public ViewHolder(View convertView) {
            trainNo = (TextView) convertView.findViewById(R.id.trainNo);
            trainType = (TextView) convertView.findViewById(R.id.trainType);
            remainNum = (TextView) convertView.findViewById(R.id.remainNum);
            startTime = (TextView) convertView.findViewById(R.id.startTime);
            endTime = (TextView) convertView.findViewById(R.id.endTime);
            from = (TextView) convertView.findViewById(R.id.from);
            to = (TextView) convertView.findViewById(R.id.to);
            seatPrice = (TextView) convertView.findViewById(R.id.seatPrice);
            duration = (TextView) convertView.findViewById(R.id.duration);
            seat = (TextView) convertView.findViewById(R.id.seat);
        }
    }
}
