package day10;
import java.lang.reflect.*;
/**
 * 	̽�������������Ϣ	
 * 	1����  
 * 	2��������η�  3������  4�������� 5��ʵ�ֵĽӿ�
 *	6���������η�   7�����Ե�����  8��������
 *	9�����췽�����η�  10�����췽�������б� 	11�����췽���׳��쳣
 *	12���������η�  13��������������  14��������  
 *	15�����������б�  16�������׳��쳣
*/
public class ReflectTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//��1�ַ�ʽ�õ�Class����
		Class c1 = null;
		try {
			c1 = Class.forName("day10.Student");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//��2�ַ�ʽ�õ�Class����
		Class c2 = Student.class;
		
		//��3�ַ�ʽ�õ�Class����
		Student s = new Student();
		Class c3 = s.getClass();
		
		//ͨ�����ַ�ʽ�õ�Class������һ����
		if(c2==c3){
			System.out.println("c2==c3");
		}
		
		print(c1);
	}
	
	public static void print(Class c){
		System.out.println("package "+c.getPackage().getName()+";");		
		System.out.print(Modifier.toString(c.getModifiers()));
		System.out.print(" class ");
		System.out.print(c.getSimpleName());
		System.out.print(" extends ");
		System.out.print(c.getSuperclass().getSimpleName());		
		Class inter[] = c.getInterfaces();
		if(inter.length>0){
			System.out.print(" implements ");
			for(int i=0;i<inter.length;i++){
				System.out.print(inter[i].getSimpleName());
				if(i<inter.length-1){
					System.out.print(",");
				}
			}
		}
		System.out.println("{");
		//all fields 
		printField(c);
		//all constractors
		printConstrator(c);
		//all Methods 
		printMethod(c);
		System.out.println("}");
	}
	
	public static void printMethod(Class c){
		Method m[] = c.getDeclaredMethods();
		if(m.length>0){
			for(int i=0;i<m.length;i++){
				Method me = m[i];
				System.out.print("\t"+Modifier.toString(me.getModifiers()));
				System.out.print(" "+me.getReturnType().getSimpleName());
				System.out.print(" "+me.getName());
				System.out.print("(");
				Class s[] = me.getParameterTypes();
				if(s.length>0){
					for(int j=0;j<s.length;j++){
						System.out.print(s[j].getSimpleName()+" arg"+j );
						if(j<s.length-1){
							System.out.print(",");
						}
					}
				}
				System.out.print(") ");
				Class cl[] = me.getExceptionTypes();
				if(cl.length>0){
					System.out.print(" throws ");
					for(int j=0;j<cl.length;j++){
						System.out.print(cl[j].getSimpleName());
						if(j<cl.length-1){
							System.out.print(",");
						}
					}
				}
				System.out.println("{");
				System.out.println("\t}");
			}
		}
	}
	
	public static void printConstrator(Class c){
		//��ȡһ��ָ���Ĺ��췽��
		/**
		try {
			Constructor con = c.getConstructor(String.class,float.class);
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		}*/
		
		Constructor con[] = c.getConstructors();
		if(con.length>0){
			for(int i=0;i<con.length;i++){
				Constructor cons = con[i];
				System.out.print("\t"+Modifier.toString(cons.getModifiers()));
				System.out.print(" "+c.getSimpleName());
				System.out.print("(");
				Class s[] = cons.getParameterTypes();
				if(s.length>0){
					for(int j=0;j<s.length;j++){
						System.out.print(s[j].getSimpleName()+" arg"+j );
						if(j<s.length-1){
							System.out.print(",");
						}
					}
				}
				
				System.out.print(")");
				Class cl[] = cons.getExceptionTypes();
				if(cl.length>0){
					System.out.print("throws ");
					for(int j=0;j<cl.length;j++){
						System.out.print(cl[j].getSimpleName());
						if(j<cl.length-1){
							System.out.print(",");
						}
					}
				}
				System.out.println("{");				
				System.out.println("\t}");
			}
			
		}
	}
	
	public static void printField(Class c){
		/**
		try {
			Field fi = c.getDeclaredField("age");
			System.out.print(" "+fi.getType().getSimpleName());
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		}*/
		
		Field f[] = c.getDeclaredFields();
		if(f.length>0){
			for(int i=0;i<f.length;i++){
				Field field = f[i];
				System.out.print("\t"+Modifier.toString(field.getModifiers()));
				System.out.print(" "+field.getType().getSimpleName());
				System.out.print(" "+field.getName());
				System.out.println(";");
			}
		}
	}

}









