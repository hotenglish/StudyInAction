package day09;
import java.lang.reflect.*;
public class  ExceptionTest2{
	public static void main(String[] args){
		/*
		一、没有发生异常	1、2、3、5
		二、有异常，捕获了	1、2、4、5 
		三、有异常，没有捕获	1、2、5
		*/
		/*
		三种组合：
		1、try---catch
		2、try---catch---finally
		3、try---finally
		*/

		try{
			System.out.println("hello");//1
			Class c = Class.forName("student");//2
			System.out.println("world");//3
		}catch(ClassNotFoundException c){
			//System.out.println(c);
			//打印出异常信息栈信息 
			c.printStackTrace(); //4
		}finally{
			//释放资源的代码
			System.out.println("finally block");//5
		}
	}
}
