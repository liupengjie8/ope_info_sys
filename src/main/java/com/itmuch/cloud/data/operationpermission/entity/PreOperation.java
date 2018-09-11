package com.itmuch.cloud.data.operationpermission.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.Query;



public class PreOperation {


private Long id;
private  String  operationName;//手术名称
private  String  operationCode;//手术编码
private  String  operationLevel;//手术等级
private  String  createDate;//创建日期
private  String  creator;//创建者
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
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getOperationName() {
	return operationName;
}
public void setOperationName(String operationName) {
	this.operationName = operationName;
}
public String getOperationCode() {
	return operationCode;
}
public void setOperationCode(String operationCode) {
	this.operationCode = operationCode;
}

public String getOperationLevel() {
	return operationLevel;
}
public void setOperationLevel(String operationLevel) {
	this.operationLevel = operationLevel;
}
public String getCreateDate() {
	return createDate;
}
public void setCreateDate(String createDate) {
	this.createDate = createDate;
}
public String getCreator() {
	return creator;
}
public void setCreator(String creator) {
	this.creator = creator;
}


	
}
