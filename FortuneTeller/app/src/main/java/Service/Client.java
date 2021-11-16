package Service;

import android.util.Log;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

public class Client implements ClientAble {
    @Override
    public String get(String resource) {
        HttpURLConnection urlConnection = null;

        try {
            URL url = new URL(resource);
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setRequestProperty("Content-Type", "application/json");
            InputStream in = urlConnection.getInputStream();
            String response = readStream(in);
            in.close();
            return response;
        }
        catch (Exception e)
        {
            Log.e("Error", e.getMessage());
        }
        finally
        {
            if (urlConnection != null)
                urlConnection.disconnect();
        }

        return null;
    }

    private String readStream(InputStream in)
    {
        return new BufferedReader(new InputStreamReader(in))
                .lines().parallel().collect(Collectors.joining("\n"));
    }
}
