package com.company;

import java.util.ArrayList;

public class Zbior {    // zbior zawierajacy tyle podzbiorow ile mamy krawedzi
    private ArrayList<Podzbior> list;
    public Zbior(int n)
    {
        list = new ArrayList<>();
        for(int i=1;i<=n;i++)
        {
            list.add(new Podzbior());
            list.get(i-1).add(i);
        }
    }
    int index(int t)
    {
        for(int i=0;i<list.size();i++)
        {
            if(list.get(i).contains(t)) return i;
        }
        return 0;
    }
    boolean move(int m, int k)  // scalanie ze soba podzbiorow
    {
        int n1 = index(m);
        int n2 = index(k);
        if(n1==n2)
        {
            return false;
        }
        else if(list.get(n1).size()>list.get(n2).size())
        {
            for(int j=list.get(n2).getList().size()-1;j>=0;j--)
            {
                list.get(n1).add(list.get(n2).getList().get(j));
                list.get(n2).remove(list.get(n2).getList().get(j));
            }
            return true;
        }
        else
        {
            for(int j=list.get(n1).getList().size()-1;j>=0;j--)
            {
                list.get(n2).add(list.get(n1).getList().get(j));
                list.get(n1).remove(list.get(n1).getList().get(j));
            }
            return true;
        }
    }
    int size()
    {
        return list.size();
    }
}
