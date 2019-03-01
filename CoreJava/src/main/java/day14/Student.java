package day14;


public class Student {
	private String name;
	private int age;
	private String gender;
	private double score;
	
	public Student(String name, int age, String gender, double score) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.score = score;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getScore() {
		return score;
	}
	public void setScore(double score) {
		this.score = score;
	}
	
}
