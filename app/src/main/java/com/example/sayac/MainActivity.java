package com.example.sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.MergeCursor;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.MediaPlayer;
import android.media.audiofx.Equalizer;
import android.os.Build;
import android.os.Bundle;
import android.os.ConditionVariable;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView value;
    Button plus , minus , ayar;
    int counter =0;
    SettingsClass settingsClass;
    Vibrator vibrator;
    MediaPlayer player;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        plus = (Button) findViewById(R.id.butonartı);
        minus = (Button) findViewById(R.id.butoneksi);
        ayar = (Button) findViewById(R.id.butonayar);
        value = (TextView) findViewById(R.id.value);
        Context context =getApplicationContext();
        settingsClass = SettingsClass.getSettingsClass(context);

        vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

        player = MediaPlayer.create(context,R.raw.beep);

        plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueUpdate(1);
            }
        });

        minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                valueUpdate(-1);
            }
        });

        ayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,settings.class));
            }
        });
    }
    public void valueUpdate(int step){
        if(step <0){
            if(settingsClass.currentValue + step < settingsClass.lowerLimit)
            {
                settingsClass.currentValue=settingsClass.lowerLimit;
                if (settingsClass.lowerVib == true){alertVib();}
                if (settingsClass.lowerSound == true){alertSound();}
            }
            else
                settingsClass.currentValue += step;
        }
        else
        {
            if(settingsClass.currentValue + step > settingsClass.upperLimit)
            {
                settingsClass.currentValue=settingsClass.upperLimit;
                if (settingsClass.upperVib == true){alertVib();}
                if (settingsClass.upperSound == true){alertSound();}
            }
            else
                settingsClass.currentValue += step;
        }
        value.setText(String.valueOf(settingsClass.currentValue));


    }



    public void alertVib(){
        if(vibrator.hasVibrator()){
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(1000,VibrationEffect.DEFAULT_AMPLITUDE));
            }
            else
                vibrator.vibrate(1000);
        }
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();

        switch (keyCode){
            case KeyEvent.KEYCODE_VOLUME_UP:
                if(action==KeyEvent.ACTION_DOWN){valueUpdate(5);}return true;
            case KeyEvent.KEYCODE_VOLUME_DOWN:
                if(action==KeyEvent.ACTION_DOWN){valueUpdate(-5);}return true;
        }


        return super.dispatchKeyEvent(event);
    }

    public void alertSound(){
        player.seekTo(0);
        player.start();
    }

    protected void onResume() {
        super.onResume();
        value.setText(String.valueOf(settingsClass.currentValue));
    }

    @Override
    protected void onPause() {
        super.onPause();
        settingsClass.saveValues();
    }
}