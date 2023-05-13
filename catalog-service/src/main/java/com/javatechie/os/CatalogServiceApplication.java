package com.javatechie.os;

import com.javatechie.os.entity.Order;
import com.javatechie.os.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@RestController
@RequestMapping("/orders")
public class CatalogServiceApplication {

    @Autowired
    private OrderRepository orderRepository;

    private static final String ELECTRONICS = "electronics";
    private static final String BLACK = "black";

    @PostConstruct
    public void initOrdersTable() {
        orderRepository.saveAll(Stream.of(
                        new Order("mobile", ELECTRONICS , "white", 20000),
                        new Order("T-Shirt", "clothes", BLACK, 999),
                        new Order("Jeans", "clothes", "blue", 1999),
                        new Order("Laptop", ELECTRONICS, "gray", 50000),
                        new Order("digital watch", ELECTRONICS, BLACK, 2500),
                        new Order("Fan", ELECTRONICS, BLACK , 50000)
                ).
                collect(Collectors.toList()));
    }

	@GetMapping
	public List<Order> getOrders(){
		return orderRepository.findAll();
	}
	@GetMapping("/{category}")
	public List<Order> getOrdersByCategory(@PathVariable String category){
		return orderRepository.findByCategory(category);
	}

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApplication.class, args);
    }

}
