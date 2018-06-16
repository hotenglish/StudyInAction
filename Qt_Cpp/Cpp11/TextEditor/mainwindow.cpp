#include "mainwindow.h"
#include "ui_mainwindow.h"
#include <QtGui>

MainWindow::MainWindow(QWidget *parent) :
    QMainWindow(parent),
    ui(new Ui::MainWindow)
{
    ui->setupUi(this);
    beSaved=false;                  //初始化文件为未保存过状态
    currentFile=tr("noname.txt");   //初始化文件名的noname.txt
    setWindowTitle(currentFile);    //初始化主窗口的标题
    init_status_Bar();              //初始化主窗口的状态栏
    connect(ui->textEdit,SIGNAL(cursorPositionChanged()),this,SLOT(get_CursorLocation()));  //光标位置改变触发函数调用，改变状态栏行列信息
}

MainWindow::~MainWindow()
{
    delete ui;
}

void MainWindow::fileNew(){           //实现新建文件的功能
    fileSaveOrNot();                  //询问是否保存当前正在编辑的文件
    beSaved=false;                    //将己保存设为假，即未保存
    currentFile=tr("noname.txt");     //给新建的文件取个临时名字
    setWindowTitle(currentFile);      //设置窗口标题
    ui->textEdit->clear();            //清空文件编辑器
    ui->textEdit->setVisible(true);   //设置文本编辑器可见
}


void MainWindow::fileSaveOrNot(){     //弹出是否保存文件对话框
    if(ui->textEdit->document()->isModified()){   //如果文件被修改过，弹出“保存”对话框
        QMessageBox promptBox;        //定义信息显示对像
        promptBox.setWindowTitle("警告");
        promptBox.setIcon(QMessageBox::Warning);
        promptBox.setText(currentFile+tr("文件尚未保存,是否保存?"));
        promptBox.setStandardButtons(QMessageBox::Yes|QMessageBox::No);
        if(promptBox.exec()==QMessageBox::Yes){
            fileSave();               //当选择保存文件时，则执行保存操作
        }
    }
}

void MainWindow::fileSave(){     //保存文件
     if(beSaved){
         //如果文件已经被保存过，直接保存文件
         saveFile(currentFile);
     }else {
         fileSaveAs();  //如果文件是第一次保存，那么调用另存为
     }
}

void MainWindow::fileSaveAs(){    //文件另存为
     QString fileName=QFileDialog::getSaveFileName(this,tr("另存为"),currentFile);
     // 获得文件名
     if(!fileName.isEmpty()){  //如果文件名不为空，则保存文件内容
        saveFile(fileName);
    }
}

bool MainWindow::saveFile(const QString &fileName){
     //保存文件内容，因为可能保存失败，所以具有返回值，来表明是否保存成功
     QFile file(fileName);
     if(!file.open(QFile::WriteOnly|QFile::Text)){
         //以只写方式打开文件，如果打开失败则弹出提示框并返回
         QMessageBox::warning(this,tr("保存文件"),tr("无法保存文件%1:\n%2").arg(fileName).arg(file.errorString()));
         return false;
     }
     //%1、%2表示后面的两个arg参数的值
     QTextStream out(&file);   //新建流对象，指向选定的文件
     out<<ui->textEdit->toPlainText();   // 文件编辑器里的内容以纯文本的形式输出到流对象中
     beSaved=true;
     currentFile=QFileInfo(fileName).canonicalFilePath();   //获得文件的标准路径
     setWindowTitle(currentFile);                           //将窗口名称改为现在窗口的路径
     return true;
}

void MainWindow::fileOpen(){  //打开文件
    fileSaveOrNot();          //是否要保存现有的文件
    QString fileName=QFileDialog::getOpenFileName(this);   //获取要打开的文件的名字
    if(!fileName.isEmpty()){                               //如果文件名不为空
        fileLoad(fileName);
    }
    ui->textEdit->setVisible(true);                        //设置文本编辑器可见
}

bool MainWindow::fileLoad(const QString &fileName){  //读取文件
    QFile file(fileName);
    if(!file.open(QFile::ReadOnly|QFile::Text)){
        QMessageBox::warning(this,tr("读取文件"),tr("无法读取文件%1:\n%2.").arg(fileName).arg(file.errorString()));
        return false;                                //如果打开文件失败，则弹出的对话框，并返回
    }
    QTextStream readin(&file);
    ui->textEdit->setText(readin.readAll());         //将磁盘上文件的内容装载到窗口的文本编辑器中
    currentFile=QFileInfo(fileName).canonicalFilePath();
    setWindowTitle(currentFile);
    return true;
}

void MainWindow::on_action_New_triggered()
{
    fileNew();
}

void MainWindow::on_action_Save_triggered()    //菜单“保存”的糟函数
{
    fileSave();
}

void MainWindow::on_action_SaveAs_triggered()  //菜单“另保存”的糟函数
{
    fileSaveAs();
}

void MainWindow::on_action_Revocation_triggered()
{
    ui->textEdit->undo();                      //撤消操作
}

void MainWindow::on_action_Copy_triggered()
{
    ui->textEdit->copy();                      //复制操作
}

void MainWindow::on_action_Paste_triggered()
{
    ui->textEdit->paste();                     //粘贴操作
}

void MainWindow::on_action_Cut_triggered()
{
    ui->textEdit->cut();                       //剪切操作
}

void MainWindow::on_action_Close_triggered()
{
    fileSaveOrNot();
    ui->textEdit->setVisible(false);
}


void MainWindow::on_action_Exit_triggered()
{
    on_action_Close_triggered();               //先执行关闭操作
    qApp->quit();                              //然后退出系统，qApp是指向应用程序的全局指针
}

void MainWindow::on_action_Open_triggered()
{
    fileOpen();
}

void MainWindow::on_action_Find_triggered()
{
    QDialog *findContentDlg=new QDialog(this);
    //新建一个对话框，用于查找操作，this表示它的父窗口是MainWindow
    findContentDlg->setWindowTitle(tr("查找"));  //设置该对话框的标题
    findContent=new QLineEdit(findContentDlg);  //将行编辑器加入到新建的“查找”对话框中
    QPushButton *find_Button=new QPushButton(tr("查找下一个"),findContentDlg);
    //在“查找”对话框中加入一个“查找下一个”的按钮
    QVBoxLayout *layout=new QVBoxLayout(findContentDlg);
    //建立一个垂直布局管理器
    layout->addWidget(findContent); //将查询内容编辑框加入布局中
    layout->addWidget(find_Button); //将查询下一个按钮加入布局中
    findContentDlg->show();         //显示“查找”对话框
    connect(find_Button,SIGNAL(clicked(bool)),this,SLOT(show_find_TextContent()));
    //设置“查找下一个”按钮的单击事件和其糟函数的关联
}

void MainWindow::show_find_TextContent(){
    QString findText=findContent->text();  //“查找下一个”按钮的糟函数
    if(!ui->textEdit->find(findText,QTextDocument::FindBackward)){  //获取行编辑器中的内容，FindBackward是一个枚举变量，表示向后查找
        QMessageBox::warning(this,tr("查找"),tr("找不到%1").arg(findText));
        //查找不到时给出提示
    }
}

void MainWindow::init_status_Bar(){
    QStatusBar *bottomBar=ui->statusBar;    //获取状态栏
    first_status_Label=new QLabel();        //新建标签
    first_status_Label->setMinimumSize(150,20);  //设置标签最小尺寸
    first_status_Label->setFrameShape(QFrame::WinPanel);  //设置标签形状
    first_status_Label->setFrameShadow(QFrame::Sunken);   //设置标签阴影
    second_status_Label=new QLabel();
    second_status_Label->setMinimumSize(150,20);
    second_status_Label->setFrameShape(QFrame::WinPanel);
    second_status_Label->setFrameShadow(QFrame::Sunken);
    bottomBar->addWidget(first_status_Label);
    bottomBar->addWidget(second_status_Label);
    first_status_Label->setText(tr("欢迎使用文本编辑器"));    //初始化内容
    second_status_Label->setText(tr("使用QT Creatro 制作"));
}

void MainWindow::get_CursorLocation(){
    int rowNum=ui->textEdit->document()->blockCount();             //获限光标所在行的行号
    const QTextCursor cursor=ui->textEdit->textCursor();
    int colNum=cursor.columnNumber();                              //获限光标所在列的行号
    this->first_status_Label->setText(tr("%1行%2列").arg(rowNum).arg(colNum));    //在状态栏显示光标位置
}


