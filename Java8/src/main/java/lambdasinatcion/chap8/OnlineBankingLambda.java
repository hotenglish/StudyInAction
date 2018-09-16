package lambdasinatcion.chap8;

import java.util.function.Consumer;

public class OnlineBankingLambda {

    public static void main(String args[]) {
        new OnlineBankingLambda().processCustomer(1337, (Customer customer) -> System.out.println("Hello!"));
    }

    public void processCustomer(int id, Consumer<Customer> makeCustomerHappy) {
        Customer customer = Database.getCustomerWithId(id);
        makeCustomerHappy.accept(customer);
    }

    // dummy Customer class
    static private class Customer {
    }

    // dummy Database class
    static private class Database {
        static Customer getCustomerWithId(int id) {
            return new Customer();
        }
    }
}
