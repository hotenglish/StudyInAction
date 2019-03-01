package design_mode.builder_mode;

interface Product {
	public String getProductInformation();
}

interface Part {
	public String getPartInformation();
}

interface Builder {
	// ������������
	void buildMainboard();

	// ��������CPU
	void buildCPU();

	// ����Ӳ��
	void buildHarddisk();

	// �������װ��õ�PC)
	// ��Ʒ����װ���̲���������У�����ת�Ƶ������Director���н��С�
	// �Ӷ�ʵ���˹��̺Ͳ����ķ���
	Product getProduct();
}

class Mainboard implements Part {
	private String name;

	public Mainboard(String name) {
		super();
		this.name = name;
	}

	public String getPartInformation() {
		return name;
	}
}

class CPU implements Part {
	private String name;

	public CPU(String name) {
		super();
		this.name = name;
	}

	public String getPartInformation() {
		return name;
	}
}

class HardDisk implements Part {
	private String name;

	public HardDisk(String name) {
		super();
		this.name = name;
	}

	public String getPartInformation() {
		return name;
	}
}

class Computer implements Product {
	Part Mainboard, CPU, HardDisk;

	public Computer(Part mainboard, Part cpu, Part hardDisk) {
		super();
		this.Mainboard = mainboard;
		this.CPU = cpu;
		this.HardDisk = hardDisk;
	}

	public String getProductInformation() {
		return "CPU:" + CPU.getPartInformation() + " MainBoard:"
				+ Mainboard.getPartInformation() + " HardDisk:"
				+ HardDisk.getPartInformation();
	}
}

class Director {

	private Builder builder;

	public Director(Builder builder) {
		this.builder = builder;
	}

	// �����ǽ����塢CPU��Ӳ����װ��PC�Ĺ���
	public void construct() {
		builder.buildMainboard();
		builder.buildCPU();
		builder.buildHarddisk();
	}
}

class ConcreteBuilder implements Builder {

	Product product;

	Part mainboard, cpu, harddisk;

	public void buildMainboard() {
		// �����Ǿ�����ι���partA�Ĵ���
		System.out.println("create MainBoard!");
		mainboard = new Mainboard("Asus");
	}

	public void buildCPU() {
		// �����Ǿ�����ι���partB�Ĵ���
		System.out.println("create CPU!");
		cpu = new CPU("AMD");
	}

	public void buildHarddisk() {
		// �����Ǿ�����ι���partB�Ĵ���
		System.out.println("create HardDisk!");
		harddisk = new HardDisk("SAMSUNG");
	}

	public Product getProduct() {
		// ���������װ��Ʒ���
		if (mainboard != null && cpu != null && harddisk != null) {
			return new Computer(mainboard, cpu, harddisk);
		} else {
			return new Computer(new Mainboard("OK"), cpu = new CPU("OK"),
					cpu = new HardDisk("OK"));
		}
	}
}

public class MyBuilder {

	public static void main(String[] args) {
		ConcreteBuilder builder = new ConcreteBuilder();
		Director director = new Director(builder);
		director.construct();
		Product product = builder.getProduct();
		System.out.println(product.getProductInformation());
	}
}
