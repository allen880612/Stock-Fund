package android.example.fund_demo;

import android.app.LoaderManager;
import android.appwidget.AppWidgetProviderInfo;
import android.content.Intent;
import android.example.fund_demo.Fund.Fund;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    android.support.v4.app.LoaderManager loaderManager = null;
    Intent intent_Fund, intent_ETF;
    private final String FUND_DATA = "FUND_DATA";
    private final String ETF_DATA = "ETF_DATA";
    private Button btn_fund, btn_ETF;
    private ProgressBar pgBar;
    private TextView label_hint;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Initialize();

    }

    private void Initialize()
    {
        btn_fund = findViewById(R.id.btn_found);
        btn_ETF = findViewById(R.id.btn_ETF);
        pgBar = findViewById(R.id.progressBar);
        label_hint = findViewById(R.id.textView);

        btn_fund.setEnabled(false);
        btn_fund.setVisibility(View.INVISIBLE);
        btn_ETF.setVisibility(View.INVISIBLE);

        loaderManager = android.support.v4.app.LoaderManager.getInstance(this);
        Bundle bundle = new Bundle();
        bundle.putString("API", "BABA0010-14644b");
        loaderManager.initLoader(0, bundle, new FundLoaderCallback());
    }

    public void GotoFund(View view) {
        startActivity(intent_Fund);
    }

    public void GotoETF(View view) {
        Intent intent = new Intent(this, android.example.fund_demo.ETF_choice.class);
        startActivity(intent);
    }

    // Loader call back
    class FundLoaderCallback implements android.support.v4.app.LoaderManager.LoaderCallbacks<ArrayList<Fund>>{

        //private final String API = "BABA0010-14644b";

        @NonNull
        @Override
        public Loader<ArrayList<Fund>> onCreateLoader(int id, @Nullable Bundle args) {
            final String API = args.getString("API", "");
            btn_fund.setEnabled(false);
            btn_fund.setVisibility(View.INVISIBLE);
            return new FundLoader(MainActivity.this, API);
        }

        @Override
        public void onLoadFinished(@NonNull Loader<ArrayList<Fund>> loader, ArrayList<Fund> funds) {
            String size = Integer.toString(funds.size());
//            for (Fund fund : funds)
//            {
//                Log.d("fund type : ", fund.GetType());
//            }
            Log.d("fund finish : ", size);
            intent_Fund = new Intent(MainActivity.this, android.example.fund_demo.Found_choice.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable(FUND_DATA, (Serializable)funds);
            intent_Fund.putExtras(bundle);
            btn_fund.setEnabled(true);
            btn_fund.setVisibility(View.VISIBLE);
            pgBar.setVisibility(View.INVISIBLE);
            label_hint.setText(getString(R.string.label_finish));


        }

        @Override
        public void onLoaderReset(@NonNull Loader<ArrayList<Fund>> loader) {

        }
    }

}
