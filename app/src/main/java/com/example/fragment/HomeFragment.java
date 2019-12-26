package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.adapter.HomneAdapter;
import com.example.base.BaseAdapter;
import com.example.base.BaseFragment;
import com.example.base.Constant;
import com.example.bean.DeviceBean;
import com.example.bean.MessageWaper;
import com.example.bean.StatusBean;
import com.example.db.Device;
import com.example.db.DeviceDao;
import com.example.db.DeviceDaoUtils;
import com.example.net.ApiService;
import com.example.net.RetrofitManager;
import com.example.peiwang.AddDeviceActivity;
import com.example.peiwang.PeiWangActivity;
import com.example.peiwang.R;
import com.orhanobut.logger.Logger;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class HomeFragment extends BaseFragment {
    HomneAdapter homneAdapter;
    @BindView(R.id.recyc_home)
    RecyclerView recycHome;
    Unbinder unbinder;
    @BindView(R.id.lin_tip)
    LinearLayout linTip;
    @BindView(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    Unbinder unbinder1;

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

        EventBus.getDefault().register(this);
        homneAdapter = new HomneAdapter(getContext());
        recycHome.setHasFixedSize(true);

        recycHome.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
        homneAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, Object data) {

            }
        });
        recycHome.setAdapter(homneAdapter);

        refreshData();

        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                refreshlayout.finishRefresh(2000/*,false*/);//传入false表示刷新失败
                refreshData();

            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(RefreshLayout refreshlayout) {
                refreshlayout.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            }
        });

    }

    private void refreshData() {
        DeviceDaoUtils deviceDaoUtils = new DeviceDaoUtils(getContext());
        List<Device> devices = deviceDaoUtils.queryAllDevice();
        Logger.d("devices:"+devices.size()+devices.isEmpty());
        if (devices.isEmpty())
            linTip.setVisibility(View.VISIBLE);
        else {
            linTip.setVisibility(View.INVISIBLE);
            homneAdapter.clear();
            for (Device d : devices) {
                getDeviceInfo(d.getSn());
            }
        }
    }

    @Override
    protected void disarmState() {
        EventBus.getDefault().unregister(this);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick(R.id.lin_tip)
    public void onViewClicked() {
        Intent intent = new Intent(getActivity(), AddDeviceActivity.class);
        getActivity().startActivity(intent);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMessageEvent(MessageWaper event) {
        switch (event.type) {
            case Constant.TEXT_TYPE_ADD_DEVICE:
                String sn = (String) event.object;
                getDeviceInfo(sn);
                DeviceDaoUtils deviceDaoUtils = new DeviceDaoUtils(getContext());
                List<Device>devices= deviceDaoUtils.queryDeviceByQueryBuilder(sn);
                if (devices.isEmpty()){
                    Device device = new Device();
                    device.setSn(sn);
                    deviceDaoUtils.insertDevice(device);
                }
                break;
        }
    }

    public void getDeviceInfo(final String sn) {
        RetrofitManager.getInstance().getRetrofit()
                //动态代理创建GithubAPI对象
                .create(ApiService.class)
                .getStatus("0", sn)
                //指定上游发送事件线程
                .subscribeOn(Schedulers.computation())
                //指定下游接收事件线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        Logger.d("Device:" + s);
                        JSONObject jsonObject = JSON.parseObject(s);
                        if (jsonObject.getIntValue("success") == 1) {
                            StatusBean statusBean = JSON.parseObject(s, StatusBean.class);
                            StatusBean.DataBean dataBean = statusBean.getData();
                            DeviceBean deviceBean = new DeviceBean(sn, dataBean.getVolume(), dataBean.getPower(), dataBean.getNetwork(),
                                    dataBean.getAutoLang(), dataBean.getFromLang(), dataBean.getToLang());
                            homneAdapter.addData(deviceBean);
                            linTip.setVisibility(View.INVISIBLE);
                        } else {
                            String msg = jsonObject.getString("msg");
                            Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
                            if (jsonObject.getIntValue("code") == -1) {

                            } else {
                                Intent intent = new Intent(getActivity(), PeiWangActivity.class);
                                getActivity().startActivity(intent);

                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        Logger.d("onError:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

}
