package design_mode.prototype;

public class SweetWord implements Cloneable {

	private String sweetWord;

	public String getSweetWord() {
		return sweetWord;
	}

	public void setSweetWord(String sweetWord) {
		this.sweetWord = sweetWord;
	}	

	@Override
	protected Object clone() throws CloneNotSupportedException {		
		return super.clone();
	}

	public static void main(String[] args) throws CloneNotSupportedException {
		SweetWord sw1 = new SweetWord();
		sw1.setSweetWord("I Love You!");
		SweetWord sw2 = (SweetWord) sw1.clone();
		System.out.println(sw1 == sw2);
		System.out.println("SweetWord2:"+sw2.getSweetWord());
	}

}
