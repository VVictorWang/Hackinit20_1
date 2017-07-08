package com.viseator.hackinit20_1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.data.GameInfo;
import com.viseator.hackinit20_1.util.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by victor on 7/9/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class GamerecordAdapter extends RecyclerView.Adapter<GamerecordAdapter.MyViewHolder>{
    private Context mContext;
    private List<GameInfo> mGameInfos = new ArrayList<>();
    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView time;
        private TextView event;
        public MyViewHolder(View itemView) {
            super(itemView);
            time = (TextView) itemView.findViewById(R.id.gamerecord_time);
            event = (TextView) itemView.findViewById(R.id.gamerecord_event);
        }
    }

    public GamerecordAdapter(Context context) {
        mContext = context;
    }

    public GamerecordAdapter(Context context, List<GameInfo> gameInfos) {
        mContext = context;
        mGameInfos = gameInfos;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.gamerecord_list, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        String week = getDayForSection(position);
        final GameInfo gameinfo = mGameInfos.get(position);
        if (position == getPositionForSection(week)) {
            holder.time.setText(week);
            holder.event.setText("");
        } else {
            holder.time.setText("");
        }
    }

    @Override
    public int getItemCount() {
        return mGameInfos.size();
    }

    public String getDayForSection(int position) {
        long time = mGameInfos.get(position).getTime();
        return DateUtils.getWeekbyTime(time);
    }

    public int getPositionForSection(String week) {
        for (int i = 0; i < getItemCount(); i++) {
            long time = mGameInfos.get(i).getTime();
            String currentweek = DateUtils.getWeekbyTime(time);
            if (currentweek.equals(week)) {
                return i;
            }
        }
        return -1;
    }
}
