package com.example.peiwang;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.adapter.LanguageSetAdapter;
import com.example.base.BaseActivity;
import com.example.base.Constant;
import com.example.bean.MessageWaper;
import com.example.db.LangDaoUtils;
import com.example.db.Language;
import com.example.net.ApiService;
import com.example.net.RetrofitManager;
import com.example.utils.CommonUtils;
import com.example.utils.SharePreferencesUtils;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;

public class LanguageSetActivity extends BaseActivity {
    List<Language> languages = new ArrayList<>();
    LangDaoUtils langDaoUtils;
    LanguageSetAdapter languageSetAdapter;
    String sn;
    int type;
    @BindView(R.id.ll_return)
    LinearLayout llReturn;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_do)
    TextView tvDo;
    @BindView(R.id.language_list)
    ListView languageList;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_language_set;
    }

    @Override
    protected void getUnbinder() {

    }

    @Override
    protected void initAllMembersView(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        tvTitle.setText("设置语言");
        langDaoUtils = new LangDaoUtils(getApplicationContext());
        languageSetAdapter = new LanguageSetAdapter(languages, this);
        languageList.setAdapter(languageSetAdapter);
        sn = getIntent().getStringExtra("sn");
        type = getIntent().getIntExtra("type", -1);
        Logger.d("type:"+type);
        getLang(sn);
        languageSetAdapter.setOnItemClickListener(new LanguageSetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (type == 0) {
                    EventBus.getDefault().post(new MessageWaper(languages.get(position),Constant.EVENT_SET_MOTHER_LANGUAGE));
                    SharePreferencesUtils.setString(LanguageSetActivity.this,"from",languages.get(position).getLangcode());
                    SharePreferencesUtils.setString(LanguageSetActivity.this,"frommemo",languages.get(position).getMomo());
                } else if (type == 1) {
                    EventBus.getDefault().post(new MessageWaper(languages.get(position),Constant.EVENT_SET_FOREIGN_LANGUAGE));
                    SharePreferencesUtils.setString(LanguageSetActivity.this,"to",languages.get(position).getLangcode());
                    SharePreferencesUtils.setString(LanguageSetActivity.this,"tomemo",languages.get(position).getMomo());
                }
            }
        });
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        sn = getIntent().getStringExtra("sn");
        type = getIntent().getIntExtra("type", -1);
        Logger.d("type:"+type);
        getLang(sn);
        languageSetAdapter.setOnItemClickListener(new LanguageSetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                if (type == 0) {
                    EventBus.getDefault().post(new MessageWaper(languages.get(position),Constant.EVENT_SET_MOTHER_LANGUAGE));
                } else if (type == 1) {
                    EventBus.getDefault().post(new MessageWaper(languages.get(position),Constant.EVENT_SET_FOREIGN_LANGUAGE));
                }
            }
        });
    }

    @Override
    protected void disarmState() {
        langDaoUtils=null;
    }

    public void getLang(String sn) {
        showLoading();
        String url = "http://cloud.earchat.com:8081/device/getclientlangs?userId=" + sn;
        RetrofitManager.getInstance().getRetrofit()
                //动态代理创建GithubAPI对象
                .create(ApiService.class)
                .getLang(url)
                //指定上游发送事件线程
                .subscribeOn(Schedulers.computation())
                //指定下游接收事件线程
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ResponseBody>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ResponseBody s) {
                        hideLoading();
                        InputStream bis = null;
                        BufferedInputStream bufferedInputStream;
                        languages.clear();
                        try {
                            bis = s.byteStream();
                            bufferedInputStream = new BufferedInputStream(bis);
                            byte[] buffer = new byte[512];
                            int len;
                            while ((len = bufferedInputStream.read(buffer)) != -1) {
                                Logger.d("len:" + len);
                                for (int i = 2; i < len; i++) {
                                    String langid = CommonUtils.byteToInt(buffer[i]) + "";
                                    languages.add(langDaoUtils.queryLanguageByQueryBuilderLangID(langid));

                                }
                                languageSetAdapter.resetData(languages);
                            }

                        } catch (Exception e) {
                            hideLoading();
                        }
                        hideLoading();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideLoading();
                        Logger.d("onError:" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @OnClick(R.id.ll_return)
    public void onViewClicked() {
        finish();
    }
}
