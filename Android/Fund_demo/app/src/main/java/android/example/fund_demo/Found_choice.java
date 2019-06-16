package android.example.fund_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;


public class Found_choice extends AppCompatActivity {

//    private Spinner spinner_type;
//    private Spinner spinner_strategy;
//    private String fType, fStrategy;
//    private final String TYPE = "type", STRATEGY = "strategy";
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_found_choice);
//
//        Initialize();
//
//        SetSpinnerListener();
//    }
//
//    void Initialize()
//    {
//        spinner_type = (Spinner)findViewById(R.id.spinner_type);
//        spinner_strategy = (Spinner)findViewById(R.id.spinner_stratage);
//
//        ArrayAdapter<CharSequence> typeList = ArrayAdapter.createFromResource(this,
//                R.array.list_types,
//                android.R.layout.simple_spinner_dropdown_item);
//
//        ArrayAdapter<CharSequence>  strategyList = ArrayAdapter.createFromResource(this,
//                R.array.list_strategys,
//                android.R.layout.simple_spinner_dropdown_item);
//
//        spinner_type.setAdapter(typeList);
//        spinner_strategy.setAdapter(strategyList);
//    }
//
//    void SetSpinnerListener()
//    {
//        spinner_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
//                fType = getResources().getStringArray(R.array.list_types)[_pos];
//                Toast.makeText(Found_choice.this, fType, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(Found_choice.this, "請選擇一項!!", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        spinner_strategy.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
//                fStrategy = getResources().getStringArray(R.array.list_strategys)[_pos];
//                Toast.makeText(Found_choice.this, fStrategy, Toast.LENGTH_SHORT).show();
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> parent) {
//                Toast.makeText(Found_choice.this, "請選擇一項!!", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//
//    public void GoToResult(View view) {
//        Intent intent = new Intent(this, Found_Result.class);
//        intent.putExtra(TYPE, fType);
//        intent.putExtra(STRATEGY, fStrategy);
//        startActivity(intent);
//    }

    private Spinner spr_risk, spr_return_time, spr_return_p;
    private Spinner  spr_beta_time, spr_beta_value, spr_scale;
    private String eRisk, eReturnTime, eReturnP;
    private String eBetaTime, eBetaValue, eScale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_choice);

        Initialize();
    }

    void Initialize()
    {
        spr_risk = (Spinner)findViewById(R.id.spinner_risk);
        spr_return_time = (Spinner)findViewById(R.id.spinner_return_time);
        spr_return_p = (Spinner)findViewById(R.id.spinner_return_percent);
        spr_beta_time = (Spinner)findViewById(R.id.spinner_beta_time);
        spr_beta_value = (Spinner)findViewById(R.id.spinner_beta_value);
        spr_scale = (Spinner)findViewById(R.id.spinner_scale);

        SetSpinnerResource();
        SetSpinnerListener();
    }

    private void SetSpinnerResource()
    {
        // spinner risk resource
        ArrayAdapter<CharSequence> riskList = ArrayAdapter.createFromResource(this,
                R.array.list_etf_number,
                android.R.layout.simple_spinner_dropdown_item);
        // spinner time resource
        ArrayAdapter<CharSequence>  listTime = ArrayAdapter.createFromResource(this,
                R.array.list_time,
                android.R.layout.simple_spinner_dropdown_item);
        // spinner return percent resource
        ArrayAdapter<CharSequence>  percentList = ArrayAdapter.createFromResource(this,
                R.array.list_return_percent,
                android.R.layout.simple_spinner_dropdown_item);
        // spinner beta interval resource
        ArrayAdapter<CharSequence>  betaValueList = ArrayAdapter.createFromResource(this,
                R.array.list_beta_value,
                android.R.layout.simple_spinner_dropdown_item);
        // spinner beta interval resource
        ArrayAdapter<CharSequence>  scaleList = ArrayAdapter.createFromResource(this,
                R.array.list_scale,
                android.R.layout.simple_spinner_dropdown_item);
        // Set adapter
        spr_risk.setAdapter(riskList);
        spr_return_time.setAdapter(listTime);
        spr_return_p.setAdapter(percentList);
        spr_beta_time.setAdapter(listTime);
        spr_beta_value.setAdapter(betaValueList);
        spr_scale.setAdapter(scaleList);

    }

    private void SetSpinnerListener()
    {
        // set risk listener
        spr_risk.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                eRisk = getResources().getStringArray(R.array.list_etf_number)[_pos];
                ToastMsg(eRisk);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ToastMsg("請選擇一項!!");
            }
        });
        // set return rate listener
        spr_return_p.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                eReturnP = getResources().getStringArray(R.array.list_return_percent)[_pos];
                ToastMsg(eReturnP);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Found_choice.this, "請選擇一項!!", Toast.LENGTH_SHORT).show();
            }

        });
        spr_return_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                eReturnTime = getResources().getStringArray(R.array.list_time)[_pos];
                ToastMsg(eReturnTime);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ToastMsg("請選擇一項!!");
            }
        });
        // set beta listener
        spr_beta_value.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                eBetaValue = getResources().getStringArray(R.array.list_beta_value)[_pos];
                ToastMsg(eBetaValue);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ToastMsg("請選擇一項!!");
            }

        });
        spr_beta_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                eBetaTime = getResources().getStringArray(R.array.list_time)[_pos];
                ToastMsg(eBetaTime);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ToastMsg("請選擇一項!!");

            }
        });
        // set scale listener
        spr_scale.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener()
        {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                eScale = getResources().getStringArray(R.array.list_scale)[_pos];
                ToastMsg(eScale);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ToastMsg("請選擇一項!!");
            }

        });
    }

    public void GoToResult(View view) {
        Intent intent = new Intent(this,Found_Result.class);
        Bundle bundle = new Bundle();
        bundle.putString("RISK", eRisk);
        bundle.putString("RETURN_TIME", eReturnTime);
        bundle.putString("RETURN_CONDITION", eReturnP);
        bundle.putString("BETA_TIME", eBetaTime);
        bundle.putString("BETA_CONDITION", eBetaValue);
        bundle.putString("SCALE", eScale);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    public void ToastMsg(String _msg)
    {
        Toast.makeText(Found_choice.this, _msg, Toast.LENGTH_SHORT).show();
    }
}
