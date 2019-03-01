package day10;

import java.lang.reflect.*;

public class ReflactTest2 {
	//ͨ�������в��������ĸ�����
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
			Object o = c.newInstance();
			
			//��ȡָ��������
			Field f = c.getDeclaredField(fieldName);
			
			//�������Ե�ֵ
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
