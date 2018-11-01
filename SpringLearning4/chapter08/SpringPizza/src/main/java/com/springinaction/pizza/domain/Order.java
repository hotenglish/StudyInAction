package com.springinaction.pizza.domain;

import com.springinaction.pizza.service.PricingEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Configurable(value = "order")
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;
    private Customer customer;
    private List<Pizza> pizzas;
    private Payment payment;

    public Order() {
        this.pizzas = new ArrayList<Pizza>();
        this.customer = new Customer();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void setPizzas(List<Pizza> pizzas) {
        this.pizzas = pizzas;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public float getTotal() {
        return pricingEngine.calculateOrderTotal(this);
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    private PricingEngine pricingEngine;

    @Autowired
    public void setPricingEngine(PricingEngine pricingEngine) {
        this.pricingEngine = pricingEngine;
    }

}
