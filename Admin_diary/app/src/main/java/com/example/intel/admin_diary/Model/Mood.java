package com.example.intel.admin_diary.Model;

import com.google.gson.annotations.SerializedName;

public class Mood {
    @SerializedName("id_mood")
    private String idMood;
    @SerializedName("mood")
    private String mood;
    private String action;

    public Mood(String idMood, String mood, String action) {
        this.idMood = idMood;
        this.mood = mood;
        this.action = action;
    }

    public String getIdMood() {
        return idMood;
    }

    public void setIdMood(String idMood) {
        this.idMood = idMood;
    }

    public String getMood() {
        return mood;
    }

    public void setMood(String mood) {
        this.mood = mood;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
