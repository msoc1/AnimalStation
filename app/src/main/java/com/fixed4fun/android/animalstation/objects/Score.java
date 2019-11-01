package com.fixed4fun.android.animalstation.objects;

import android.os.Parcel;
import android.os.Parcelable;

public class Score implements Parcelable {
    long time;
    int score;
    String name;

    protected Score(Parcel in) {
        time = in.readLong();
        score = in.readInt();
        name = in.readString();
    }


    public Score() {
    }

    public static final Creator<Score> CREATOR = new Creator<Score>() {
        @Override
        public Score createFromParcel(Parcel in) {
            return new Score(in);
        }

        @Override
        public Score[] newArray(int size) {
            return new Score[size];
        }
    };

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Score(String name, long time, int score) {
        this.time = time;
        this.score = score;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Score{" +
                " name=" + name +
                ", time=" + time +
                ", score=" + score +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(time);
        parcel.writeInt(score);
        parcel.writeString(name);
    }
}