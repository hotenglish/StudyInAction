package scjp_book;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class ConvertToDate {

	
	public static void main(String[] args) throws ParseException {
		
		System.out.println("************ 日期及字符串转换实例 ***************");
		DateFormat df=new SimpleDateFormat("yyyy-mm-dd hh:ms:ss");
		Date date=df.parse("2007-3-21 02:20:21");
		//System.out.println(new java.util.Date(df));
		System.out.println(date);		
		String zero=new String();
		String one=new String("a string");
		char[] a={'a',' ','s','t','r','i','n','g'};
		String two=new String(a);
		String three=one.substring(2,one.length());
		StringBuffer sb=new StringBuffer(one);
		System.out.println("zero: "+zero);
		System.out.println("one: "+one);
		//System.out.println("a:"+a);
		System.out.println("three:"+three);
		System.out.println("sb:"+sb);
		
		System.out.println("************ 变量定义实例 ***************");
		int z=0xffff;
		short s=(short)0xffff;
		long l=0xffff;
		double x=0.5e-4;
		//float y=44.0;	
		System.out.println("z:"+z);
		System.out.println("s:"+s);
		System.out.println("l:"+l);
		System.out.println("x:"+x);		
		
		System.out.println("************ 字符串调换实例 **************");
		String result=reverse("ABC");		
		System.out.println("Result of Method reverse one:"+result);
		
		result=reverse();		
		System.out.println("Result of Method reverse two:"+result);
	}

	public static String reverse(String parameter) {
		String mystring =parameter;
		StringBuffer mybuffer=new StringBuffer(mystring);
		return mybuffer.reverse().toString();		
	}
	
	public static String reverse() {
		String mystring =new String("ABC");
		char[] temps=mystring.toCharArray();		
		for(int i=0;i<temps.length/2;i++)
		{
			char temp=temps[i];
			temps[i]=temps[temps.length-i-1];
			temps[temps.length-i-1]=temp;
		}
		return new String(temps);
	}	
}
