package com.fms.trainings.restController;

import com.fms.trainings.entities.Order;
import com.fms.trainings.entities.Training;
import com.fms.trainings.service.OrderService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/order")
@CrossOrigin("*")
public class OrderRestController {

    private OrderService orderService;

    public OrderRestController(OrderService orderService) {
        this.orderService = orderService;
    }
    @PostMapping("/save")
    public @ResponseBody ResponseEntity<Long> saveOrder(@RequestBody Order order){
        return new ResponseEntity<Long>(orderService.saveOrder(order), HttpStatus.OK);
    }


}
