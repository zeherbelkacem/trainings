package com.fms.trainings.service;

import com.fms.trainings.entities.Order;
import com.fms.trainings.entities.OrderItem;
import com.fms.trainings.entities.Training;
import com.fms.trainings.repository.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private OrderItemService orderItemService;
    private TrainingService trainingService;

    public OrderServiceImpl(OrderRepository orderRepository, OrderItemService orderItemService, TrainingService trainingService) {
        this.orderRepository = orderRepository;
        this.orderItemService = orderItemService;
        this.trainingService = trainingService;
    }

    @Override
    public Order getById(Long customerId) {
        return null;
//        return orderRepository.findById(customerId).get();
    }

    @Override
    public Long saveOrder(Order order) {
        System.out.println("order: " + order);
        /*
         * As we can't add or update a child (orderitem) row (foreign key constraint,
         * start by save a default parent row (order)
         */
        orderRepository.save(
                new Order(0, 0, new Date(), order.getTotalPrice(), null, null));
        long lastOrderId = getLastOrderId();
        System.out.println(lastOrderId);
        /* each line in the bucket corresponds to an item in the order table */
        for (OrderItem orderItem : order.getOrderItems()) {
            Training training = trainingService.getTrainingById(orderItem.getId());
            orderItemService.saveOrderItem(new OrderItem(0, orderItem.getQuantity(),
                    orderItem.getQuantity() * training.getPrice(), orderRepository.findById(lastOrderId).get(), training));
        }
        long orderNb = lastOrderId + 100000;

        Order orderToSave = new Order(lastOrderId, orderNb, new Date(), order.getTotalPrice(), null,
                null);
        orderRepository.save(orderToSave);

        return orderNb;
    }

    @Override
    public long getLastOrderId() {
        long lastInsertedId = 0;
        List<Order> orders = orderRepository.findAll();
        if (orders.size() != 0) {
            Order lastOrder = orders.get(orders.size() - 1);
            lastInsertedId = lastOrder.getId();
        }

        return lastInsertedId;
    }
}
