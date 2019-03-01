package day05;
public interface InterfaceA{
	void test2(int a);	
}

interface InterfaceB{
	void test1();
}

interface InterfaceC extends InterfaceA,InterfaceB{
	void test3();
}
