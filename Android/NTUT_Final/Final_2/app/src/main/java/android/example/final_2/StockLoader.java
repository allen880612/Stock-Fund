package android.example.final_2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


public class StockLoader extends AsyncTaskLoader<StockManager>
{
    private final String DATA_URL = "http://www.twse.com.tw/exchangeReport/STOCK_DAY?response=json&date=";
    private StockManager sManager;

    private String date;
    private String stockCode[];


    // Constructor
    public StockLoader(@NonNull Context context, String _api, String[] _stockCode) {
        super(context);

        date = GetDate();
        stockCode = _stockCode;
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    @Nullable
    @Override
    public StockManager loadInBackground()
    {

        Log.d("auau", "loadinBK");
        Map<String, ArrayList<StockHolder> > stockMap = new HashMap<>();
        ArrayList<StockHolder> stockData = new ArrayList<StockHolder>();

        for (int i = 0; i < 2; i++)
        {
            try {
                String jsonResult = GetData(stockCode[i]);  // Get json result from api
                StockHolder sh = GetStock(jsonResult);      // parser json to stock info
                stockData.add(sh);
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        sManager = new StockManager(stockData);
        sManager.SortStock();
        sManager.SetTop50();

        return sManager;

    }


    // Get the system date
    private String GetDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);        // get yesterday

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;   // calender start from "0 month"
        int day = calendar.get(Calendar.DATE);

        String date = String.valueOf(year);
        if (month < 10)
            date += "0";
        date += String.valueOf(month);
        if (day < 10)
            date += "0";
        date += String.valueOf(day);

        Log.d("Date", date);
        return date;
    }

    // Get json data from sever
    private String GetData(String _code)
    {
        String result = " ";
        HttpURLConnection connection = null;
        BufferedReader reader = null;

        // token get fail
        if (_code.equals(" "))
        {
            return result;
        }
        try {
            URL url = new URL(DATA_URL + date + "&stockNo=" +_code);
//            String userCredentials = "username:password";
            //String basicAuth = "Bearer " + (URLEncoder.encode(_token, "UTF-8"));
            //String basicAuth = "Bearer " + _token;
            //connection.setRequestProperty("Authorization", basicAuth);
            connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setRequestMethod("POST");
            connection.connect();
            int response_code = connection.getResponseCode();
            String response = Integer.toString(response_code);
            if (response_code != 200)
            {
                Log.d("auau Error : ", response);
                return result;
            }
            Log.d("auau connect success : ", response);

            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();

            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                String re = new String(line.getBytes("ISO-8859-1"), "UTF-8");
                Log.d("Response: ", "> " + line);   //here u will get whole response...... :-)
            }

            Log.d("run", "GetData success");
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

    // Get Stock object from parse json
    private StockHolder GetStock(String _jsonArray)
    {
//        if (_jsonArray.isEmpty() || _jsonArray.equals(" "))
//        {
//            Log.d("Error", "Sever no data or no NetWork");
//            return null;
//        }

        String mResult = "";
        StockHolder resultStock = null;
        try {
            JSONObject jobj = new JSONObject(_jsonArray);
            Log.d("all", _jsonArray);

            // Get String data of Stock
            JSONArray jData = jobj.getJSONArray("data");
            JSONArray jTitle = jobj.getJSONArray("title");


            for (int i = 0; i < jData.length(); i++)
            {
                JSONArray jStock = jData.getJSONArray(i);
                String[] dataStrings = new String[jStock.length()];

                for (int j = 0; j < jStock.length(); j++)
                {
                    dataStrings[j] = jStock.getString(j);
                    if (i == 1)
                    {
                        Log.d("auau data info", j + " " + jStock.getString(j));
                    }
                }
                resultStock = new StockHolder(dataStrings);

//                String sCode = jStocks.getString(0);    // 股票代號
//                String sName = jStocks.getString(1);    // 股票名稱
//                String sOpen = jStocks.getString(3);    // 開盤價
//                String sHigh = jStocks.getString(4);    // 最高價
//                String sLow = jStocks.getString(5);     // 最低
//                String sClose = jStocks.getString(6);   // 收盤
//                String sUpDown = jStocks.getString(7);  // 漲跌
//                String sUPDownP = jStocks.getString(8); // 漲幅(%)
//
//                String result = sCode + " " + sName + " " + sOpen + " "
//                                + sHigh + " " + sLow + " " + sClose;
//                Log.d("auau Result " , result);

                //resultStock.add(new StockHolder());

                //Log.d("auau Stock " , jStocks.toString());

//                String 切割法
//                String temp = jStock.toString();
//                String[] ary = temp.split(",");
//                for (int j = 0; j < ary.length; j++)
//                {
//
//                    Log.d("auau String ", ary[j]);
//                }

            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultStock;
    }

//    private ArrayList<StockHolder> SortStock(ArrayList<StockHolder> _stocks, Comparator _comparator)
//    {
//        // sort by close
//        Collections.sort(_stocks, _comparator);
//
//        for ( StockHolder sh : _stocks )
//        {
//            Log.d("auau sort:", sh.GetCode() + ", upDown percent: " + sh.GetUpDownP());
//        }
//        return _stocks;
//    }

}
