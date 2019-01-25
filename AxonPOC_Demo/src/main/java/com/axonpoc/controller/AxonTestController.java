
package com.axonpoc.controller;

import java.util.List;
import java.util.UUID;

import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.axonpoc.coreapi.commands.ConfirmOrderCommand;
import com.axonpoc.coreapi.commands.PlaceOrderCommand;
import com.axonpoc.coreapi.commands.ShipOrderCommand;
import com.axonpoc.coreapi.queries.FindAllOrderedProductsQuery;
import com.axonpoc.coreapi.queries.OrderedProduct;

@RestController
@RequestMapping("api/v1/")
@EnableAutoConfiguration
public class AxonTestController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    public AxonTestController(CommandGateway commandGateway, QueryGateway queryGateway) {
        this.commandGateway = commandGateway;
        this.queryGateway = queryGateway;
    }

    @RequestMapping(value = "/ship-order",method = RequestMethod.POST)
    public void shipOrder() {
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, "Deluxe Chair"));
        commandGateway.send(new ConfirmOrderCommand(orderId));
        commandGateway.send(new ShipOrderCommand(orderId));
    }

    @RequestMapping(value = "/ship-unconfirmed-order",method = RequestMethod.POST)
    public void shipUnconfirmedOrder() {
        String orderId = UUID.randomUUID().toString();
        commandGateway.send(new PlaceOrderCommand(orderId, "Deluxe Chair"));
        // This throws an exception, as an Order cannot be shipped if it has not been confirmed yet.
        commandGateway.send(new ShipOrderCommand(orderId));
    }

    @RequestMapping(value = "/all-orders",method = RequestMethod.GET)
    public List<OrderedProduct> findAllOrderedProducts() {
        return queryGateway.query(new FindAllOrderedProductsQuery(), ResponseTypes.multipleInstancesOf(OrderedProduct.class))
                           .join();
    }

}
