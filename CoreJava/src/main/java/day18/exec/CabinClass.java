package day18.exec;

public enum CabinClass {
	FirstClass("ͷ�Ȳ�",1.8),
	OfficialClass("�����",1.3),
	EconomyClass("���ò�",1.0);		
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
