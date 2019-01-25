package com.axonpoc.commandmodel;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import com.axonpoc.coreapi.commands.ConfirmOrderCommand;
import com.axonpoc.coreapi.commands.PlaceOrderCommand;
import com.axonpoc.coreapi.commands.ShipOrderCommand;
import com.axonpoc.coreapi.events.OrderConfirmedEvent;
import com.axonpoc.coreapi.events.OrderPlacedEvent;
import com.axonpoc.coreapi.events.OrderShippedEvent;

@Aggregate
public class OrderAggregate {

    @AggregateIdentifier
    private String orderId;
    private boolean orderConfirmed;

    @CommandHandler
    public OrderAggregate(PlaceOrderCommand command) {
        apply(new OrderPlacedEvent(command.getOrderId(), command.getProduct()));
    }

    @CommandHandler
    public void handle(ConfirmOrderCommand command) {
        apply(new OrderConfirmedEvent(orderId));
    }

    @CommandHandler
    public void handle(ShipOrderCommand command) {
        if (!orderConfirmed) {
            throw new IllegalStateException("Cannot ship an order which has not been confirmed yet.");
        }

        apply(new OrderShippedEvent(orderId));
    }

    @EventSourcingHandler
    public void on(OrderPlacedEvent event) {
        this.orderId = event.getOrderId();
        orderConfirmed = false;
    }

    @EventSourcingHandler
    public void on(OrderConfirmedEvent event) {
        orderConfirmed = true;
    }

    protected OrderAggregate() {
        // Required by Axon to build a default Aggregate prior to Event Sourcing
    }

}
