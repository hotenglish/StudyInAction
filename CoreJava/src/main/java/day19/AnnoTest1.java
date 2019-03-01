package day19;

import java.util.*;

public class AnnoTest1 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Student().move();
	}

}

@InProgress
class Person {
	// @InProgress
	int age;

	@InProgress
	@Author(name = "zhangsan", id = "005", groupId = "100")
	public void m1() {

	}
	
	@InProgress1(author= "zhanglan",limited="20071103")
	public void method1() {
		//.....
	}

	@InProgress1(author= "lisi",limited="20071105")
	public void method2() {
		//.....
	}
	@InProgress1(author= "wangwu",limited="20071110")
	public void method3() {
		//....
	}
	@InProgress1(author= "Mary",limited="20071210")
	public void method4() {
		//.....
	}

	public void method5() {

	}
}

class Student extends Person {
	@Deprecated
	@SuppressWarnings(value = { "unchecked" })
	public void move() {
		ArrayList list = new ArrayList();
		list.add("hello");
		list.add(3);
	}

	@Override
	public void m1() {

	}
}
