package day18;
/**
 * �ɱ䳤����
 * 1������ԭ�򣺵��пɱ䳤�Ĳ��������Ͳ��ɱ䳤�Ĳ���������ƥ��ʱ�����ȵ����в��ɱ䳤�����ķ���
 * 2���ɱ䳤����������Ϊһ�����������һ������
 * 3��������������б���
 * @author new
 *
 */
public class VarArgsTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//m5		m1		m3
		m();
		m("hello");
		m("hello","hehe");
		//m("hello","hehe","haha");
		method("hello","hehe","haha");
		String str[] = {"hello","hehe","haha"};
		m("hello",str);	
		method(str);//���������
		//method("4","8","9");
	}
	
	public static void m(String s){
		System.out.println("m1");
	}
	
	public static void m(String...s){
		System.out.println("m2");
	}
	
	public static void m(String s1,String s2){
		System.out.println("m3");		
	}
	
     public static void m(String s1,String...s2){
    	 System.out.println("m4");    	 
     }
     
     public static void m(){
    	 System.out.println("m5");
     }
     
     public static void method(String...s){
    	 for(int i=0;i<s.length;i++){
    		 System.out.println(s[i]);
    	 }
     }
     
     public static void method1(int s[]){
    	 
     }
}




