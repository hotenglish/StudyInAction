package design_mode.strategy_mode;

interface DiscountStrategy {
	public double calculateDiscount();
}

class FlatRateStrategy implements DiscountStrategy {

	private int copies = 0;

	public FlatRateStrategy(int copies) {
		this.copies = copies;
	}

	public double calculateDiscount() {
		if (copies < 100)
			return 1;
		else if (copies < 1000)
			return 0.8;

		return 0.75;
	}
}

class NoDiscountStrategy implements DiscountStrategy {

	public NoDiscountStrategy() {
	}

	public double calculateDiscount() {
		return 1;
	}
}

class Commodity {

	private double price;

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
}

public class Payee {

	private DiscountStrategy discountStrategy;

	private Commodity commodity;

	public Payee(Commodity commodity, DiscountStrategy discountStrategy) {
		this.commodity = commodity;
		this.discountStrategy = discountStrategy;
	}

	public double calculatePrice() {
		return commodity.getPrice() * discountStrategy.calculateDiscount();
	}

	public static void main(String[] args) {
		Commodity comm=new Commodity();		
		comm.setPrice(10000);		
		Payee payee=new Payee(comm,new FlatRateStrategy(1000));
		System.out.println("PayAmount:"+payee.calculatePrice());
        payee=new Payee(comm,new NoDiscountStrategy()); 
		System.out.println("NoDiscountPayAmount:"+payee.calculatePrice());
	}

}
