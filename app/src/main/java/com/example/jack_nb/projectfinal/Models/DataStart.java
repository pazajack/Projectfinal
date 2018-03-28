package com.example.jack_nb.projectfinal.Models;

/**
 * Created by JACK-NB on 28/03/2018.
 */

public class DataStart {
    private int num;
    private int status;

    public DataStart() {

    }

    public DataStart(int num, int status) {
        this.num = num;
        this.status = status;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
