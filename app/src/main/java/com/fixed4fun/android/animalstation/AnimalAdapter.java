package com.fixed4fun.android.animalstation;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class AnimalAdapter extends ArrayAdapter<Animal> {
    AnimalAdapter(Activity context, List<Animal> animal){
        super(context,0,animal);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View animalView = convertView;

        if(animalView == null){
            animalView = LayoutInflater.from(getContext()).inflate(R.layout.animals2, parent,false);
        }


        Animal currentAnimal = getItem(position);

        ImageView animalImageView = animalView.findViewById(R.id.images);
        assert currentAnimal != null;
        animalImageView.setImageResource(currentAnimal.getmAnimalIcon());

        TextView animalNameView = animalView.findViewById(R.id.images2);
        animalNameView.setText(currentAnimal.getmAnimalName());

        return animalView;


    }
}
