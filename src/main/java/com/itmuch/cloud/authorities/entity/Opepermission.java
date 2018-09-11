package com.itmuch.cloud.authorities.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
@ApiModel("菜单")
public class Opepermission {
	@ApiModelProperty("id")
	private  Integer id;
	@ApiModelProperty("菜单名称")
	private  String  name;//名称
	@ApiModelProperty("菜单描述")
	private  String  descript;//描述
	@ApiModelProperty("菜单地址")
	private  String   url;//访问地址
	@ApiModelProperty("菜单父级id")
	private  Integer  pid;//父级id
	@ApiModelProperty("菜单等级 0 一级 1 二级 ...")
	private   Integer  level;//等级
	@ApiModelProperty("父级菜单名称  没有显示'无'")
	private   String pName;//父级菜单名称
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
	public String getpName() {
		return pName;
	}
	public void setpName(String pName) {
		this.pName = pName;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescript() {
		return descript;
	}
	public void setDescript(String descript) {
		this.descript = descript;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public Integer getLevel() {
		return level;
	}
	public void setLevel(Integer level) {
		this.level = level;
	}
	
}
