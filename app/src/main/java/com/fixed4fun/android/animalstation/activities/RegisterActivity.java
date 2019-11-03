package com.fixed4fun.android.animalstation.activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.objects.NewUser;
import com.fixed4fun.android.animalstation.utilities.AnimalStation;
import com.fixed4fun.android.animalstation.utilities.Translations;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity {

    private String username;
    private String password;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private Button logInButton;
    private String userID;
    private String userEmail;

    private FirebaseAuth mAuth;
    private FirebaseDatabase mFirebaseDatabaseInstance;
    private DatabaseReference mFirebaseDatabase;
    private FirebaseUser user;
    ArrayList<String> textTranslations = Translations.getTranslationsNew(AnimalStation.getContext());
    TextView usernameTextView;
    TextView passwordTxtView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.login_layout);

        usernameTextView = findViewById(R.id.usernameTextView);
        usernameTextView.setText(textTranslations.get(28));
        passwordTxtView = findViewById(R.id.passwordTextView);
        passwordTxtView.setText(textTranslations.get(29));

        usernameEditText = findViewById(R.id.usernameEditText);
        usernameEditText.setHint(textTranslations.get(28));
        passwordEditText = findViewById(R.id.passwordEditText);
        passwordEditText.setHint(textTranslations.get(29));
        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(this::onRegister);
        registerButton.setText(textTranslations.get(32));
        logInButton = findViewById(R.id.log_in_button);
        logInButton.setOnClickListener(this::onLogin);
        logInButton.setText(textTranslations.get(33));

        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabaseInstance = FirebaseDatabase.getInstance();

    }


    public void onRegister(View v) {
        username = usernameEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        if (username.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_SHORT).show();
        } else if (username.length() > 14) {
            Toast.makeText(getApplicationContext(), "Username too long!", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
        } else if (password.length() < 6) {
            Toast.makeText(getApplicationContext(), "Enter longer password!", Toast.LENGTH_SHORT).show();
        } else if (username.contains(" ")) {
            Toast.makeText(getApplicationContext(), "Spaces not allowed!", Toast.LENGTH_SHORT).show();
        } else {


            mAuth.createUserWithEmailAndPassword(username + "@animalstation.pl", password)
                    .addOnSuccessListener(RegisterActivity.this, task -> {
                        mFirebaseDatabase = mFirebaseDatabaseInstance.getReference("users");
                        user = FirebaseAuth.getInstance().getCurrentUser();
                        userID = user.getUid();
                        userEmail = username + "@animalstation.pl";
                        NewUser user = new NewUser(userEmail, userID);
                        mFirebaseDatabase.child(userID).setValue(user);

                        Toast.makeText(getApplicationContext(), textTranslations.get(33), Toast.LENGTH_SHORT).show();
                    }).addOnFailureListener(RegisterActivity.this, task -> {
                Toast.makeText(getApplicationContext(), "Username taken", Toast.LENGTH_SHORT).show();

            });
        }

    }

    public void onLogin(View v) {
        username = usernameEditText.getText().toString().trim() + "@animalstation.pl";
        String user = usernameEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        if (user.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_LONG).show();
        } else if (password.length() == 0) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_LONG).show();
        } else {
            mAuth.signInWithEmailAndPassword(username, passwordEditText.getText().toString().trim())
                    .addOnCompleteListener(RegisterActivity.this, (task) -> {
                        if (!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), textTranslations.get(34) + " " + usernameEditText.getText().toString(), Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), textTranslations.get(35), Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });

        }
    }

}
