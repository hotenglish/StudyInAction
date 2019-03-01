package day12;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SwingComponentTest implements ActionListener,
   ItemListener,AdjustmentListener,ChangeListener{
	private static int j=0;
	private JTextArea jtextarea;
	private Container con;
	private JFrame jframe;
	private JPanel jpanela;
	private JPanel jpanelb;
	private JCheckBox jcheckbox1,jcheckbox2,jcheckbox3;
	private JRadioButton jradiobutton1,jradiobutton2,jradiobutton3;
	private JPasswordField jpasswordfield;
	private JToggleButton jtogglebutton;
	private JScrollPane jscrollpane;
	private JScrollBar jscrollbar;
	private JSlider jslider;
	private JComboBox jcombobox;
	private JProgressBar jprogressbar;
	private JButton jbutton1,jbutton2;

	public static void main(String[] args) {
		new SwingComponentTest().go();
	}
	
	public void init(){		
		//��ʾ���ı���true����Ĭ��������Ǳ�ѡ�е�
		jcheckbox1=new JCheckBox("��ѡ����һ",true);
		jcheckbox2=new JCheckBox("��ѡ�����");
		jcheckbox3=new JCheckBox("��ѡ������");
		
		//���췽����JCheckBoxͬ
		jradiobutton1=new JRadioButton("��ѡ��һ",true);
		jradiobutton2=new JRadioButton("��ѡ���");
		jradiobutton3=new JRadioButton("��ѡ����");
		
		//��TextField���췽��ͬ��
		jpasswordfield=new JPasswordField(10);
		
		//�밴ť�Ĺ��췽����ͬ 
		jtogglebutton=new JToggleButton("ѡ�����");

		//������������������
		jscrollbar=new JScrollBar(0);
		
		//Ҳ���Դ�����������ָ�����򡣲�ָ���Ļ���Ĭ�����м䡣
		//������ָ��ˮƽ���û��Ǵ�ֱ���á�JScrollBarҲһ��
		jslider=new JSlider();
		
		//������һ�����飬��������е�ÿ��ֵ�����������б��е�ÿһ��
		jcombobox=new JComboBox(new String[]{"BMW","BENZ","AUDI","FIAT"});
		
		//������
		jprogressbar=new JProgressBar();
		
		//��ť
		jbutton1=new JButton("����");
		jbutton2=new JButton("����");
		
		//jscrollpane.add(jtextarea);
		jpanelb.add(jcheckbox1);
		jpanelb.add(jcheckbox2);
		jpanelb.add(jcheckbox3);
		
		jpanelb.add(jradiobutton1);
		jpanelb.add(jradiobutton2);
		jpanelb.add(jradiobutton3);
		
		jpanelb.add(jpasswordfield);
		jpanelb.add(jtogglebutton);
		//jpanelb.add(jscrollpane);
		jpanelb.add(jscrollbar);
		jpanelb.add(jslider);
		jpanelb.add(jcombobox);
		jpanelb.add(jbutton1);
		jpanelb.add(jprogressbar);
		jpanelb.add(jbutton2);
		
		eventHandle();
	
	}
	
	public void go(){
		//jframe.setSize(400,600);
		jframe.pack();
		jframe.setVisible(true);
	}
	public void eventHandle(){
		jframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//ActionListener
		jtogglebutton.addActionListener(this);
		jcheckbox1.addActionListener(this);
		jcheckbox2.addActionListener(this);
		jcheckbox3.addActionListener(this);
		jradiobutton1.addActionListener(this);
		jradiobutton2.addActionListener(this);
		jradiobutton3.addActionListener(this);
		
		//ItemListener
		jcombobox.addItemListener(this);
		
		//AdjustmentListener�ı�����ֵ��������ʱ�򴥷��¼�
		jscrollbar.addAdjustmentListener(this);
		
		//ChangeListener���Զ�̬�ı�ֵ��
		jslider.addChangeListener(this);
		jprogressbar.addChangeListener(this);
		
		jbutton1.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
					jprogressbar.setValue(jprogressbar.getValue()-10);				
			}
		});
		jbutton2.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){	
					jprogressbar.setValue(jprogressbar.getValue()+10);				
			}
		});
		
	}
	
	public SwingComponentTest(){
		jframe=new JFrame("All Swing Components Test");
		
		jpanelb=new JPanel();
		jtextarea=new JTextArea(15,20);
		jtextarea.setFont(new Font("",Font.BOLD,20));
		jscrollpane=new JScrollPane(jtextarea);
		con=jframe.getContentPane();
		con.setLayout(new BorderLayout());
		con.add(jscrollpane,BorderLayout.NORTH);
		con.add(jpanelb,BorderLayout.CENTER);
		init();
	}
	
	public void actionPerformed(ActionEvent e){		
		JToggleButton jtb=(JToggleButton)e.getSource();
		if(jtb.isSelected())
			jtextarea.append("\n"+jtb.getText()+" was be selected!");	
		else 
			jtextarea.append("\n"+jtb.getText()+" was be eliminated!");		
	}
	
	public void itemStateChanged(ItemEvent e){
		j++;
		JComboBox jcb=(JComboBox)e.getSource();
		String selectedItem=(String)jcb.getSelectedItem();
	    jtextarea.append("\n"+selectedItem+" was be selected!"+j);	
	}
	public void adjustmentValueChanged(AdjustmentEvent e){
		JScrollBar jsb=(JScrollBar)e.getSource();
		int value=jsb.getValue();
	    jtextarea.append("\nJScrollBar's currently value is:"+value);	
	}
	public void stateChanged(ChangeEvent e){
		Object obj=e.getSource();
		int value=0;
		if(obj instanceof JSlider){
			JSlider js=(JSlider)obj;
		    value=js.getValue();
		}
		if(obj instanceof JProgressBar){
			JProgressBar jpb=(JProgressBar)obj;
			value=jpb.getValue();
		}		
	    jtextarea.append("\nJSlider/JProgressBar 's currently value is:"+value+"%");	
	}
}


