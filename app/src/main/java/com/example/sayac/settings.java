package com.example.sayac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

public class settings extends AppCompatActivity {
    Button upperPlus ,upperMinus ,lowerPlus ,lowerMinus;
    CheckBox upperVib , upperSound ,lowerVib ,lowerSound;
    TextView upperLimit,lowerLimit;

    SettingsClass settingsClass;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        upperPlus = (Button) findViewById(R.id.upperPlus);
        upperMinus = (Button) findViewById(R.id.buUpperMinus);
        lowerPlus = (Button) findViewById(R.id.lowerPlus);
        lowerMinus = (Button) findViewById(R.id.lowerMinus);
        upperVib = (CheckBox) findViewById(R.id.upperVib);
        upperSound = (CheckBox) findViewById(R.id.upperSound);
        lowerVib = (CheckBox) findViewById(R.id.lowerVib);
        lowerSound = (CheckBox) findViewById(R.id.lowerSound);
        upperLimit = (TextView) findViewById(R.id.upperLimit);
        lowerLimit = (TextView) findViewById(R.id.lowerLimit);

        settingsClass =SettingsClass.getSettingsClass(getApplicationContext());

        upperPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.upperLimit++;
                upperLimit.setText(String.valueOf(settingsClass.upperLimit));
            }
        });

        upperMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.upperLimit--;
                upperLimit.setText(String.valueOf(settingsClass.upperLimit));
            }
        });

        lowerPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.lowerLimit++;
                lowerLimit.setText(String.valueOf(settingsClass.lowerLimit));
            }
        });

        lowerMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                settingsClass.lowerLimit--;
                lowerLimit.setText(String.valueOf(settingsClass.lowerLimit));
            }
        });

        upperVib.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.upperVib =b;
            }
        }));

        upperSound.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.upperSound =b;
            }
        }));
        lowerVib.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.lowerVib =b;
            }
        }));

        lowerSound.setOnCheckedChangeListener((new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                settingsClass.lowerSound =b;
            }
        }));

        upperLimit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(upperLimit.getText().toString().length() !=0){
                    settingsClass.upperLimit=Integer.parseInt(upperLimit.getText().toString());
                }

            }
        });

        lowerLimit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(lowerLimit.getText().toString().length() !=0){
                    settingsClass.lowerLimit=Integer.parseInt(lowerLimit.getText().toString());
                }

            }
        });





    }
    @Override
    protected void onResume() {
        super.onResume();
        upperLimit.setText(String.valueOf(settingsClass.upperLimit));
        lowerLimit.setText(String.valueOf(settingsClass.lowerLimit));
        upperVib.setChecked(settingsClass.upperVib);
        upperSound.setChecked(settingsClass.upperSound);
        lowerVib.setChecked(settingsClass.lowerVib);
        lowerSound.setChecked(settingsClass.lowerSound);
    }

    @Override
    protected void onPause() {
        super.onPause();
        settingsClass.saveValues();
    }
}