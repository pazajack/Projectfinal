package com.example.jack_nb.projectfinal.Models;

import com.google.firebase.database.Exclude;

import java.util.HashMap;
import java.util.Map;

public class Data {
    private int num;
    private String date;

    public Data() {
    }

    public Data(int num, String date) {
        this.num = num;
        this.date = date;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Exclude
    public Map<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("num", num);
        result.put("date", date);

        return result;
    }
}
