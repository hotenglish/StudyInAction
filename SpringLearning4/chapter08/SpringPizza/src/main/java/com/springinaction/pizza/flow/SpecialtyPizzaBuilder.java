package com.springinaction.pizza.flow;

import com.springinaction.pizza.domain.Pizza;
import com.springinaction.pizza.domain.Topping;
import org.apache.log4j.Logger;
import org.springframework.webflow.execution.Action;
import org.springframework.webflow.execution.Event;
import org.springframework.webflow.execution.RequestContext;

import java.util.ArrayList;
import java.util.List;

public class SpecialtyPizzaBuilder implements Action {

    public static final Logger LOGGER = Logger.getLogger(SpecialtyPizzaBuilder.class);


    @Override
    public Event execute(RequestContext request) throws Exception {
        String type = request.getRequestParameters().get("pizzaType");
        LOGGER.debug("BUILDING A SPECIALTY PIZZA:  " + type);

        Pizza pizza = (Pizza) request.getFlowScope().get("pizza");
        if("MEAT".equals(type)){
            LOGGER.debug("BUILDING A CARNIVORE");

            List<Topping> meats = new ArrayList<>();

            meats.add(Topping.CANADIAN_BACON);
            meats.add(Topping.HAMBURGER);
            meats.add(Topping.PEPPERONI);
            meats.add(Topping.SAUSAGE);

            pizza.setToppings(meats);
        }else if("VEGGIE".equals(type)){
            LOGGER.debug("BUILDING A HERBIVORE");
            List<Topping> meats = new ArrayList<>();

            meats.add(Topping.GREEN_PEPPER);
            meats.add(Topping.MUSHROOM);
            meats.add(Topping.PINEAPPLE);
            meats.add(Topping.TOMATO);

            pizza.setToppings(meats);
        }else if("THEWORKS".equals(type)){
            LOGGER.debug("BUILDING AN OMNIVORE");

            List<Topping> meats = new ArrayList<>();
            System.out.println("THE WORKS!");

            meats.add(Topping.CANADIAN_BACON);
            meats.add(Topping.HAMBURGER);
            meats.add(Topping.PEPPERONI);
            meats.add(Topping.SAUSAGE);
            meats.add(Topping.GREEN_PEPPER);
            meats.add(Topping.MUSHROOM);
            meats.add(Topping.PINEAPPLE);
            meats.add(Topping.TOMATO);
            meats.add(Topping.EXTRA_CHEESE);
            meats.add(Topping.ONION);
            meats.add(Topping.JALAPENO);

            pizza.setToppings(meats);
        }

        return new Event(this,"success");
    }
}
