package android.example.fund_demo;

import android.content.Intent;
import android.example.fund_demo.Fund.Fund;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Found_Result extends AppCompatActivity {

//    private final String TYPE = "type", STRATEGY = "strategy";
//    private TextView label_top;
//    private ListView lv_result;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_found__result);
//
//        Initialize();
//    }
//
//    void Initialize ()
//    {
//        Intent intent = getIntent();
//        String fType =  intent.getStringExtra(TYPE);
//        String fStrategy =  intent.getStringExtra(STRATEGY);
//        String combine = fType + " " + fStrategy;
//        label_top = findViewById(R.id.label_top);
//        label_top.setText(combine);
//
//        //String[] list_result = getResources().getStringArray(R.array.list_area);
//        String[] list_result = {combine + "1", combine + "2", combine + "3"};
//        ArrayAdapter<String> fAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.item_fund, list_result);
//        lv_result = findViewById(R.id.viewList);
//        lv_result.setAdapter(fAdapter);
//    }

    //--//

    private TextView label_top;
    private ListView lv_result;
    private RecyclerView fundRecycler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found__result);

        Initialize();
    }

    private void Initialize()
    {
        Object fund_obj = getIntent().getSerializableExtra("MEET_FUND");
        if(fund_obj != null)
        {
            ArrayList<Fund> funds = (ArrayList<Fund>) fund_obj;

            for (Fund fund : funds)
            {
                Log.d("fund : ", fund.GetCode() + ", " + fund.GetName());
            }

            ArrayList<Fund> sort =  (ArrayList<Fund>) funds.clone();
            Collections.sort(sort, returnComparator);

            fundRecycler = findViewById(R.id.recycler_fund);
            FundAdapter fAdapter = new FundAdapter(this, sort);
            fundRecycler.setAdapter(fAdapter);
            fundRecycler.setLayoutManager(new LinearLayoutManager(this));
        }


//        label_top = (TextView) findViewById(R.id.label_top);
//        String tempResult = "";
//        tempResult = GetResult();
//        label_top.setText(tempResult);

    }

    private String GetResult()
    {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null)
        {
            String result = "";
            result += bundle.getString("RISK", "") + "\n";
            result += bundle.getString("RETURN_TIME", "") + "\n";
            result += bundle.getString("RETURN_CONDITION", "") + "\n";
            result += bundle.getString("BETA_TIME", "") + "\n";
            result += bundle.getString("BETA_CONDITION", "") + "\n";
            result += bundle.getString("SCALE", "");
            return result;
        }
        return "ERROR";
    }

    public static Comparator returnComparator = new Comparator()
    {
        @Override
        public int compare(Object o1, Object o2)
        {
            Fund f1 = (Fund) o1;
            Fund f2 = (Fund) o2;

            float n1 =  f1.GetReturn_1y().isEmpty() ? 0 : (Float.valueOf(f1.GetReturn_1y()));   // 防空字串
            float n2 =  f2.GetReturn_1y().isEmpty() ? 0 : (Float.valueOf(f2.GetReturn_1y()));   // 防空字串

            // Big to small (down to)
            if (n1 > n2)
                return -1;
            else if(n1 < n2)
                return 1;
            return 0;
        }
    };
}
