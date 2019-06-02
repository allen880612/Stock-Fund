package android.example.fund_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import javax.sql.RowSetListener;


public class Found_choice extends AppCompatActivity {

    private Spinner spinner_type;
    private Spinner spinner_strategy;
    private String fType, fStrategy;
    private final String TYPE = "type", STRATEGY = "strategy";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_choice);

        Initialize();

        SetSpinnerListener();
    }

    void Initialize()
    {
        spinner_type = (Spinner)findViewById(R.id.spinner_type);
        spinner_strategy = (Spinner)findViewById(R.id.spinner_stratage);

        ArrayAdapter<CharSequence> typeList = ArrayAdapter.createFromResource(this,
                R.array.list_types,
                android.R.layout.simple_spinner_dropdown_item);

        ArrayAdapter<CharSequence>  strategyList = ArrayAdapter.createFromResource(this,
                R.array.list_strategys,
                android.R.layout.simple_spinner_dropdown_item);

        spinner_type.setAdapter(typeList);
        spinner_strategy.setAdapter(strategyList);
    }

    void SetSpinnerListener()
    {
        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                fType = getResources().getStringArray(R.array.list_types)[_pos];
                Toast.makeText(Found_choice.this, fType, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Found_choice.this, "請選擇一項!!", Toast.LENGTH_SHORT).show();
            }
        });

        spinner_strategy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                fStrategy = getResources().getStringArray(R.array.list_strategys)[_pos];
                Toast.makeText(Found_choice.this, fStrategy, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Found_choice.this, "請選擇一項!!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void GoToResult(View view) {
        Intent intent = new Intent(this, Found_Result.class);
        intent.putExtra(TYPE, fType);
        intent.putExtra(STRATEGY, fStrategy);
        startActivity(intent);
    }
}
