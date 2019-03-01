package design_mode.factory_mode;

interface Wing {
}

class KFCWing implements Wing {
}

class McdonaldWing implements Wing {
}

class KFCFactory {
	public static Wing createWing() {
		return new KFCWing();
	}
}

class McdonaldFactory {
	public static Wing createWing() {
		return new McdonaldWing();
	}
}

class Girl {
	public void eat(Wing wing) {
		System.out.println("味道好极了..." + wing);
	}
}

public class Boy {
	public static void main(String[] args) {
		Girl girl = new Girl();
		girl.eat(KFCFactory.createWing());
	}
}
