package day17;
import java.io.*;
/**
 * 
 * @author new
 *
 */
public class PipedStreamTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		PipedInputStream pis = new PipedInputStream();
		PipedOutputStream pos = null;
		try {
			pos = new PipedOutputStream(pis);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		try {
			pis.connect(pos);
		} catch (IOException e) {
			e.printStackTrace();
		}*/
		A a = new A(pis);
		B b = new B(pos);
		a.start();
		b.start();
	}
}

class A extends Thread{
	PipedInputStream pis;
	public A(PipedInputStream pis){
		this.pis = pis;
	}
	public void run(){
		byte b[]= new byte[1024];
		String s = "";
		try {
			System.out.println((char)pis.read());
			System.out.println((char)pis.read());
			int len = pis.read(b);
			s = new String(b,0,len);
			System.out.println(s);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

class B extends Thread{
	PipedOutputStream pos;
	public B(PipedOutputStream pos){
		this.pos = pos;
	}
	public void run(){
		String s = "hello,how are you";
		try {
			pos.write('a');
			pos.write('h');
			pos.write(s.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}






