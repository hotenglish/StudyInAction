package design_mode.adapter_mode;

//��汾
interface English {
	public void speechEnglish();
}

interface Chinese {
	public void speechChinese();
}

class Boy implements Chinese {
	public void speechChinese() {
		System.out.println("�ܸ�����ʶ��Ŷ��");
	}
}

class Adapter extends Boy implements English {
	public void speechEnglish() {
		// ����
		speechChinese();
	}
}

public class Peri {

	public static void main(String[] args) {
		English en = new Adapter();
		en.speechEnglish();
	}
}
