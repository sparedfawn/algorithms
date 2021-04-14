package com.company;

public class Label {


    private int vertex;
    private int flow;



    public Label()
    {
        this.vertex = 0;
        this.flow = 0;
    }



    public int getFlow() {
        return flow;
    }

    public int getVertex() {
        return vertex;
    }

    public void setFlow(int flow) {
        this.flow = flow;
    }

    public void setVertex(int value) {
        this.vertex = value;
    }

}
