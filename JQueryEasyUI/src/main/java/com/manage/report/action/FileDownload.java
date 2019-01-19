package com.manage.report.action;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import java.io.FileInputStream;
import java.io.InputStream;

//文件下载
public class FileDownload extends ActionSupport {

    private int number;
    private String fileName;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    //返回一个输入流 作为客户端来说是输入流 对于服务器端是输出流
    public InputStream getDownloadFile() throws Exception {
        if (1 == number) {
            this.fileName = fileName + ".csv";
            return ServletActionContext.getServletContext().getResourceAsStream("temp/" + fileName);
        } else if (2 == number) {
            // 设置编码
            this.fileName = new String(this.fileName.getBytes("GBK"), "ISO-8859-1");
            return new FileInputStream(fileName);
        } else if (3 == number) {
            this.fileName = "发货城市统计.rar";
            // 设置编码
            this.fileName = new String(this.fileName.getBytes("GBK"), "ISO-8859-1");
            return ServletActionContext.getServletContext().getResourceAsStream("upload/发货城市统计.rar");
        } else {
            return null;
        }
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

}
