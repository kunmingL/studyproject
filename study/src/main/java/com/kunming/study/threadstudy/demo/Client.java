package com.kunming.study.threadstudy.demo;

import com.kunming.study.threadstudy.demo.service.InventoryService;
import com.kunming.study.threadstudy.demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Client implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Client.class, args);
    }

    @Autowired
    private OrderService orderService;

    @Autowired
    private InventoryService inventoryService;

    @Override
    public void run(String... args) throws Exception {
        // 初始化商品库存
        long productId = 1L;
        inventoryService.initProduct(productId, 100); // 只有100件库存

        // 模拟200个并发请求
        ExecutorService executor = Executors.newFixedThreadPool(200);
        for (int i = 0; i < 200; i++) {
            executor.execute(() -> orderService.createOrder(productId));
        }
        executor.shutdown();
        executor.awaitTermination(1, TimeUnit.MINUTES);
    }

}
