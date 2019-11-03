package com.fixed4fun.android.animalstation.activities;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.adapters.RankingListAdapter;
import com.fixed4fun.android.animalstation.objects.Score;
import com.fixed4fun.android.animalstation.utilities.AnimalStation;
import com.fixed4fun.android.animalstation.utilities.Translations;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class RankingActivity extends AppCompatActivity {

    TextView loginText;
    RankingListAdapter adapter;
    ImageView backtoMain;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    ArrayList<Score> listOfGlobalScores = new ArrayList<>();
    ArrayList<Score> listofLocalScores = new ArrayList<>();
    ProgressBar progressBar;
    FirebaseUser firebaseUser;
    FirebaseAuth firebaseAuth;
    Button localRanking;
    Button globalRanking;
    RecyclerView rV;
    TextView rankingTextView;
    ArrayList<String> textTranslations = Translations.getTranslationsNew(AnimalStation.getContext());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ranking_layout);


        backtoMain = findViewById(R.id.back_to_main);
        backtoMain.setOnClickListener(view -> onBackPressed());

        progressBar = findViewById(R.id.progressBar);
        firebaseAuth = FirebaseAuth.getInstance();
        rV = findViewById(R.id.ranking_recyclerView);
        loginText = findViewById(R.id.logInTextView);
        localRanking = findViewById(R.id.localRanking);
        globalRanking = findViewById(R.id.globalRanking);
        rankingTextView = findViewById(R.id.ranking_text_view);

        adapter = new RankingListAdapter(this, listOfGlobalScores);
        if (firebaseAuth.getCurrentUser() != null) {
            firebaseUser = firebaseAuth.getCurrentUser();
            loginText.setVisibility(View.GONE);
            if (!listOfGlobalScores.isEmpty()) {
                listOfGlobalScores.clear();
            } else {
                prepareList();
            }
        }
        localRanking.setOnClickListener((view) -> localRankingOnClick());
        localRanking.setText(textTranslations.get(26));
        globalRanking.setOnClickListener(v -> globalRankingOnclick());
        globalRanking.setText(textTranslations.get(27));
        rV.setLayoutManager(new LinearLayoutManager(this));
        rV.setScrollbarFadingEnabled(false);
        rV.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        loginText.setVisibility(View.GONE);
        if (!listOfGlobalScores.isEmpty()) {
            listOfGlobalScores.clear();
        } else {
            prepareList();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent intent = new Intent(RankingActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }


    public void prepareList() {
        if (!checkInternetConnection()) {
            loginText.setText("No internet");
            progressBar.setVisibility(View.GONE);
            loginText.setVisibility(View.VISIBLE);
        } else {
            mFirebaseInstance = FirebaseDatabase.getInstance();
            mFirebaseDatabase = mFirebaseInstance.getReference("scores");
            mFirebaseDatabase.keepSynced(true);
            mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    progressBar.setVisibility(View.GONE);
                    loginText.setVisibility(View.GONE);
                    listOfGlobalScores.clear();
                    listofLocalScores.clear();
                    addData(dataSnapshot);
                    adapter.notifyDataSetChanged();
                    sorting(listOfGlobalScores);
                    sorting(listofLocalScores);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    }

    private void addData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Score score = new Score();
            score.setName(ds.getValue(Score.class).getName());
            score.setTime(ds.getValue(Score.class).getTime());
            score.setScore(ds.getValue(Score.class).getScore());
            if (firebaseUser != null) {
                if (firebaseUser.getEmail().equals((ds.getValue(Score.class).getName()) + "@animalstation.pl")) {
                    listofLocalScores.add(score);
                }
            }
            listOfGlobalScores.add(score);
        }
    }


    private void sorting(ArrayList<Score> unsortedList) {
        for (int i = 0; i < unsortedList.size(); i++) {
            for (int j = i + 1; j <= unsortedList.size() - 1; j++) {
                if (unsortedList.get(i).getScore() < unsortedList.get(j).getScore()) {
                    Collections.swap(unsortedList, j, i);
                }
            }
        }
        for (int i = 0; i < unsortedList.size(); i++) {
            for (int j = i + 1; j <= unsortedList.size() - 1; j++) {
                if (unsortedList.get(i).getScore() == unsortedList.get(j).getScore()) {
                    sortTime(unsortedList, unsortedList.get(i), unsortedList.get(j));
                }
            }
        }
    }

    private void sortTime(ArrayList<Score> sortingForMinutes, Score o1, Score o2) {
        if (o1.getTime() > o2.getTime()) {
            Collections.swap(sortingForMinutes, sortingForMinutes.indexOf(o2), sortingForMinutes.indexOf(o1));
        }
    }

    private void globalRankingOnclick() {
        loginText.setVisibility(View.GONE);
        if (listOfGlobalScores.isEmpty()) {
            if (!checkInternetConnection()) {
                loginText.setText("No internet");
                progressBar.setVisibility(View.GONE);
                loginText.setVisibility(View.VISIBLE);
            } else {
                progressBar.setVisibility(View.VISIBLE);
            }
        } else {
            progressBar.setVisibility(View.GONE);
        }
        localRanking.setBackgroundColor(Color.TRANSPARENT);
        globalRanking.setBackgroundColor(Color.parseColor("#1A000000"));
        adapter = new RankingListAdapter(this, listOfGlobalScores);
        rV.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        rV.setVisibility(View.VISIBLE);
    }

    private void localRankingOnClick() {
        globalRanking.setBackgroundColor(Color.TRANSPARENT);
        localRanking.setBackgroundColor(Color.parseColor("#1A000000"));
        if (firebaseAuth.getCurrentUser() != null) {
            //User logged in, we can populate both local and global ranking, default is global ranking
            progressBar.setVisibility(View.VISIBLE);
            firebaseUser = firebaseAuth.getCurrentUser();
            loginText.setVisibility(View.GONE);
            adapter = new RankingListAdapter(this, listofLocalScores);
            rV.setAdapter(adapter);
            if (listofLocalScores.isEmpty()) {
                if (checkInternetConnection()) {
                    loginText.setText("Go and play!");
                } else {
                    loginText.setText("No internet");
                }
                loginText.setVisibility(View.VISIBLE);
            }
            adapter.notifyDataSetChanged();
            progressBar.setVisibility(View.GONE);

        } else {
            loginText.setVisibility(View.VISIBLE);
            rV.setVisibility(View.GONE);
        }
    }

    public boolean checkInternetConnection() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        return connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED;
    }


}
