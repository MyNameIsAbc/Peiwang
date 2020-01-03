package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.base.BaseAdapter;
import com.example.base.Constant;
import com.example.bean.DeviceBean;
import com.example.peiwang.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomneAdapter extends BaseAdapter<DeviceBean> {


    public HomneAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_device_list, parent, false);
        return new DeviceHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int position, DeviceBean data) {
        DeviceHolder deviceHolder = (DeviceHolder) viewHolder;
        deviceHolder.setData(data);
    }


    class DeviceHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_device_sn)
        TextView tvDeviceSn;

        @BindView(R.id.tv_device_from)
        TextView tvDeviceFrom;
        @BindView(R.id.tv_device_to)
        TextView tvDeviceTo;
        @BindView(R.id.tv_do)
        ImageView tv;


        DeviceHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(DeviceBean statusBean) {
            if (statusBean.getNetwork() == -1) {
                tvDeviceSn.setText(statusBean.getSn());
                tvDeviceSn.setTextColor(context.getResources().getColor(R.color.colorglay));

                tvDeviceFrom.setTextColor(context.getResources().getColor(R.color.colorglay));
                tvDeviceTo.setTextColor(context.getResources().getColor(R.color.colorglay));
            } else {
                tvDeviceSn.setText(statusBean.getSn());
                tvDeviceSn.setTextColor(context.getResources().getColor(android.R.color.black));

                Constant.setLngUI(statusBean.getFromLang(), tvDeviceFrom, null);
                Constant.setLngUI(statusBean.getToLang(), tvDeviceTo, null);
                tvDeviceFrom.setTextColor(context.getResources().getColor(R.color.colorText));
                tvDeviceTo.setTextColor(context.getResources().getColor(R.color.colorText));
            }
        }
    }


}
