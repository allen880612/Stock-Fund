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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Map;

public class Load extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks< StockManager > {

    private final String URL = "URL";
    private final String STOCK = "STOCK";
    private final String CHOICE = "CHOICE";
    private final String CHOICE_TEXT = "CHOICE_TEXT";

    private TextView label_menu;
    private ProgressBar progressBar;
    private Button btn_start;
    private Spinner spr_choice;

    private static boolean isLoaded = false;
    private String choice, choice_key;

    private Intent mIntent;
    private  StockManager sManager;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load);

        Initialize();
        SetSpinnerListener();
        LoadStocks();

    }

    void Initialize()
    {
        label_menu = (TextView) findViewById(R.id.label_menu);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        btn_start = (Button) findViewById(R.id.btn_start);
        spr_choice = (Spinner) findViewById(R.id.spinner_choice);

//        isLoading = true;

        progressBar.setVisibility(View.VISIBLE);
        btn_start.setVisibility(View.INVISIBLE);

        ArrayAdapter<CharSequence> choiceAdapter = ArrayAdapter.createFromResource(this,
                R.array.choice_list,
                R.layout.spinner_item);

        spr_choice.setAdapter(choiceAdapter);
    }

    void SetSpinnerListener() {
        spr_choice.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                // Get now choice key
                choice = getResources().getStringArray(R.array.choice_list)[_pos];
                choice_key = getResources().getStringArray(R.array.choice_key_list)[_pos];

                Log.d("auau choice ", choice);
                Log.d("auau choice key", choice_key);
                //Toast.makeText(Load.this, choice, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Load.this, "請選擇一項!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    void LoadStocks()
    {
        //        Bundle queryBundle = new Bundle();
//        String API = "BABA0010-14663b";
////        queryBundle.putString(URL, API);
////        LoaderManager.getInstance(this).restartLoader(0, queryBundle, this);
//        if(LoaderManager.getInstance(this).getLoader(0) == null)
//        {
//            LoaderManager.getInstance(this).initLoader(0,null,this);
//        }
        LoaderManager.getInstance(this).initLoader(0,null,this);
    }

    public void Start(View view) {

        mIntent = new Intent(this, Description.class);
        Bundle mBundle = new Bundle();

        ArrayList<StockHolder> choice_stock = sManager.GetTop50().get(choice_key);
        //ArrayList<StockHolder> choice_stock = sManager.GetArrayList("close");

//        for (StockHolder sh : choice_stock)
//        {
//            Log.d("auau choice", sh.GetCode() + " Close Price : " + sh.GetClose());
//        }

        mBundle.putSerializable(STOCK, (Serializable) choice_stock);
        mBundle.putString(CHOICE, choice_key);
        mBundle.putString(CHOICE_TEXT, choice);

        mIntent.putExtras(mBundle);
        startActivity(mIntent);
        Log.d("auau", "not in Loading ");

    }

    @NonNull
    @Override
    public Loader< StockManager > onCreateLoader(int i, @Nullable Bundle args) {
        Log.d("auau", "Start Load");
        String API = "BABA0010-14663b";

        String[] stockCode = getResources().getStringArray(R.array.list_stock_code);
        //String stockData = getResources().getString(R.string.stock_data_json);

        return new StockLoader(this, API);
    }

    @Override
    public void onLoadFinished(@NonNull Loader< StockManager > loader, StockManager _sManager) {
        Log.d("auau", "Finish Load");
        sManager = _sManager;
        isLoaded = false;

        label_menu.setText(getString(R.string.label_finish));
        btn_start.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onLoaderReset(@NonNull Loader< StockManager > loader) {

    }
}
