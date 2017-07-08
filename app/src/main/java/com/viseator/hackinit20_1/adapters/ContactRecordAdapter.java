package com.viseator.hackinit20_1.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.data.GameDataEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 7/9/17.
 * email: chengyiwang@hustunique.com
 * blog: www.victorwang.science                                            #
 */

public class ContactRecordAdapter extends RecyclerView.Adapter{
    private Context mContext;
    private List<GameDataEntity> mGameDataEntities = new ArrayList<>();
    class MyViewHolder extends RecyclerView.ViewHolder {
        private ImageView status_image;
        private TextView time;
        public MyViewHolder(View itemView) {
            super(itemView);
            status_image = (ImageView) itemView.findViewById(R.id.contact_record_image);
            time = (TextView) itemView.findViewById(R.id.contact_record_time);
        }
    }

    public ContactRecordAdapter(Context context) {
        mContext = context;
    }

    public ContactRecordAdapter(Context context, List<GameDataEntity> gameDataEntities) {
        mContext = context;
        mGameDataEntities = gameDataEntities;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_record, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mGameDataEntities.size();
    }
}
