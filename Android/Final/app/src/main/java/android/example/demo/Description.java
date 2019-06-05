package android.example.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Description extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

    }

    public void Next(View view) {
        // Get data and continue pass
        Bundle passBundle = getIntent().getExtras();
        Intent intent = new Intent(Description.this, BackTesting.class);
        intent.putExtras(passBundle);
        startActivity(intent);
    }
}
