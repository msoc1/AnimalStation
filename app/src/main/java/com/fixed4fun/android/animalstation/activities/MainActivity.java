package com.fixed4fun.android.animalstation.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.objects.Animal;
import com.fixed4fun.android.animalstation.quizActivities.FootPrintQuiz;
import com.fixed4fun.android.animalstation.quizActivities.IconQuiz;
import com.fixed4fun.android.animalstation.quizActivities.NameQuiz;
import com.fixed4fun.android.animalstation.quizActivities.SoundQuiz;
import com.fixed4fun.android.animalstation.quizActivities.SpecialQuiz;
import com.fixed4fun.android.animalstation.utilities.Translations;
import com.google.firebase.auth.FirebaseAuth;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    public static int languageNumber = 1;
    public static String nameForQuiz;
    private Button signInButton;
    private FirebaseAuth firebaseAuth;

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

        ImageView rankingImageView = findViewById(R.id.goToRanking);
        rankingImageView.setOnClickListener(this);

        ImageView specialQuiz = findViewById(R.id.goToSpecialQuiz);
        specialQuiz.setOnClickListener(this);

        signInButton = findViewById(R.id.sign_in_button);
        signInButton.setText(Translations.getTranslations().get(24).toUpperCase());

        firebaseAuth = FirebaseAuth.getInstance();
        signInButton.setOnClickListener(this::onSignInButtonClick);

    }


    public void onSignInButtonClick(View view) {
        if (firebaseAuth.getCurrentUser() == null) {
            signInButton.setText("Sign in");
            Toast.makeText(getApplicationContext(), "goin to register", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(MainActivity.this, RegisterActivity.class));
        } else {
            Toast.makeText(getApplicationContext(), "signing out " + firebaseAuth.getCurrentUser().getDisplayName(), Toast.LENGTH_SHORT).show();
            signInButton.setText("Sign out");
            firebaseAuth.signOut();
        }
    }


    public static int checkForPrevious(int correctAnimal, ArrayList<Animal> zwierzaki) {
        /* this method makes sure that there wont be doubled animal in all quizzes */

        if (nameForQuiz != null) {
            if (nameForQuiz.equals(zwierzaki.get(correctAnimal).getmAnimalName())) {

                if (correctAnimal + 1 > 3) {
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
                startActivity(new Intent(MainActivity.this, NameQuiz.class));
                break;
            case R.id.goToList:
                startActivity(new Intent(MainActivity.this, ChosenAnimalsView.class));
                break;
            case R.id.goToSoundQuiz:
                startActivity(new Intent(MainActivity.this, SoundQuiz.class));
                break;
            case R.id.goToIconQuiz:
                startActivity(new Intent(MainActivity.this, IconQuiz.class));
                break;
            case R.id.languages:
                startActivity( new Intent(MainActivity.this, LanguagesView.class));
                break;
            case R.id.about_me:
                startActivity(new Intent(MainActivity.this, FootPrintQuiz.class));
                break;
            case R.id.goToRanking:
                startActivity(new Intent(MainActivity.this, RankingActivity.class));
                break;
            case R.id.goToSpecialQuiz:
                startActivity(new Intent(MainActivity.this, SpecialQuiz.class));
                break;
        }
    }
}
