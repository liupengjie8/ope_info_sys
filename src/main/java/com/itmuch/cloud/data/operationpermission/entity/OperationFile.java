package com.itmuch.cloud.data.operationpermission.entity;

public class OperationFile {

	private  Integer  id;
	private   String  fileName;//文件名称
	private   String  fileUrl;//文件地址
	private    Integer   fileType;//文件类型 0 宣教文件  1出院指导文件
	private  Integer operationId;//手术id
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	
	public Integer getFileType() {
		return fileType;
	}
	public void setFileType(Integer fileType) {
		this.fileType = fileType;
	}
	public Integer getOperationId() {
		return operationId;
	}
	public void setOperationId(Integer operationId) {
		this.operationId = operationId;
	}
	
	
}
