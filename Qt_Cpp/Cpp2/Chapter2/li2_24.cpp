//li2_24.cpp
#include <iostream>
#include <cstdlib>
using namespace std;
int main(){
    int *p=NULL,*t;
    int i;
    p=new int[10];              //申请10个整型元素的数组空间
    *p=10;
    if(p==NULL){
        cout<<"allocation failure\n";
        exit(1);
    }
    for(i=0;i<10;i++){
        p[i]=i;
    }
    for(t=p;t<p+10;t++){
        cout<<*t<<" ";
    }
    cout<<endl;
    for(i=0;i<10;i++){
        cout<<p[i]<<" ";
    }
    cout<<endl;
    delete []p;
    return 0;
}
