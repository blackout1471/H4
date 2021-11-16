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

        // Create new thread to do network activity.
        // TODO: Move to real service in service.
        new Thread(new Runnable() {
            @Override
            public void run() {
                view.updateRates(rate.getRates());
            }
        }).start();
    }

    /**
     * Converts the given currency and amount to alle the available currencies,
     * and contact the view that the currencies has been converted.
     * @param currentCurrency The 3 letter representing the currency
     * @param amount The amount to convert.
     */
    public void convertToRate(String currentCurrency, double amount) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                view.rateConverted(rate.convertCurrency(currentCurrency, amount));
            }
        }).start();
    }

    public interface View {
        /**
         * Callback for when rates has been updated.
         * @param rates The new rates.
         */
        void updateRates(ArrayList<Rate> rates);

        /**
         * Callback for when rate has been converted.
         * @param rates The new rates converted.
         */
        void rateConverted(ArrayList<Rate> rates);
    }
}
