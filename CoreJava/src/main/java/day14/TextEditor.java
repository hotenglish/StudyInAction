package day14;
import java.awt.*;
import javax.swing.*;
import javax.swing.event.*;

import java.io.*;
import java.awt.event.*;

public class TextEditor extends JFrame implements ActionListener{
	JPanel jp;
	JTextArea jta;
	JScrollPane jsp;
	JMenuBar jmb;//�˵���
	JMenu file,edit,view;//�˵�
	File currentFile=null;//��ǰ�򿪵��ļ�
	boolean wasSaved=true;//��ǰ�ļ��Ƿ��ѱ���
	public TextEditor(){
		super("My Text Editor v1.0");
		jp=new JPanel();
		jmb=new JMenuBar();
		jta=new JTextArea(50,50);
		jta.getDocument().addDocumentListener(new DocumentListener(){
			public void changedUpdate(DocumentEvent arg0) {
				wasSaved=false;
			}
			public void insertUpdate(DocumentEvent arg0) {
				wasSaved=false;
			}
			public void removeUpdate(DocumentEvent arg0) {
				wasSaved=false;
			}
			
		});

		jsp=new JScrollPane(jta);
		file=new JMenu("File");
		JMenuItem item;//�˵�ѡ��
		file.add(item=new JMenuItem("New")); item.addActionListener(this);
		file.add(item=new JMenuItem("Open...")); item.addActionListener(this);
		file.addSeparator();//��ӷָ���
		file.add(item=new JMenuItem("Save")); item.addActionListener(this);
		file.add(item=new JMenuItem("Save As...")); item.addActionListener(this);
		file.addSeparator();
		file.add(item=new JMenuItem("Quit")); item.addActionListener(this);
		
		edit=new JMenu("Edit");
		edit.add(item=new JMenuItem("Find...")); item.addActionListener(this);
		edit.add(item=new JMenuItem("Replace...")); item.addActionListener(this);
		
		view=new JMenu("View");
		view.add(item=new JMenuItem("Font...")); item.addActionListener(this);
		view.add(item=new JMenuItem("Color...")); item.addActionListener(this);

	}
	
	public void init(){	
		jmb.add(file);
		jmb.add(edit);
		jmb.add(view);
		this.setJMenuBar(jmb);//�Ѳ˵����ӵ���ǰJFrame
		Container con=getContentPane();
		con.add(jsp,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(800,600);
		jta.setFont(new Font("����",Font.PLAIN,18));
		setVisible(true);
	}

	public static void main(String[] args) {
		new TextEditor().init();
	}
	
	public void actionPerformed(ActionEvent e){
		String command=e.getActionCommand();
		if(command.equals("Open...")){//open...�˵�����
			if(askSave()==0){
				return;
			}
			JFileChooser jfc=new JFileChooser();
			jfc.showOpenDialog(this);
			File f=jfc.getSelectedFile();
			if(f!=null){
				openFile(f);
				currentFile=f;
			}
			return;
		}
		if(command.equals("Color...")){//Color �˵�����
			Color c=JColorChooser.showDialog(this,"��ѡ��������ɫ",Color.BLACK);
			jta.setForeground(c);
			return;
		}
		if(command.equals("Quit")){//Quit 
			if(askSave()==0)
				return;
			else
				System.exit(0);
		}
		if(command.equals("Save As...")){
			JFileChooser jfc=new JFileChooser();
			jfc.showSaveDialog(this);
			File f=jfc.getSelectedFile();
			if(f!=null){
				saveFile(f);
				currentFile=f;
			}
			return;
		}
		if(command.equals("Save")){
			if(!wasSaved){
				if(currentFile==null){
					JFileChooser jfc=new JFileChooser();
					jfc.showSaveDialog(this);//�򿪵ĶԻ����������Ĵ���
					File f=jfc.getSelectedFile();//File����һ���ļ�����Ŀ¼
					if(f!=null){
						saveFile(f);
						currentFile=f;
					}
				}
				else
					saveFile(currentFile);
			}
			return;
		}
		if(command.equals("Font...")){
			JOptionPane.showMessageDialog(this,"�ù���δʵ�֣�");
		}
		if(command.equals("Find...")){
			JOptionPane.showMessageDialog(this,"�ù���δʵ�֣�");
		}
		if(command.equals("Replace...")){
			JOptionPane.showMessageDialog(this,"�ù���δʵ�֣�");			
		}
	
	}
	
	public void saveFile(File f){//�����ļ�
		FileWriter fw=null;
		try {
			fw=new FileWriter(f);
			fw.write(jta.getText());
			fw.flush();
			wasSaved=true;
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			if(fw!=null)try {fw.close();} catch (IOException e) {}
		}
	}
	
	public void openFile(File f){//���ļ�
		FileReader fr=null;
		BufferedReader br=null;
		jta.setText("");
		try {
			fr=new FileReader(f);
			br=new BufferedReader(fr);
			String str=null;
			while((str=br.readLine())!=null){
				jta.append(str+"\n");
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		finally{
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				
			}
			wasSaved=true;
			if(br!=null)try{br.close();}catch(IOException e){}
			if(fr!=null)try{fr.close();}catch(IOException e){}
		}
	}
	
	/**
	 * �÷�����ִ���˳�����ʱ������ļ�ʱѯ���û��Ƿ񱣴������ļ���
	 * �÷������жϵ�ǰ�ļ������Ƿ�ı��δ���棬
	 * ���ǣ��򵯳�ѯ�ʶԻ���
	 * @return
	 */
	public int askSave(){
		if(!wasSaved ){//���û�б���
			int choice=JOptionPane.showConfirmDialog(this,"�Ƿ񱣴��ļ���","�����ļ���",
						JOptionPane.YES_NO_CANCEL_OPTION);
			switch(choice){
				case JOptionPane.OK_OPTION : //Ҫ�󱣴��ļ�
				           if(currentFile==null){//������½��ļ�
				               JFileChooser jfc=new JFileChooser();
				               jfc.showSaveDialog(this);
				               File f=jfc.getSelectedFile();
				               if(f!=null){
					               saveFile(f);
					               currentFile=f; 
				               }
	
				           }
				           else{//����Ǵ򿪵ľ��ļ�
				               saveFile(currentFile);
				           }
				           return 1;
				case JOptionPane.NO_OPTION : return 1;//�������ļ�
				case JOptionPane.CANCEL_OPTION : return 0;//ȡ��
			}
		}
		return -1;
	}	
}


