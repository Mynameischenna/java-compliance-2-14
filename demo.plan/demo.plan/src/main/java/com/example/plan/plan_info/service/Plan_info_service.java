package com.example.plan.plan_info.service;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plan.plan_info.entity.Plan_info_entity;
import com.example.plan.plan_info.repository.Plan_info_repo;


@Service
public class Plan_info_service {
    @Autowired
    private Plan_info_repo repository;

    public Plan_info_entity saveProduct(Plan_info_entity plan_info) {
        return repository.save(plan_info);
    }

    public List<Plan_info_entity> saveProducts(List<Plan_info_entity> products) {
        return repository.saveAll(products);
    }

    public List<Plan_info_entity> getProducts() {
        return repository.findAll();
    }

    public Plan_info_entity getProductById(int id) {
        return repository.findById(id).orElse(null);
    }

//    public Plan_info_entity getProductByName(String name) {
//        return repository.findByName(name);
//    }

    public String deleteProduct(int id) {
        repository.deleteById(id);
        return "product removed !! " + id;
    }

    public Plan_info_entity updateProduct(Plan_info_entity plan_info_entity) {
        Plan_info_entity existingProduct=null;
		try {
			existingProduct = repository.findById(plan_info_entity.getEv_id()).orElse(null);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
        existingProduct.setGa_id(plan_info_entity.getGa_id());
        existingProduct.setIsrl_code(plan_info_entity.getIsrl_code());
        existingProduct.setPlan_nam(plan_info_entity.getPlan_nam());
        return repository.save(existingProduct);
    }


}

