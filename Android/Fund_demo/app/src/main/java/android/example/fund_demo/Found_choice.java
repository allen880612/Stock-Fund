package android.example.fund_demo;

import android.content.Intent;
import android.example.fund_demo.Fund.Fund;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;


public class Found_choice extends AppCompatActivity {

    private Spinner spr_risk, spr_return_time, spr_return_p;
    private Spinner  spr_beta_time, spr_beta_value, spr_scale;
    private String fRisk, fReturnTime, fReturnP;
    private String fBetaTime, fBetaValue, fScale;

    private ArrayList<Fund> inputFunds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_choice);

        Initialize();

        if (savedInstanceState == null)
        {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null)
            {
                ArrayList<Fund> saveFund = (ArrayList<Fund>) bundle.getSerializable("FUND_DATA");
                inputFunds =  (ArrayList<Fund>) saveFund.clone();
//                Log.d("auau get", "get success");
//                for (Fund fund : inputFunds)
//                {
//                    // 符合篩選條件
//                    Log.d("auau getTest", fund.GetName());
//                }
            }
        }
        else
        {
            Object f_obj = savedInstanceState.getSerializable("SAVE_FUND");
            if (savedInstanceState.getSerializable("SAVE_FUND") != null)
            {
                Log.d("auau state", "restore from create");
                inputFunds = (ArrayList<Fund>) f_obj;
            }
        }
    }

    public void GetFundResource()
    {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null)
        {
            inputFunds = (ArrayList<Fund>) bundle.getSerializable("FUND_DATA");
            Log.d("auau get", "get success");
            for (Fund fund : inputFunds)
            {
                // 符合篩選條件
                Log.d("auau getTest", fund.GetName());
            }
        }

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

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (inputFunds != null)
        {
            outState.putSerializable("SAVE_FUND", (Serializable) inputFunds);

        }
        Log.d("auau state", "save instance");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null)
        {
            Object f_obj = savedInstanceState.getSerializable("SAVE_FUND");
            inputFunds = (ArrayList<Fund>) f_obj;
            Log.d("auau state", "restore from saveInstance");
        }
        else
        {
            Log.d("auau state", "restore error");
        }
        Log.d("auau state", "restore");
    }

    private void SetSpinnerResource()
    {
        // spinner risk resource
        ArrayAdapter<CharSequence> riskList = ArrayAdapter.createFromResource(this,
                R.array.list_prefer,
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
                fRisk = getResources().getStringArray(R.array.list_etf_number)[_pos];
                ToastMsg(fRisk);
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
                fReturnP = getResources().getStringArray(R.array.list_return_percent)[_pos];
                ToastMsg(fReturnP);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(Found_choice.this, "請選擇一項!!", Toast.LENGTH_SHORT).show();
            }

        });
        spr_return_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                fReturnTime = getResources().getStringArray(R.array.list_time)[_pos];
                ToastMsg(fReturnTime);
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
                fBetaValue = getResources().getStringArray(R.array.list_beta_value)[_pos];
                ToastMsg(fBetaValue);
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                ToastMsg("請選擇一項!!");
            }

        });
        spr_beta_time.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int _pos, long id) {
                fBetaTime = getResources().getStringArray(R.array.list_time)[_pos];
                ToastMsg(fBetaTime);

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
                fScale = getResources().getStringArray(R.array.list_scale)[_pos];
                ToastMsg(fScale);
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
        ArrayList<Fund> fundChoice = GetChoiceFunds();
        bundle.putSerializable("MEET_FUND", (Serializable) fundChoice);
        bundle.putString("RISK", fRisk);
        bundle.putString("RETURN_TIME", fReturnTime);
        bundle.putString("RETURN_CONDITION", fReturnP);
        bundle.putString("BETA_TIME", fBetaTime);
        bundle.putString("BETA_CONDITION", fBetaValue);
        bundle.putString("SCALE", fScale);
        intent.putExtras(bundle);
        startActivity(intent);
        //finish();
    }

    private ArrayList<Fund> GetChoiceFunds()
    {
        //Bundle bundle = getIntent().getExtras();
        ArrayList<Fund> meetFunds = new ArrayList<>();

        if (inputFunds == null) // not restore
        {
            Bundle bundle = getIntent().getExtras();
            if (bundle != null)
            {
                inputFunds = (ArrayList<Fund>) bundle.getSerializable("FUND_DATA");
            }

        }

        for (Fund fund : inputFunds)
        {
            // 符合篩選條件
            if (MeetCondition(fund))
            {
                meetFunds.add(fund);
            }
        }
        return meetFunds;
    }

    private boolean MeetCondition(Fund fund)
    {
        if (!MeetRisk(fund))
            return false;
        if (!MeetReturn(fund))
            return false;
        if (MeetBeta(fund))
            return false;
        if (MeetScale(fund))
            return false;

        return true;
    }
    private boolean MeetRisk(Fund fund)
    {
        String fund_risk = fund.GetRisk();

        if (fRisk.equals("保守"))
            return fund_risk.equals("RR1") || fund_risk.equals("RR2");
        else if (fRisk.equals("穩健"))
            return fund_risk.equals("RR3") || fund_risk.equals("RR4");
        else if (fRisk.equals("積極"))
            return fund_risk.equals("RR5");
        return true;    //all
    }
    private boolean MeetReturn(Fund fund)
    {
        String fund_return = fReturnTime.equals("一年") ?  fund.GetReturn_1y() : fund.GetReturn_2y();
        float fr_double = fund_return.isEmpty()? 0 : Float.valueOf(fund_return);

        if (fReturnP.equals("5% 以上"))
            return fr_double >= 0.05;
        else if (fReturnP.equals("10% 以上"))
            return fr_double >= 0.1;
        else if (fReturnP.equals("15% 以上"))
            return fr_double >= 0.15;
        return true;    //all
    }
    private boolean MeetBeta(Fund fund)
    {
        String fund_beta = fBetaTime.equals("一年") ?  fund.GetBeta_1y() : fund.GetBeta_2y();
        float fb_double = fund_beta.isEmpty()? 10 : Float.valueOf(fund_beta);
        fb_double = Math.abs(fb_double);

        if (fBetaValue.equals("正負 0.3以下"))
            return fb_double < 0.3;
        else if (fBetaValue.equals("正負 0.7以下"))
            return fb_double < 0.7;
        else if (fBetaValue.equals("正負 1以下"))
            return fb_double < 1;
        else if (fBetaValue.equals("正負 2以下"))
            return fb_double < 2;
        return true;    //all
    }
    private boolean MeetScale(Fund fund)
    {
        String fund_scale = fund.GetScale();
        float fs_double = fund_scale.isEmpty()? 0 : Float.valueOf(fund_scale);

        if (fScale.equals("50億 以上"))
            return fs_double > 50;
        else if (fBetaValue.equals("100億 以上"))
            return fs_double > 100;
        else if (fBetaValue.equals("300億 以上"))
            return fs_double > 300;
        return true;    //all
    }

    public void ToastMsg(String _msg)
    {
        Toast.makeText(Found_choice.this, _msg, Toast.LENGTH_SHORT).show();
    }
}
