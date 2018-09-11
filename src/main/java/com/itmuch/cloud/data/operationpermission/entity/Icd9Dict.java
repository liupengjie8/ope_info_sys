package com.itmuch.cloud.data.operationpermission.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
public class Icd9Dict {

	

private Integer id;
private String operationCode;//icd9 手术名称
private  String  operationName;//icd9 手术编码
private String operationScale;//icd9 手术等级
private  Integer stdIndicator;//标准标志
private Integer approved;//启用备注
private  String createDate;//创建日期
private   String  inputCode;//输入码
private  String  operationLevel;//手术级别
private  Integer  usedIndicator;//用户标注
private  String  comment;//icd9-cm3解释
private  String  flag;//是否有效
private  String  isMatched;//是否匹配
private  Integer page;
private  Integer  size;
private  Integer  bz;  //0 代表启用

public Integer getBz() {
	return bz;
}
public void setBz(Integer bz) {
	this.bz = bz;
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
public String getOperationCode() {
	return operationCode;
}
public void setOperationCode(String operationCode) {
	this.operationCode = operationCode;
}
public String getOperationName() {
	return operationName;
}
public void setOperationName(String operationName) {
	this.operationName = operationName;
}
public String getOperationScale() {
	return operationScale;
}
public void setOperationScale(String operationScale) {
	this.operationScale = operationScale;
}
public Integer getStdIndicator() {
	return stdIndicator;
}
public void setStdIndicator(Integer stdIndicator) {
	this.stdIndicator = stdIndicator;
}
public Integer getApproved() {
	return approved;
}
public void setApproved(Integer approved) {
	this.approved = approved;
}
public String getCreateDate() {
	return createDate;
}
public void setCreateDate(String createDate) {
	this.createDate = createDate;
}
public String getInputCode() {
	return inputCode;
}
public void setInputCode(String inputCode) {
	this.inputCode = inputCode;
}
public String getOperationLevel() {
	return operationLevel;
}
public void setOperationLevel(String operationLevel) {
	this.operationLevel = operationLevel;
}
public Integer getUsedIndicator() {
	return usedIndicator;
}
public void setUsedIndicator(Integer usedIndicator) {
	this.usedIndicator = usedIndicator;
}
public String getComment() {
	return comment;
}
public void setComment(String comment) {
	this.comment = comment;
}
public String getFlag() {
	return flag;
}
public void setFlag(String flag) {
	this.flag = flag;
}
public String getIsMatched() {
	return isMatched;
}
public void setIsMatched(String isMatched) {
	this.isMatched = isMatched;
}


	
}
