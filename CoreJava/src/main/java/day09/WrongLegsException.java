package day09;
//自定义异常类
public class WrongLegsException extends Exception{
	public WrongLegsException(String message){
		//调用父类构造方法
		super(message);
	}
}
