package com.example.administrator.revcjsrbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    JsrBroadcast mJsrBroadcast;
    private Button mButton;
    private Button mBtnLed;
    private Button mBtnOff;

    private EditText light_Edt;
    private EditText level_Edt;
    private EditText onTime_Edt;
    private EditText offTime_Edt;
    private int mLevel = Integer.MAX_VALUE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mButton = (Button) findViewById(R.id.button);
        mBtnLed = (Button) findViewById(R.id.btn_led);
        mBtnOff = (Button) findViewById(R.id.btn_off);
        mButton.setText("GPSOFF");

        light_Edt = (EditText) findViewById(R.id.light_Edt);
        level_Edt = (EditText) findViewById(R.id.level_Edt);
        onTime_Edt = (EditText) findViewById(R.id.onTime_Edt);
        offTime_Edt = (EditText) findViewById(R.id.offTime_Edt);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button) v;
                Intent intent = new Intent();
                if (btn.getText().equals("GPSOFF")) {
                    intent.setAction(Gqtfield.ACTION_ENABLE_GPS);
                    btn.setText("GPSON");
                    btn.setTextColor(Color.RED);
                    Toast.makeText(MainActivity.this, "GPS打开成功" + btn.getCurrentTextColor(), Toast.LENGTH_SHORT).show();
                } else {
                    intent.setAction(Gqtfield.ACTION_DISABLE_GPS);
                    btn.setText("GPSOFF");
                    Toast.makeText(MainActivity.this, "GPS关闭成功", Toast.LENGTH_SHORT).show();
                }
                sendBroadcast(intent);

            }
        });

        mBtnLed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sColor = light_Edt.getText().toString().trim();
                String sLevel = level_Edt.getText().toString().trim();
                String sOnTime = onTime_Edt.getText().toString().trim();
                String sOffTime = offTime_Edt.getText().toString().trim();
                Toast.makeText(MainActivity.this, "send color = " + sColor + " level = " + sLevel + " ledBrightTime = " + sOnTime + " ledOffTime = " + sOffTime, Toast.LENGTH_SHORT).show();

                if (sColor == null || sColor.equals("")) {
                    Toast.makeText(MainActivity.this, "颜色值为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sLevel == null || sLevel.equals("")) {
                    Toast.makeText(MainActivity.this, "优先级为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sOnTime == null || sOnTime.equals("")) {
                    Toast.makeText(MainActivity.this, "灯亮时间为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (sOffTime == null || sOffTime.equals("")) {
                    Toast.makeText(MainActivity.this, "灯灭时间为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(Gqtfield.ACTION_CONTROL_LED);
                Bundle bundle = new Bundle();

                int color = Integer.decode(sColor).intValue();
                int level = Integer.valueOf(sLevel);
                bundle.putInt(Gqtfield.EXTRA_LED_COLOR, color);
                bundle.putInt(Gqtfield.EXTRA_LED_LEVEL, level);
                bundle.putString(Gqtfield.EXTRA_LED_BRIGHT_TIME, sOnTime);
                bundle.putString(Gqtfield.EXTRA_LED_OFF_TIME, sOffTime);
                intent.putExtras(bundle);
                sendBroadcast(intent);

            }
        });

        mBtnOff.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sLevel = level_Edt.getText().toString().trim();
                if (sLevel == null || sLevel.equals("")) {
                    Toast.makeText(MainActivity.this, "优先级为空", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(Gqtfield.ACTION_CONTROL_LED_OFF);
                Bundle bundle = new Bundle();
                int level = Integer.valueOf(sLevel);
                bundle.putInt(Gqtfield.EXTRA_LED_LEVEL, level);
                intent.putExtras(bundle);
                sendBroadcast(intent);
            }
        });

        mJsrBroadcast = new JsrBroadcast();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Gqtfield.ACTION_PTT_UP);
        filter.addAction(Gqtfield.ACTION_PTT_DOWN);
        filter.addAction(Gqtfield.ACTION_SWITCH_PTT_GROUP_LEFT);
        filter.addAction(Gqtfield.ACTION_SWITCH_PTT_GROUP_RIGHT);
        filter.addAction(Gqtfield.ACTION_CONTROL_LED);
        filter.addAction(Gqtfield.ACTION_CONTROL_LED_OFF);
        registerReceiver(mJsrBroadcast, filter);

    }

    class JsrBroadcast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String sKey = intent.getAction();
            switch (sKey) {
                case Gqtfield.ACTION_PTT_UP:
                    Toast.makeText(MainActivity.this, "PPT UP", Toast.LENGTH_SHORT).show();
                    break;
                case Gqtfield.ACTION_PTT_DOWN:
                    Toast.makeText(MainActivity.this, "PPT DOWN", Toast.LENGTH_SHORT).show();
                    break;
                case Gqtfield.ACTION_SWITCH_PTT_GROUP_LEFT:
                    Toast.makeText(MainActivity.this, "ACTION_SWITCH_PTT_GROUP_LEFT", Toast.LENGTH_SHORT).show();
                    break;
                case Gqtfield.ACTION_SWITCH_PTT_GROUP_RIGHT:
                    Toast.makeText(MainActivity.this, "ACTION_SWITCH_PTT_GROUP_RIGHT", Toast.LENGTH_SHORT).show();
                    break;
                case Gqtfield.ACTION_CONTROL_LED:
                    Toast.makeText(MainActivity.this, "ACTION_CONTROL_LED", Toast.LENGTH_SHORT).show();
                    Bundle bundle = intent.getExtras();
                    int ledColor = bundle.getInt(Gqtfield.EXTRA_LED_COLOR, -1);
                    if ((ledColor & 0xffffff) == 0xff0000) {
                        Toast.makeText(MainActivity.this, "RED", Toast.LENGTH_SHORT).show();
                    } else if ((ledColor & 0xffffff) == 0x00ff00) {
                        Toast.makeText(MainActivity.this, "BULE", Toast.LENGTH_SHORT).show();
                    } else if ((ledColor & 0xffffff) == 0x0000ff) {
                        Toast.makeText(MainActivity.this, "GREEN", Toast.LENGTH_SHORT).show();
                    } else {

                    }
                    int ledLevel = bundle.getInt(Gqtfield.EXTRA_LED_LEVEL, -1);
                    String ledBrightTime = bundle.getString(Gqtfield.EXTRA_LED_BRIGHT_TIME, "0");
                    long brightTime = Long.valueOf(ledBrightTime);
                    String ledOffTime = bundle.getString(Gqtfield.EXTRA_LED_OFF_TIME, "0");
                    long offTime = Long.valueOf(ledOffTime);

                    if ((ledLevel <= mLevel) && (ledLevel > 0)) {
                        Toast.makeText(MainActivity.this, "ledLevel " + ledLevel + "高于上一个优先级", Toast.LENGTH_SHORT).show();
                        Toast.makeText(MainActivity.this, "color = " + ledColor + " level = " + ledLevel + " ledBrightTime = " + brightTime + " ledOffTime = " + offTime, Toast.LENGTH_SHORT).show();
                        mLevel = ledLevel;
                    }else{
                        Toast.makeText(MainActivity.this, "ledLevel " + ledLevel + "低于上一个优先级", Toast.LENGTH_SHORT).show();
                    }
                    break;
                case Gqtfield.ACTION_CONTROL_LED_OFF:
                    Toast.makeText(MainActivity.this, "ACTION_CONTROL_LED_OFF", Toast.LENGTH_SHORT).show();
                    Bundle bul = intent.getExtras();
                    int level = bul.getInt(Gqtfield.EXTRA_LED_LEVEL, -1);
                    if ((level <= mLevel) && (level > 0)) {
                        Toast.makeText(MainActivity.this, "ledLevel " + level + "高于上一个优先级", Toast.LENGTH_SHORT).show();
                        mLevel = level;
                    }else{
                        Toast.makeText(MainActivity.this, "ledLevel " + level + "低于上一个优先级", Toast.LENGTH_SHORT).show();
                    }
                    break;
            }
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(mJsrBroadcast);
    }
}
