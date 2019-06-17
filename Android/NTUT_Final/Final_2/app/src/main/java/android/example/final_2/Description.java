package android.example.final_2;

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
        Intent intent = new Intent(Description.this, MainActivity.class);
        Bundle passBundle = getIntent().getExtras();
        intent.putExtras(passBundle);
        startActivity(intent);
    }
}
