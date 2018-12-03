package com.example.intel.admin_diary.Model;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GetLokasi   {
    @SerializedName("status")
    String status;
    @SerializedName("result")
    List<Lokasi> listDataLokasi;
    @SerializedName("message")
    String message;

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Lokasi> getListDataLokasi() {
        return listDataLokasi;
    }

    public void setListDataPembelian(List<Lokasi> listDataLokasi) {
        this.listDataLokasi = listDataLokasi;
    }


}
