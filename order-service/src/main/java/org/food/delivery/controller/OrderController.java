package org.food.delivery.controller;

import org.food.delivery.dto.OrderRequestDto;
import org.food.delivery.entity.PurchaseOrder;
import org.food.delivery.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/order")
public class OrderController {
    private final OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping(path = "allOrders")
    public ResponseEntity<List<PurchaseOrder>> getAllOrders(){
        List<PurchaseOrder> allOrders = orderService.getOrders();
        return new ResponseEntity<List<PurchaseOrder>>(allOrders, HttpStatus.OK);
    }


    @PostMapping(path = "raise")
    public ResponseEntity<PurchaseOrder> order(@RequestBody OrderRequestDto requestDto){
        PurchaseOrder raiseOrder = orderService.initiateOrder(requestDto);
        return new ResponseEntity<PurchaseOrder>(raiseOrder, HttpStatus.CREATED);
    }
}
