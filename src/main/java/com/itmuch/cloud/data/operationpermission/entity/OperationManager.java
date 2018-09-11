package com.itmuch.cloud.data.operationpermission.entity;

public class OperationManager {

	
	private  Integer  id;
	private  String  chiefDoctor;//医生
	private  String  operationName;//手术名称
	private  Integer  operationId;//手术id
	private  String  patientId;//病人id
	private   String  patientName;//病人名称
	private  String   operationRoom;//手术室
	private   String  operationDate;//手术日期
	private  String   startDate;//手术开始时间
	private  String   endDate;//收速回结束时
	private  String   sDate;
	private  String   eDate;
	private  Integer page;
	private  Integer  size;
	private   Integer  evaluateState;// 评论状态 0 未评估 1 允许出院  2 不允许出院
	private  String evaluateTime;//评估时间
	public String getEvaluateTime() {
		return evaluateTime;
	}
	public void setEvaluateTime(String evaluateTime) {
		this.evaluateTime = evaluateTime;
	}
	public Integer getEvaluateState() {
		return evaluateState;
	}
	public void setEvaluateState(Integer evaluateState) {
		this.evaluateState = evaluateState;
	}
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
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getChiefDoctor() {
		return chiefDoctor;
	}
	public void setChiefDoctor(String chiefDoctor) {
		this.chiefDoctor = chiefDoctor;
	}
	public String getOperationName() {
		return operationName;
	}
	public void setOperationName(String operationName) {
		this.operationName = operationName;
	}
	public Integer getOperationId() {
		return operationId;
	}
	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	public String getPatientName() {
		return patientName;
	}
	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}
	public String getOperationRoom() {
		return operationRoom;
	}
	public void setOperationRoom(String operationRoom) {
		this.operationRoom = operationRoom;
	}
	public String getOperationDate() {
		return operationDate;
	}
	public void setOperationDate(String operationDate) {
		this.operationDate = operationDate;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getsDate() {
		return sDate;
	}
	public void setsDate(String sDate) {
		this.sDate = sDate;
	}
	public String geteDate() {
		return eDate;
	}
	public void seteDate(String eDate) {
		this.eDate = eDate;
	}
}
