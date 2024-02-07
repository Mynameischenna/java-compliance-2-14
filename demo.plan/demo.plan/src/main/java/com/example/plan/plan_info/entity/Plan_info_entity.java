package com.example.plan.plan_info.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
//@Table(name = "plan_info_")
public class Plan_info_entity {

    @Id
    private int ev_id;
    private String plan_nam;
    private String isrl_code;
    private String ga_id;
    
    public Plan_info_entity()
    {
    	
    }

	public Plan_info_entity(int ev_id, String plan_nam, String isrl_code, String ga_id) {
		super();
		this.ev_id = ev_id;
		this.plan_nam = plan_nam;
		this.isrl_code = isrl_code;
		this.ga_id = ga_id;
	}


	public int getEv_id() {
		return ev_id;
	}

	public void setEv_id(int ev_id) {
		this.ev_id = ev_id;
	}

	public String getPlan_nam() {
		return plan_nam;
	}

	public void setPlan_nam(String plan_nam) {
		this.plan_nam = plan_nam;
	}

	public String getIsrl_code() {
		return isrl_code;
	}

	public void setIsrl_code(String isrl_code) {
		this.isrl_code = isrl_code;
	}

	public String getGa_id() {
		return ga_id;
	}

	public void setGa_id(String ga_id) {
		this.ga_id = ga_id;
	}

	@Override
	public String toString() {
		return "Product [ev_id=" + ev_id + ", plan_nam=" + plan_nam + ", isrl_code=" + isrl_code + ", ga_id=" + ga_id
				+ "]";
	}
	
    
    
}


