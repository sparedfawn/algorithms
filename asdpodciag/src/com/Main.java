package com;

import java.io.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        File file = new File("In0301.txt");
        Scanner input = null;
        try
        {
            input = new Scanner(file);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        PrintWriter output = null;
        try
        {
             output = new PrintWriter(new FileWriter("Out0301.txt"));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        int n = input.nextInt();
        for (int i = 0; i < n; i++)
        {
            String s1 = input.next();
            String s2 = input.next();
            int l1 = s1.length();
            int l2 = s2.length();
            int[][] tab = new int[l1 + 1][l2 + 1];
            for (int j = 0; j <= l1; j++)
            {
                for (int k = 0; k <= l2; k++)       // wypelnianie tablicy (metoda programowania dynamiczna)
                {
                    if (j == 0 || k == 0)
                    {
                        tab[j][k] = 0;
                    }
                    else if (s1.charAt(j - 1) == s2.charAt(k - 1))
                    {
                        tab[j][k] = tab[j - 1][k - 1] + 1;
                    }
                    else
                    {
                        if (tab[j][k - 1] > tab[j - 1][k])
                        {
                            tab[j][k] = tab[j][k - 1];
                        }
                        else
                        {
                            tab[j][k] = tab[j - 1][k];
                        }
                    }
                }
            }
            String odp = "";
            while(tab[l1][l2]!=0)       // odczytywanie wyniku z tablicy
            {
                if(s1.charAt(l1-1)==s2.charAt(l2-1))
                {
                    odp=s1.charAt(l1-1)+odp;
                    l1--;
                    l2--;
                }
                else
                {
                    if(tab[l1-1][l2]>=tab[l1][l2-1])
                    {
                        l1--;
                    }
                    else
                    {
                        l2--;
                    }
                }
            }
            output.println(odp);
        }
        input.close();
        output.close();
    }
}
