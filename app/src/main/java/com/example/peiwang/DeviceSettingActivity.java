package com.example.peiwang;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.base.BaseActivity;
import com.example.base.Constant;
import com.example.bean.DeviceBean;
import com.example.bean.MessageWaper;
import com.example.bean.StatusBean;
import com.example.db.Language;
import com.example.net.ApiService;
import com.example.net.RetrofitManager;
import com.orhanobut.logger.Logger;
import com.xw.repo.BubbleSeekBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class DeviceSettingActivity extends BaseActivity {
    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_do)
    TextView tvDo;
    @BindView(R.id.iv_fromlng)
    ImageView ivFromlng;
    @BindView(R.id.tv_fromlng)
    TextView tvFromlng;
    @BindView(R.id.iv_switch)
    ImageView ivSwitch;
    @BindView(R.id.iv_tolng)
    ImageView ivTolng;
    @BindView(R.id.tv_tolng)
    TextView tvTolng;
    @BindView(R.id.lin_top)
    LinearLayout linTop;
    DeviceBean deviceBean;
    @BindView(R.id.seekbar)
    BubbleSeekBar seekbar;
    @BindView(R.id.auto_check)
    CheckBox autoCheck;

    int volume;
    int autoTranslate;

    String from, to;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_device;
    }

    @Override
    protected void getUnbinder() {

    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        EventBus.getDefault().register(this);
        ButterKnife.bind(this);
        tvDo.setVisibility(View.VISIBLE);
        tvTitle.setText("设置设备");
        deviceBean = (DeviceBean) getIntent().getSerializableExtra("bean");
        Logger.d("deviceBean:" + deviceBean);
        from=deviceBean.getFromLang();
        to=deviceBean.getToLang();
        Constant.setLngUI(deviceBean.getFromLang(), tvFromlng, ivFromlng);
        Constant.setLngUI(deviceBean.getToLang(), tvTolng, ivTolng);
        volume=deviceBean.getVolume();
        seekbar.setProgress(deviceBean.getVolume());
        seekbar.setOnProgressChangedListener(new BubbleSeekBar.OnProgressChangedListener() {
            @Override
            public void onProgressChanged(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
            }

            @Override
            public void getProgressOnActionUp(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat) {
                Logger.d("getProgressOnActionUp:" + progress);
                volume = progress;
            }

            @Override
            public void getProgressOnFinally(BubbleSeekBar bubbleSeekBar, int progress, float progressFloat, boolean fromUser) {
            }
        });

        if (deviceBean.getAutoLang() == 0) {
            autoCheck.setChecked(false);
            autoTranslate = 0;
        } else {
            autoCheck.setChecked(true);
            autoTranslate = 1;
        }
        autoCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)
                    autoTranslate = 1;
                else
                    autoTranslate = 0;
            }
        });
    }

    @Override
    protected void disarmState() {
        EventBus.getDefault().unregister(this);
    }


    @OnClick({R.id.ll_return, R.id.iv_fromlng, R.id.tv_fromlng, R.id.iv_switch, R.id.iv_tolng, R.id.tv_tolng, R.id.tv_do})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ll_return:
                finish();
                break;
            case R.id.iv_fromlng:
            case R.id.tv_fromlng:
                Intent intent = new Intent(this, LanguageSetActivity.class);
                intent.putExtra("sn", deviceBean.getSn());
                intent.putExtra("type", 0);
                startActivity(intent);
                break;
            case R.id.iv_switch:
                break;
            case R.id.iv_tolng:
            case R.id.tv_tolng:
                Intent intent2 = new Intent(this, LanguageSetActivity.class);
                intent2.putExtra("sn", deviceBean.getSn());
                intent2.putExtra("type", 1);
                startActivity(intent2);
                break;
            case R.id.tv_do:
                saveLanguage();
                saveVolume();
                break;
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void Event(MessageWaper messageWaper) {
        Language language;
        switch (messageWaper.type) {
            case Constant.EVENT_SET_FOREIGN_LANGUAGE:
                language = (Language) messageWaper.object;
                to = language.getLangcode();
                Constant.setLngUI(language.getLangcode(), tvTolng, ivTolng);
                if (TextUtils.isEmpty(tvTolng.getText().toString())) {
                    ivTolng.setVisibility(View.INVISIBLE);
                } else {
                    ivTolng.setVisibility(View.VISIBLE);
                }
                break;
            case Constant.EVENT_SET_MOTHER_LANGUAGE:
                language = (Language) messageWaper.object;
                from = language.getLangcode();
                Constant.setLngUI(language.getLangcode(), tvFromlng, ivFromlng);
                if (TextUtils.isEmpty(tvTolng.getText().toString())) {
                    ivFromlng.setVisibility(View.INVISIBLE);
                } else {
                    ivFromlng.setVisibility(View.VISIBLE);
                }
                break;
        }
    }


    public void saveLanguage() {
        if (from.equals(to)){
            showToast("翻译语言不能与母语相同!");
            return;
        }
        if (to.equals("zh_cn")&&autoTranslate==1){
            String temp=from;
            from=to;
            to=temp;
        }
        showLoading();
        RetrofitManager.getInstance().getRetrofit()
                //动态代理创建GithubAPI对象
                .create(ApiService.class)
                .changeLanguage("0", deviceBean.getSn(), from, to, autoTranslate)
                //指定上游发送事件线程
                .subscribeOn(Schedulers.computation())
                //指定下游接收事件线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StatusBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StatusBean s) {
                        if (s.getSuccess() == 1)
                            showToast("修改语言成功");
                        else
                            showToast(s.getMsg());
                        hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                        hideLoading();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void saveVolume(){
        RetrofitManager.getInstance().getRetrofit()
                //动态代理创建GithubAPI对象
                .create(ApiService.class)
                .changeVolume("0", deviceBean.getSn(), volume)
                //指定上游发送事件线程
                .subscribeOn(Schedulers.computation())
                //指定下游接收事件线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<StatusBean>() {

                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(StatusBean s) {
                        if (s.getSuccess() == 1)
                            showToast("修改音量成功");
                        else
                            showToast(s.getMsg());

                        hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showToast(e.getMessage());
                        hideLoading();
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
