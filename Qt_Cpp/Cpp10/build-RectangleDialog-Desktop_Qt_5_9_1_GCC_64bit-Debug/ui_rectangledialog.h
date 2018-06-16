/********************************************************************************
** Form generated from reading UI file 'rectangledialog.ui'
**
** Created by: Qt User Interface Compiler version 5.9.1
**
** WARNING! All changes made in this file will be lost when recompiling UI file!
********************************************************************************/

#ifndef UI_RECTANGLEDIALOG_H
#define UI_RECTANGLEDIALOG_H

#include <QtCore/QVariant>
#include <QtWidgets/QAction>
#include <QtWidgets/QApplication>
#include <QtWidgets/QButtonGroup>
#include <QtWidgets/QDialog>
#include <QtWidgets/QGridLayout>
#include <QtWidgets/QHeaderView>
#include <QtWidgets/QLabel>
#include <QtWidgets/QLineEdit>
#include <QtWidgets/QPushButton>
#include <QtWidgets/QSpacerItem>
#include <QtWidgets/QVBoxLayout>

QT_BEGIN_NAMESPACE

class Ui_RectangleDialog
{
public:
    QGridLayout *gridLayout;
    QVBoxLayout *verticalLayout;
    QLabel *label_1;
    QLabel *label_2;
    QLabel *label_3;
    QVBoxLayout *verticalLayout_2;
    QLineEdit *lengthEdit;
    QLineEdit *widthEdit;
    QLineEdit *rectangleAreaEdit;
    QVBoxLayout *verticalLayout_3;
    QPushButton *calculateButton;
    QPushButton *closeButton;
    QSpacerItem *verticalSpacer;

    void setupUi(QDialog *RectangleDialog)
    {
        if (RectangleDialog->objectName().isEmpty())
            RectangleDialog->setObjectName(QStringLiteral("RectangleDialog"));
        RectangleDialog->resize(366, 128);
        gridLayout = new QGridLayout(RectangleDialog);
        gridLayout->setSpacing(6);
        gridLayout->setContentsMargins(11, 11, 11, 11);
        gridLayout->setObjectName(QStringLiteral("gridLayout"));
        verticalLayout = new QVBoxLayout();
        verticalLayout->setSpacing(6);
        verticalLayout->setObjectName(QStringLiteral("verticalLayout"));
        label_1 = new QLabel(RectangleDialog);
        label_1->setObjectName(QStringLiteral("label_1"));

        verticalLayout->addWidget(label_1);

        label_2 = new QLabel(RectangleDialog);
        label_2->setObjectName(QStringLiteral("label_2"));

        verticalLayout->addWidget(label_2);

        label_3 = new QLabel(RectangleDialog);
        label_3->setObjectName(QStringLiteral("label_3"));

        verticalLayout->addWidget(label_3);


        gridLayout->addLayout(verticalLayout, 0, 0, 2, 1);

        verticalLayout_2 = new QVBoxLayout();
        verticalLayout_2->setSpacing(6);
        verticalLayout_2->setObjectName(QStringLiteral("verticalLayout_2"));
        lengthEdit = new QLineEdit(RectangleDialog);
        lengthEdit->setObjectName(QStringLiteral("lengthEdit"));

        verticalLayout_2->addWidget(lengthEdit);

        widthEdit = new QLineEdit(RectangleDialog);
        widthEdit->setObjectName(QStringLiteral("widthEdit"));

        verticalLayout_2->addWidget(widthEdit);

        rectangleAreaEdit = new QLineEdit(RectangleDialog);
        rectangleAreaEdit->setObjectName(QStringLiteral("rectangleAreaEdit"));

        verticalLayout_2->addWidget(rectangleAreaEdit);


        gridLayout->addLayout(verticalLayout_2, 0, 1, 2, 1);

        verticalLayout_3 = new QVBoxLayout();
        verticalLayout_3->setSpacing(6);
        verticalLayout_3->setObjectName(QStringLiteral("verticalLayout_3"));
        calculateButton = new QPushButton(RectangleDialog);
        calculateButton->setObjectName(QStringLiteral("calculateButton"));
        calculateButton->setEnabled(false);

        verticalLayout_3->addWidget(calculateButton);

        closeButton = new QPushButton(RectangleDialog);
        closeButton->setObjectName(QStringLiteral("closeButton"));

        verticalLayout_3->addWidget(closeButton);


        gridLayout->addLayout(verticalLayout_3, 0, 2, 1, 1);

        verticalSpacer = new QSpacerItem(17, 38, QSizePolicy::Minimum, QSizePolicy::Expanding);

        gridLayout->addItem(verticalSpacer, 1, 2, 1, 1);

#ifndef QT_NO_SHORTCUT
        label_1->setBuddy(lengthEdit);
        label_2->setBuddy(widthEdit);
        label_3->setBuddy(rectangleAreaEdit);
#endif // QT_NO_SHORTCUT

        retranslateUi(RectangleDialog);

        calculateButton->setDefault(true);


        QMetaObject::connectSlotsByName(RectangleDialog);
    } // setupUi

    void retranslateUi(QDialog *RectangleDialog)
    {
        RectangleDialog->setWindowTitle(QApplication::translate("RectangleDialog", "\347\237\251\345\275\242\351\235\242\347\247\257\350\256\241\347\256\227\347\252\227\345\217\243", Q_NULLPTR));
        label_1->setText(QApplication::translate("RectangleDialog", "<html><head/><body><p>\347\237\251\345\275\242\350\276\271\351\225\277&amp;L:</p></body></html>", Q_NULLPTR));
        label_2->setText(QApplication::translate("RectangleDialog", "<html><head/><body><p>\347\237\251\345\275\242\350\276\271\345\256\275&amp;W\357\274\232</p></body></html>", Q_NULLPTR));
        label_3->setText(QApplication::translate("RectangleDialog", "<html><head/><body><p>\347\237\251\345\275\242\351\235\242\347\247\257\357\274\232</p></body></html>", Q_NULLPTR));
        calculateButton->setText(QApplication::translate("RectangleDialog", "\350\256\241\347\256\227\347\237\251\345\275\242\351\235\242\347\247\257", Q_NULLPTR));
        closeButton->setText(QApplication::translate("RectangleDialog", "\345\205\263\351\227\255", Q_NULLPTR));
    } // retranslateUi

};

namespace Ui {
    class RectangleDialog: public Ui_RectangleDialog {};
} // namespace Ui

QT_END_NAMESPACE

#endif // UI_RECTANGLEDIALOG_H
