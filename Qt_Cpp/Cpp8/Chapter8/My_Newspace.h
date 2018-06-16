//My_Newspace.h  //头文件名
#include <iostream>

using namespace std;

namespace My_Newspace {
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

void My_Newspace::Array::Set(int u){x=u;}
void My_Newspace::Array::Display(){cout<<x<<endl;}
void My_Newspace::Matrix::Set(int v){x=v;}
void My_Newspace::Matrix::Show(){cout<<x<<endl;}
