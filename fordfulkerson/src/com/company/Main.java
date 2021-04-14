package com.company;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static PrintWriter output = null;
    public static int n, s, k;


    public static void init (EdgeList list) {

        File file = new File("In0401.txt");
        Scanner input = null;

        try
        {
            input = new Scanner(file);
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        n = input.nextInt();  // wczytywanie danych
        s = input.nextInt();
        k = input.nextInt();

        String buf = input.nextLine();

        for (int i = 1; i <= n; i++) {

            String temp = input.nextLine();

            if (!temp.equals("--"))

                for (int j = 0; j < temp.length(); j++) {

                    int end = Integer.parseInt(String.valueOf(temp.charAt(j)));

                    j+=2;
                    int cost = 0;

                    while (temp.charAt(j) >= '0' && temp.charAt(j) <= '9') {

                        cost *= 10;
                        cost += Integer.parseInt(String.valueOf (temp.charAt(j)));
                        j++;

                        if (j == temp.length())  break;

                    }

                    list.add(new Edge(i, end, cost));
                }
        }

        input.close();

        try
        {
            output = new PrintWriter(new File("Out0401.txt"));
        }

        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }

    public static void flow (Label[] labels, EdgeList list) {

        int temp = k;
        int flow = labels[k].getFlow();

        output.println();
        output.print(k + " ");

        while (temp != s) {

            int wierz = labels[temp].getVertex();
            int index = list.indexof(new Edge(wierz, temp, 0));

            output.print(wierz+" ");

            if (list.ifFront(list.getList().get(index), wierz))

                list.getList().get(index).setFlow(list.getList().get(index).getFlow() + flow);

            else

                list.getList().get(index).setFlow(list.getList().get(index).getFlow() - flow);


            temp = wierz;
        }
    }

    public static int fordfulkerson(Stack stack, Label[] labels, EdgeList list) {

        while (!stack.empty()) {

            int v = stack.top();
            stack.pop();

            ArrayList<Edge> neighbors = list.neighbors(v);    // wybor sasiada wierzcholka v

            for (Edge neighbor : neighbors) {

                if (list.ifFront(neighbor, v))  // przypadek gdy krawedz jest przednia


                    if (labels[neighbor.getEnd()].getVertex() == 0 && labels[neighbor.getEnd()].getFlow() == 0)

                        // przypadek gdy wierzcholek nie ma labels
                        if (neighbor.getCapacity() - neighbor.getFlow() > 0)
                        {
                            // warunek czy przepustowosc-przeplyw>0
                            labels[neighbor.getEnd()].setVertex(v);
                            labels[neighbor.getEnd()].setFlow(Math.min(labels[v].getFlow(), neighbor.getCapacity() - neighbor.getFlow()));

                            if (neighbor.getEnd() == k)
                            {
                                // warunek jesli doszlismy do konca (zwiekszenie przeplywu o wartosc)
                                flow(labels, list);

                                stack.clear();
                                stack.push(s);

                                return 0;
                            }
                            else
                            {
                                stack.push(neighbor.getEnd()); // zwiekszenie stosu jesli nie jestesmy na koncu

                                output.print(neighbor.getEnd() + " ");
                            }
                        }

                else
                    // krawedz wsteczna
                    if (labels[neighbor.getStart()].getVertex() == 0 && labels[neighbor.getStart()].getFlow() == 0)

                        // sprawedzenie czy wierzcholek ma etykiete
                        if (neighbor.getFlow() > 0)
                        {
                            // czy przeplyw dodatni
                            labels[neighbor.getStart()].setVertex(v);
                            labels[neighbor.getStart()].setFlow(Math.min(labels[v].getFlow(), neighbor.getFlow()));

                            if (neighbor.getStart() == k)
                            {
                                // jesli doszlismy do konca (zwiekszenie przeplywu)
                                flow(labels, list);

                                stack.clear();
                                stack.push(s);

                                return 0;
                            }
                            else
                            {
                                stack.push(neighbor.getStart());   // zwiekszenie stosu jesli nie koniec

                                output.print(neighbor.getStart() + " ");
                            }
                        }
                    }
        }

        return 1;
    }

    public static void main(String[] args)
    {
        EdgeList list = new EdgeList();

        init(list);

        Stack stack = new Stack();
        Label[] labels = new Label[n + 1];

        int control = 0;

        while (control == 0)
        {

            for (int i = 0; i <= n; i++)    labels[i] = new Label();


            labels[s].setFlow(999);  // etykieta dla poczatku
            labels[s].setVertex(-1);
            stack.push(s);

            output.println();
            output.print("Stos: ");
            output.print(s + " ");

            control = fordfulkerson(stack, labels, list);
        }

        int first = 0;

        for (int i = 0; i < list.length(); i++)
        {
            if (list.getList().get(i).getStart() == first)
            {
                output.print(list.getList().get(i).getEnd() + " " + list.getList().get(i).getFlow() + ", ");
            }
            else
            {
                first = list.getList().get(i).getStart();

                output.println();
                output.print("[" + first + "]: " + list.getList().get(i).getEnd() + " " + list.getList().get(i).getFlow() + ", ");
            }
        }

        output.println();

        ArrayList<Edge> score = list.neighbors(k);
        int result = 0;

        for (int i = 0; i < score.size(); i++)
        {
            output.print(score.get(i).getFlow());

            if (i < score.size() - 1)
            {
                output.print("+");
            }

            result += score.get(i).getFlow();
        }

        output.print("=" + result);
        output.close();
    }
}
