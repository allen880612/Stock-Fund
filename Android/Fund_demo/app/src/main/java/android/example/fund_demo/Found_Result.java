package android.example.fund_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class Found_Result extends AppCompatActivity {

    private final String TYPE = "type", STRATEGY = "strategy";
    private TextView label_top;
    private ListView lv_result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found__result);

        Initialize();
    }

    void Initialize ()
    {
        Intent intent = getIntent();
        String fType =  intent.getStringExtra(TYPE);
        String fStrategy =  intent.getStringExtra(STRATEGY);
        String combine = fType + " " + fStrategy;
        label_top = findViewById(R.id.label_top);
        label_top.setText(combine);

        //String[] list_result = getResources().getStringArray(R.array.list_area);
        String[] list_result = {combine + "1", combine + "2", combine + "3"};
        ArrayAdapter<String> fAdapter = new ArrayAdapter<String>(this, R.layout.list_item, R.id.item_fund, list_result);
        lv_result = findViewById(R.id.viewList);
        lv_result.setAdapter(fAdapter);
    }
}
