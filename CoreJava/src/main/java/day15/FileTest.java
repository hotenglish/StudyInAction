package day15;
import java.io.*;
import java.util.*;
public class FileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//表示当前目录 
		String fileName = ".";
		
		//命令行参数传进来的目录 
		if(args.length>0){
			fileName = args[0];
		}
		
		//构造File对象 
		File f = new File(fileName);
		
		//文件个数 
		int fileNum = 0;
		//目录个数
		int dirNum = 0;
		//是否为一级目录
		if(f.isDirectory()){
			//列出目录下所有子目录和文件
			File files[] = f.listFiles();
			if(files.length>0){
				for(File file:files){
					//最后一次修改时间
					System.out.print(new Date(file.lastModified())+"\t");
					if(file.isDirectory()){
						System.out.print("<DIR>"+"\t");
						dirNum++;
					}else{
						//文件大小
						System.out.print(file.length()+"\t");
						fileNum++;
					}
					System.out.println(file.getName());
				}				
			}			
		}else{
			System.out.println("不是一级文件目录！");
		}
		System.out.println(dirNum+"个目录");
		System.out.println(fileNum+"个文件");
	}
}













