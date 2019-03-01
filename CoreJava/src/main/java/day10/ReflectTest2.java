package day10;

import java.lang.reflect.*;

public class ReflectTest2 {
	//ͨ�������в��������ĸ����� reflect.Student name lili move
	//������������������ֵ��������
	//
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args == null || args.length < 4) {
			throw new RuntimeException("�봫���ĸ�������");
		}
		//�õ����ݹ������ĸ�����
		String className = args[0];
		String fieldName = args[1];
		String fieldValue = args[2];
		String methodName = args[3];
		
		try {
            //����Class��Ķ���
			Class c = Class.forName(className);
			
			//����c��������Ķ���
			//Object o = c.newInstance();
			Constructor con = c.getConstructor(String.class,int.class);
			Object o = con.newInstance("xiaoqiang",20);			
			
			//��ȡָ��������
			Field f = c.getDeclaredField(fieldName);
			f.setAccessible(true);
			System.out.println("value: "+f.get(o));
			
			//�������Ե�ֵ
			f.set(o, fieldValue);
			System.out.println("new value: "+f.get(o));
			
			//��ȡָ���ķ���			
			Method m = c.getDeclaredMethod(methodName);
			
			//���÷���
			m.invoke(o);
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
		}catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}

	}

}
