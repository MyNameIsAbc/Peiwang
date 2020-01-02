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
        @BindView(R.id.tv_device_name)
        TextView tvDeviceName;
        @BindView(R.id.tv_device_from)
        TextView tvDeviceFrom;
        @BindView(R.id.tv_device_to)
        TextView tvDeviceTo;
        @BindView(R.id.iv_status)
        ImageView imageView;
        @BindView(R.id.iv_power)
        ImageView iv_power;
        @BindView(R.id.tv_do)
        TextView tv;
        @BindView(R.id.tv_do2)
        TextView tv2;

        DeviceHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(DeviceBean statusBean) {
            if (statusBean.getNetwork() == -1) {
                tvDeviceSn.setText("设备SN:" + statusBean.getSn());
                tvDeviceSn.setTextColor(context.getResources().getColor(R.color.colorglay));
                tvDeviceName.setText("模式:已离线");
                tvDeviceName.setTextColor(context.getResources().getColor(R.color.colorglay));
                tvDeviceFrom.setTextColor(context.getResources().getColor(R.color.colorglay));
                tvDeviceTo.setTextColor(context.getResources().getColor(R.color.colorglay));
                imageView.setImageResource(R.mipmap.ic_wifi_0);
                tv.setTextColor(context.getResources().getColor(R.color.colorglay));
                tv2.setTextColor(context.getResources().getColor(R.color.colorglay));
            } else {
                tvDeviceSn.setText("设备SN:" + statusBean.getSn());
                tvDeviceSn.setTextColor(context.getResources().getColor(android.R.color.black));
                if (statusBean.getAutoLang() == 1)
                    tvDeviceName.setText("模式:自动");
                else
                    tvDeviceName.setText("模式:固定");
                tvDeviceName.setTextColor(context.getResources().getColor(android.R.color.black));
                Constant.setLngUI(statusBean.getFromLang(), tvDeviceFrom, null);
                Constant.setLngUI(statusBean.getToLang(), tvDeviceTo, null);
                tvDeviceFrom.setTextColor(context.getResources().getColor(android.R.color.black));
                tvDeviceTo.setTextColor(context.getResources().getColor(android.R.color.black));
                if (statusBean.getNetwork() == 1)
                    imageView.setImageResource(R.mipmap.ic_wifi_1);
                else if (statusBean.getNetwork() == 2)
                    imageView.setImageResource(R.mipmap.ic_wifi_2);
                else if (statusBean.getNetwork() == 3)
                    imageView.setImageResource(R.mipmap.ic_wifi_3);

                if (statusBean.getPower() == 1)
                    iv_power.setImageResource(R.mipmap.power_1);
                else if (statusBean.getPower() == 2)
                    iv_power.setImageResource(R.mipmap.power_2);
                else if (statusBean.getPower() == 3)
                    iv_power.setImageResource(R.mipmap.power_3);
                tv.setTextColor(context.getResources().getColor(R.color.colorText));
                tv2.setTextColor(context.getResources().getColor(R.color.colorText));
            }
        }
    }


}
