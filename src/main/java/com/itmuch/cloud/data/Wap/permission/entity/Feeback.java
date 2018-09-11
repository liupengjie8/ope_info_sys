package com.itmuch.cloud.data.Wap.permission.entity;

public class Feeback {

	private  Integer  id;
	private  Integer wapUserId;//wap 用户id
	private  String  message;//内容
	private  String  submitDate;//提交日期
	private  Integer page;
	private  Integer  size;
	private  String   sDate;
	private  String   eDate;
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
	public String getSubmitDate() {
		return submitDate;
	}
	public void setSubmitDate(String submitDate) {
		this.submitDate = submitDate;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getWapUserId() {
		return wapUserId;
	}
	public void setWapUserId(Integer wapUserId) {
		this.wapUserId = wapUserId;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
