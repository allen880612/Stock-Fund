package android.example.demo;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity {

    private TextView tv_stock1, tv_stock2, tv_date;
    private RecyclerView recyclerView_1, recyclerView_2;
    private String mURL;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mURL = "https://ntutwebtest.000webhostapp.com/Untitled-4/view/index.php?signal=";
        recyclerView_1 = findViewById(R.id.recycleView_1);
        recyclerView_2 = findViewById(R.id.recycleView_2);
        tv_date = findViewById(R.id.tv_date);

        SetDate();
        new PHPAsyncTask(this, recyclerView_1, recyclerView_2).execute(mURL);
        //tv_stock1 = findViewById(R.id.tv_stock1);
        //tv_stock2 = findViewById(R.id.tv_stock2);
        //new PHPAsyncTask(this, tv_stock1, tv_stock2).execute(mUrl);

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
