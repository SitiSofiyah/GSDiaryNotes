package com.example.intel.admin_diary.Model;

import com.google.gson.annotations.SerializedName;

public class Lokasi {
    @SerializedName("id_lokasi")
    private String id_lokasi;
    @SerializedName("lokasi")
    private String lokasi;

    public Lokasi(String id_lokasi, String lokasi) {
        this.id_lokasi = id_lokasi;
        this.lokasi = lokasi;
    }

    public String getId_lokasi() {
        return id_lokasi;
    }

    public void setId_lokasi(String id_lokasi) {
        this.id_lokasi = id_lokasi;
    }

    public String getLokasi() {
        return lokasi;
    }

    public void setLokasi(String lokasi) {
        this.lokasi = lokasi;
    }
}
