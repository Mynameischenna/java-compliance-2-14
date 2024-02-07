package com.example.plan.plan_info.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.plan.plan_info.entity.PlanInfo;
import com.example.plan.plan_info.repository.PlanInfoRepo;

@Service
public class PlanInfoService {

	@Autowired
	private PlanInfoRepo repo;
	
	
	public List<PlanInfo> getPlansInfo()
	{
		return repo.findAll();
	}
}
