package com.example.baitap3_slider;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ImagesSlider implements Serializable {
    @SerializedName("id")
    int id;
    @SerializedName("position")
    int position;
    @SerializedName("avatar")
    String avatar;

    public ImagesSlider(int id, int position, String avatar) {
        this.id = id;
        this.position = position;
        this.avatar = avatar;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
