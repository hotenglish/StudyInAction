package day18;
/**
 * 可变长参数
 * 1、优先原则：当有可变长的参数方法和不可变长的参数方法都匹配时，优先调用有不可变长参数的方法
 * 2、可变长参数必须作为一个方法的最后一个参数
 * 3、可以用数组进行遍历
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
		method(str);//用数组调用
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




