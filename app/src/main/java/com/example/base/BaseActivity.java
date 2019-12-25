package com.example.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;


import com.example.peiwang.R;
import com.example.utils.FixMenLeak;
import com.example.utils.StatusBarUtils;


public abstract class BaseActivity extends AppCompatActivity implements BaseView {
    protected abstract int getContentViewId();

    protected abstract void getUnbinder();

    protected abstract void initAllMembersView(Bundle savedInstanceState);

    protected abstract void disarmState();

    //进度条
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        progressDialog = new ProgressDialog(this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("正在加载数据");
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
//                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(Color.TRANSPARENT);
//        }

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
        StatusBarUtils.setStatusBarColor(this, R.color.colorWhite);

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getContentViewId());
        getUnbinder();
        initAllMembersView(savedInstanceState);

    }

    @Override
    public View getView() {
        View view = findViewById(getContentViewId());
        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disarmState();
        FixMenLeak.fixLeak(this);
        //取消订阅
//        basePresenter.unSubscription();
//        basePresenter.detachView();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void showLoading() {
        progressDialog.show();
    }

    @Override
    public void hideLoading() {
        progressDialog.dismiss();
    }

    @Override
    public void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showError() {
        showToast("");
    }

    @Override
    public void showLog(String msg) {
        Log.e("m_tag", msg);
    }

    @Override
    public Context getContext() {
        return BaseActivity.this;
    }

    public void gotoActivity(Class<?> cls) {
        startActivity(new Intent(BaseActivity.this, cls));
    }

}
