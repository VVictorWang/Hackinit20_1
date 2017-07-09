package com.viseator.hackinit20_1.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.adapters.ExcelAdapter;
import com.viseator.hackinit20_1.adapters.GamerecordAdapter;
import com.viseator.hackinit20_1.data.DataBean;
import com.viseator.hackinit20_1.data.DataCount;
import com.viseator.hackinit20_1.data.GameData;
import com.viseator.hackinit20_1.data.GameDataEntity;
import com.viseator.hackinit20_1.util.DateUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


public class ThisWeekFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private String mParam1;
    private String mParam2;
    private RecyclerView weekday_list, game_list;
    private ExcelAdapter weekday_adapter;
    private GamerecordAdapter mGamerecordAdapter;
    private List<GameDataEntity> mGameDataEntities = new ArrayList<>();
    private View rootView;

    private List<DataCount> mDataCounts = new ArrayList<>();

    public ThisWeekFragment() {
        // Required empty public constructor
    }


    public static ThisWeekFragment newInstance(String param1, String param2) {
        ThisWeekFragment fragment = new ThisWeekFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_this_week, container, false);
        // Inflate the layout for this fragment
        initView();
        return rootView;
    }

    private void initView() {
        initData();

        weekday_list = (RecyclerView) rootView.findViewById(R.id.hour_list_week);
        game_list = (RecyclerView) rootView.findViewById(R.id.game_record_list);
        weekday_adapter = new ExcelAdapter(getActivity(), mDataCounts);
        mGamerecordAdapter = new GamerecordAdapter(getActivity(), mGameDataEntities);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        weekday_list.setLayoutManager(linearLayoutManager);
        weekday_list.setAdapter(weekday_adapter);
        LinearLayoutManager linearLayoutManager1 = new LinearLayoutManager(getActivity());
        game_list.setAdapter(mGamerecordAdapter);
        game_list.setLayoutManager(linearLayoutManager1);

    }

    private void initData() {
        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 7; calendar.add(Calendar.DATE, -1),i++) {
            Date date = calendar.getTime();
            List<GameDataEntity> gameDataEntities = GameData.getInstance().getDataByDay(date);
            int time = DateUtils.sumEveryDay(gameDataEntities);
            DataCount dataCount = new DataCount();
            dataCount.setDate(date);
            dataCount.setRuntime(time);
            mDataCounts.add(dataCount);

        }
        mGameDataEntities = GameData.getInstance().getDataList();
    }


}
