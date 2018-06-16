//li3_28.cpp
#include <iostream>
using namespace std;
class Student;          //前向引用声明
class Teacher{
  public:
    Teacher();
    void Tdisp();
private:
    int tno;
    char tname[10];
    friend void Disp(Teacher &t,Student &s);   //声明Disp为本类的友元函数
};

Teacher::Teacher(){
    cout<<"请输入教师工号和姓名:";
    cin>>tno;
    cin>>tname;
}

void Teacher::Tdisp(){
    cout<<"教师信息:"<<endl;
    cout<<"工号:"<<tno;
    cout<<"姓名:"<<tname;
    cout<<endl;
}

class Student{
public:
    Student();
    void Sdisp();                              //显示学生基本信息
private:
    int sno;
    char sname[10];
    friend void Disp(Teacher &t,Student &s);   //声明Disp为本类的友元函数
};

Student::Student(){
    cout<<"请输入学生编号和姓名:";
    cin>>sno;
    cin>>sname;
}

void Student::Sdisp(){
    cout<<"学生信息:"<<endl;
    cout<<"学号:"<<sno;
    cout<<"姓名:"<<sname;
    cout<<endl;
}

void Disp(Teacher &t,Student &s){             //输出选课学生的学号和任课教师的工号
    cout<<"教师编号："<<t.tno<<endl;
    cout<<"学生编号："<<s.sno<<endl;
    cout<<endl;
}

int main(){
    Teacher t1;
    Student s1;
    Disp(t1,s1);
    return 0;
}
