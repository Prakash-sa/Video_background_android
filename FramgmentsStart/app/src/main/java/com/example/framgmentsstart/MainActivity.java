package com.example.framgmentsstart;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    private VideoView mvideo;
    MediaPlayer mmediaplayer;
    int cur;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mvideo=findViewById(R.id.background_vid);
        Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.socialmedia);
        mvideo.setVideoURI(uri);
        mvideo.start();
        mvideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mmediaplayer=mediaPlayer;
                mmediaplayer.setLooping(true);
                if(cur!=0){
                    mmediaplayer.seekTo(cur);
                    mmediaplayer.start();
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mmediaplayer.release();
        mmediaplayer=null;
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Capture the current video position and pause the video.
        cur = mmediaplayer.getCurrentPosition();
        mvideo.pause();
    }



    @Override
    protected void onResume() {
        super.onResume();
        mvideo.start();
    }
}
