package com.itmuch.cloud.data.operationpermission.entity;

public class PatientEvaluate {

	private  Integer  id;
	private  Integer managerId;//患者id
	private  String  evaluateTime;//评估时间
	private  Integer evaluateUser;//评估人
	private  String  evaluateUserName;
	private  Integer evaluateUserType;//评估人类型 0 医生  1 护士
	private  Integer  operationType;// 0 可日间手术  1 不可日间手术  2 等待其他结果
	private  String  operationName;//手术名称
	private  String  patientName;//姓名
	private  Integer  age;//年龄
	private  Integer page;
	private  Integer  size;
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getSize() {
		return size;
	}
	public void setSize(Integer size) {
		this.size = size;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getManagerId() {
		return managerId;
	}
	public void setManagerId(Integer managerId) {
		this.managerId = managerId;
	}
	public String getEvaluateTime() {
		return evaluateTime;
	}
	public void setEvaluateTime(String evaluateTime) {
		this.evaluateTime = evaluateTime;
	}
	public Integer getEvaluateUser() {
		return evaluateUser;
	}
	public void setEvaluateUser(Integer evaluateUser) {
		this.evaluateUser = evaluateUser;
	}
	public String getEvaluateUserName() {
		return evaluateUserName;
	}
	public void setEvaluateUserName(String evaluateUserName) {
		this.evaluateUserName = evaluateUserName;
	}
	public Integer getEvaluateUserType() {
		return evaluateUserType;
	}
	public void setEvaluateUserType(Integer evaluateUserType) {
		this.evaluateUserType = evaluateUserType;
	}
	public Integer getOperationType() {
		return operationType;
	}
	public void setOperationType(Integer operationType) {
		this.operationType = operationType;
	}
}
