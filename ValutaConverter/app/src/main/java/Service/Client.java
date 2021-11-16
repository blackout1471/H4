package Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.stream.Collectors;

public class Client implements ClientAble {

    public Client()
    { }

    @Override
    public String get(String resource) throws Exception{
        HttpURLConnection con = null;
        try
        {
            URL url = new URL(resource);
            con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setRequestProperty("Content-Type", "application/json");
            return readDataFromConnection(con);
        }
        catch (Exception e)
        {
            throw e;
        }
        finally {
            if (con != null)
                con.disconnect();
        }
    }

    /**
     * Reads the input stream from the given connection.
     * @param con The connection to read from.
     * @return Returns the content in input stream from the connection
     * @throws Exception throws if io exception or unknown protocol to read from.
     */
    private String readDataFromConnection(URLConnection con) throws Exception
    {
        try (InputStream in = con.getInputStream())
        {
            return new BufferedReader(new InputStreamReader(in))
                    .lines().collect(Collectors.joining("\n"));
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }
}
