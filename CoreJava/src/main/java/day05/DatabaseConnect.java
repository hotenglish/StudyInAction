package day05;
public class DatabaseConnect{
	public static void main(String args[]){
		if(args==null || args.length<=0){
			System.out.println("�������������ݿ����ͣ�");
			return;
		}
		String driverName = args[0];
		//Driver d = new OracleDriver();
		Driver d = getDriver(driverName);
		d.connect();
	}
	public static Driver getDriver(String driverName){
		if(driverName.equals("oracle")){
			return new OracleDriver();
		}else if(driverName.equals("db2")){
			return new Db2Driver();
		}else if(driverName.equals("mySql")){
			return new MySqlDriver();
		}else if(driverName.equals("sqlServer")){
			return new SqlServerDriver();
		}else{
			return null;
		}
	}
}

interface Driver{
	void connect();
}

class OracleDriver implements Driver{
	public void connect(){
		System.out.println("����oracle�ɹ���");
	}
}

class Db2Driver implements Driver{
	public void connect(){
		System.out.println("����DB2�ɹ���");
	}
}

class MySqlDriver implements Driver{
	public void connect(){
		System.out.println("����MySql�ɹ���");
	}
}

class SqlServerDriver implements Driver{
	public void connect(){
		System.out.println("����SqlServer�ɹ���");
	}
}
