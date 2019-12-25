package com.example.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.example.adapter.HomneAdapter;
import com.example.base.BaseAdapter;
import com.example.base.BaseFragment;
import com.example.peiwang.R;

import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {
    HomneAdapter homneAdapter;

    @Override
    public int getContentViewId() {
        return R.layout.fragment_home;
    }

    @Override
    public void getUnbinder() {
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        homneAdapter = new HomneAdapter();
        View view= LayoutInflater.from(getContext()).inflate(R.layout.empty_layout,null);
        homneAdapter.setEmptyView(view);
        homneAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {

            }
        });
    }

    @Override
    protected void disarmState() {

    }
}
