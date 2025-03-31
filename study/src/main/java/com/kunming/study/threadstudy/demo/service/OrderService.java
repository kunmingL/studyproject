package com.kunming.study.threadstudy.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService {
    @Autowired
    private InventoryService inventoryService;

    public void createOrder(long productId) {
        if (inventoryService.deductStock(productId)) {
            System.out.println(Thread.currentThread().getName() + " 下单成功");
        } else {
            System.out.println(Thread.currentThread().getName() + " 库存不足");
        }
    }
}