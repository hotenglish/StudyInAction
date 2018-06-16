#include "rectangledialog.h"
#include <QtGui>

RectangleDialog::RectangleDialog(QWidget *parent) : QDialog(parent){
    setupUi(this);
    QRegExp regExp("^[0-9]+(\\.[0-9]+)?$");
    lengthEdit->setValidator(new QRegExpValidator(regExp,this));
    widthEdit->setValidator(new QRegExpValidator(regExp,this));
    connect(calculateButton,SIGNAL(clicked()),this,SLOT(calculateClicked()));
    connect(closeButton,SIGNAL(clicked()),this,SLOT(close()));
    connect(this,SIGNAL(rectangleLengthWidth(const QString &,const QString &)),
            this,SLOT(rectangleAreaCalculate(const QString &,const QString &)));
    connect(lengthEdit,SIGNAL(textChanged(const QString &)),this,SLOT(on_lengthEdit_textChanged(const QString &)));
    connect(widthEdit,SIGNAL(textChanged(const QString &)),this,SLOT(on_widthEdit_textChanged(const QString &)));
}

void RectangleDialog::on_lengthEdit_textChanged(const QString &){
    calculateButton->setEnabled(isContentFinished());
}

void RectangleDialog::on_widthEdit_textChanged(const QString &){
    calculateButton->setEnabled(isContentFinished());
}

bool RectangleDialog::isContentFinished(){
    return lengthEdit->hasAcceptableInput() && widthEdit->hasAcceptableInput();
}

void RectangleDialog::calculateClicked(){
    QString lengthString=lengthEdit->text();
    QString widthString=widthEdit->text();
    emit rectangleLengthWidth(lengthString,widthString);
}

void RectangleDialog::rectangleAreaCalculate(const QString &lengthStr,const QString &widthStr){
    double length=lengthStr.toDouble();
    double width=widthStr.toDouble();
    double result=length*width;
    rectangleAreaEdit->setText(QString::number(result,'f',2));
}
