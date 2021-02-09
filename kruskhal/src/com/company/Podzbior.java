package com.company;

import java.util.ArrayList;

public class Podzbior {     // klasa zawierajaca sklad podzbioru
    private ArrayList<Integer> list;
    public Podzbior()
    {
        list = new ArrayList<>();
    }

    int indexof(int t)
    {
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i)==t) return i;
        }
        return 0;
    }
    int size()
    {
        return list.size();
    }
    void add(int t)
    {
        list.add(t);
    }
    void remove(int t)
    {
        list.remove(indexof(t));
    }
    boolean contains(int t)
    {
        return list.contains(t);
    }

    public ArrayList<Integer> getList() {
        return list;
    }
}
