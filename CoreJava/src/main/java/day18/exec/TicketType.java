package day18.exec;

public enum TicketType {
	Adult(1.0),
	Child(0.3),
	Baby(0.1);
	public double priceoff;
	private TicketType(double priceoff)
	{
	this.priceoff=priceoff;
	}
}


