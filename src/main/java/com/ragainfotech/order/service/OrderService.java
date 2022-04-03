package com.ragainfotech.order.service;

import com.ragainfotech.order.model.Order;
import com.ragainfotech.order.model.Payment;
import com.ragainfotech.order.model.TransactionRequest;
import com.ragainfotech.order.model.TransactionResponse;
import com.ragainfotech.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    OrderRepository repository;

    @Autowired
    RestTemplate restTemplate;

    public TransactionResponse saveOrder(TransactionRequest request){
        String message="";
        Order order = repository.save(request.getOrder());
        Payment payment = new Payment();
        payment.setOrderId(order.getId());
        payment.setAmount(order.getPrice());
        Payment paymentResponse = restTemplate.postForObject("http://PAYMENT-SERVICE/payment/submitPayment", payment, Payment.class);

        if(paymentResponse.getPaymentStatus().equalsIgnoreCase("Success")){
            message = "Payment processing is successful and order placed";
        }else {
            message = "Payment processing is failed in payment service";
        }
        return new TransactionResponse(order,paymentResponse.getAmount(),paymentResponse.getTransactionId(),message);
    }
}
