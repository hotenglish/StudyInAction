#include <QApplication>
#include <QDialog>
#include "ui_rectangledialog.h"
#include "rectangledialog.h"

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);
    RectangleDialog *dialog=new RectangleDialog();
    dialog->show();
    return app.exec();
}
