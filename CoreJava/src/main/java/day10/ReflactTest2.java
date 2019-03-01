package day10;

import java.lang.reflect.*;

public class ReflactTest2 {
	//通过命令行参数传递四个参数
	//类名、属性名、属性值、方法名
	//
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args == null || args.length < 4) {
			throw new RuntimeException("请传递四个参数！");
		}
		//得到传递过来的四个参数
		String className = args[0];
		String fieldName = args[1];
		String fieldValue = args[2];
		String methodName = args[3];
		
		try {
            //生成Class类的对象
			Class c = Class.forName(className);
			
			//生成c描述的类的对象
			Object o = c.newInstance();
			
			//获取指定的属性
			Field f = c.getDeclaredField(fieldName);
			
			//设置属性的值
			f.set(o, fieldValue);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e1) {
			e1.printStackTrace();
		} catch (IllegalAccessException e1) {
			e1.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

	}

}
