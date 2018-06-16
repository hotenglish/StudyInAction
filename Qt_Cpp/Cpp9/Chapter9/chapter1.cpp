#include <QApplication>   //将类QApplication的定义包含到程序中
#include <QLabel>         //将类QLabel的定义包含到程序中

int main(int argc,char *argv[]){
    QApplication app(argc,argv);
    QLabel *label1=new QLabel("Hello Every One,Welcome to Qt world!");
    QLabel *label2=new QLabel("<h2><B>Hello Every One,<B><font color=red>Welcome to Qt world!</font></h2>");
    label1->show();
    label2->show();
    return app.exec();
}
