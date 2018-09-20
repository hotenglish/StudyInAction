package lambdasinatcion.chap10;

import java.util.Optional;

import static java.util.Optional.empty;
import static java.util.Optional.of;

public class OptionalMain {

    public String getCarInsuranceName(Optional<Person> person) {
        return person.flatMap(Person::getCar)
                .flatMap(Car::getInsurance)
                .map(Insurance::getName)
                .orElse("Unknown");
    }

    public static void main(String... args){
        Person person1=new Person();
        Insurance pingAn=new Insurance();
        pingAn.setName("pingAn");
        Car car1=new Car();
        car1.setInsurance(of(pingAn));
        person1.setCar(of(car1));

        Person person2=new Person();
        Car car2=new Car();
        car2.setInsurance(empty());// if uncomment this statement, that will be throw exception
        person2.setCar(of(car2));

        OptionalMain optionalMain=new OptionalMain();
        System.out.println(optionalMain.getCarInsuranceName(of(person1)));
        System.out.println(optionalMain.getCarInsuranceName(of(person2)));
    }
}
