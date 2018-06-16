#include "scalabledialog.h"
#include <QtGui>

ScalableDialog::ScalableDialog(QWidget *parent):QDialog(parent){
    setupUi(this);
    this->secondaryGroupBox->hide();
    this->layout()->setSizeConstraint(QLayout::SetFixedSize);
}

void ScalableDialog::on_moreButton_clicked(){
    moreButton->setText(secondaryGroupBox->isVisible()?"更少(&S)":"更多(&M)");
}
