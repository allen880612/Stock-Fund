package android.example.fund_demo;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class APIManager {

    public APIManager()
    {
        Initialize();
    }

    private void Initialize()
    {
        token = GetToken();
    }

    // Get the token
    private String GetToken()
    {
        String token = " ";

        // 帳號設定------------
        String appid= "20190507130014440"; //cycu.owl@gmail.com
        String appsecret = "fffee680708411e9aa98000c29beef84";
        // 連線---------------
//        String token_url = "https://owl.cmoney.com.tw/OwlApi/auth";
//        String data_url = "https://owl.cmoney.com.tw/OwlApi/api/v2/json/";
        // 組連線參數--------------
        String token_params = "appId=" + appid + "&appSecret=" + appsecret;
        String header1 = "Content-Type";
        String header2 = "application/x-www-form-urlencoded";

        // 步驟一:驗證----------
        HttpURLConnection conn = null;
        BufferedReader reader = null;

        try {
            // Build the URL
            URL url = new URL(TOKEN_URL);
            byte[] postDataBytes = token_params.getBytes("UTF-8");

            // Connect to api
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            //conn.setRequestProperty("Content-Length", String.valueOf(postDataBytes.length));
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.getOutputStream().write(postDataBytes);

            // Ensure the server response
            int response_code = conn.getResponseCode();
            if (response_code != 200)
            {
                Log.d("Get token error", Integer.toString(response_code));
                return token;
            }
            Log.d("Get token success", Integer.toString(response_code));

            // Receive the data and return it
            InputStream stream = conn.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                String re = new String(line.getBytes("ISO-8859-1"), "UTF-8");
                Log.d("Response: ", "> " + line);   //here u will get whole response...... :-)
            }
            Log.d("run", "GetData");
            token = buffer.toString();
            return token;

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return token;
    }

    // Get json data from sever
    public String GetData(String _api)
    {
        String result = " ";
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        // token get fail
        if (token.equals(" "))
        {
            return result;
        }
        try {
            URL url = new URL(DATA_URL + _api);
//            String userCredentials = "username:password";
            //String basicAuth = "Bearer " + (URLEncoder.encode(_token, "UTF-8"));
            String basicAuth = "Bearer " + token;
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestProperty("Authorization", basicAuth);
            connection.setRequestMethod("GET");
            connection.connect();
            int response_code = connection.getResponseCode();

            if (response_code != 200)
            {
                Log.d("auau Error", Integer.toString(response_code));
                return result;
            }
            Log.d("auau connect success", Integer.toString(response_code));

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                String re = new String(line.getBytes("ISO-8859-1"), "UTF-8");
                Log.d("Response: ", "> " + line);   //here u will get whole response...... :-)
            }
            Log.d("run", "GetData");
            return buffer.toString();


        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
            try {
                if (reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    private String API;
    private String token;
    //private String jsonResult;
    private final String DATA_URL = "https://owl.cmoney.com.tw/OwlApi/api/v2/json/";
    private final String TOKEN_URL = "https://owl.cmoney.com.tw/OwlApi/auth";
}
