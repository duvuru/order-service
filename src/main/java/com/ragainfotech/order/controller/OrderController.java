package com.ragainfotech.order.controller;

import com.ragainfotech.order.model.Order;
import com.ragainfotech.order.model.Payment;
import com.ragainfotech.order.model.TransactionRequest;
import com.ragainfotech.order.model.TransactionResponse;
import com.ragainfotech.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    OrderService orderService;

    @PostMapping("/submitOrder")
    public TransactionResponse submitOrder(@RequestBody TransactionRequest request){
        return orderService.saveOrder(request);
    }
}
