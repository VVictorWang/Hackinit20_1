package com.viseator.hackinit20_1.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.viseator.hackinit20_1.R;
import com.viseator.hackinit20_1.adapters.GamerecordAdapter;
import com.viseator.hackinit20_1.data.DataBean;
import com.viseator.hackinit20_1.data.GameData;
import com.viseator.hackinit20_1.data.GameDataEntity;
import com.viseator.hackinit20_1.util.DateUtils;

import java.util.List;


public class TodayDataFragment extends Fragment {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private RecyclerView mRecyclerView;
    private TextView total;
    private GamerecordAdapter mGamerecordAdapter;
    private Activity mActivity;
    private View rootview;


    private List<GameDataEntity> mGamerecords;
    private String mParam1;
    private String mParam2;


    public TodayDataFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TodayDataFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TodayDataFragment newInstance(String param1, String param2) {
        TodayDataFragment fragment = new TodayDataFragment();
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
        mActivity = getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_today_data, container, false);
        initView();
        return rootview;
    }

    private void initView() {
        total = (TextView) rootview.findViewById(R.id.game_hour_all);
        mRecyclerView = (RecyclerView) rootview.findViewById(R.id.game_record_list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        initData();
    }

    private void initData() {

        mGamerecords = GameData.getInstance().getDataList();
        if (mGamerecords != null) {
            mGamerecords = DateUtils.sortGameInfo(mGamerecords);
            mGamerecordAdapter = new GamerecordAdapter(mActivity, mGamerecords);
            mRecyclerView.setAdapter(mGamerecordAdapter);
        }
    }

}
