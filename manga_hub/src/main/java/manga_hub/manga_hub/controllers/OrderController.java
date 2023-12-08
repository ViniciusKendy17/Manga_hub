package manga_hub.manga_hub.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import manga_hub.manga_hub.Services.OrderService;
import manga_hub.manga_hub.models.OrderModel;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderController {
 
    @Autowired
    private OrderService service;

    @PostMapping
    public OrderModel criarPedido(@RequestBody OrderModel order) {
        return service.createOrder(order);
    }



}