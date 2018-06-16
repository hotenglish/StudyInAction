#ifndef SCALABLEDIALOG_H
#define SCALABLEDIALOG_H
#include <QDialog>
#include <ui_scalabledialog.h>

class ScalableDialog : public QDialog, public Ui::ScalableDialog
{
    Q_OBJECT

public:
    ScalableDialog(QWidget *parent = 0);
    void find();

private slots:
    void on_moreButton_clicked();
};

#endif
