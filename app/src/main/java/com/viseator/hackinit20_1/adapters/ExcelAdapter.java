package com.viseator.hackinit20_1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.data.GameDataEntity;

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
    private HashMap<Date, Integer> infor = new HashMap<>();

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

    public ExcelAdapter(Context context, HashMap<Date, Integer> infor) {
        mContext = context;
        this.infor = infor;
    }

    @Override
    public int getItemCount() {
        return 7;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.everyday_game_hour, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

    }
}
