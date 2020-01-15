package com.example.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.view.KeyEvent;

import com.example.base.Constant;
import com.example.bean.MessageEvent;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;


public class MusicIntentReceiver extends BroadcastReceiver {
    int i=0;
    @Override
    public void onReceive(Context context, Intent intent) {
        i++;
        //获得触发的Action
        String action = intent.getAction();
        //获取KeyEvent对象
        KeyEvent event = intent.getParcelableExtra(Intent.EXTRA_KEY_EVENT);
        //获取按键触发的时间
        long eventtime = event.getEventTime();
        //获得按键码
        int keycode = event.getKeyCode();
        Logger.e("MusicIntentReceiver action:" + action);
        Logger.e("keycode:" + keycode);
        Logger.e("init i:"+i);

        if (keycode == KeyEvent.KEYCODE_MEDIA_PLAY || keycode == KeyEvent.KEYCODE_MEDIA_PAUSE) {
            EventBus.getDefault().post(new MessageEvent(Constant.EVENT_BLUETOOTH_RECORDING_EVENT,null));
        }
    }
}
