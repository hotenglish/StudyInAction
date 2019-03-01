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
		//显示的文本，true代表默认情况下是被选中的
		jcheckbox1=new JCheckBox("复选择项一",true);
		jcheckbox2=new JCheckBox("复选择项二");
		jcheckbox3=new JCheckBox("复选择项三");
		
		//构造方法与JCheckBox同
		jradiobutton1=new JRadioButton("单选项一",true);
		jradiobutton2=new JRadioButton("单选项二");
		jradiobutton3=new JRadioButton("单选项三");
		
		//与TextField构造方法同。
		jpasswordfield=new JPasswordField(10);
		
		//与按钮的构造方法相同 
		jtogglebutton=new JToggleButton("选择粗体");

		//滚动条，参数代表方向
		jscrollbar=new JScrollBar(0);
		
		//也可以传参数，可以指定方向。不指定的话，默认在中间。
		//还可以指定水平放置还是垂直放置。JScrollBar也一样
		jslider=new JSlider();
		
		//可以是一个数组，会把数组中的每个值当成是下拉列表中的每一项
		jcombobox=new JComboBox(new String[]{"BMW","BENZ","AUDI","FIAT"});
		
		//进度条
		jprogressbar=new JProgressBar();
		
		//按钮
		jbutton1=new JButton("减少");
		jbutton2=new JButton("增加");
		
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
		
		//AdjustmentListener改变它的值，滑动的时候触发事件
		jscrollbar.addAdjustmentListener(this);
		
		//ChangeListener可以动态改变值的
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


