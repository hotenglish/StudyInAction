package day09;
import java.util.Random;
public class RandomNumer
{
	public static void main(String args[]){
		int i,j,temp;
		int x[];
		x=new int[100];
		Random number=new Random();
		boolean flag=false;
		System.out.println("100����?:");
		for(i=0;i<100;i++){
			temp = number.nextInt(1000);
			x[i]=temp;
			System.out.print(temp+" ");
			
			}
		System.out.print("\n"+"����");
			for(i=0;i<100;i++)
		{
	        j=2;
			while(x[i]%j!=0)
			{
				j++;
				//System.out.print("J:"+j);
			}
			if(x[i]==j)
			{
			System.out.print(x[i]+" ");
			}
		}
		System.out.print("\n"+"����");
			for(i=0;i<100;i++)
		{
	        j=2;
			while(x[i]%j!=0)
			j++;
			if(x[i]!=j)
			{
			System.out.print(x[i]+" ");
			}
		}
	}
			
}
