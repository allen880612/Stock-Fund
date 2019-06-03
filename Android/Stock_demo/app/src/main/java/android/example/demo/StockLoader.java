package android.example.demo;

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
import java.util.ArrayList;
import java.util.Calendar;

public class StockLoader extends AsyncTaskLoader<StockManager>
{
    private String mUrl;
    private StockManager sManager;
    // Constructor
    public StockLoader(@NonNull Context context, String _url) {
        super(context);
        mUrl = _url;
        sManager = new StockManager();
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
        String jsonData_0 = GetData(mUrl, "0");
        String jsonData_1 = GetData(mUrl, "1");

        sManager.AddStocktoMap("s1", GetStock(jsonData_0));
        sManager.AddStocktoMap("s2", GetStock(jsonData_1));
        return sManager;
    }



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
    private String GetData(String _url, String _signal)
    {
        String result = " ";

        HttpURLConnection connection = null;
        BufferedReader reader = null;
        try {
            URL url = new URL(_url + _signal);
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(stream));
            StringBuffer buffer = new StringBuffer();
            String line = "";
            while ((line = reader.readLine()) != null) {
                buffer.append(line+"\n");
                String re = new String(line.getBytes("ISO-8859-1"), "UTF-8");
                Log.d("Response: ", "> " + line);   //here u ll get whole response...... :-)
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

    // Get Stock object from parse json
    private ArrayList<StockHolder> GetStock(String _jsonArray)
    {
//        if (_jsonArray.isEmpty() || _jsonArray.equals(" "))
//        {
//            Log.d("Error", "Sever no data or no NetWork");
//            return null;
//        }
        String mResult = "";
        ArrayList<StockHolder> resultStock = new ArrayList<StockHolder>();
        try {
            JSONObject jobj = new JSONObject(_jsonArray);
            Log.d("all", _jsonArray);
            String title = jobj.getString("Title");
            JSONArray data = jobj.getJSONArray("Data");
            String date = jobj.getString("Date");

            for (int i = 0; i < data.length(); ++i)
            {
                try {
                    JSONObject jdata = data.getJSONObject(i);
                    String mCode = jdata.getString("code");
                    String mType = jdata.getString("type");
                    String mDate = jdata.getString("date");
                    String mName = jdata.getString("name");
                    String mPrice = jdata.getString("price");

                    mResult += mCode + " " + mName + " 收盤價：" + mPrice + "\n";
                    Log.d("stockData", mResult + " date:" +  mDate);
                    // Check stock had existed in array
                    Boolean isExisted = false;
                    Boolean isToday = true;

                    if (!mDate.equals(GetDate()))
                        isToday = false;

                    for (StockHolder sh : resultStock)
                    {
                        Log.d("Existed?",  mCode + " = " + sh.code);
                        if (mCode.equals(sh.code))
                        {
                            isExisted = true;
                        }
                    }
                    if (!isExisted /*&& isToday*/)
                        resultStock.add(new StockHolder(mCode, mName, mPrice, mDate, mType));

                    Log.d("run", "GetStock");
                }
                catch (JSONException e)
                {
                }
            }
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return resultStock;
    }
}
