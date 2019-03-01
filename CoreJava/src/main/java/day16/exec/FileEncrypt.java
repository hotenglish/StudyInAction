package day16.exec;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/**
 * 文件加密器 
 * @author new
 *
 */
public class FileEncrypt {
	public static void main(String[] args) {
		new FileEncryptFrame().init();
	}

}
class FileEncryptFrame extends JFrame{
	   JPanel jp1;
	   JPanel jp2;
	   JLabel jlb1;
	   JLabel jlb2;
	   JFileChooser jfc;//文件选择器
	   JCheckBox jcb;//复选框
	   JTextField jtf;
	   JButton jb1;
	   JButton jb2;
	   public FileEncryptFrame(){
		   super("文件加密/解密器  v1.0");
		   jp1=new JPanel();
		   jp2=new JPanel();
		   jlb1=new JLabel("选择要加密/解密的文件：");
		   jlb2=new JLabel("选择加密/解密算子（0-255）：");
		   jfc=new JFileChooser();
		   jtf=new JTextField(10);
		   jcb=new JCheckBox("覆盖原文件");
		   jb1=new JButton("加密/解密");
		   jb2=new JButton("取消");
	   }
	   public void init(){	   
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	     getContentPane().setLayout(new BorderLayout());
	     getContentPane().add(jp1,BorderLayout.CENTER);
	     getContentPane().add(jp2,BorderLayout.SOUTH);
	     
	     jp1.setAlignmentX(JPanel.LEFT_ALIGNMENT);
	     jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
         //不显示文件选择器的按钮
	     jfc.setControlButtonsAreShown(false);
	     jp1.add(jlb1);
	     jp1.add(jfc);
	     jp1.add(jlb2);
	     jp1.add(jtf);
	     jp1.add(jcb);
	   
	     jp2.add(jb1);
	     jp2.add(jb2);
	     
	     setVisible(true);
	     setSize(520,480);
	     this.setResizable(false);
	     //事件处理方法
	     setEvent();
	   }
	   public void setEvent(){
		   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   //按钮加密/解密，添加事件处理
		   jb1.addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		//获取所选取的用来加密和解密的文件对象
		    		 File f=jfc.getSelectedFile();
		    		 //获取加密/解密算子
		    		 int code=Integer.parseInt(jtf.getText());
		    		 //传递相应的信息给Encrypter，进行加密
		    		 new Encrypter(code,f,jcb.isSelected()).encode();
		    		 final JDialog jd=new JDialog(new FileEncryptFrame(),"加密成功！");
		    		 jd.add(new JLabel("文件加密成功！"),BorderLayout.NORTH);
		    		 JButton jbok;
		    		 jd.add(jbok=new JButton("确定"),BorderLayout.SOUTH);
		    		 jbok.addActionListener(new ActionListener(){
		    			 public void actionPerformed(ActionEvent e){
		    				 jd.dispose();
		    			 }
		    		 });
		    		 jd.setLocationRelativeTo(FileEncryptFrame.this);
		    		 jd.setSize(300,200);
		    		 jd.setVisible(true);
		    	}
		    });
		    //按钮取消的事件处理
		   jb2.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e){
				  System.exit(0);}
		   });
	   } 
}

class Encrypter{
	//加密/解密算子
	int code;
	//是否覆盖原文件
	boolean rewrite;
	//原文件（要被加密/解密的文件）对象
	File sourceFile;
	//文件输入流
    FileInputStream fin;
    //文件输出流
	FileOutputStream fout;
	//原文件名
	String sourceFileName;
	//目标文件名
	String objFileName;
	
	public Encrypter(int code,File file,boolean rewrite){   
		code=code; 
		sourceFile=file;
		this.rewrite=rewrite;
		//获取原文件的名字
		sourceFileName=sourceFile.getAbsolutePath();
		//获取.在原文件名字符串中最后一次出现的位置
		int indexOfDot=sourceFileName.lastIndexOf('.');
		//如果原文件名不包含后缀名
		if(indexOfDot==-1){
			//设置目标文件名的后缀名为.sec
			objFileName=sourceFileName+".sec";
			
		}
		//如果原文件名包含扩展名（后缀名）
		else {
			//获取原文件名的扩展名
			String postfixName=sourceFileName.substring(indexOfDot);
			//如果原文件的扩展名是.sec
			if(postfixName.equals(".sec")){
				//设置目标文件名，为原文件名称去掉后缀名的部分
				objFileName=sourceFileName.substring(0,indexOfDot);
			}
			//如果原文件的扩展名不是.sec
			else{
				//设置目标文件名，为原文件名加上“.sec”
				objFileName=sourceFileName+".sec";
			}
		}
	}
	public void encode(){
        //覆盖原文件
		if(rewrite){
			RandomAccessFile fra=null;
			try {
				fra=new RandomAccessFile(sourceFile,"rw");
				int temp;
				while((temp=fra.read())!=-1){
					fra.seek(fra.getFilePointer()-1);
					fra.write(temp^code);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(fra!=null)try{fra.close();}catch(IOException e){}
			}
		}
        //不覆盖原文件
		else{
			FileInputStream fis=null;
			FileOutputStream fos=null;
			try {
				fis=new FileInputStream(sourceFile);	
				fos=new FileOutputStream(objFileName);
				while(fis.available()>0){
					fos.write(fis.read()^code);
				}
				fos.flush();
			} catch (Exception e) {
				e.printStackTrace();
			}finally{
				if(fis!=null)try{fis.close();}catch(IOException e){}
				if(fos!=null)try{fos.close();}catch(IOException e){}
			}
		}

	}
}
