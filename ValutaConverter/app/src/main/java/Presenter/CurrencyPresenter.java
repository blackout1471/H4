package Presenter;

import java.util.ArrayList;

import Model.Rate;
import Service.Client;
import Service.ClientAble;
import Service.FixerRateDao;
import Service.RateDaoAble;

public class CurrencyPresenter {
    private final ClientAble client;
    private final String key;

    private final View view;
    private final RateDaoAble rate;

    public CurrencyPresenter(View _view) {
        key = "531faf8ed3b3e8fdaf6c5b6891938033";
        client = new Client();

        view = _view;
        rate = new FixerRateDao(client, key);

        new Thread(new Runnable() {
            @Override
            public void run() {
                view.updateRates(rate.getRates());
            }
        }).start();
    }

    public void convertToRate(String currentCurrency, double amount) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                view.rateConverted(rate.convertCurrency(currentCurrency, amount));
            }
        }).start();
    }

    public interface View {
        void updateRates(ArrayList<Rate> rates);
        void rateConverted(ArrayList<Rate> rates);
    }
}
