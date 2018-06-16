//You_Newspace.h  //头文件名
#include <iostream>

using namespace std;

namespace You_Newspace {
    class Array{
        private:
           int x;
        public:
           void Set(int);
           void Display();
    };

    //在此可以加入其他的变更或常量的声明
    class Matrix{
        private:
           int x;
        public:
           void Set(int);
           void Show();
    };
}

void You_Newspace::Array::Set(int u){x=u;}
void You_Newspace::Array::Display(){cout<<x<<endl;}
void You_Newspace::Matrix::Set(int v){x=v;}
void You_Newspace::Matrix::Show(){cout<<x<<endl;}
