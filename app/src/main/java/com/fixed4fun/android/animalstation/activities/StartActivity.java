package com.fixed4fun.android.animalstation.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.fixed4fun.android.animalstation.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.start_activity);
        View startView = findViewById(R.id.start_activity_screen);


        startView.setOnClickListener(v -> {
            Intent goToMain = new Intent(StartActivity.this, MainActivity.class);
            goToMain.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(goToMain);

        });

        TextView policy = findViewById(R.id.policy);
        policy.setOnClickListener(v -> {
                String url = "http://www.poszukiwaczezycia.pl/privacy-policy.html";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);

        });



    }
}
