package day15;
import java.io.*;
public class ObjectStreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ObjectOutputStream oos = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("D:\\stu.dat");
			oos = new ObjectOutputStream(fos);
			for(int i=0;i<10;i++){
				Student s= new Student("Student"+i);
				oos.writeObject(s);
			}
			oos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				if(oos!=null){
					oos.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				if(fos!=null){
					fos.close();
				}				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}		
	}
}

class Student implements Serializable{
	String name;
	public Student(String name){
		this.name = name;
	}
}









