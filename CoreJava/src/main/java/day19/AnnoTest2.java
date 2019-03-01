package day19;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
public class AnnoTest2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Class c = null;
		try {
			 c = Class.forName("day19.Person");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		Method ms[] = c.getDeclaredMethods();
		System.out.println("作者\t方法名\t预期完成时间");
		for(Method m:ms){
			Annotation a = m.getAnnotation(InProgress1.class);
			if(a!=null){
				InProgress1 in = (InProgress1)a;
				String author = in.author();
				String limited = in.limited();
				System.out.println(author+"\t"+m.getName()+"\t"+limited);
			}
		}
	}

}










