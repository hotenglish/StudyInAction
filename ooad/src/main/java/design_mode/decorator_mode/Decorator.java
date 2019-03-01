package design_mode.decorator_mode;

interface Graphic {
	public void draw();
}

class Square implements Graphic {

	public void draw() {
		System.out.println("����һ�������Σ�");
	}
}

public class Decorator {
	
	private Graphic graphic;

	public Decorator(Graphic graphic) {
		this.graphic = graphic;
	}

	public void draw() {
		drawBorder();
		graphic.draw();
	}

	public void drawBorder() {
		System.out.println("����������ӱ߿�");
	}

	public static void main(String[] args) {
		Square square=new Square();
		Decorator decorator=new Decorator(square);
		decorator.draw();
	}
}
