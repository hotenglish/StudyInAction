package day18.exec;

public enum CourseType {
	CoreJava("ma"){void teacherMothed(){System.out.println("ma teache");}},
	Unix("min"){void teacherMothed(){System.out.println("min teache");}},
	Oracle("shen"){void teacherMothed(){System.out.println("shen teache");}};
	
	public String Teacher;
	
	private CourseType(String teacher)
	{
		this.Teacher=teacher;
	}
	
	abstract void teacherMothed();
}
