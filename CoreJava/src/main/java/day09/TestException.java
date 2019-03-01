package day09;
import java.io.*;
import java.sql.*;
/**
	main��������m1������
	m1��������m2������
	m2��������m3������
	��ʾ��ӡջ��Ϣ
*/
public class TestException{
	public static void main(String args[]){
		System.out.println("main start......");
		if(args==null || args.length<=0){
			throw new NullPointerException("�봫������");
		}
		int num = Integer.parseInt(args[0]);
		m1(num);
		System.out.println("main end......");
	}

	static void m1(int i){
		System.out.println("m1 start......");
		//�����쳣
		try{
			m2(i);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(EOFException e){
			e.printStackTrace();
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}catch(MyException e){
			e.printStackTrace();
		}

		System.out.println("m1 end......");
	}

	static void m2(int i) throws FileNotFoundException,
							EOFException,
							ClassNotFoundException,
							SQLException,
							MyException{
		System.out.println("m2 start......");
		m3(i);
		System.out.println("m2 end......");
	}

	static void m3(int i) throws FileNotFoundException,
							EOFException,
							ClassNotFoundException,
							SQLException,
							MyException{
		if(i==0){
			throw new FileNotFoundException("�ļ��Ҳ����ˣ���ô�죿");
		}
		else if(i==2){
			throw new NullPointerException("��ָ���쳣");
		}
		else if(i==3){
			throw new EOFException("��ȡ�ļ�����");
		}
		else if(i==4){
			throw new ClassNotFoundException("���ļ��Ҳ�����");
		}
		else if(i==5){
			throw new SQLException("���ݿ����Ӵ���");
		}
		else if(i==6){
			throw new MyException("���Զ�����쳣����");
		}
	}
}

/**
	�Զ����Ѽ���쳣��
*/
class MyException extends Exception{
	public MyException(String message){
		super(message);
	}
}