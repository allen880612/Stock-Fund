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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{

    android.support.v4.app.LoaderManager loaderManager = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        loaderManager = android.support.v4.app.LoaderManager.getInstance(this);

        Bundle bundle = new Bundle();
        bundle.putString("API", "BABA0010-14644b");
        loaderManager.initLoader(0, bundle, new FundLoaderCallback());
    }

    public void GotoFund(View view) {
        Intent intent = new Intent(this, android.example.fund_demo.Found_choice.class);
        startActivity(intent);
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
            return new FundLoader(MainActivity.this, "BABA0010-14644b");
        }

        @Override
        public void onLoadFinished(@NonNull Loader<ArrayList<Fund>> loader, ArrayList<Fund> funds) {

        }

        @Override
        public void onLoaderReset(@NonNull Loader<ArrayList<Fund>> loader) {

        }
    }

}
