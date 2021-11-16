package Service;

import com.google.gson.Gson;

import Model.FortuneTellerDto;

public class FortuneService implements FortuneAble {

    private final String baseUrl = "http://yerkee.com/api/";
    private ClientAble client;
    private Gson jsonConverter;

    public FortuneService(ClientAble _client, Gson _jsonConverter)
    {
        client = _client;
        jsonConverter = _jsonConverter;
    }

    @Override
    public FortuneTellerDto getFortune() {
        String response = client.get(baseUrl + "fortune");
        return jsonConverter.fromJson(response, FortuneTellerDto.class);
    }
}
