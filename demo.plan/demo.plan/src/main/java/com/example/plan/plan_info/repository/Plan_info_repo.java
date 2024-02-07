package com.example.plan.plan_info.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.plan.plan_info.entity.Plan_info_entity;
@Repository
public interface Plan_info_repo extends JpaRepository<Plan_info_entity, Integer> {
}