package com.example.base;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.utils.FixMenLeak;

import butterknife.Unbinder;


public abstract class BaseFragment extends Fragment implements BaseView {
    public abstract int getContentViewId();

    public abstract void getUnbinder();

    protected abstract void initAllMembersView(Bundle savedInstanceState);

    protected abstract void disarmState();

    protected Context mContext;
    protected View rootView;
    public Unbinder unbinder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (rootView == null) {
            rootView = inflater.inflate(getContentViewId(), container, false);
        } else {
            //  二次加载删除上一个子view
            ViewGroup viewGroup = (ViewGroup) rootView.getParent();
            if (viewGroup != null) {
                viewGroup.removeView(rootView);
            }
        }
        this.mContext = getContext();
        getUnbinder();
        initAllMembersView(savedInstanceState);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        disarmState();
        FixMenLeak.fixLeak(getContext());
    }

    @Override
    public void showLoading() {
        checkActivityAttached();
        ((BaseActivity) mContext).showLoading();
    }

    @Override
    public void hideLoading() {
        checkActivityAttached();
        ((BaseActivity) mContext).hideLoading();
    }

    @Override
    public void showToast(String msg) {
        checkActivityAttached();
        ((BaseActivity) mContext).showToast(msg);
    }

    @Override
    public void showError() {
        checkActivityAttached();
        ((BaseActivity) mContext).showError();
    }

    @Override
    public void showLog(String msg) {
        checkActivityAttached();
        ((BaseActivity) mContext).showLog(msg);
    }

    /**
     * 检查activity连接情况
     */
    public void checkActivityAttached() {
        if (getActivity() == null) {
            throw new ActivityNotAttachedException();
        }
    }

    public static class ActivityNotAttachedException extends RuntimeException {
        public ActivityNotAttachedException() {
            super("Fragment has disconnected from Activity ! - -.");
        }
    }

}
