package com.example.plan.plan_info.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "planInfoNew")
public class PlanInfo {
	@Id
	private int planInfoId;
	private int planId;
	private String clientName;
	private String prospectivePlanNumber;
	private String einNumber;
	private String instiution;
	private String planType;
	
	public PlanInfo()
	{
		
	}

	public PlanInfo(int planInfoId, int planId, String clientName, String prospectivePLanNumber, String einNumber,
			String instiution, String planType) {
		super();
		this.planInfoId = planInfoId;
		this.planId = planId;
		this.clientName = clientName;
		this.prospectivePlanNumber = prospectivePLanNumber;
		this.einNumber = einNumber;
		this.instiution = instiution;
		this.planType = planType;
	}

	public int getPlanInfoId() {
		return planInfoId;
	}

	public void setPlanInfoId(int planInfoId) {
		this.planInfoId = planInfoId;
	}

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public String getProspectivePlanNumber() {
		return prospectivePlanNumber;
	}

	public void setProspectivePlanNumber(String prospectivePlanNumber) {
		this.prospectivePlanNumber = prospectivePlanNumber;
	}

	public String getEinNumber() {
		return einNumber;
	}

	public void setEinNumber(String einNumber) {
		this.einNumber = einNumber;
	}

	public String getInstiution() {
		return instiution;
	}

	public void setInstiution(String instiution) {
		this.instiution = instiution;
	}

	public String getPlanType() {
		return planType;
	}

	public void setPlanType(String planType) {
		this.planType = planType;
	}

	@Override
	public String toString() {
		return "PlanInfo [planInfoId=" + planInfoId + ", planId=" + planId + ", clientName=" + clientName
				+ ", prospectivePlanNumber=" + prospectivePlanNumber + ", einNumber=" + einNumber + ", instiution="
				+ instiution + ", planType=" + planType + "]";
	}
	
	
	
}
