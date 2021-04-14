package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int n;
    public static int[][] graph, normal;
    public static int[] delta;
    public static PrintWriter output = null;

    public static void init() {

        File file = new File("In0402.txt"); // czytanie z plikow i inicjalizacja grafow
        Scanner input = null;

        try
        {
            input = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        try
        {
            output = new PrintWriter(new FileWriter("Out0402.txt"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }


        n = input.nextInt();
        graph = new int[n+1][n+1];   // tworzenie grafu z wierzcholkiem zero (krok 1)
        normal = new int[n][n];

        String buf = input.nextLine();

        for (int i = 0; i <= n; i++)

            for (int j = 0; j <= n; j++)

                if (i == 0)
                {
                    graph[i][j] = 0;
                }
                else if (j == 0)
                {
                    graph[i][j] = 999;
                }
                else if (i == j)
                {
                    graph[i][j] = 0;
                    normal[i-1][j-1] = 0;
                }
                else
                {
                    graph[i][j] = 999;
                    normal[i-1][j-1] = 999;
                }



        for (int i = 1; i < n; i++)
        {
            String temp = input.nextLine(); // wczytywanie list incydencji z pliku

            if(temp.equals("--"))
                i++;

            else
                for (int j = 0; j < temp.length(); j++) {

                    int ending = Integer.parseInt(String.valueOf(temp.charAt(j)));
                    j += 2;
                    char sign = temp.charAt(j);

                    if (sign == '-') {

                        j++;
                        int value = 0;

                        while (temp.charAt(j) >= '0' && temp.charAt(j) <= '9') {

                            value *= 10;
                            value += Integer.parseInt(String.valueOf(temp.charAt(j)));

                            j++;

                            if(j==temp.length())
                                break;

                        }

                        graph[i][ending] = -value;
                        normal[i-1][ending-1] = -value;
                    }

                    else {

                        int waga = 0;

                        while (temp.charAt(j) >= '0' && temp.charAt(j) <= '9') {

                            waga *= 10;
                            waga += Integer.parseInt(String.valueOf(temp.charAt(j)));

                            j++;

                            if (j==temp.length())
                                break;

                        }

                        graph[i][ending] = waga;
                        normal[i-1][ending-1] = waga;
                    }
                }
        }


        input.close();
    }

    static int gready_step (int[] dist, boolean[] visited) // (do alg dijkstry) wybor najblizszego wagowo nieodwiedzonego wierzcholka
    {
        int index = -1;
        int scales = 999;

        for (int i = 0; i < n; i++)

            if (!visited[i]  &&  dist[i] <= scales)
            {
                scales = dist[i];
                index = i;
            }

        return index;
    }

    static int[] dijkstra (int[][] normal, int out)       // algorytm dijkstry
    {
        int[] dist = new int[n];
        boolean[] visited = new boolean[n];

        for (int i=0;i<n;i++) {

            visited[i] = false;
            dist[i] = 999;
        }
        dist[out] = 0;

        for (int i=0; i<n-1; i++) {

            int a = gready_step(dist, visited);
            visited[a] = true;

            for (int b = 0; b < n; b++) {

                if(!visited[b] && dist[a]!=999 && dist[a]+normal[a][b] < dist[b] && normal[a][b]!=0)

                    dist[b] = dist[a]+normal[a][b];
            }
        }

        return dist;
    }
    static ArrayList<Integer> neighbors (int w) {

        ArrayList<Integer> list = new ArrayList<>();

        for (int i = 0; i <= n; i++)

            if (graph[w][i]!=999 && w!=i)

                list.add(i);


        return list;
    }
    static boolean bellmanford (int s)   // algorytm bellmana-forda (zwraca true jesli jest cykl ujemny, false jesli nie)
    {
        delta = new int[n+1];

        for (int i = 0; i <= n; i++)

            if (i == s)
                delta[i] = 0;

            else
                delta[i] = 999;



        for (int i = 0; i < n-1; i++)

            for (int v = 0; v <= n; v++) {

                ArrayList<Integer> temp = neighbors(v);

                for (Integer integer : temp)

                    if (delta[v] + graph[v][integer] < delta[integer])

                        delta[integer] = delta[v] + graph[v][integer];

            }

        for (int v = 0; v <= n; v++)

            for (int w = 0; w <= n; w++)

                if (graph[v][w]!=999)

                    if(delta[w]> delta[v]+ graph[v][w])

                        return true;



        return false;
    }

    public static void main(String[] args) {

        init();

        if(!bellmanford(0)) {

            for (int k : delta)

                output.print(k + " ");


            for(int i=0 ;i<=n;i++)

                for(int j=0;j<=n;j++)

                    if(i!=j && graph[i][j]!=999)

                        graph[i][j] = graph[i][j]+ delta[i]- delta[j];



            output.println();

            for (int i = 0; i <= n; i++) {

                output.print("[" + i + "]: ");

                for (int j = 0; j <= n; j++)

                    if (i != j && graph[i][j] != 999)

                        output.print(j + "(" + graph[i][j] + ") ");


                output.println();
            }

            for(int i = 1; i <= n; i++) {

                int[] tab;
                tab = dijkstra(normal, i-1);

                output.print("Delta^["+i+"][");

                for(int j = 0; j < n; j++) {

                    if (tab[j] - delta[j + 1] + delta[i] < 900)
                        output.print(tab[j] - delta[j + 1] + delta[i]);

                    else
                        output.print("999");


                    output.print(" ");
                }

                output.print("], D["+i+"][");

                for(int j = 0; j < n; j++)

                    output.print(tab[j]+" ");


                output.print("]");
                output.println();
            }
        }

        else
        {
            output.println("Graf ma cykl o ujemnej wadze");
        }

        output.close();
    }
}
