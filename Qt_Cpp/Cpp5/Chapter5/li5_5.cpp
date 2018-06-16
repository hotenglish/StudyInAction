//li5_5.cpp
#include <iostream>

using namespace std;

class Base{
    public:
        virtual void Fn(){    //基类的成员函数定义为虚函数
            cout<<"In Base class\n";
        }
};

class Subclass:public Base{
    public:
        virtual void Fn(){            //子类的成员函数定义为虚函数
            cout<<"In SubClass\n";    //虚函数的“虚”属性可以自动继承下来，派生类的virtual可以默认
        }
};

void Test(Base& b){
    b.Fn();
}

int main()
{
    Base bc;
    Subclass sc;
    cout << "Calling Test(bc)\n";
    Test(bc);
    cout << "Calling Test(sc)\n";
    Test(sc);
    return 0;
}
