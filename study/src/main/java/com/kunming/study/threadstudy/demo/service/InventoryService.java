package com.kunming.study.threadstudy.demo.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class InventoryService {
    private Map<Long, Integer> stock = new HashMap<>();

    public void initProduct(long productId, int quantity) {
        stock.put(productId, quantity);
    }

    // 存在超卖问题的实现
    public boolean deductStock(long productId) {
        int current = stock.get(productId);
        if (current > 0) {
            stock.put(productId, current - 1);
            return true;
        }
        return false;
    }
}