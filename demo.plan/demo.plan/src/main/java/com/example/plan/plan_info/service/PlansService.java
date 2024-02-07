package com.example.plan.plan_info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plan.plan_info.entity.Plans;
import com.example.plan.plan_info.repository.PlansRepo;
@Service
public class PlansService {
	@Autowired
	private PlansRepo Plans_repo;
	
	public List<Plans> getPlans()
	{
		return Plans_repo.findAll();
	}
	

}
