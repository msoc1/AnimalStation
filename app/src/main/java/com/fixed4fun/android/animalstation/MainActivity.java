package com.fixed4fun.android.animalstation;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static int languageNumber = 1;
    public static String nameForQuiz;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.activity_main);

        ImageView listOfAnimals = findViewById(R.id.goToList);
        listOfAnimals.setOnClickListener(this);


        ImageView listOfAnimals2 = findViewById(R.id.goToNameQuiz);
        listOfAnimals2.setOnClickListener(this);


        ImageView listOfAnimals3 = findViewById(R.id.goToSoundQuiz);
        listOfAnimals3.setOnClickListener(this);

        ImageView listOfAnimals4 = findViewById(R.id.goToIconQuiz);
        listOfAnimals4.setOnClickListener(this);


        ImageView listOfAnimals5 = findViewById(R.id.languages);
        listOfAnimals5.setOnClickListener(this);

        ImageView listOfAnimals6 = findViewById(R.id.about_me);
        listOfAnimals6.setOnClickListener(this);


    }

    public static int checkForPrevious(int correctAnimal, ArrayList<Animal> zwierzaki){
        /* this method makes sure that there wont be doubled animal in all quizzes */

        if(nameForQuiz!=null) {
            if (nameForQuiz.equals(zwierzaki.get(correctAnimal).getmAnimalName())) {

                if(correctAnimal+1 > 3){
                    correctAnimal--;
                } else correctAnimal++;

            }
        }

        nameForQuiz = zwierzaki.get(correctAnimal).getmAnimalName();
        return correctAnimal;
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {


            case R.id.goToNameQuiz:
                Intent goToList = new Intent(MainActivity.this, NameQuiz.class);
                startActivity(goToList);
                break;
            case R.id.goToList:
                Intent goToList2 = new Intent(MainActivity.this, ChosenAnimalsView.class);
                startActivity(goToList2);
                break;
            case R.id.goToSoundQuiz:
                Intent goToList3 = new Intent(MainActivity.this, SoundQuiz.class);
                startActivity(goToList3);
                break;
            case R.id.goToIconQuiz:
                Intent goToList4 = new Intent(MainActivity.this, IconQuiz.class);
                startActivity(goToList4);
                break;
            case R.id.languages:
                Intent goToList5 = new Intent(MainActivity.this, LanguagesView.class);
                startActivity(goToList5);
                break;
            case R.id.about_me:
                Intent goToList6 = new Intent(MainActivity.this, FootPrintQuiz.class);
                startActivity(goToList6);
                break;


        }

    }
}
