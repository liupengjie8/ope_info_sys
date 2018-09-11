package com.itmuch.cloud.authorities.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmuch.cloud.authorities.dao.OpepermissionDao;
import com.itmuch.cloud.authorities.entity.Opepermission;

@Api(tags="系统管理")
@Controller
@RequestMapping(value = "/com/sys/")
@CrossOrigin
public class SystemController {
	@Autowired
	private OpepermissionDao  perDao;
	
	@ApiOperation(value="查询用户与角色之间的关系")
	@ResponseBody
	@PostMapping("getPermissionByUser")
	public  Map<String,Object> getPermissionByUser(){
	
		String  str = (String) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		if(str!=null && !"".equals(str)){
            String split = str.split("-")[1];
            split=split.replace("[","");
            split=split.replace("]","");
            if(!"".equals(split)){
            	List<Opepermission> perList=perDao.getPermissionByUser(Integer.parseInt(split),0);
            }
		}
		
		 Map<String,Object>  map=new HashMap<String, Object>(); 
	List<Opepermission> perList=perDao.findPermissionBylevel(0);
	List<Map<String,Object>> chrenList=new ArrayList<Map<String,Object>>();
	List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
     for (Opepermission ope:perList) {
		 Map<String,Object>  dataMap=new HashMap<String, Object>(); 
		 dataMap.put("name",ope.getName());
		 dataMap.put("level",ope.getLevel());
		 dataMap.put("url",ope.getUrl());
		 dataMap.put("children",chrenList);
		 dataList.add(dataMap);
    	 
	}
		 map.put("data",dataList);
		 map.put("zt","true");
		 
		 return map;
		
	}
	
	
	
}
