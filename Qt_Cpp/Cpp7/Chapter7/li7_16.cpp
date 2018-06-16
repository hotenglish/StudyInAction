//li7_16.cpp
#include <iostream>
#include <stdexcept>
using namespace std;

class Array{
    private:
        int size;
        int *ptr;
    public:
       Array(int s=1){
           size=s;
           ptr=new int[s];                  //为数组分配内存空间
       }

       int &operator[] (int offset);        //重载[]运算符

       ~Array(){
          delete[] ptr;
       }
};

int &Array::operator[] (int offset){
    if(offset<0 || offset>=size)
        throw out_of_range("out_of_range error!");
        //抛出异常对象，并传递异常特征字符串“out_of_range error!”
    return ptr[offset];
}

int main(){
    Array array(4);
    try{
        for(int i=0;i<10;i++){        //依次为数组元素赋值0～3，当时i=4时抛出越界异常
            array[i]=i;
            cout<<array[i]<<" ";
        }
        cout<<endl;
    }catch(out_of_range &excep){      //捕获out_of_range类型的异常
        cout<<excep.what()<<endl;
    }
    return 0;
}
