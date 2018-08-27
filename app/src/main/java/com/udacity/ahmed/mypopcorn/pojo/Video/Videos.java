
package com.udacity.ahmed.mypopcorn.pojo.Video;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Videos implements Parcelable {

    public static final Creator<Videos> CREATOR = new Creator<Videos>() {
        @Override
        public Videos createFromParcel(Parcel source) {
            return new Videos(source);
        }

        @Override
        public Videos[] newArray(int size) {
            return new Videos[size];
        }
    };
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("results")
    @Expose
    private List<VideoResult> results = new ArrayList<>();

    public Videos() {
    }

    protected Videos(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.results = new ArrayList<>();
        in.readList(this.results, VideoResult.class.getClassLoader());
    }

    /**
     * @return The id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id The id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return The results
     */
    public List<VideoResult> getResults() {
        return results;
    }

    /**
     * @param results The results
     */
    public void setResults(List<VideoResult> results) {
        this.results = results;
    }

    @Override
    public String toString() {
        return "Videos{" +
                "id=" + id +
                ", results=" + results +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeList(this.results);
    }
}
