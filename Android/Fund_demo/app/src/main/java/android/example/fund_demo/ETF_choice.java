package android.example.fund_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

public class ETF_choice extends AppCompatActivity {

    private Spinner spr_type;
    private Spinner spr_number;
    private String fType, fNumber;
    private final String TYPE = "type", NUMBER = "number";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_etf_choice);

        Initialize();
        SetSpinnerListener();
    }

    void Initialize()
    {
        spr_type = (Spinner)findViewById(R.id.spinner_type);
        spr_number = (Spinner)findViewById(R.id.spinner_number);

        ArrayAdapter<CharSequence> typeList = ArrayAdapter.createFromResource(this,
                R.array.list_etf_types,
                android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence>  strategyList = ArrayAdapter.createFromResource(this,
                R.array.list_etf_number,
                android.R.layout.simple_spinner_dropdown_item);

        spr_type.setAdapter(typeList);
        spr_number.setAdapter(strategyList);
    }

    void SetSpinnerListener()
    {
        spr_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                fType = getResources().getStringArray(R.array.list_etf_types)[_pos];
                //ToastMsg(fType);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ToastMsg("請選擇一項!!");
            }
        });

        spr_number.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                fNumber = getResources().getStringArray(R.array.list_etf_number)[_pos];
                ToastMsg(fNumber);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ToastMsg("請選擇一項!!");
            }
        });
    }

    public void GoToResult(View view) {
        Intent intent = new Intent(this, ETF_Result.class);
        intent.putExtra(TYPE, fType);
        intent.putExtra(NUMBER, fNumber);
        startActivity(intent);
    }

    public void ToastMsg(String _msg)
    {
        Toast.makeText(ETF_choice.this, _msg, Toast.LENGTH_SHORT).show();
    }
}
