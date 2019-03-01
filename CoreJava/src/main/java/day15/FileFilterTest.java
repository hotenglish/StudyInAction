package day15;
import java.io.*;
public class FileFilterTest {
		public static void main(String[] args) {
			File f=new File("c:\\");
			filter(f);
		}
		// 列举出dir下面所有的以.java后缀的文件
		public static void filter(File dir){
			//调用listFiles方法，用匿名内部类方式实现过滤 
			File[] fs=dir.listFiles(new FileFilter(){
				public boolean accept(File f){
					//如果为目录，返回true
					if (f.isDirectory()) return true;
					else {
						//为文件，则以.java为后缀返回true，否则，返回false
						String name=f.getName();
						int index = name.indexOf(".txt");
						boolean flag = (index!=-1);
						return flag;
					
					}
				}			
			});
			for(int i=0;i<fs.length;i++){
				if (fs[i].isFile())
					//打印出绝对路径
					System.out.println(fs[i].getAbsolutePath());
				//递归调用filter方法，找出该目录下所有过滤后的文件
				else filter(fs[i]);
			}
		}
	}





