package com.example.ex14_service_broadcastreceiver_contentprovider;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {

    //음악을 재생하는 서비스~
    MediaPlayer mp;

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }


    //1.startService라는 명령이 들어오면 -> onStartCommand() 이 메소드 실행
    //2.stopService라는 명령이 들어오면 -> onDestroy() 이 메소드가 실행

    //1.startService라는 명령이 들어오면 -> onStartCommand() 이 메소드 실행
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("서비스1", "onStartCommand() 33333");
        mp = MediaPlayer.create(this, R.raw.song1);
        mp.setLooping(true);//무한 반복 재생
        mp.start();

        return super.onStartCommand(intent, flags, startId);
    }

    //2.stopService라는 명령이 들어오면 -> onDestroy() 이 메소드가 실행

    @Override
    public void onDestroy() {
        super.onDestroy();

        mp.stop();
    }
}