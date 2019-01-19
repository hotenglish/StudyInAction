package com.manage.data.bean;
import java.io.Serializable;

public class Custom implements Serializable {
	private static final long serialVersionUID = 1L;
	private int id;
	private String name;
	private String sex;
	private int age;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String toString(){
		return "ID:"+this.getId()+",姓名："+this.getName()+",性别："+this.getSex()+",年龄："+this.getAge();
	}
}