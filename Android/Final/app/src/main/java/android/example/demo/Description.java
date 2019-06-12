package android.example.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class Description extends AppCompatActivity {

    private TextView company, code, open, close, high, low;
    private TextView updown, updownP, amplitude, PBR;
    private TextView deal_num, deal_price, PER, PER_4S;
    private TextView mk_value, mk_percent, goban, turnover;

    private StockHolder sh = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        Object obj = getIntent().getSerializableExtra("STOCK_INFO");

        Initialize();

        if (obj == null)
        {
            company.setText("錯誤，公司資訊缺失");
        }
        else
        {
            sh = (StockHolder) obj;
            SetText();
        }

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

    public void Initialize()
    {
        company = findViewById(R.id.label_name);
        code = findViewById(R.id.label_code);
        open = findViewById(R.id.value_open);
        close = findViewById(R.id.value_close);
        high = findViewById(R.id.value_high);
        low = findViewById(R.id.value_low);
        updown = findViewById(R.id.value_upDown);
        updownP = findViewById(R.id.value_upDownP);
        amplitude = findViewById(R.id.value_amplitude);

        goban = findViewById(R.id.value_goban);
        mk_value = findViewById(R.id.value_mk_value);
        mk_percent = findViewById(R.id.value_mk_valueP);
        turnover = findViewById(R.id.value_turnover);

        deal_num = findViewById(R.id.value_deal_num);
        deal_price = findViewById(R.id.value_deal_price);
        PER = findViewById(R.id.value_PER);
        PER_4S= findViewById(R.id.value_PER_4S);

    }

    public void SetText()
    {
        company.setText(sh.GetName());
        code.setText(sh.GetCode());
        open.setText(sh.GetOpen());
        close.setText(sh.GetClose());
        high.setText(sh.GetHigh());
        low.setText(sh.GetLow());
        updown.setText(sh.GetUpDown());
        updownP.setText(sh.GetUpDown());
        amplitude.setText(sh.GetAmplitude());

        goban.setText(sh.GetGoban());

        mk_value.setText(sh.GetMk_value());
        mk_percent.setText(sh.GetMk_percent());
        turnover.setText(sh.GetTurnover());

        deal_num.setText(sh.GetDeal_num());
        deal_price.setText(sh.GetDeal_price());
        PER.setText(sh.GetPER());
        PER_4S.setText(sh.GetPER_4S());

    }

    public void Next(View view) {
        // Get data and continue pass
//        Intent intent = new Intent(Description.this, MainActivity.class);
//        Bundle passBundle = getIntent().getExtras();
//        intent.putExtras(passBundle);
//        startActivity(intent);
        finish();
    }
}
