package com.company;

public class Edge {


    private int start;
    private int end;
    private int capacity;
    private int flow;



    public Edge(int start, int end, int capacity)
    {
        this.end = end;
        this.start = start;
        this.capacity = capacity;
        flow = 0;
    }

    public Edge(int start, int end, int capacity, int flow)
    {
        this.end = end;
        this.start = start;
        this.capacity = capacity;
        this.flow = flow;
    }



    public int getEnd() {
        return end;
    }

    public int getStart() {
        return start;
    }

    public int getFlow() {
        return flow;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

}
