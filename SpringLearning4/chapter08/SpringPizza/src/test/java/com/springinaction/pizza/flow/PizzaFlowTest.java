package com.springinaction.pizza.flow;

import com.springinaction.pizza.domain.Customer;
import com.springinaction.pizza.domain.Order;
import com.springinaction.pizza.domain.Pizza;
import com.springinaction.pizza.service.CustomerNotFoundException;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.webflow.config.FlowDefinitionResource;
import org.springframework.webflow.config.FlowDefinitionResourceFactory;
import org.springframework.webflow.test.MockExternalContext;
import org.springframework.webflow.test.MockFlowBuilderContext;
import org.springframework.webflow.test.execution.AbstractXmlFlowExecutionTests;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@Ignore("Need to write individual tests for each subflow and test top-level flow with mock flows.")
public class PizzaFlowTest extends AbstractXmlFlowExecutionTests {

    @Override
    protected FlowDefinitionResource getResource(FlowDefinitionResourceFactory resourceFactory) {
        return resourceFactory.createResource("file:src/main/webapp/WEB-INF/flows/pizza/pizza-flow.xml");
    }

    protected void configureFlowBuilderContext(MockFlowBuilderContext builderContext) {
        PizzaFlowActions pizzaFlowActions = mock(PizzaFlowActions.class);

        try {
            when(pizzaFlowActions.lookupCustomer("9725551234")).thenReturn(new Customer("9725551234"));
            when(pizzaFlowActions.lookupCustomer("5051231234")).thenThrow(new CustomerNotFoundException());
        } catch (CustomerNotFoundException e) {
        }

        builderContext.registerBean("pizzaFlowActions", pizzaFlowActions);
    }

    @Test
    public void testStartFlow() {
        startFlow(new MockExternalContext());
        assertCurrentStateEquals("welcome");
    }

    @Test
    public void testKnownPhoneEnteredEventFromWelcomeState() {
        startFlow(new MockExternalContext());
        MockExternalContext context = new MockExternalContext();
        context.putRequestParameter("phoneNumber", "9725551234");
        context.setEventId("phoneEntered");
        setCurrentState("welcome");
        resumeFlow(context);
        assertCurrentStateEquals("showOrder");
        Order order = (Order) getFlowAttribute("order");
        assertEquals("9725551234", order.getCustomer().getName());
    }

    @Test
    public void testUnknownPhoneEnteredEventFromWelcomeState() {
        startFlow(new MockExternalContext());

        MockExternalContext context = new MockExternalContext();
        context.putRequestParameter("phoneNumber", "5051231234");
        context.setEventId("phoneEntered");

        setCurrentState("welcome");
        resumeFlow(context);
        assertCurrentStateEquals("registrationForm");
    }

    @Test
    public void testShouldTransitionFromShowOrderToCreatePizza() {
        startFlow(new MockExternalContext());

        MockExternalContext context = new MockExternalContext();
        context.setEventId("createPizza");
        setCurrentState("showOrder");
        resumeFlow(context);
        assertCurrentStateEquals("createPizza");
        assertNotNull(getFlowAttribute("pizza"));
    }

    @Test
    public void testShouldAddPizzaOnAddPizzaEvent() {
        startFlow(new MockExternalContext());
        setCurrentState("createPizza");
        getFlowScope().put("pizza", new Pizza());
        MockExternalContext context = new MockExternalContext();
        context.putRequestParameter("toppings", "TOMATO");
        context.setEventId("addPizza");
        resumeFlow(context);

        assertCurrentStateEquals("showOrder");
        Order order = (Order) getFlowAttribute("order");
        List<Pizza> pizzas = order.getPizzas();
        assertEquals(1, pizzas.size());
    }

    @Test
    public void testShouldNotAddPizzaOnCancelEvent() {
        startFlow(new MockExternalContext());
        setCurrentState("createPizza");

        MockExternalContext context = new MockExternalContext();
        context.setEventId("cancel");
        resumeFlow(context);

        assertCurrentStateEquals("showOrder");
        Order order = (Order) getFlowAttribute("order");
        assertEquals(0, order.getPizzas().size());
    }
}
