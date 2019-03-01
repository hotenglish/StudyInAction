package day03.exec;

public class MyStack{
	private int arr[];
	private int count;

	public MyStack(int size){
		arr = new int[size];
		count = 0;
	}
	
	public void push(int n){
		if(count==arr.length){ 
			System.out.println("栈空间已满，添加 "+n+" 时失败！");
			return;
		}
		arr[count++]=n;
	}	

	public int pop(){
		if(count ==0){
			System.out.println("栈空间为空，出栈失败！");
			return -1;
		}
		int number = arr[--count];
		System.out.println(number+"出栈成功");
		return number;
	}

	public void print(){
		System.out.print("[");
		for(int i=0;i<count;i++){
			System.out.print(arr[i]+" ");
		}
		System.out.println("]");
	}

	public static void main(String[] args) 
	{
		MyStack s = new MyStack(5);
		s.push(3);
		s.push(8);
		s.push(9);
		s.push(10);
		s.push(22);
		s.push(15);
		s.push(33);
		s.print();
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		s.pop();
		s.print();
	}
}
