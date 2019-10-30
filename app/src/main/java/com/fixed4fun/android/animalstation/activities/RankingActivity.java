package com.fixed4fun.android.animalstation.activities;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.adapters.RankingListAdapter;
import com.fixed4fun.android.animalstation.objects.Score;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class RankingActivity extends AppCompatActivity {

    RankingListAdapter adapter;
    ImageView backtoMain;
    private FirebaseDatabase mFirebaseInstance;
    private DatabaseReference mFirebaseDatabase;
    ArrayList<Score> listOfScores = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.ranking_layout);

        //listOfScores = new ArrayList<>();

        backtoMain = findViewById(R.id.back_to_main);
        backtoMain.setOnClickListener(view -> finish());

        mFirebaseInstance = FirebaseDatabase.getInstance();

        mFirebaseDatabase = mFirebaseInstance.getReference("scores");
        mFirebaseDatabase.keepSynced(true);
        mFirebaseDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //listOfScores.clear();
                addData(dataSnapshot);
                sorting(listOfScores);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        RecyclerView rV = findViewById(R.id.ranking_recyclerView);

        rV.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RankingListAdapter(this, listOfScores);
        rV.setScrollbarFadingEnabled(false);
        rV.setAdapter(adapter);


    }


    private void addData(DataSnapshot dataSnapshot) {
        for (DataSnapshot ds : dataSnapshot.getChildren()) {
            Score score = new Score();
            score.setName(ds.getValue(Score.class).getName());
            score.setTime(ds.getValue(Score.class).getTime());
            score.setScore(ds.getValue(Score.class).getScore());
            listOfScores.add(score);
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


}
