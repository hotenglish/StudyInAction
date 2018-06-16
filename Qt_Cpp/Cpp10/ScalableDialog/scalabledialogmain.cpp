#include "scalabledialog.h"
#include <QTextCodec>
#include <QApplication>

int main(int argc, char *argv[])
{
    QApplication app(argc, argv);
    QTextCodec::setCodecForLocale(QTextCodec::codecForName("GBK"));
    ScalableDialog *dialog=new ScalableDialog();
    dialog->show();
    return app.exec();
}
