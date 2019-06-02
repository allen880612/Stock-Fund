package android.example.fund_demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Found_choice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_found_choice);

        Spinner spinner = (Spinner)findViewById(R.id.spinner_1);
        ArrayAdapter<CharSequence> lunchList = ArrayAdapter.createFromResource(this,
                R.array.list_area,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(lunchList);
    }

    public void GoToResult(View view) {
        //Intent intent = new Intent(this, MainActivity.class);
    }
}
