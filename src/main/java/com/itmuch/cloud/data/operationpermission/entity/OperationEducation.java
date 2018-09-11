package com.itmuch.cloud.data.operationpermission.entity;

public class OperationEducation {

	/**
	 * 宣教
	 */
	private  Integer  id;
	private  Integer  oId;//手术id
	private  String   illGuid;//疾病相关知识指导
	private  String   medicineGuid;//用药指导
	private  String   foodGuid;//饮食指导
	private String    mindGuid;//心里指导
	private  String   operationPlan;//术日安排
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getoId() {
		return oId;
	}
	public void setoId(Integer oId) {
		this.oId = oId;
	}
	public String getIllGuid() {
		return illGuid;
	}
	public void setIllGuid(String illGuid) {
		this.illGuid = illGuid;
	}
	public String getMedicineGuid() {
		return medicineGuid;
	}
	public void setMedicineGuid(String medicineGuid) {
		this.medicineGuid = medicineGuid;
	}
	public String getFoodGuid() {
		return foodGuid;
	}
	public void setFoodGuid(String foodGuid) {
		this.foodGuid = foodGuid;
	}
	public String getMindGuid() {
		return mindGuid;
	}
	public void setMindGuid(String mindGuid) {
		this.mindGuid = mindGuid;
	}
	public String getOperationPlan() {
		return operationPlan;
	}
	public void setOperationPlan(String operationPlan) {
		this.operationPlan = operationPlan;
	}

	
	
}
