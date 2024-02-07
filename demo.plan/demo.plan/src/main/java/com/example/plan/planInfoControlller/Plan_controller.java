package com.example.plan.planInfoControlller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.plan.plan_info.entity.Plans;
import com.example.plan.plan_info.service.PlansService;

@RestController
@CrossOrigin
public class Plan_controller {
	@Autowired
	private PlansService service;
	
	@GetMapping("/getplans")
	public List<Plans> getplans()
	{
		return service.getPlans();
	}

}
