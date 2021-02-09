package com.company;

public class krawedz {
    private int waga;
    private int jeden;
    private int dwa;

    public krawedz(int waga, int jeden, int dwa)
    {
        this.waga = waga;
        this.jeden = jeden;
        this.dwa = dwa;
    }

    public void setWaga(int waga) {
        this.waga = waga;
    }

    public void setJeden(int jeden) {
        this.jeden = jeden;
    }

    public void setDwa(int dwa) {
        this.dwa = dwa;
    }

    public int getWaga() {
        return waga;
    }

    public int getJeden() {
        return jeden;
    }

    public int getDwa() {
        return dwa;
    }
}
