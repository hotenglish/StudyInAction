package day15;
import java.io.*;
public class FileFilterTest {
		public static void main(String[] args) {
			File f=new File("c:\\");
			filter(f);
		}
		// �оٳ�dir�������е���.java��׺���ļ�
		public static void filter(File dir){
			//����listFiles�������������ڲ��෽ʽʵ�ֹ��� 
			File[] fs=dir.listFiles(new FileFilter(){
				public boolean accept(File f){
					//���ΪĿ¼������true
					if (f.isDirectory()) return true;
					else {
						//Ϊ�ļ�������.javaΪ��׺����true�����򣬷���false
						String name=f.getName();
						int index = name.indexOf(".txt");
						boolean flag = (index!=-1);
						return flag;
					
					}
				}			
			});
			for(int i=0;i<fs.length;i++){
				if (fs[i].isFile())
					//��ӡ������·��
					System.out.println(fs[i].getAbsolutePath());
				//�ݹ����filter�������ҳ���Ŀ¼�����й��˺���ļ�
				else filter(fs[i]);
			}
		}
	}





