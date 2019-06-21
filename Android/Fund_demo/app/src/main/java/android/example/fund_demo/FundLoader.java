package android.example.fund_demo;

import android.content.Context;
import android.example.fund_demo.Fund.Fund;
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
import java.util.HashMap;
import java.util.Map;

public class FundLoader extends AsyncTaskLoader<ArrayList<Fund>> {


    private String API;
    private String token;
    private final String DATA_URL = "https://owl.cmoney.com.tw/OwlApi/api/v2/json/";
    private final String TOKEN_URL = "https://owl.cmoney.com.tw/OwlApi/auth";
    private final String BETA_API = "L1-352100a";
    private final String RETURN_API = "L1-352433b";

    private APIManager apiManager;
    private Map<String, String> map_r_1y, map_r_2y;
    private ArrayList<Fund> ResultFunds = null;

    public FundLoader(@NonNull Context context, String _api) {
        super(context);
        API = _api;
        map_r_1y = new HashMap<>();
        map_r_2y = new HashMap<>();
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        if (ResultFunds != null)
        {
            Log.d("auau", "Loader had exist");
        }
        else
            forceLoad();
    }

    @Nullable
    @Override
    public ArrayList<Fund> loadInBackground()
    {

//        Log.d("auau", "loadinBK");
//
//        try {
//            JSONObject jObj= new JSONObject(GetToken());
//            String token = jObj.getString("token");
//            Log.d("auau Token", token);
//
//            String jsonResult = GetData(token, API);
//            return null;
//
//        } catch (JSONException e) {
//            e.printStackTrace();
//        }
//
//        return null;
        apiManager = new APIManager();
        token = apiManager.GetToken();
        SetReturnMap();
        String JSONResult = apiManager.GetData(token, API);
        ResultFunds = GetFunds(JSONResult);

        return ResultFunds;
    }

    private void SetReturnMap()
    {
        String jsonResult = apiManager.GetData(token, "L1-352433b");    // Get 年化報酬率
        try
        {
            JSONObject j_obj = new JSONObject(jsonResult);
            JSONArray jData = j_obj.getJSONArray("Data");

            for (int i = 0; i < jData.length(); i++)
            {
                JSONArray jFund = jData.getJSONArray(i);
                String fundName = jFund.getString(0);
                String return_1y = jFund.getString(6);
                String return_2y = jFund.getString(7);
                map_r_1y.put(fundName, return_1y);
                map_r_2y.put(fundName, return_2y);
                Log.d("auau Set map", fundName + ", " + return_1y + ", " + return_2y);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private ArrayList<Fund> GetFunds(String _jsonData)
    {
        ArrayList<Fund> resultStocks = new ArrayList<Fund>();
        try
        {
            JSONObject jobj = new JSONObject(_jsonData);
            Log.d("all", _jsonData);

            // Get String data of Stock
            JSONArray jData = jobj.getJSONArray("Data");
            for (int i = 0; i < jData.length(); i++)
            {
                JSONArray jStock = jData.getJSONArray(i);
                String[] dataStrings = new String[jStock.length()];

                if (jStock.getString(3).equals("") || !IsTaiwan(jStock.getString(12)))
                {
                    Log.d("auau Data Error:", "empty or 下市 or 境外");
                }
                else
                {
                    for (int j = 0; j < jStock.length(); j++)
                    {
                        dataStrings[j] = jStock.getString(j);

                        if (i == 0)
                        {
                            Log.d("auau data info", j + " " + jStock.getString(j));
                        }
                    }

                    Fund newFund = CreateNewFund(dataStrings);
                    if ( newFund != null){
                        resultStocks.add(newFund);
                    }
                }
            }
            return resultStocks;
        }
        catch (JSONException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    private boolean IsTaiwan(String _input)
    {
        if (_input.length() != 3)
            return false;
        return _input.charAt(2) == '1';
    }


    private Fund CreateNewFund(String[] _data) {
        Fund fund = new Fund(_data);

        // Get fund beta
        String risk_api = BETA_API + "/" + fund.GetCode() + "/1";
        Log.d("auau api :", risk_api);

        String betaResult = apiManager.GetData(token, risk_api);

        if (apiManager.IsSuccessful() && !betaResult.isEmpty())
        {
            try
            {
                JSONObject jo_beta = new JSONObject(betaResult);
                JSONArray ja_beta = jo_beta.getJSONArray("Data");
                JSONArray ja_beta_f = ja_beta.getJSONArray(0);

                String beta_1y = ja_beta_f.getString(5);
                String beta_2y = ja_beta_f.getString(6);
                String return_1y = map_r_1y.get(fund.GetCode());
                String return_2y = map_r_2y.get(fund.GetCode());

                fund.SetBeta_1y(beta_1y);      // 一年 Beta
                fund.SetBeta_2y(beta_2y);      // 兩年 Beta
                fund.SetReturn_1y(return_1y);  // 一年 報酬率
                fund.SetReturn_2y(return_2y);  // 一年 報酬率
                Log.d("auau create code", fund.GetCode());
                Log.d("auau beta", beta_1y + ", " + beta_2y);
                Log.d("auau return", return_1y + ", " + return_2y);

                return fund;

            } catch (JSONException e)
            {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            return null;
        }

    }
}
