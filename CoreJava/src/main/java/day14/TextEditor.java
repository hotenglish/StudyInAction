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
	JMenuBar jmb;//菜单条
	JMenu file,edit,view;//菜单
	File currentFile=null;//当前打开的文件
	boolean wasSaved=true;//当前文件是否已保存
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
		JMenuItem item;//菜单选项
		file.add(item=new JMenuItem("New")); item.addActionListener(this);
		file.add(item=new JMenuItem("Open...")); item.addActionListener(this);
		file.addSeparator();//添加分割线
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
		this.setJMenuBar(jmb);//把菜单条加到当前JFrame
		Container con=getContentPane();
		con.add(jsp,BorderLayout.CENTER);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		setSize(800,600);
		jta.setFont(new Font("宋体",Font.PLAIN,18));
		setVisible(true);
	}

	public static void main(String[] args) {
		new TextEditor().init();
	}
	
	public void actionPerformed(ActionEvent e){
		String command=e.getActionCommand();
		if(command.equals("Open...")){//open...菜单处理
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
		if(command.equals("Color...")){//Color 菜单处理
			Color c=JColorChooser.showDialog(this,"请选择文字颜色",Color.BLACK);
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
					jfc.showSaveDialog(this);//打开的对话框所依赖的窗体
					File f=jfc.getSelectedFile();//File描述一个文件或者目录
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
			JOptionPane.showMessageDialog(this,"该功能未实现！");
		}
		if(command.equals("Find...")){
			JOptionPane.showMessageDialog(this,"该功能未实现！");
		}
		if(command.equals("Replace...")){
			JOptionPane.showMessageDialog(this,"该功能未实现！");			
		}
	
	}
	
	public void saveFile(File f){//保存文件
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
	
	public void openFile(File f){//打开文件
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
	 * 该方法在执行退出操作时或打开新文件时询问用户是否保存现有文件。
	 * 该方法会判断当前文件内容是否改变后未保存，
	 * 若是，则弹出询问对话框。
	 * @return
	 */
	public int askSave(){
		if(!wasSaved ){//如果没有保存
			int choice=JOptionPane.showConfirmDialog(this,"是否保存文件？","保存文件？",
						JOptionPane.YES_NO_CANCEL_OPTION);
			switch(choice){
				case JOptionPane.OK_OPTION : //要求保存文件
				           if(currentFile==null){//如果是新建文件
				               JFileChooser jfc=new JFileChooser();
				               jfc.showSaveDialog(this);
				               File f=jfc.getSelectedFile();
				               if(f!=null){
					               saveFile(f);
					               currentFile=f; 
				               }
	
				           }
				           else{//如果是打开的旧文件
				               saveFile(currentFile);
				           }
				           return 1;
				case JOptionPane.NO_OPTION : return 1;//不保存文件
				case JOptionPane.CANCEL_OPTION : return 0;//取消
			}
		}
		return -1;
	}	
}


