package com.codespacelab.order.service;

import com.codespacelab.order.model.dto.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.ArrayList;

@Slf4j
@Service
public class OrderService
{
    private List<OrderDTO> orders;
    private final UserService userService;

    public OrderService(UserService userService)
    {
        this.userService = userService;
        OrderDTO order1 = new OrderDTO(123L, Arrays.asList("Burger", "Meal"), "Collected");
        OrderDTO order2 = new OrderDTO(456L, Arrays.asList("Burger", "Meal", "Meal"), "Pick-up");
        orders = new ArrayList<>(Arrays.asList(order1, order2));
    }

    public List<OrderDTO> getOrders(Long userId)
    {
        userService.isUserValid(userId);
        return orders;
    }

    public OrderDTO getOrder(Long id, Long userId)
    {
        userService.isUserValid(userId);
        Optional<OrderDTO> orderOptional = orders.stream().filter(order -> order.getId().equals(id)).findFirst();
        return orderOptional.orElse(null);
    }

    public OrderDTO addOrder(OrderDTO orderDto, Long userId)
    {
        userService.isUserValid(userId);
        OrderDTO newOrder = new OrderDTO(orders.size() + 100L, orderDto.getItems(), "Pending");
        orders.add(newOrder);
        return newOrder;
    }

    public OrderDTO updateOrder(OrderDTO orderDto, Long userId)
    {
        userService.isUserValid(userId);
        Optional<OrderDTO> orderOptional = orders.stream()
                .filter(order -> order.getId() == orderDto.getId())
                .map(order -> {
                    order.setItems(orderDto.getItems());
                    order.setStatus(orderDto.getStatus());
                    return order;
                }).findFirst();
        return orderOptional.orElse(null);
    }

    public boolean deleteOrder(Long id, Long userId)
    {
        userService.isUserValid(userId);
        final int originalSize = orders.size();
        orders = orders.stream().filter(order -> !order.getId().equals(id)).collect(Collectors.toList());

        return originalSize > orders.size();
    }
}
