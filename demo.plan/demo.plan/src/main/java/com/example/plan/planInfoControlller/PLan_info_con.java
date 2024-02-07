package com.example.plan.planInfoControlller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.plan.plan_info.entity.Plan_info_entity;
import com.example.plan.plan_info.service.Plan_info_service;

import java.util.List;

@RestController
@CrossOrigin("*")
public class PLan_info_con {

    @Autowired
    private Plan_info_service service;

    @PostMapping("/addPlan")
    public Plan_info_entity addProduct(@RequestBody Plan_info_entity plan) {
        return service.saveProduct(plan);
    }

    @PostMapping("/addPlans")
    public List<Plan_info_entity> addProducts(@RequestBody List<Plan_info_entity> plans) {
        return service.saveProducts(plans);
    }

    @GetMapping("/plans")
    public List<Plan_info_entity> findAllProducts() {
        return service.getProducts();
    }

    @GetMapping("/planById/{id}")
    public Plan_info_entity findProductById(@PathVariable int id) {
        return service.getProductById(id);
    }

//    @GetMapping("/product/{name}")
//    public Product findProductByName(@PathVariable String name) {
//        return service.getProductByName(name);
//    }

    @PutMapping("/update")
    public Plan_info_entity updateProduct(@RequestBody Plan_info_entity plan_info_entity) {
        return service.updateProduct(plan_info_entity);
    }

    @DeleteMapping("/deletePlan/{id}")
    public String deleteProduct(@PathVariable int id) {
        return service.deleteProduct(id);
    }
}
