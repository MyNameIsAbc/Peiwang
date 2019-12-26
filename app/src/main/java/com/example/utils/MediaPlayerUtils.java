package com.example.utils;

import android.media.MediaPlayer;

import java.io.IOException;

public class MediaPlayerUtils {
    private static MediaPlayerUtils mediaPlayerUtils;


    private MediaPlayerUtils() {
    }

    public synchronized static MediaPlayerUtils getInstance() {

        if (mediaPlayerUtils == null) {
            mediaPlayerUtils = new MediaPlayerUtils();
        }
        return mediaPlayerUtils;

    }

    MediaPlayer mediaPlayer;

    public void startPlay(String url) {
        try {
            mediaPlayer = new MediaPlayer();

            mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mp) {
                    mediaPlayer.start();
                }
            });
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mediaPlayer=null;
                }
            });
            mediaPlayer.setDataSource(url);
            mediaPlayer.prepareAsync();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
