package com.itmuch.cloud.data.operationpermission.entity;

public class LeaveMessage {

	private  Integer  id;
	private  Integer  evaluateId;//患者评估id
	private  String  messageDate;//留言时间
	private  String   message;//留言
	private  Integer  readState;//已读，未读 标志  0 未读 1 已读
	public Integer getReadState() {
		return readState;
	}
	public void setReadState(Integer readState) {
		this.readState = readState;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getEvaluateId() {
		return evaluateId;
	}
	public void setEvaluateId(Integer evaluateId) {
		this.evaluateId = evaluateId;
	}
	public String getMessageDate() {
		return messageDate;
	}
	public void setMessageDate(String messageDate) {
		this.messageDate = messageDate;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
