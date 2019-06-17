package android.example.demo;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Load extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<StockManager>{

    private final String URL = "URL";
    private String severURL;
    private TextView label_menu;
    private ProgressBar progressBar;
    private Button btn_start;
    private boolean isLoading;
    private Intent mIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        Initialize();
    }

    void Initialize()
    {
        label_menu = (TextView) findViewById(R.id.label_menu);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        btn_start = (Button) findViewById(R.id.btn_start);
        isLoading = true;

        progressBar.setVisibility(View.VISIBLE);
        btn_start.setVisibility(View.INVISIBLE);

        severURL = "http://ntutwebtest.000webhostapp.com/Untitled-4/view/index.php?signal=";
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

    public void Start(View view) {
        if (!isLoading)
        {
            startActivity(mIntent);
            Log.d("auau", "not in Loading ");
        }
    }

    @NonNull
    @Override
    public Loader<StockManager> onCreateLoader(int i, @Nullable Bundle args)
    {
        Log.d("auau", "Start Load");
        Intent mIntent = new Intent(Load.this,MainActivity.class);
//        String mURL = "";
//        if (args != null) {
//            mURL = args.getString("queryString");
//        }
        return new StockLoader(this, severURL);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<StockManager> loader, StockManager stockManager) {
        Log.d("auau", "Finish Load");
        mIntent = new Intent(this, Description.class);
        Bundle mBundle = new Bundle();
        mBundle.putSerializable("STOCK_1", (Serializable)stockManager.GetArrayList("s1"));
        mBundle.putSerializable("STOCK_2", (Serializable)stockManager.GetArrayList("s2"));
        mIntent.putExtras(mBundle);
        isLoading = false;

        label_menu.setText(getString(R.string.label_finish));
        btn_start.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<StockManager> loader)
    {

    }
}
