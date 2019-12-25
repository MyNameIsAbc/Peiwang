package com.example.fragment;

import android.os.Bundle;

import com.example.base.BaseFragment;
import com.example.peiwang.R;

import butterknife.ButterKnife;

public class AboutFragment extends BaseFragment {
    @Override
    public int getContentViewId() {
        return R.layout.fragment_about;
    }

    @Override
    public void getUnbinder() {
        unbinder = ButterKnife.bind(this, rootView);
    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    @Override
    protected void disarmState() {

    }
}
