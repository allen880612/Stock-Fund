package android.example.fund_demo;

import android.appwidget.AppWidgetProviderInfo;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private APIManager f_apiManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        f_apiManager = new APIManager();
        SetFundDatabase();
    }

    private void SetFundDatabase()
    {
        String testJson = f_apiManager.GetData("BABA0010-14644b");
        Log.d("auau json", testJson);
    }

    public void GotoFund(View view) {
        Intent intent = new Intent(this, android.example.fund_demo.Found_choice.class);
        startActivity(intent);
    }

    public void GotoETF(View view) {
        Intent intent = new Intent(this, android.example.fund_demo.ETF_choice.class);
        startActivity(intent);
    }
}
