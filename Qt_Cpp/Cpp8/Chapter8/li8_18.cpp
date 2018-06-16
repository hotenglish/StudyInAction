//li8_18.cpp
#include <iostream>
#include "My_Newspace.h"   //包含头文件
#include "You_Newspace.h"  //包含头文件

int main(){
    My_Newspace::Array ma;    //两者都可以用Array,互不干涉
    You_Newspace::Array ia;   //两者都可以用Array,互不干涉
    ma.Set(5);ia.Set(8);
    You_Newspace::Matrix mb;  //两者都可以用Matrix,互不干涉
    My_Newspace::Matrix ib;   //两者都可以用Matrix,互不干涉
    mb.Set(8);ib.Set(9);
    ma.Display();ia.Display();
    mb.Show();ib.Show();
    return 0;
}

