package com.fixed4fun.android.animalstation.activities;


import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import androidx.appcompat.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.GridView;
import android.widget.ImageView;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.adapters.AnimalAdapter;
import com.fixed4fun.android.animalstation.utilities.AnimalsToUse;
import com.fixed4fun.android.animalstation.utilities.LanguagesToUse;

public class ChosenAnimalsView extends AppCompatActivity {
    public static int pos;
    public static boolean wasOnList;
    private long mLastClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.chosen_animal_view);
        AnimalsToUse.getAnimals();


        AnimalAdapter adapter = new AnimalAdapter(this, AnimalsToUse.getAnimals());
        GridView gridView = findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {

            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();

            Intent chosenAnimal = new Intent(ChosenAnimalsView.this, ChosenAnimal.class);
            chosenAnimal.putExtra("My Animal", AnimalsToUse.getAnimals().get(position));
            startActivity(chosenAnimal);
            pos = position;
        });

        ImageView backToMain = findViewById(R.id.back_to_main);
        backToMain.setOnClickListener(v -> {
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            finish();
        });

        final ImageView falg = findViewById(R.id.falg2);
        falg.setImageResource(LanguagesToUse.getLanguages().get(MainActivity.languageNumber).getmLanguage());

        falg.setOnClickListener(v -> {
            wasOnList = true;
            if (SystemClock.elapsedRealtime() - mLastClickTime < 1000) {
                return;
            }
            mLastClickTime = SystemClock.elapsedRealtime();
            Intent gotoLanguage = new Intent(ChosenAnimalsView.this, LanguagesView.class);
            gotoLanguage.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(gotoLanguage);
            finish();

        });

    }
}