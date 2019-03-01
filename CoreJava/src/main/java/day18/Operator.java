package day18;

public enum Operator {
     //	public static final Operator ADD = new Operator();
	ADD{double getValue(double a,double b){return a+b;}},
	SUB{double getValue(double a,double b){return a-b;}},
	TIMES{double getValue(double a,double b){return a*b;}},
	MODIFY{double getValue(double a,double b){return a/b;}};
	
	
	abstract double getValue(double a,double b);
	
	/*
	public double getValue(double a,double b){
		double d = 0;
		if(this==ADD){
			 d=(a+b);
		}
		if(this==SUB){
			d= a-b;
		}
		if(this==TIMES){
			d= a*b;
		}
		if(this==MODIFY){
			d= a/b;
		}
		return d;
	}*/
	
	
}

