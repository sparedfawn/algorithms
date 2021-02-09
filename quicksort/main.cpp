#include <iostream>
#include <fstream>

using namespace std;

int dziel(int tab[], int L, int P)
{
    int pom, piwot, Ltemp = L, Ptemp = P;
    piwot = tab[L];
    while(true)
    {
        while(tab[Ltemp] < piwot)
        {
            Ltemp++;
        }
        while(tab[Ptemp] > piwot)
        {
            Ptemp--;
        }
        if(Ltemp >= Ptemp)
        {
            return Ptemp;
        }
        else
        {
            pom = tab[Ltemp];
            tab[Ltemp] = tab[Ptemp];
            tab[Ptemp] = pom;
            Ltemp++;
            Ptemp--;
        }
    }
}

void quicksort(int tab[], int L, int P)
{
    int srodek;
    if(L < P)
    {
        srodek = dziel(tab, L, P);
        quicksort(tab, L, srodek);
        quicksort(tab, srodek+1, P);
    }
}


int main()
{
    ifstream dane("In0201.txt");
    ofstream wynik("Out0201.txt");
    int n;
    dane>>n;
    int *tab = new int[n];
    for(int i=0;i<n;i++)
    {
        dane>>tab[i];
    }
    quicksort(tab, 0, n-1);
    for(int i=0;i<n;i++)
    {
        wynik<<tab[i]<<" ";
    }

    delete [] tab;
}
