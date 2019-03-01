package answer;


public class Animal {

    private String name;
    private int legs;
    public Animal() {
        this("AAA", 4);
    }

    public Animal(String name, int legs) {
        this.name = name;
        this.legs = legs;
    }

    public void move() {
        System.out.println(this.name + "Moving!!");
    }

    public void move(int n) {
        for (int i = 0; i < n; i++) {
            System.out.println(this.name + "Moving!!");
        }
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLegs(int legs) {
        this.legs = legs;
    }

    public String getName() {
        return name;
    }

    public int getLegs() {
        return legs;
    }
    public static void main(String[] args) {
            Animal animal = new Animal();
            System.out.println("Name: " + animal.getName() + " legs:" +
                               animal.getLegs());
            animal.move();
            Animal animal2 = new Animal("mycat", 4);
            System.out.println("Name: " + animal2.getName() + " legs:" +
                               animal2.getLegs());
            animal2.move(3);
            Fish fish = new Fish("myfish");
            fish.move();
            Bird bird = new Bird("mybird");
            bird.move();
            Zoo zoo=new Zoo();
        }
}



class Fish extends Animal {
    private String name;
    public Fish(String name) {
        this.name = name;
        super.setLegs(0);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void move() {
        System.out.println(name + " swimming!!");
    }
}


class Bird extends Animal {
    private String name;
    public Bird(String name) {
        this.name = name;
        super.setLegs(2);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void move() {
        System.out.println(name + " flying!!");
    }
}

class Zoo extends Animal{
        public Zoo() {
            Animal A = new Animal();
            A.move(2);
            Fish fish = new Fish("myfish");
            Bird bird = new Bird("mybird");
            fish.move();
            bird.move();
        }
    }

