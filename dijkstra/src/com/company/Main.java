package com.company;

import java.io.*;
import java.util.Scanner;

public class Main {
    static int n;
    static int najkrotsza(int dist[], Boolean zaliczenie[])     // algorytm zachlanny wybiera najblizsza wagowo krawedz ktora nie byla odwiedzona
    {
        int index = -1;
        int waga = 999;
        for(int i=0;i<n;i++)
        {
            if(zaliczenie[i]==false && dist[i]<=waga)
            {
                waga = dist[i];
                index = i;
            }
        }
        return index;
    }

    static void dijkstra(int ujscie, int graf[][])  // algorytm dijkstry
    {
        int pred[] = new int[n];    // tablica poprzednikow
        int dist[] = new int[n];    // tablica calkowitej wagi przejscia
        Boolean zaliczenie[] = new Boolean[n];  // tablica kontrolujaca w ktorym wierzcholku algorytm juz byl

        for(int i=0;i<n;i++)    // poczatkowe inicjalizacje
        {
            zaliczenie[i] = false;
            dist[i] = 999;
        }
        dist[ujscie] = 0;
        pred[ujscie] = -1;

        for(int i=0; i<n-1; i++)        // wlasciwa czesc algorytmu
        {
            int a = najkrotsza(dist, zaliczenie);
            zaliczenie[a] = true;
            for(int b = 0; b < n; b++)
            {
                if(!zaliczenie[b] && dist[a]!=999 && dist[a]+graf[a][b] < dist[b] && graf[a][b]!=0)
                {
                    pred[b] = a;
                    dist[b] = dist[a]+graf[a][b];
                }
            }
        }

        PrintWriter output = null;      // wypisywanie otrzymanego wyniku do pliku
        try
        {
            output = new PrintWriter(new FileWriter("Out0301.txt"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        output.print("Dist: ");
        for(int i = 0; i<n; i++)
        {
            output.print(dist[i]+" ");
        }
        output.println();
        output.print("Pred: ");
        for(int i = 0; i<n; i++)
        {
            int temp = pred[i];
            if(temp!= -1)
            {
                temp++;
            }
            output.print(temp+" ");
        }
        output.close();
    }

    public static void main(String[] args)
    {
        File file = new File("In0303.txt");
        Scanner input = null;
        try
        {
            input = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        n = input.nextInt();
        int ujscie = input.nextInt();
        int[][] graf = new int[n][n];
        for(int i=0; i<n; i++)
        {
            for(int j=0; j<n; j++)
            {
                graf[j][i] = input.nextInt();   // wczytywanie macierzy wag krawedzi odwracajac kierunki krawedzi
            }
        }
        dijkstra(ujscie-1, graf);
        input.close();
    }
}
