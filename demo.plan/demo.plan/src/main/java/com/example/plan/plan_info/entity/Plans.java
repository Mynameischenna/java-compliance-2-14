package com.example.plan.plan_info.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Plans {
	@Id
	private int planId;
	private String PlanNumber;
	private String PlanName;
	
	public Plans() {
		
	}

	public Plans(int planId, String planNumber, String planName) {
		super();
		this.planId = planId;
		PlanNumber = planNumber;
		PlanName = planName;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getPlanNumber() {
		return PlanNumber;
	}

	public void setPlanNumber(String planNumber) {
		PlanNumber = planNumber;
	}

	public String getPlanName() {
		return PlanName;
	}

	public void setPlanName(String planName) {
		PlanName = planName;
	}

	@Override
	public String toString() {
		return "Plans [planId=" + planId + ", PlanNumber=" + PlanNumber + ", PlanName=" + PlanName + "]";
	}
	
	
}
