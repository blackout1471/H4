package com.example.fortuneteller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;

import Model.FortuneTellerDto;
import Service.Client;
import Service.FortuneAble;
import Service.FortuneService;

public class MainActivity extends AppCompatActivity {

    private FortuneAble fortuneService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fortuneService = new FortuneService(new Client(), new Gson());
        ((Button)(findViewById(R.id.fortuneBtn))).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fortuneOnClick(v);
            }
        });
    }

    private void onGetFortuneFinished(FortuneTellerDto dto)
    {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ((TextView)findViewById(R.id.fortuneText)).setText(dto.fortune);
            }
        });
    }

    public void fortuneOnClick(View view)
    {
        new Thread(new Runnable() {
            @Override
            public void run() {
                FortuneTellerDto dto = fortuneService.getFortune();
                onGetFortuneFinished(dto);
            }
        }).start();
    }
}