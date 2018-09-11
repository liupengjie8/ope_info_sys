package com.itmuch.cloud.authorities.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmuch.cloud.authorities.dao.OperoleDao;
import com.itmuch.cloud.authorities.dao.OpeuserDao;
import com.itmuch.cloud.authorities.entity.Operole;
import com.itmuch.cloud.authorities.entity.Opeuser;

@Api(tags="用户管理")
@Controller
@RequestMapping(value = "/com/user/")
@CrossOrigin
public class OpeuserController {

	@Autowired
	private  OpeuserDao  userDao;
	@Autowired
	private OperoleDao  roleDao;
	 @Autowired
	    protected BCryptPasswordEncoder bCryptPasswordEncoder;
	/**
	 * 查询全部
	 * @param name
	 * @return
	 */
	@ApiOperation(value="查询全部用户", notes="total:总的行数  id userName:用户名称，"
			+ "passWord:密码 ")
	@ApiImplicitParam(name = "name", value = "角色名称", required = false, dataType = "String")
 @ResponseBody
 @GetMapping("getUserList")
 public Map<String,Object>  getRoleList(String name,Integer page,Integer size){
	 Opeuser  param=new  Opeuser();
	 String zt="true";
	 Map<String,Object>  map=new HashMap<String, Object>(); 
	 if(name!=null && !"".equals(name)){
		 param.setUserName("%"+name+"%");
	 }
	 if(page==null ){
		 page=1;
	 }
	if(size==null){
		size=10;
	}
	page=(page-1)*size;
	param.setPage(page);
	param.setSize(size);
	 try {
		 List<Opeuser>  userList=userDao.getAllUser(param);
		 List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();
    for (Opeuser ope:userList) {
   	 Map<String,Object>  dataMap=new HashMap<String, Object>(); 
   	dataMap.put("userName",ope.getUserName());
	dataMap.put("passWord",ope.getPassWord());
	dataMap.put("id",ope.getId());
	dataList.add(dataMap);
	}
		 Integer  total=userDao.getTotal(param);
		 map.put("total",total);
		map.put("data",dataList); 
		 
	} catch (Exception e) {
		e.printStackTrace();
		zt="false";	
	}
	
	 
	 map.put("zt",zt);
	 
	 return map;
	 
 }
	
	/**
	 * 根据id查询角色
	 * @param name
	 * @return
	 */
	@ApiOperation(value="根据id查询用户", notes="total:总的行数  id userName:用户名称，"
			+ "passWord:密码 ")
	@ApiImplicitParam(paramType="query",name = "id", value = "id", required = true, dataType = "Integer")
 @ResponseBody
 @GetMapping("getUserById")
 public Map<String,Object>  getUserById(Integer  id){
	 String zt="true";
	 Map<String,Object>  map=new HashMap<String, Object>(); 
	try {
		if(id!=null){
			 Opeuser  ope=userDao.getUserById(id);
	   
	   	 Map<String,Object>  dataMap=new HashMap<String, Object>(); 
	   	dataMap.put("userName",ope.getUserName());
		dataMap.put("passWord",ope.getPassWord());
		dataMap.put("id",ope.getId());
			map.put("data",dataMap); 
			
			
			
		}else{
			zt="false";
		}
		
		
		
	} catch (Exception e) {
		e.printStackTrace();
		zt="false";
	}
	return map;
	}
	
	
	  /**
	    * 新增角色
	    * @param ope
	    * @return
	    */
		@ApiOperation(value="新增用户", notes="zt:true,false")
		@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name = "id", value = "id", required =false, dataType = "Integer"),
		@ApiImplicitParam(paramType="query",name = "userName", value = "用户名称", required =true, dataType = "String"),
		@ApiImplicitParam(paramType="query",name = "passWord", value = "密码", required =false, dataType = "String")
		})
	 @ResponseBody
	 @PostMapping("insertUser")	
public  Map<String,Object>	insertUser(Opeuser user){
			Map<String,Object>  map=new HashMap<String, Object>(); 
			String zt="true";
	  try {
		if(user.getUserName()==null){
			user.setUserName("");
		}
		 if(user.getPassWord()==null){
			 user.setPassWord("");
		 } 
        user.setPassWord(bCryptPasswordEncoder.encode(user.getPassWord()));
		 if(user.getId()!=null && user.getId().intValue()!=0){
			 userDao.updateUser(user);
			 
		 }else{
			 userDao.insertUser(user);
		 } 
		 
		 
		 
		 
	} catch (Exception e) {
  e.printStackTrace();
  zt="false";
	
	}
	
	  map.put("zt",zt);
		return map;
		}
		
		
		
		  /**
		   * 根据id删除
		   * @param id
		   * @return
		   */
			@ApiOperation(value="根据id删除用户")
			@ApiImplicitParam(paramType="query",name = "ids", value = "ids", required =true, dataType = "String")
		 @ResponseBody
		 @PostMapping("deleteUserById") 	
		public  Map<String,Object>  deleteUserById(String  ids){
				Map<String,Object>  map=new HashMap<String, Object>(); 
				String zt="true";
				try{
					if(ids!=null && !"".equals(ids)&& !"undefined".equals(ids)){
						ids=ids.substring(0,ids.length()-1);
						String[] arr=ids.split(",");
						for (String str:arr) {
                         userDao.deleteUser(Integer.valueOf(str));
						}
						
				}else{
					zt="false";
				}
				}catch (Exception e) {
					e.printStackTrace();
					zt="false";
				}
		        map.put("zt",zt);
				return map;
				
				
			}
			
			
@ApiOperation(value="查询全部角色", notes=" id descript:描述，"
					+ "name:代表 角色名称 ")			
@ResponseBody
@PostMapping("getAllRole")
public  Map<String,Object>  getAllRole(){
	Map<String,Object>  map=new HashMap<String, Object>(); 
	String zt="true";
	try {
		List<Operole> roleList=roleDao.findAllRole();
		List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();
		for (Operole role:roleList) {
			Map<String,Object>  dataMap=new  HashMap<String, Object>();
			dataMap.put("id",role.getId());
			dataMap.put("label",role.getName());
			dataList.add(dataMap);
		}
		map.put("data",dataList);
		
		
	} catch (Exception e) {
        e.printStackTrace();
        zt="false";
	
	}
	
	
	  map.put("zt",zt);
		return map;
}
	
@ApiOperation(value="新增用户与角色之间的关系")
	@ApiImplicitParams({
	@ApiImplicitParam(paramType="query",name = "uid", value = "用户id", required =true, dataType = "Integer"),
	@ApiImplicitParam(paramType="query",name = "ids", value = "ids", required =true, dataType = "String")
	})
@ResponseBody
@PostMapping("insertUserAndRole")
public Map<String,Object> insertUserAndRole(Integer uid,String ids){
	  Map<String,Object>  map=new HashMap<String, Object>();
      String zt="true";
	try {
		
	
	if(uid!=null && !"".equals(ids) && ids!=null && !"undefined".equals(ids)){
		userDao.deleteUserAndRole(uid);
		ids=ids.substring(0,ids.length()-1);
		String[] arr=ids.split(",");
		for (String str:arr) {
			userDao.insertUserAndRole(uid,Integer.parseInt(str));
			
		}
		
		
	}
	} catch (Exception e) {
		e.printStackTrace();
		zt="false";
	}
	map.put("zt",zt);
	return map;
	
}
@ApiOperation(value="查询用户与角色之间的关系")
@ApiImplicitParam(paramType="query",name = "uid", value = "用户id", required =true, dataType = "Integer")
@ResponseBody
@PostMapping("getRoleAndUserByUid")
public Map<String,Object>  getRoleAndUserByUid(Integer uid){
	  Map<String,Object>  map=new HashMap<String, Object>();
      String zt="true";
	 try {
		 if(uid!=null){
			List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();

		List<Operole>  roleList=roleDao.getRoleAndUserByUid(uid);
		 for (Operole role:roleList) {
			 Map<String,Object>  dataMap=new  HashMap<String, Object>();
			dataMap.put("id",role.getId());
			dataMap.put("label",role.getName());
			dataList.add(dataMap);
		}
		 map.put("data",dataList);
		 }else{
			zt="false"; 
		 }
	} catch (Exception e) {
		e.printStackTrace();
		zt="false";
	}
	
	map.put("zt",zt);
	return map;

}


}
