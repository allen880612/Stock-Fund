package android.example.demo;

import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.ExecutionException;


public class MainActivity extends AppCompatActivity {

    private TextView tv_stock1, tv_stock2, tv_date;
    private RecyclerView recyclerView_1, recyclerView_2;
    private String mURL;
    private ArrayList<StockHolder> stockList_1, stockList_2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Initialize();
        stockList_1 = (ArrayList<StockHolder>)getIntent().getExtras().get("STOCK_1");
        stockList_2 = (ArrayList<StockHolder>)getIntent().getExtras().get("STOCK_2");
        SetRecycleView();
    }

    void Initialize()
    {
        mURL = "https://ntutwebtest.000webhostapp.com/Untitled-4/view/index.php?signal=";
        recyclerView_1 = findViewById(R.id.recycleView_1);
        recyclerView_2 = findViewById(R.id.recycleView_2);
        tv_date = findViewById(R.id.tv_date);
        SetDate();
        //AsyncTask task = new PHPAsyncTask(this, recyclerView_1, recyclerView_2).execute(mURL);
    }

    private void SetRecycleView()
    {
        StockInfoAdapter adapter1 = new StockInfoAdapter(this, stockList_1);
        StockInfoAdapter adapter2 = new StockInfoAdapter(this, stockList_2);
        recyclerView_1.setAdapter(adapter1);
        recyclerView_1.setLayoutManager(new LinearLayoutManager(this));
        recyclerView_2.setAdapter(adapter2);
        recyclerView_2.setLayoutManager(new LinearLayoutManager(this));
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
        tv_date.setText( getResources().getText(R.string.default_label_date) + date);
    }

    public void Refresh(View view) {
        SetDate();
        new PHPAsyncTask(this, recyclerView_1, recyclerView_2).execute(mURL);
    }

}
