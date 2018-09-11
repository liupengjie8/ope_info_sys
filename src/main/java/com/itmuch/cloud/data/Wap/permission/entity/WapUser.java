package com.itmuch.cloud.data.Wap.permission.entity;

public class WapUser {

	private  Integer  id;
	private  String  userName;//用户名
	private  String  passWord;//密码
	private  Integer  registerSource;//注册源  0 微信用户注册 1 其他注册
	private  Integer   isQua;//是否实名认证 1 是 0否
	private  String    reallyName;//真实姓名
	private  String    telphone;//电话号码
	private  String    patientId;//病人id
	private  String    openId;//微信的唯一标识
	private   String  headimgurl;//微信头像地址
	public String getHeadimgurl() {
		return headimgurl;
	}
	public void setHeadimgurl(String headimgurl) {
		this.headimgurl = headimgurl;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public Integer getRegisterSource() {
		return registerSource;
	}
	public void setRegisterSource(Integer registerSource) {
		this.registerSource = registerSource;
	}
	public Integer getIsQua() {
		return isQua;
	}
	public void setIsQua(Integer isQua) {
		this.isQua = isQua;
	}
	public String getReallyName() {
		return reallyName;
	}
	public void setReallyName(String reallyName) {
		this.reallyName = reallyName;
	}
	public String getTelphone() {
		return telphone;
	}
	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}
	public String getPatientId() {
		return patientId;
	}
	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}
	
	
}
