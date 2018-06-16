#ifndef MAINWINDOW_H
#define MAINWINDOW_H

#include <QMessageBox>
#include <QFileDialog>
#include <QMainWindow>
#include <QLineEdit>
#include <QPushButton>
#include <QLabel>

namespace Ui {
class MainWindow;
}

class MainWindow : public QMainWindow
{
    Q_OBJECT

public:
    explicit MainWindow(QWidget *parent = 0);
    ~MainWindow();

private slots:
    void on_action_New_triggered();

    void on_action_Open_triggered();

    void on_action_Save_triggered();

    void on_action_SaveAs_triggered();

    void on_action_Revocation_triggered();

    void on_action_Copy_triggered();

    void on_action_Paste_triggered();

    void on_action_Cut_triggered();

    void on_action_Close_triggered();

    void on_action_Exit_triggered();

    void show_find_TextContent();    // 该函数中实现查询字符串的功能

    void on_action_Find_triggered();

    void get_CursorLocation();       //获取光标位置信息

private:
    Ui::MainWindow *ui;
    bool beSaved;                 //为true时标志文件已经保存，为false时标志文件尚未保存
    QLineEdit *findContent;
    QLabel *first_status_Label;   //声明两个标签对象，用于显示状态信息
    QLabel *second_status_Label;
    void init_status_Bar();       //初始化状态栏
    QString currentFile;          //保存当前文件的文件名
    void fileNew();               //新建文件函数
    void fileSaveOrNot();         //是否保存修改过的文件函数
    void fileSave();              //保存文件
    void fileSaveAs();            //文件另存为
    bool saveFile(const QString &fileName);      //存储文件
    void fileOpen();                             //打开文件的函数
    bool fileLoad(const QString& fileName);      //读取装载文件的函数
};

#endif // MAINWINDOW_H
