package answer;
public class Person {
    private String name;
    private int age;
    private boolean gender;
    private boolean Married;

    public String marry(Person P) {
        String reason = null;
        if (this.gender == P.gender) {
            reason = "same gender";
        }
        if (this.Married == true || P.isMarried() == true) {
            reason += "one was Married";
        }
        if (this.age < 24 && this.gender == true) {
            reason += "The age of " + this.name + " is less than 24";
        }
        if (this.age < 22 && this.gender == false) {
            reason += "The age of " + this.name + " is less than 22";
        }

        if (P.age < 24 && P.gender == true) {
            reason += "The age of " + this.name + " is less than 24";
        }
        if (P.age < 22 && P.gender == false) {
            reason += "The age of " + this.name + " is less than 22";
        }

        return reason;
    }

    public Person() {
    }

    public static void main(String[] args) {
        Person person = new Person();
        Person p = new Person();
        person.marry(p); 
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public void setIsMarried(boolean Married) {
        this.Married = Married;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public boolean isGender() {
        return gender;
    }

    public boolean isMarried() {
        return Married;
    }
}