package android.example.demo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class BackTesting extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_back_testing);
    }

    public void Use(View view) {
        // Get data and continue pass , nice!
        Bundle passBundle = getIntent().getExtras();
        Intent intent = new Intent(BackTesting.this, MainActivity.class);
        intent.putExtras(passBundle);
        startActivity(intent);
    }
}
