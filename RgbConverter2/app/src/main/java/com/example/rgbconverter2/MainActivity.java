package com.example.rgbconverter2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import Model.Rgb;

public class MainActivity extends AppCompatActivity {

    private Rgb rgbValue;
    private String hexValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgbValue = new Rgb((char)0, (char)0, (char)0);
        initializeSeekBars();
    }

    private void initializeSeekBars() {
        // find all required seekbars
        SeekBar seekR = findViewById(R.id.r);
        SeekBar seekG = findViewById(R.id.g);
        SeekBar seekB = findViewById(R.id.b);

        // Set max values for seekbars
        seekR.setMax(255);
        seekG.setMax(255);
        seekB.setMax(255);

        // Set listeners
        setSeekBarListener(seekR);
        setSeekBarListener(seekG);
        setSeekBarListener(seekB);
    }

    private void setSeekBarListener(SeekBar bar)
    {
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId())
                {
                    case R.id.r:
                        rgbValue.setrValue((char)progress);
                        ((TextView)findViewById(R.id.rCurValue)).setText("" + (int)rgbValue.getrValue());
                        break;
                    case R.id.g:
                        rgbValue.setgValue((char)progress);
                        ((TextView)findViewById(R.id.gCurValue)).setText("" + (int)rgbValue.getgValue());
                        break;
                    case R.id.b:
                        rgbValue.setbValue((char)progress);
                        ((TextView)findViewById(R.id.bCurValue)).setText("" + (int)rgbValue.getbValue());
                        break;
                }
                setHexValue(Integer.toHexString(rgbValue.convertToInt()));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }

    public String getHexValue() {
        return hexValue;
    }

    public void setHexValue(String hexValue) {
        this.hexValue = hexValue;

        TextView tView = findViewById(R.id.HexValue);
        tView.setText("Hex: " + hexValue);
    }
}