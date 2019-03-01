package day15;
import java.io.*;
import java.util.*;
public class FileTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//��ʾ��ǰĿ¼ 
		String fileName = ".";
		
		//�����в�����������Ŀ¼ 
		if(args.length>0){
			fileName = args[0];
		}
		
		//����File���� 
		File f = new File(fileName);
		
		//�ļ����� 
		int fileNum = 0;
		//Ŀ¼����
		int dirNum = 0;
		//�Ƿ�Ϊһ��Ŀ¼
		if(f.isDirectory()){
			//�г�Ŀ¼��������Ŀ¼���ļ�
			File files[] = f.listFiles();
			if(files.length>0){
				for(File file:files){
					//���һ���޸�ʱ��
					System.out.print(new Date(file.lastModified())+"\t");
					if(file.isDirectory()){
						System.out.print("<DIR>"+"\t");
						dirNum++;
					}else{
						//�ļ���С
						System.out.print(file.length()+"\t");
						fileNum++;
					}
					System.out.println(file.getName());
				}				
			}			
		}else{
			System.out.println("����һ���ļ�Ŀ¼��");
		}
		System.out.println(dirNum+"��Ŀ¼");
		System.out.println(fileNum+"���ļ�");
	}
}













