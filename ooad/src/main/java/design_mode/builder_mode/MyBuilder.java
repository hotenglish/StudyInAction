package design_mode.builder_mode;

interface Product {
	public String getProductInformation();
}

interface Part {
	public String getPartInformation();
}

interface Builder {
	// 创建部件主板
	void buildMainboard();

	// 创建部件CPU
	void buildCPU();

	// 创建硬盘
	void buildHarddisk();

	// 返回最后装配好的PC)
	// 成品的组装过程不在这里进行，而是转移到下面的Director类中进行。
	// 从而实现了过程和部件的分离
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

	// 这里是将主板、CPU和硬盘组装成PC的过程
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
		// 这里是具体如何构建partA的代码
		System.out.println("create MainBoard!");
		mainboard = new Mainboard("Asus");
	}

	public void buildCPU() {
		// 这里是具体如何构建partB的代码
		System.out.println("create CPU!");
		cpu = new CPU("AMD");
	}

	public void buildHarddisk() {
		// 这里是具体如何构建partB的代码
		System.out.println("create HardDisk!");
		harddisk = new HardDisk("SAMSUNG");
	}

	public Product getProduct() {
		// 返回最后组装成品结果
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
