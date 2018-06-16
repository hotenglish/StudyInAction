//li6_4.cpp
#include <iostream>

using namespace std;

class Date{
    int mo,da,yr;
    static int dys[];
public:
    Date (int m=0,int d=0,int y=0){ mo =m; da=d; yr=y; }
    void Display() const {
        cout<<'\n'<<mo<<'/'<<da<<'/'<<yr;
    }

    //Overloaded +operator
    Date operator+(int) const;

    //Overloaded prefix ++operator
    Date operator++()
       {*this=*this+1; return *this;}

    //Overloaded postfix ++operator
    Date operator++(int)    //Date++,后缀
       {Date dt=*this; *this=*this+1; return dt;}
};

int Date::dys[]={31,28,31,30,31,30,31,31,30,31,30,31};

//Overloaded +operator definition
Date Date::operator+(int n)const{
    Date dt=*this;
    n+=dt.da;
    while(n>dys[dt.mo-1]){
        n-=dys[dt.mo-1];
        if(++dt.mo==13){
            dt.mo=1;
            dt.yr++;
        }
    }
    dt.da=n;
    return dt;
}

int main()
{
    Date olddate(2,28,1997);
    olddate++;
    olddate.Display();
    ++olddate;
    olddate=olddate+30;
    olddate.Display();
    return 0;
}
