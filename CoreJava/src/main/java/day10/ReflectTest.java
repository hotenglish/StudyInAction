package day10;
import java.lang.reflect.*;
/**
 * 	探查出所有下列信息	
 * 	1、包  
 * 	2、类的修饰符  3、类名  4、父类名 5、实现的接口
 *	6、属性修饰符   7、属性的类型  8、属性名
 *	9、构造方法修饰符  10、构造方法参数列表 	11、构造方法抛出异常
 *	12、方法修饰符  13、方法返回类型  14、方法名  
 *	15、方法参数列表  16、方法抛出异常
*/
public class ReflectTest {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//第1种方式得到Class对象
		Class c1 = null;
		try {
			c1 = Class.forName("day10.Student");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		//第2种方式得到Class对象
		Class c2 = Student.class;
		
		//第3种方式得到Class对象
		Student s = new Student();
		Class c3 = s.getClass();
		
		//通过三种方式得到Class对象都是一样的
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
		//获取一个指定的构造方法
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









