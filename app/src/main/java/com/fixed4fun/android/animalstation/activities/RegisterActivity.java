package com.fixed4fun.android.animalstation.activities;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.fixed4fun.android.animalstation.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private String username;
    private String password;
    private EditText usernameEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private Button logInButton;


    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        this.setContentView(R.layout.login_layout);

        usernameEditText = findViewById(R.id.usernameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(this::onRegister);
        logInButton = findViewById(R.id.log_in_button);
        logInButton.setOnClickListener(this::onLogin);

        mAuth = FirebaseAuth.getInstance();
    }



    public void onRegister(View v){
            username = usernameEditText.getText().toString().trim();
            password = passwordEditText.getText().toString().trim();
            if (usernameEditText.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_SHORT).show();
            }
            if (passwordEditText.getText().toString().length() < 6) {
                Toast.makeText(getApplicationContext(), "Enter longer password!", Toast.LENGTH_SHORT).show();
            }
            if (passwordEditText.getText().toString().isEmpty()) {
                Toast.makeText(getApplicationContext(), "Enter longer password!", Toast.LENGTH_SHORT).show();
            }

            mAuth.createUserWithEmailAndPassword(username + "@animalstation.pl", password)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    Toast.makeText(getApplicationContext(), "You can log in!" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                }
            });

    }

    public void onLogin(View v){
        String username = usernameEditText.getText().toString().trim()+"@animalstation.pl";
        password = passwordEditText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(username, passwordEditText.getText().toString().trim())
                    .addOnCompleteListener(RegisterActivity.this, (task) -> {
                        if(!task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), task.isSuccessful()+" " + username + " " +password, Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Logging in!", Toast.LENGTH_LONG).show();
                            finish();
                        }
                    });

    }



}
