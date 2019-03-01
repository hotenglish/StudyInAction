package day18.exec;

public enum CabinClass {
	FirstClass("头等舱",1.8),
	OfficialClass("公务舱",1.3),
	EconomyClass("经济舱",1.0);		
	String name;
	double priceOff;
	private CabinClass(String name,double priceOff)
	{
		this.name=name;
		this.priceOff=priceOff;
	}
	
	public String toString()
	{
      return this.name+" PriceOff:"+this.priceOff;		
	}
}
