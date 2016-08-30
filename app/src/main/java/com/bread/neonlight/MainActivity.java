package com.bread.neonlight;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends Activity {
    private int currentColor = 0;
    private int[] colors = {
            R.color.color_1,
            R.color.color_2,
            R.color.color_3,
            R.color.color_4,
            R.color.color_5,
            R.color.color_6,
    };
    private int[] ids = {
            R.id.view_1,
            R.id.view_2,
            R.id.view_3,
            R.id.view_4,
            R.id.view_5,
            R.id.view_6,

    };
    TextView[] views = new TextView[ids.length];
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                for (int i = 0; i < ids.length; i++) {
                    views[i].setBackgroundResource(colors[Math.abs((i + currentColor) % ids.length)]);

                }
                currentColor++;
            }

            super.handleMessage(msg);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for (int i = 0; i < ids.length; i++) {
            views[i] = (TextView) findViewById(ids[i]);
        }
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                    handler.sendEmptyMessage(0x123);
            }
        },0,500);

    }
}
