package design_mode.template_mode;


abstract class Account
{
    protected String accountNumber;

    public Account(String accountNumber)
    {
        this.accountNumber = accountNumber;
    }

    public final double calculateInterest()
    {
        double interestRate = doCalculateInterestRate();
        String accountType = doCalculateAccountType();
        double amount = calculateAmount(accountType, accountNumber);

	   	return amount * interestRate;
    }
                                    
    abstract protected String doCalculateAccountType() ;

    abstract protected double doCalculateInterestRate() ;

    public final double calculateAmount(String accountType, String accountNumber)
    {
        //retrieve amount from database...here is only a mock-up
        return 1680.00D;
    }
}

class DollarAccount extends Account 
{
    public DollarAccount(String accountNumber) {
		super(accountNumber);		
	}

	public String doCalculateAccountType()
    {
        return "Dollar";
    }

    public double doCalculateInterestRate()
    {
        return 0.085D;
    }
}
class RMBAccount extends Account 
{
	
    public RMBAccount(String accountNumber) {
		super(accountNumber);		
	}

	public String doCalculateAccountType()
    {	
        return "RMB";
    }

    public double doCalculateInterestRate()
    {
        return 0.075D;
    }
}

public class TemplateMode {

	
	public static void main(String[] args) {
		RMBAccount rmb=new RMBAccount("10001");
		
		DollarAccount dollar=new DollarAccount("10002");
		System.out.println("RMB:"+rmb.calculateInterest()+" Dollar:"+dollar.calculateInterest());


	}

}
