package design_mode.composite_mode;

import java.util.*;

interface Graphics {
	public void draw();
}

class Circle implements Graphics {
	public void draw() {
		System.out.println("A Circle was drawn!");
	}
}

class Line implements Graphics {
	public void draw() {
		System.out.println("A Line was drawn!");
	}
}

class Rectangle implements Graphics {
	public void draw() {
		System.out.println("A Rectangle was drawn!");
	}
}

public class Picture {

	private List<Graphics> list = new ArrayList<Graphics>();

	public void draw() {
		for (Graphics g : list)
			g.draw();
	}

	public void add(Graphics g) {
		list.add(g);
	}

	public void remove(Graphics g) {
		list.remove(g);
	}

	public Graphics getChild(int i) {
		return (Graphics) list.get(i);
	}

	public static void main(String[] args) {

		Picture picture = new Picture();
		picture.add(new Circle());
		picture.add(new Line());
		picture.add(new Rectangle());
		picture.draw();
	}
}
