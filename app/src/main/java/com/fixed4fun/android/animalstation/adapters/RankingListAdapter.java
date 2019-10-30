package com.fixed4fun.android.animalstation.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.fixed4fun.android.animalstation.R;
import com.fixed4fun.android.animalstation.objects.Score;

import java.util.ArrayList;


public class RankingListAdapter extends RecyclerView.Adapter<RankingListAdapter.RankingViewHolder> {

    private ArrayList<Score> listOfScores;
    private LayoutInflater mInflater;
//    TextView indexRanking;
//    //indexRanking.setText(score.getIndex());
//    TextView usernameRanking;
//    // usernameRanking.setText(score.getName());
//    TextView pointsRanking;
//    // pointsRanking.setText(String.valueOf(score.getScore()));
//    TextView timeRanking;
//    //  timeRanking.setText(String.valueOf(score.getTime()));

    public RankingListAdapter(Context context, ArrayList<Score> list) {
        this.mInflater = LayoutInflater.from(context);
        this.listOfScores = list;
    }

    @NonNull
    @Override
    public RankingViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.ranking_item_layout, parent, false);
        return new RankingViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RankingViewHolder holder, int position) {
        Score score = listOfScores.get(position);
        holder.indexRanking.setText(String.valueOf(position + 1));
        holder.pointsRanking.setText(String.valueOf(score.getScore()));
        holder.usernameRanking.setText(score.getName());
        long time = score.getTime();
        if (time / 1000 < 60) {
            String toSet = time / 1000 + ":" + time % 100 + "s";
            holder.timeRanking.setText(toSet);
        } else {
            String toSet = time / 60000 + ":" + (time % 60000) / 1000 + ":" + time % 100 + "s";
            holder.timeRanking.setText(toSet);
        }
    }

    @Override
    public int getItemCount() {
        return listOfScores.size();
    }


    public class RankingViewHolder extends RecyclerView.ViewHolder {

        TextView indexRanking;
        TextView usernameRanking;
        TextView pointsRanking;
        TextView timeRanking;

        public RankingViewHolder(@NonNull View itemView) {
            super(itemView);
            indexRanking = itemView.findViewById(R.id.index_ranking);
            usernameRanking = itemView.findViewById(R.id.name_ranking);
            pointsRanking = itemView.findViewById(R.id.points_ranking);
            timeRanking = itemView.findViewById(R.id.time_ranking);
        }
    }

}