package com.company;

import java.util.ArrayList;

public class EdgeList {

    private ArrayList<Edge> list;



    public EdgeList()
    {
        list = new ArrayList<>();
    }



    void add(Edge k)
    {
        list.add(k);
    }


    int indexof(Edge k)
    {
        for (Edge edge : list) {

            if (edge.getStart()==k.getStart() && edge.getEnd()==k.getEnd() || edge.getStart()==k.getEnd() && edge.getEnd()==k.getStart())

                return list.indexOf(edge);

        }

        return 0;
    }


    ArrayList<Edge> neighbors(int vertex) {

        ArrayList<Edge> temp = new ArrayList<>();

        for (Edge edge : list) {

            if (edge.getEnd() == vertex || edge.getStart() == vertex)   temp.add(edge);

        }

        return temp;
    }


    int length()
    {
        return list.size();
    }


    boolean ifFront(Edge k, int vertex)
    {
        return k.getEnd() != vertex;
    }


    public ArrayList<Edge> getList()
    {
        return list;
    }
}
