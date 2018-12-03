package com.example.intel.admin_diary.Model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GetMood {
    @SerializedName("status")
    private String status;
    @SerializedName("result")
    private List<Mood> result = new ArrayList<Mood>();
    @SerializedName("message")
    private String message;
    public GetMood(List<Mood> moodList) {}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Mood> getResult() {
        return result;
    }

    public void setResult(List<Mood> result) {
        this.result = result;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
