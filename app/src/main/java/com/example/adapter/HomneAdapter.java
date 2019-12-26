package com.example.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.base.BaseAdapter;
import com.example.bean.DeviceBean;
import com.example.bean.StatusBean;
import com.example.peiwang.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class HomneAdapter extends BaseAdapter<DeviceBean> {


    public HomneAdapter(Context context) {
        super(context);
    }

    @Override
    public RecyclerView.ViewHolder onCreate(ViewGroup parent, int viewType) {
        View view = layoutInflater.inflate(R.layout.layout_device_list, null);
        return new DeviceHolder(view);
    }

    @Override
    public void onBind(RecyclerView.ViewHolder viewHolder, int position, DeviceBean data) {
        DeviceHolder deviceHolder = (DeviceHolder) viewHolder;
        deviceHolder.setData(data);
    }


    static
    class DeviceHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_device_sn)
        TextView tvDeviceSn;
        @BindView(R.id.tv_device_name)
        TextView tvDeviceName;
        @BindView(R.id.tv_device_lang)
        TextView tvDeviceLang;
        @BindView(R.id.tv_device_volume)
        TextView tvDeviceVolume;
        @BindView(R.id.tv_charge)
        TextView tvCharge;
        @BindView(R.id.tv_money)
        TextView tvMoney;

        DeviceHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

        public void setData(DeviceBean statusBean) {
            tvDeviceSn.setText("设备SN:" + statusBean.getSn());
            if (statusBean.getAutoLang() == 1)
                tvDeviceName.setText("翻译模式:自动切换语言");
            else
                tvDeviceName.setText("翻译模式:固定语言");
            tvCharge.setText("电量:" + statusBean.getPower());
            tvDeviceVolume.setText("音量:" + statusBean.getVolume());
            tvDeviceLang.setText("语言：" + "fromLang:" + statusBean.getFromLang() + "   toLang:" + statusBean.getToLang());
        }
    }
}
