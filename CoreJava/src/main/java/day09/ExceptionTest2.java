package day09;
import java.lang.reflect.*;
public class  ExceptionTest2{
	public static void main(String[] args){
		/*
		һ��û�з����쳣	1��2��3��5
		�������쳣��������	1��2��4��5 
		�������쳣��û�в���	1��2��5
		*/
		/*
		������ϣ�
		1��try---catch
		2��try---catch---finally
		3��try---finally
		*/

		try{
			System.out.println("hello");//1
			Class c = Class.forName("student");//2
			System.out.println("world");//3
		}catch(ClassNotFoundException c){
			//System.out.println(c);
			//��ӡ���쳣��Ϣջ��Ϣ 
			c.printStackTrace(); //4
		}finally{
			//�ͷ���Դ�Ĵ���
			System.out.println("finally block");//5
		}
	}
}
