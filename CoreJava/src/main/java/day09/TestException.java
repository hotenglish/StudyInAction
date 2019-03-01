package day09;
import java.io.*;
import java.sql.*;
/**
	main方法调用m1方法，
	m1方法调用m2方法，
	m2方法调用m3方法，
	演示打印栈信息
*/
public class TestException{
	public static void main(String args[]){
		System.out.println("main start......");
		if(args==null || args.length<=0){
			throw new NullPointerException("请传参数！");
		}
		int num = Integer.parseInt(args[0]);
		m1(num);
		System.out.println("main end......");
	}

	static void m1(int i){
		System.out.println("m1 start......");
		//处理异常
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
			throw new FileNotFoundException("文件找不到了，怎么办？");
		}
		else if(i==2){
			throw new NullPointerException("空指针异常");
		}
		else if(i==3){
			throw new EOFException("读取文件错误");
		}
		else if(i==4){
			throw new ClassNotFoundException("类文件找不到了");
		}
		else if(i==5){
			throw new SQLException("数据库连接错误");
		}
		else if(i==6){
			throw new MyException("我自定义的异常哈哈");
		}
	}
}

/**
	自定义已检查异常类
*/
class MyException extends Exception{
	public MyException(String message){
		super(message);
	}
}