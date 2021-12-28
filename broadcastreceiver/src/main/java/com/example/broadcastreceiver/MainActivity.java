package com.example.broadcastreceiver;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView iv1;
    EditText edt1;
    MyBroadcastReceiver br1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv1 = findViewById(R.id.ivBattery);
        edt1 = findViewById(R.id.edtBattery);

        br1 = new MyBroadcastReceiver();

    }

    //1.BR을 등록 -> onResume()
    //2.BR을 등록해지 -> onPause()


    @Override
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter1 = new IntentFilter();
        intentFilter1.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(br1, intentFilter1);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(br1);
    }

    class MyBroadcastReceiver extends BroadcastReceiver
    {

        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();

            if(action.equals(Intent.ACTION_BATTERY_CHANGED))
            {
                int remain = intent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
                edt1.setText("현재 배터리: "+remain + "%");

                if(remain >=90)
                {
                    iv1.setImageResource(R.drawable.battery_100);
                }
                else if(remain >=70)
                {
                    iv1.setImageResource(R.drawable.battery_80);
                }
                else if(remain >=50)
                {
                    iv1.setImageResource(R.drawable.battery_60);
                }
                else if(remain >=10)
                {
                    iv1.setImageResource(R.drawable.battery_20);
                }
                else
                {
                    iv1.setImageResource(R.drawable.battery_0);
                }

                int plug = intent.getIntExtra(BatteryManager.EXTRA_PLUGGED, 0);
                switch (plug)
                {
                    case 0:
                        edt1.setText("전원 연결 안됨!");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_AC:
                        edt1.setText("AC전원 연결!");
                        break;
                    case BatteryManager.BATTERY_PLUGGED_USB:
                        edt1.setText("USB전연 연결!");
                        break;
                }
            }
        }
    }
}