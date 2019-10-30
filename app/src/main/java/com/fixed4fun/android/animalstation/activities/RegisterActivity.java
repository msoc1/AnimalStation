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
import com.fixed4fun.android.animalstation.objects.NewUser;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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
        mFirebaseDatabaseInstance = FirebaseDatabase.getInstance();

    }


    public void onRegister(View v) {
        username = usernameEditText.getText().toString().trim();
        password = passwordEditText.getText().toString().trim();
        if (usernameEditText.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter username!", Toast.LENGTH_SHORT).show();
        } else if (passwordEditText.getText().toString().isEmpty()) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
        } else if (passwordEditText.getText().toString().length() < 6) {
            Toast.makeText(getApplicationContext(), "Enter longer password!", Toast.LENGTH_SHORT).show();
        } else if(usernameEditText.getText().toString().contains(" ")) {
            Toast.makeText(getApplicationContext(), "Spaces not allowed!", Toast.LENGTH_SHORT).show();

        } else {


            mAuth.createUserWithEmailAndPassword(username + "@animalstation.pl", password)
                    .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            mFirebaseDatabase = mFirebaseDatabaseInstance.getReference("users");
                            user = FirebaseAuth.getInstance().getCurrentUser();
                            userID = user.getUid();
                            userEmail = username + "@animalstation.pl";
                            NewUser user = new NewUser(userEmail, userID);

                            mFirebaseDatabase.child(userID).setValue(user);

                            Toast.makeText(getApplicationContext(), "You can log in!" + task.isSuccessful(), Toast.LENGTH_SHORT).show();
                        }
                    });
        }

    }

    public void onLogin(View v) {
        String username = usernameEditText.getText().toString().trim() + "@animalstation.pl";
        password = passwordEditText.getText().toString().trim();

        mAuth.signInWithEmailAndPassword(username, passwordEditText.getText().toString().trim())
                .addOnCompleteListener(RegisterActivity.this, (task) -> {
                    if (!task.isSuccessful()) {
                        Toast.makeText(getApplicationContext(), task.isSuccessful() + " " + username + " " + password, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Logging in!", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });

    }


}
