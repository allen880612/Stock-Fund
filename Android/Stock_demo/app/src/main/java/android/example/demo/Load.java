package android.example.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Load extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<StockManager>{

    private final String URL = "URL";
    private String severURL;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        severURL = "https://ntutwebtest.000webhostapp.com/Untitled-4/view/index.php?signal=";
        if(LoaderManager.getInstance(this).getLoader(0) == null)
        {
           LoaderManager.getInstance(this).initLoader(0,null,this);
        }
        //LoadDataFromSever();
}

    void LoadDataFromSever()
    {
        Bundle queryBundle = new Bundle();
        //severURL = "https://ntutwebtest.000webhostapp.com/Untitled-4/view/index.php?signal=";
        queryBundle.putString(URL, severURL);
        LoaderManager.getInstance(this).restartLoader(0, queryBundle, this);
    }

    @NonNull
    @Override
    public Loader<StockManager> onCreateLoader(int i, @Nullable Bundle args)
    {
        Log.d("auau", "Start Load");
        Intent mIntent = new Intent(this,MainActivity.class);
//        String mURL = "";
//        if (args != null) {
//            mURL = args.getString("queryString");
//        }
        return new StockLoader(this, severURL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<StockManager> loader, StockManager stockManager) {
        Log.d("auau", "Finish Load");
        Intent mIntent = new Intent(this,MainActivity.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("STOCK_1", (Serializable)stockManager.GetArrayList("s1"));
        mBundle.putSerializable("STOCK_2", (Serializable)stockManager.GetArrayList("s2"));
        mIntent.putExtras(mBundle);
        startActivity(mIntent);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<StockManager> loader) {

    }
}
