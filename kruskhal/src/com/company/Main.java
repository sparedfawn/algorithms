package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static int dziel(ArrayList<krawedz> lista, int L, int P) // dzielenie tabeli wzgledem wybranego piwota dla algorytmu sortujacego
    {
        int pom, piwot, Ltemp = L, Ptemp = P;
        piwot = lista.get(L).getWaga();
        while(true)
        {
            while(lista.get(Ltemp).getWaga()<piwot)
            {
                Ltemp++;
            }
            while(lista.get(Ptemp).getWaga()>piwot)
            {
                Ptemp--;
            }
            if(Ltemp>=Ptemp)
            {
                return Ptemp;
            }
            else
            {
                pom = lista.get(Ltemp).getWaga();
                lista.get(Ltemp).setWaga(lista.get(Ptemp).getWaga());
                lista.get(Ptemp).setWaga(pom);

                pom = lista.get(Ltemp).getJeden();
                lista.get(Ltemp).setJeden(lista.get(Ptemp).getJeden());
                lista.get(Ptemp).setJeden(pom);

                pom = lista.get(Ltemp).getDwa();
                lista.get(Ltemp).setDwa(lista.get(Ptemp).getDwa());
                lista.get(Ptemp).setDwa(pom);

                Ltemp++;
                Ptemp--;
            }
        }
    }

    public static void quicksort(ArrayList<krawedz> lista, int L, int P)    // sortowanie
    {
        int srodek;
        if(L < P)
        {
            srodek = dziel(lista, L, P);
            quicksort(lista, L, srodek);
            quicksort(lista, srodek+1, P);
        }
    }

    public static void main(String[] args) {
	    File file = new File("In0302.txt");
	    ArrayList<krawedz> baza = new ArrayList();
	    Scanner input = null;
        try {
            input = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter output = null;
        try {
            output = new PrintWriter(new FileWriter("Out0302.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        int n = input.nextInt();
        int m = input.nextInt();
        String buf = input.nextLine();
        for(int i=1;i<=n;i++)   // czytanie z pliku
        {
            String temp = input.nextLine();
            for(int j=0;j<temp.length();j++)
            {
                int dwa = Integer.parseInt(String.valueOf(temp.charAt(j)));
                j=j+2;
                int waga = Integer.parseInt(String.valueOf(temp.charAt(j)));
                j++;
                if(dwa>i)
                {
                    krawedz toadd = new krawedz(waga, i, dwa);
                    baza.add(toadd);
                }
            }
        }

        quicksort(baza, 0, m-1);    // sortowanie krawedzi wedlug wag

        Zbior zbior = new Zbior(n);

        int sumawag = 0;
        for(int i=0;i<m;i++)
        {
            // wybieranie krawedzi do momentu az bedziemy mieli jeden pozdbior ze wszystkimi wierzcholkami
            int temp1 = baza.get(i).getJeden();
            int temp2 = baza.get(i).getDwa();
            if(zbior.size()>1)
            {
                boolean temp = zbior.move(temp1, temp2);
                if(temp)
                {
                    sumawag+=baza.get(i).getWaga();
                    output.print(temp1+" "+temp2+" ["+baza.get(i).getWaga()+"], ");
                }
            }
            else
            {
                break;
            }
        }
        output.println();
        output.println(sumawag);
        output.close();
        input.close();
    }
}
