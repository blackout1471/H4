package com.example.valutaconverter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

import Model.Rate;
import Presenter.CurrencyPresenter;

public class MainActivity extends AppCompatActivity implements CurrencyPresenter.View {
    // Presenter
    CurrencyPresenter presenter;

    // ui
    Button converterButton;
    Spinner dropDownMenu;
    EditText valueText;
    ListView rateConvertedView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new CurrencyPresenter(this);

        dropDownMenu = findViewById(R.id.currencyDropDown);
        converterButton = findViewById(R.id.convertButton);
        valueText = findViewById(R.id.currencyTextField);
        rateConvertedView = findViewById(R.id.currenyListView);

        findViewById(R.id.convertButton).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String currentRate = dropDownMenu.getSelectedItem().toString();
                double amount = Integer.parseInt(valueText.getText().toString());

                presenter.convertToRate(currentRate, amount);
            }
        });
    }

    public void setDropDownRates(ArrayList<Rate> dropDownRates) {
        Context curContext = this;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<String> labels = new ArrayList<>();

                for (int i = 0; i < dropDownRates.size(); i++)
                    labels.add(dropDownRates.get(i).getName());

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(curContext, android.R.layout.simple_spinner_item, labels);
                dropDownMenu.setAdapter(adapter);
            }
        });
    }

    public void setListRateResults(ArrayList<Rate> rates)
    {
        Context curContext = this;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                List<String> ratesLabels = new ArrayList<>();

                for (Rate rate : rates) {
                    ratesLabels.add("name: " + rate.getName() + " value: " + rate.getValue());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(curContext, android.R.layout.simple_list_item_1, ratesLabels);
                rateConvertedView.setAdapter(adapter);
            }
        });
    }

    @Override
    public void updateRates(ArrayList<Rate> rates) {
        setDropDownRates(rates);
    }

    @Override
    public void rateConverted(ArrayList<Rate> rates) {
        setListRateResults(rates);
    }
}