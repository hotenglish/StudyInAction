package lambdasinatcion.chap8;

abstract class OnlineBanking {

    public static void main(String[] args) {
        new OnlineBanking(){
            @Override
            void makeCustomerHappy(Customer c) {
                System.out.println("Hello!");
            }
        }.processCustomer(1337);
    }

    public void processCustomer(int id) {
        Customer c = DataBase.getCustomerWithId(id);
        makeCustomerHappy(c);
    }

    abstract void makeCustomerHappy(Customer c);

    // dummy Customer class
    static private class Customer {
    }

    // dummy Datbase class
    static private class DataBase {
        static Customer getCustomerWithId(int id) {
            return new Customer();
        }
    }
}
