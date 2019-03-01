package day16.exec;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/**
 * �ļ������� 
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
	   JFileChooser jfc;//�ļ�ѡ����
	   JCheckBox jcb;//��ѡ��
	   JTextField jtf;
	   JButton jb1;
	   JButton jb2;
	   public FileEncryptFrame(){
		   super("�ļ�����/������  v1.0");
		   jp1=new JPanel();
		   jp2=new JPanel();
		   jlb1=new JLabel("ѡ��Ҫ����/���ܵ��ļ���");
		   jlb2=new JLabel("ѡ�����/�������ӣ�0-255����");
		   jfc=new JFileChooser();
		   jtf=new JTextField(10);
		   jcb=new JCheckBox("����ԭ�ļ�");
		   jb1=new JButton("����/����");
		   jb2=new JButton("ȡ��");
	   }
	   public void init(){	   
		 setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE );
	     getContentPane().setLayout(new BorderLayout());
	     getContentPane().add(jp1,BorderLayout.CENTER);
	     getContentPane().add(jp2,BorderLayout.SOUTH);
	     
	     jp1.setAlignmentX(JPanel.LEFT_ALIGNMENT);
	     jp1.setLayout(new FlowLayout(FlowLayout.LEFT));
         //����ʾ�ļ�ѡ�����İ�ť
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
	     //�¼�������
	     setEvent();
	   }
	   public void setEvent(){
		   this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   //��ť����/���ܣ�����¼�����
		   jb1.addActionListener(new ActionListener(){
		    	public void actionPerformed(ActionEvent e){
		    		//��ȡ��ѡȡ���������ܺͽ��ܵ��ļ�����
		    		 File f=jfc.getSelectedFile();
		    		 //��ȡ����/��������
		    		 int code=Integer.parseInt(jtf.getText());
		    		 //������Ӧ����Ϣ��Encrypter�����м���
		    		 new Encrypter(code,f,jcb.isSelected()).encode();
		    		 final JDialog jd=new JDialog(new FileEncryptFrame(),"���ܳɹ���");
		    		 jd.add(new JLabel("�ļ����ܳɹ���"),BorderLayout.NORTH);
		    		 JButton jbok;
		    		 jd.add(jbok=new JButton("ȷ��"),BorderLayout.SOUTH);
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
		    //��ťȡ�����¼�����
		   jb2.addActionListener(new ActionListener(){
		      public void actionPerformed(ActionEvent e){
				  System.exit(0);}
		   });
	   } 
}

class Encrypter{
	//����/��������
	int code;
	//�Ƿ񸲸�ԭ�ļ�
	boolean rewrite;
	//ԭ�ļ���Ҫ������/���ܵ��ļ�������
	File sourceFile;
	//�ļ�������
    FileInputStream fin;
    //�ļ������
	FileOutputStream fout;
	//ԭ�ļ���
	String sourceFileName;
	//Ŀ���ļ���
	String objFileName;
	
	public Encrypter(int code,File file,boolean rewrite){   
		code=code; 
		sourceFile=file;
		this.rewrite=rewrite;
		//��ȡԭ�ļ�������
		sourceFileName=sourceFile.getAbsolutePath();
		//��ȡ.��ԭ�ļ����ַ��������һ�γ��ֵ�λ��
		int indexOfDot=sourceFileName.lastIndexOf('.');
		//���ԭ�ļ�����������׺��
		if(indexOfDot==-1){
			//����Ŀ���ļ����ĺ�׺��Ϊ.sec
			objFileName=sourceFileName+".sec";
			
		}
		//���ԭ�ļ���������չ������׺����
		else {
			//��ȡԭ�ļ�������չ��
			String postfixName=sourceFileName.substring(indexOfDot);
			//���ԭ�ļ�����չ����.sec
			if(postfixName.equals(".sec")){
				//����Ŀ���ļ�����Ϊԭ�ļ�����ȥ����׺���Ĳ���
				objFileName=sourceFileName.substring(0,indexOfDot);
			}
			//���ԭ�ļ�����չ������.sec
			else{
				//����Ŀ���ļ�����Ϊԭ�ļ������ϡ�.sec��
				objFileName=sourceFileName+".sec";
			}
		}
	}
	public void encode(){
        //����ԭ�ļ�
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
        //������ԭ�ļ�
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
