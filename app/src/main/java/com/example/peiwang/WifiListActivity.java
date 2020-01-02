package com.example.peiwang;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class WifiListActivity extends BaseActivity {
    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_do)
    TextView tvDo;
    @BindView(R.id.recycle_list)
    RecyclerView recycleList;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_wifi_list;
    }

    @Override
    protected void getUnbinder() {

    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {

    }

    @Override
    protected void disarmState() {

    }



    @OnClick({R.id.ll_return, R.id.tv_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.tv_do:
                break;
        }
    }
}
