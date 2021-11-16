package com.example.rgbconverter2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import Model.Rgb;

public class MainActivity extends AppCompatActivity {

    /*
    * Retrieve the hex values
     */
    public String getHexValue() {
        return hexValue;
    }

    /*
    * set hex value
     */
    public void setHexValue(String hexValue) {
        this.hexValue = hexValue;

        TextView tView = findViewById(R.id.HexValue);
        tView.setText("Hex: " + hexValue);
    }

    private Rgb rgbValue;
    private String hexValue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rgbValue = new Rgb((char)0, (char)0, (char)0);
        initializeSeekBars();
    }

    /*
    * Initializes the seekbars settings
     */
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

    /*
    * Sets the given seekbars listener.
     */
    private void setSeekBarListener(SeekBar bar)
    {
        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                switch (seekBar.getId())
                {
                    case R.id.r:
                        rgbValue.setRValue((char)progress);
                        ((TextView)findViewById(R.id.rCurValue)).setText("" + (int)rgbValue.getRValue());
                        break;
                    case R.id.g:
                        rgbValue.setGValue((char)progress);
                        ((TextView)findViewById(R.id.gCurValue)).setText("" + (int)rgbValue.getGValue());
                        break;
                    case R.id.b:
                        rgbValue.setBValue((char)progress);
                        ((TextView)findViewById(R.id.bCurValue)).setText("" + (int)rgbValue.getBValue());
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
}