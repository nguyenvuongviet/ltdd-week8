package com.example.baitap3_slider;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class MessageModel implements Serializable {

    @SerializedName("success")
    boolean success;
    @SerializedName("message")
    String message;
    @SerializedName("result")
    List<ImagesSlider> result;

    public MessageModel(boolean success, String message, List<ImagesSlider> result) {
        this.success = success;
        this.message = message;
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<ImagesSlider> getResult() {
        return result;
    }

    public void setResult(List<ImagesSlider> result) {
        this.result = result;
    }
}
