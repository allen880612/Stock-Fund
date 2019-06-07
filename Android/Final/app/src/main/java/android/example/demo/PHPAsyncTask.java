package android.example.demo;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.ArrayMap;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.WeakReference;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class PHPAsyncTask extends
        AsyncTask<String, Integer,  Map<String, ArrayList< StockHolder >>> {

    private WeakReference<TextView> tv_stock1, tv_stock2, tv_date;
    private WeakReference<RecyclerView> recycleView_1, recycleView_2;
    private WeakReference<ProgressBar> progressBar;
    private WeakReference<Button> btn_refresh;

    private final WeakReference<Context> mContext;
    private StockManager sManager;

    // Constructor RecycleView
    public PHPAsyncTask(Context context, RecyclerView _rv1, RecyclerView _rv2, ProgressBar _pb, TextView _date, Button _btn)
    {
        mContext = new WeakReference<>(context);
        recycleView_1 = new WeakReference<>(_rv1);
        recycleView_2 = new WeakReference<>(_rv2);
        progressBar = new WeakReference<>(_pb);
        tv_date = new WeakReference<>(_date);
        btn_refresh = new WeakReference<>(_btn);

    }

//    // Constructor TextView
//    public PHPAsyncTask(Context context, TextView _tv1, TextView _tv2, ProgressBar _pb)
//    {
//        mainContext = context;
//        tv_stock1 = new WeakReference<>(_tv1);
//        tv_stock2 = new WeakReference<>(_tv2);
//        progressBar = new WeakReference<>(_pb);
//        shManager = new StockManager();
//    }

    // Pre do Something in here
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressBar.get().setProgress(0);
        progressBar.get().setVisibility(View.VISIBLE);
        tv_date.get().setVisibility(View.GONE);
        btn_refresh.get().setEnabled(false);
    }

    // Update
    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);

        progressBar.get().setProgress(values[0]);
    }

    @Override
    protected Map<String, ArrayList< StockHolder >> doInBackground( String... urls) {

        Map<String, ArrayList< StockHolder >> stockMap = new HashMap<>();
        ArrayList<StockHolder> stock = new ArrayList<StockHolder>();

        // Get json data form sever
        String jsonData_0 = GetData(urls[0], "0");
        publishProgress(25);
        String jsonData_1 = GetData(urls[0], "1");
        publishProgress(50);

        // parser json to StockHolder store information
        stockMap.put("s1", GetStock(jsonData_0));   //做多
        publishProgress(75);
        stockMap.put("s2", GetStock(jsonData_1));   //做空
        publishProgress(100);

        Log.d("run", "DoInBackground");
        return stockMap;
    }

    // onPostExecute displays the results of the AsyncTask.
    @Override
    protected void onPostExecute(Map<String, ArrayList< StockHolder >> _result) {

//        StockInfoAdapter adapter1 = new StockInfoAdapter(mContext.get(), _result.get("s1"));
//        StockInfoAdapter adapter2 = new StockInfoAdapter(mContext.get(), _result.get("s2"));
//        recycleView_1.get().setAdapter(adapter1);
//        recycleView_1.get().setLayoutManager(new LinearLayoutManager(mContext.get()));
//        recycleView_2.get().setAdapter(adapter2);
//        recycleView_2.get().setLayoutManager(new LinearLayoutManager(mContext.get()));

        progressBar.get().setVisibility(View.GONE);
        tv_date.get().setVisibility(View.VISIBLE);
        btn_refresh.get().setEnabled(true);

        Log.d("run", "PostExecute");
        Log.d("Result: ", "> " + _result);
    }

    // Get Today date
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

                    mResult += mCode + " " + mName + " " + mPrice + " " + mDate + "\n";

                    // Check stock had existed in array
                    Boolean isExisted = false;
                    Boolean isToday = true;

                    if (!mDate.equals(GetDate()))
                        isToday = false;


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
        Log.d("stockData", mResult);
        return resultStock;
    }

}
