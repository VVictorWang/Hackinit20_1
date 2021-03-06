package com.viseator.hackinit20_1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.data.DataCount;
import com.viseator.hackinit20_1.data.GameDataEntity;
import com.viseator.hackinit20_1.util.DateUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by victor on 7/9/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class ExcelAdapter extends RecyclerView.Adapter<ExcelAdapter.MyViewHolder> {
    private Context mContext;
    private List<DataCount> mGameDataEntities = new ArrayList<>();

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView day;
        private TextView hour;

        public MyViewHolder(View itemView) {
            super(itemView);
            day = (TextView) itemView.findViewById(R.id.date_text);
            hour = (TextView) itemView.findViewById(R.id.everyday_hour_text);
        }
    }

    public ExcelAdapter(Context context) {
        mContext = context;
    }

    public ExcelAdapter(Context context, List<DataCount> infor) {
        mContext = context;
        mGameDataEntities = infor;
    }

    @Override
    public int getItemCount() {
        return mGameDataEntities.size();
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.everyday_game_hour, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        DataCount entity = mGameDataEntities.get(position);
        holder.day.setText(DateUtils.formatDate(entity.getDate()));
        int runtime = entity.getRuntime();
        int minute = (int)(Math.random() * 100 + 10);
        holder.hour.setText(minute + "分钟");
    }
}
