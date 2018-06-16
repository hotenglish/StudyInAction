#ifndef RECTANGLEDIALOG
#define RECTANGLEDIALOG
#include <QDialog>
#include "ui_rectangledialog.h"

class RectangleDialog : public QDialog,public Ui::RectangleDialog
{
    Q_OBJECT
public:
    RectangleDialog(QWidget *parent = 0);

signals:
    void rectangleLengthWidth(const QString &lengthString,const QString widthString);

private slots:
    void on_lengthEdit_textChanged(const QString &);
    void on_widthEdit_textChanged(const QString &);
    void calculateClicked();
    void rectangleAreaCalculate(const QString &lengthStr,const QString &widthStr);

private:
    bool isContentFinished();
};

#endif
