package com.company;

import java.util.ArrayList;

public class Stack {

    private ArrayList<Integer> s;
    private int length;



    public Stack()
    {
        length = 0;
        s = new ArrayList<>();
    }



    void push(int e)
    {
        s.add(e);
        length++;
    }

    int top()
    {
        return s.get(length-1);
    }

    void pop()
    {
        s.remove(length-1);
        length--;
    }

    boolean empty()
    {
        return length == 0;
    }

    void clear()
    {
        while(length>0)
        {
            pop();
        }
    }

}
