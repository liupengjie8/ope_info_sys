package com.itmuch.cloud.data.operationpermission.entity;

public class OutHospitalEvaluate {

	private  Integer id;//
	private  Integer  ManagerId;
	private   String   templature;//体温
	private  String  pulse;//脉搏
	private   String  breath;//呼吸
	private   Integer  lifeStatus;// 生命体征  0 正常  1异常
	private   String   action;//活动能力
	private   Integer   pain;//疼痛指数
	private   Integer   vomit;//术后恶心呕吐  1 是 0否
	private   Integer   bleed;//切口出血  1是  0否
	private    Integer  outHospital;//准许离院  1是  0 否
	private    String   evaluateName;//评估人
	private   String     other;//其他
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getManagerId() {
		return ManagerId;
	}
	public void setManagerId(Integer managerId) {
		ManagerId = managerId;
	}

	public String getTemplature() {
		return templature;
	}
	public void setTemplature(String templature) {
		this.templature = templature;
	}
	public String getPulse() {
		return pulse;
	}
	public void setPulse(String pulse) {
		this.pulse = pulse;
	}
	public String getBreath() {
		return breath;
	}
	public void setBreath(String breath) {
		this.breath = breath;
	}
	public Integer getLifeStatus() {
		return lifeStatus;
	}
	public void setLifeStatus(Integer lifeStatus) {
		this.lifeStatus = lifeStatus;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public Integer getPain() {
		return pain;
	}
	public void setPain(Integer pain) {
		this.pain = pain;
	}
	public Integer getVomit() {
		return vomit;
	}
	public void setVomit(Integer vomit) {
		this.vomit = vomit;
	}
	public Integer getBleed() {
		return bleed;
	}
	public void setBleed(Integer bleed) {
		this.bleed = bleed;
	}
	public Integer getOutHospital() {
		return outHospital;
	}
	public void setOutHospital(Integer outHospital) {
		this.outHospital = outHospital;
	}
	public String getEvaluateName() {
		return evaluateName;
	}
	public void setEvaluateName(String evaluateName) {
		this.evaluateName = evaluateName;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
	
}
