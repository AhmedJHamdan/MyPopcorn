package com.udacity.ahmed.mypopcorn.pojo.Credits;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

import com.udacity.ahmed.mypopcorn.pojo.Cast.Cast;

/**
 * Created by Ahmed Hamdan.
 */


public class Credits implements Parcelable {

    public static final Creator<Credits> CREATOR = new Creator<Credits>() {
        @Override
        public Credits createFromParcel(Parcel source) {
            return new Credits(source);
        }

        @Override
        public Credits[] newArray(int size) {
            return new Credits[size];
        }
    };
    @SerializedName("cast")
    @Expose
    private List<Cast> cast = null;
    @SerializedName("crew")
    @Expose
    private List<Object> crew = null;

    public Credits() {
    }

    protected Credits(Parcel in) {
        this.cast = new ArrayList<>();
        in.readList(this.cast, Cast.class.getClassLoader());
        this.crew = new ArrayList<>();
        in.readList(this.crew, Object.class.getClassLoader());
    }

    public List<Cast> getCast() {
        return cast;
    }

    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }

    public List<Object> getCrew() {
        return crew;
    }

    public void setCrew(List<Object> crew) {
        this.crew = crew;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeList(this.cast);
        dest.writeList(this.crew);
    }
}