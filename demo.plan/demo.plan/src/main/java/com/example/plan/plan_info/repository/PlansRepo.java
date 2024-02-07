package com.example.plan.plan_info.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plan.plan_info.entity.Plans;
@Repository
public interface PlansRepo  extends JpaRepository<Plans, Integer>{

}
