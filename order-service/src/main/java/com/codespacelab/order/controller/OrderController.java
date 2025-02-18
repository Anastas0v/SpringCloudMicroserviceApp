package com.codespacelab.order.controller;

import com.codespacelab.order.model.dto.OrderDTO;
import com.codespacelab.order.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/order")
public class OrderController
{
    @Value("${spring.datasource.url}")
    private String database;

    private final OrderService orderService;

    public OrderController(final OrderService orderService)
    {
        this.orderService = orderService;
    }

    @GetMapping("/all")
    public List<OrderDTO> getOrders()
    {
        log.info("DataBase URL: " + database);
        return orderService.getOrders();
    }

    @GetMapping
    public OrderDTO getOrder(@RequestParam(name = "id") Long id)
    {
        return orderService.getOrder(id);
    }

    @PostMapping
    public OrderDTO addOrder(@Validated @RequestBody OrderDTO orderDTO)
    {
        return orderService.addOrder(orderDTO);
    }

    @PutMapping
    public OrderDTO updateOrder(@Validated @RequestBody OrderDTO orderDTO)
    {
        return orderService.updateOrder(orderDTO);
    }

    @DeleteMapping
    public boolean deleteOrder(@RequestParam(name = "id") Long id)
    {
        return orderService.deleteOrder(id);
    }
}
