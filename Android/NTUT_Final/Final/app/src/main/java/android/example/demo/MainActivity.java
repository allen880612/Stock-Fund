package android.example.demo;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private TextView tv_stock1, tv_stock2, tv_date, label_top;
    private RecyclerView recyclerView_1, recyclerView_2;
    private String mURL;
    //private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialize();
        SetRecycleView();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        boolean flag = false;
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                flag = true;
                break;
            default:
                flag = super.onOptionsItemSelected(item);
                break;
        }
        return flag;
    }

    void Initialize()
    {
        recyclerView_1 = findViewById(R.id.recycleView_1);
        //recyclerView_2 = findViewById(R.id.recycleView_2);
        //tv_date = findViewById(R.id.tv_date);
        //progressBar = findViewById(R.id.progressBar);
        label_top = findViewById(R.id.label_buy1);

        //progressBar.setVisibility(View.INVISIBLE);
        SetDate();
    }

    private void SetRecycleView()
    {
        ArrayList<StockHolder> stockList = null;
        String choice = "";

        if ( getIntent().getExtras()!= null)
        {
            String top_text = getIntent().getExtras().getString("CHOICE_TEXT", "錯誤");
            label_top.setText(top_text);
            stockList = (ArrayList<StockHolder>)getIntent().getExtras().get("STOCK");
            choice = getIntent().getExtras().getString("CHOICE", "CLOSE");
        }

        if (stockList != null)
        {
            StockInfoAdapter adapter = new StockInfoAdapter(this, stockList, choice);
            recyclerView_1.setAdapter(adapter);
            recyclerView_1.setLayoutManager(new LinearLayoutManager(this));
        }
    }

    private void SetDate()
    {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);        // get yesterday

        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;   // calender start from "0 month"
        int day = calendar.get(Calendar.DATE);

        String date = String.valueOf(year) + "/";
        if (month < 10)
            date += "0";
        date += String.valueOf(month) + "/" + String.valueOf(day);

        Log.d("Date", date);
        //tv_date.setText( getResources().getText(R.string.default_label_date) + date);
    }
}
