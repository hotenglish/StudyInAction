#include <iostream>

using namespace std;

class Parent{
    public:
        Parent(){
            cout<<"执行默认构造函数 Parent()"<<endl;
        }

};

class Member_A{
  public:
    Member_A(){
        cout<<"执行默认构造函数 Member_A()"<<endl;
    }
};


class Member_B{
  public:
    Member_B(){
        cout<<"执行默认构造函数 Member_B()"<<endl;
    }
};

class Child: public Parent{
  private:
    int value;
    Member_A a;
    Member_B b;
  public:
    Child(int p){                 //自动调用 Parent()
        cout<<"执行构造函数 Child(int p)"<<endl;
        value=p;
    }
};

int main(){
    Child ob(100);
    return 0;
}
