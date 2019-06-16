package android.example.fund_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class ETF_Result extends AppCompatActivity {

    private TextView label_top;
    private ListView lv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etf__result);

        Initialize();
    }

    private void Initialize()
    {
//        String[] list_result = getResources().getStringArray(R.array.list_ETF);
//        ArrayAdapter<String> fAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.item_fund, list_result);
//        lv_result = findViewById(R.id.viewList);
//        lv_result.setAdapter(fAdapter);
        label_top = (TextView) findViewById(R.id.label_top);
        String tempResult = "";
        tempResult = GetResult();
        label_top.setText(tempResult);

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
}
