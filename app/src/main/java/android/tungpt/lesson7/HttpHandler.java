package android.tungpt.lesson7;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HttpHandler {

    private static final String METHOD_REQUEST_API = "GET";
    private static final String SPACE = "\n";
    private String mResponse;
    private URL mUrl;
    private String mLine;

    public HttpHandler() {
    }

    public String makeServiceCall(String url) {
        mResponse = null;
        try {
            mUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
            connection.setRequestMethod(METHOD_REQUEST_API);
            InputStream inputStream = new BufferedInputStream(connection.getInputStream());
            mResponse = convertStreamToString(inputStream);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mResponse;
    }

    private String convertStreamToString(InputStream stream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder stringBuilder = new StringBuilder();
        try {
            while ((mLine = bufferedReader.readLine()) != null) {
                stringBuilder.append(mLine).append(SPACE);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }
}
