package com.example.utils;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.peiwang.R;
import com.orhanobut.logger.Logger;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChooseDeviceDialog extends Dialog {
    @BindView(R.id.tv_cancel)
    TextView tvCancel;
    @BindView(R.id.rb_male)
    RadioButton rbMale;
    @BindView(R.id.rl_male)
    RelativeLayout rlMale;
    @BindView(R.id.rb_female)
    RadioButton rbFemale;
    @BindView(R.id.rl_ali_pay)
    RelativeLayout rlFemale;
    @BindView(R.id.btn_pay)
    Button btnPay;
    int type;
    Context context;
    private OnSelectClickListener listener;

    public ChooseDeviceDialog(Context context) {
        super(context, R.style.ActionSheetDialogStyle);
        this.context = context;
    }

    public ChooseDeviceDialog setData(int type) {
        this.type = type;
        return this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.device_choose_layout);
        ButterKnife.bind(this);
        switch (this.type){
            case 1:
                rbMale.setChecked(true);
                rbFemale.setChecked(false);
                break;
            case 2:
                rbMale.setChecked(false);
                rbFemale.setChecked(true);
                break;
            case 3:
                rbMale.setChecked(false);
                rbFemale.setChecked(false);
                break;
        }
    }

    @OnClick({R.id.tv_cancel, R.id.rl_male, R.id.rl_ali_pay, R.id.btn_pay})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_cancel:
                dismiss();
                break;
            case R.id.rl_male:
                rbMale.setChecked(true);
                rbFemale.setChecked(false);
                type = 1;
                break;
            case R.id.rl_ali_pay:
                rbMale.setChecked(false);
                rbFemale.setChecked(true);
                type = 2;
                break;
            case R.id.btn_pay:
                if (listener != null){
                    Logger.d("type:"+type);
                    listener.onSelectClick(type);
                    dismiss();
                }
                break;
        }
    }

    @Override
    public void show() {
        super.show();
        /**
         * 设置宽度全屏，要设置在show的后面
         */
        if (getWindow() != null) {
            WindowManager.LayoutParams params = getWindow().getAttributes();
            params.width = WindowManager.LayoutParams.MATCH_PARENT;
            params.height = WindowManager.LayoutParams.WRAP_CONTENT;
            getWindow().setAttributes(params);
            getWindow().setGravity(Gravity.BOTTOM);
            setCanceledOnTouchOutside(true);
        }
        if (type == 0)
            dismiss();
    }

    public interface OnSelectClickListener {
        void onSelectClick(int selectType);
    }

    public ChooseDeviceDialog setListener(ChooseDeviceDialog.OnSelectClickListener listener) {
        this.listener = listener;
        return this;
    }
}
