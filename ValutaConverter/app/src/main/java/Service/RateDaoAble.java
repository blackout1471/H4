package Service;

import java.util.ArrayList;

import Model.Rate;

public interface RateDaoAble {
    /**
    * Retrieves all rates available
    * @return Returns a list of all the available rates.
     **/
    ArrayList<Rate> getRates();

    /**
    * Converts a currency to all the other currencies available.
    * @param fromCurrency the currency to convert from.
    * @param amountToConvert the amount of the from currency, that should be converted to toCurrency.
    * @return Returns a single rate with the converted amount.
     **/
    ArrayList<Rate> convertCurrency(String fromCurrency, double amountToConvert);
}
