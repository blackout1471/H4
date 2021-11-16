package Service;

import android.util.Log;

import com.google.gson.Gson;
import java.lang.reflect.Field;
import java.util.ArrayList;
import Model.FixerRatesDto;
import Model.FixerRatesRootDto;
import Model.Rate;

public class FixerRateDao implements RateDaoAble {

    private final ClientAble client;
    private final Gson jsonSerializer;

    private final String baseUrl;
    private final String apiKey;

    public FixerRateDao(ClientAble _client, String _apiKey)
    {
        client = _client;
        jsonSerializer = new Gson();
        baseUrl = "http://data.fixer.io/api/";
        apiKey = _apiKey;
    }

    @Override
    public ArrayList<Rate> getRates() {
        ArrayList<Rate> rates = new ArrayList<Rate>();
        String resource = "latest";
        String url = buildUrl(resource);

        try {
            String response = client.get(url);
            FixerRatesRootDto rootDto = jsonSerializer.fromJson(response, FixerRatesRootDto.class);
            FixerRatesDto dto = rootDto.rates;

            // Do some reflection
            Field[] fields = FixerRatesDto.class.getDeclaredFields();
            for (Field field : fields) {
                double val = field.getDouble(dto);
                String name = field.getName();
                rates.add(new Rate(name, val));
            }
        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
        }

        return rates;
    }

    @Override
    public ArrayList<Rate> convertCurrency(String fromCurrency, double amountToConvert) {
        ArrayList<Rate> rates = getRates();
        ArrayList<Rate> newRates = new ArrayList<Rate>();

        Rate curRate = rates.stream().filter(e -> e.getName() == fromCurrency).findFirst().orElse(null);
        double euroValue = amountToConvert / curRate.getValue();

        for (Rate rate : rates)
            newRates.add(new Rate(rate.getName(), rate.getValue() * euroValue));

        return rates;
    }

    /**
     * Method builds the url from the given resource and adds api key.
     * @param resource The resource which should be appended to url.
     * @return The full url to the given resource.
     */
    private String buildUrl(String resource)
    {
        String finalResource = baseUrl + resource;

        if (finalResource.contains("?"))
            finalResource += "&access_key="+apiKey;
        else
            finalResource += "?access_key="+apiKey;

        return finalResource;
    }
}
