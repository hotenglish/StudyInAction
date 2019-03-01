package design_mode.adapter_mode;

//类版本
interface English {
	public void speechEnglish();
}

interface Chinese {
	public void speechChinese();
}

class Boy implements Chinese {
	public void speechChinese() {
		System.out.println("很高兴认识你哦！");
	}
}

class Adapter extends Boy implements English {
	public void speechEnglish() {
		// 翻译
		speechChinese();
	}
}

public class Peri {

	public static void main(String[] args) {
		English en = new Adapter();
		en.speechEnglish();
	}
}
