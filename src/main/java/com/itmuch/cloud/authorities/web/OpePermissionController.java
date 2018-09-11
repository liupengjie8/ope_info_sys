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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmuch.cloud.authorities.dao.OpepermissionDao;
import com.itmuch.cloud.authorities.entity.Opepermission;
import com.itmuch.cloud.data.operationpermission.entity.PreOperation;
@Api(tags="菜单管理")
@Controller
@RequestMapping(value = "/com/permission/")
@CrossOrigin
public class OpePermissionController {

	@Autowired
	private OpepermissionDao  perDao;
	/**
	 * 查询全部
	 * @param name
	 * @return
	 */
	@ApiOperation(value="查询全部菜单", notes=" total:总的记录数 id descript:描述，"
			+ "name:代表 菜单名称 url：访问地址 ，pid：父菜单id,pName:父级菜单名称，level:菜单等级 0代表一级 1..")
	@ApiImplicitParams({@ApiImplicitParam(paramType="query",name = "name", value = "菜单名称", required = false, dataType = "String"),
	@ApiImplicitParam(paramType="query",name = "page", value = "页数", required = false, dataType = "Integer"),
	@ApiImplicitParam(paramType="query",name = "size", value = "每页的行数", required = false, dataType = "Integer")
	})
 @ResponseBody
 @PostMapping("getPermissionList")
 public Map<String,Object>  getPermissionList(String name,Integer page,Integer size){
	 Opepermission  param=new Opepermission();
	 String zt="true";
	 Map<String,Object>  map=new HashMap<String, Object>(); 
	 if(name!=null && !"".equals(name)){
		 param.setName("%"+name+"%");
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
		 List<Opepermission>  opeList=perDao.findAllByPermission(param);
		 List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();
    for (Opepermission ope:opeList) {
   	 Map<String,Object>  dataMap=new HashMap<String, Object>(); 
   	dataMap.put("name",ope.getName());
	dataMap.put("descript",ope.getDescript());
	dataMap.put("url",ope.getUrl());
	dataMap.put("pid",ope.getPid());
	dataMap.put("pName",ope.getpName());
	dataMap.put("level",ope.getLevel());
	dataMap.put("id",ope.getId());
	dataList.add(dataMap);
	}
		 Integer row=perDao.getPermissionTotal(param);
		 map.put("total",row);
		map.put("data",dataList); 
		 
	} catch (Exception e) {
		e.printStackTrace();
		zt="false";	
	}
	
	 
	 map.put("zt",zt);
	 
	 return map;
	 
 }
 /**
  * 根据id查询
  * @param id
  * @return
  */
	@ApiOperation(value="根据id查询菜单", notes="id descript:描述,parent:父级菜单"
			+ "name:代表 菜单名称 url：访问地址 ，pid：父菜单id,pName:父级菜单名称，level:菜单等级 0代表一级 1..")
	@ApiImplicitParam(paramType="query",name = "id", value = "id", required =true, dataType = "Integer")
 @ResponseBody
 @PostMapping("getPermissionById") 
public  Map<String,Object>  getPermissionById(Integer  id){
		Map<String,Object>  map=new HashMap<String, Object>(); 
  		 List<Map<String,Object>>  parentList=new ArrayList<Map<String,Object>>();

		String zt="true";
		try {
			if(id!=null){
			   	 Map<String,Object>  dataMap=new HashMap<String, Object>(); 
              	Opepermission ope=perDao.findPermissionById(id);
              	if(ope!=null){
              	   	dataMap.put("name",ope.getName());
              		dataMap.put("descript",ope.getDescript());
              		dataMap.put("url",ope.getUrl());
              		dataMap.put("pid",ope.getPid());
              		dataMap.put("pName",ope.getpName());
              		dataMap.put("level",ope.getLevel());
              		dataMap.put("id",ope.getId());
              		if(ope.getLevel()!=null && ope.getLevel()!=0){
              		 List<Opepermission>  opeList=perDao.findPermissionBylevel(ope.getLevel()-1);
                for (Opepermission parent:opeList) {
               	 Map<String,Object>  parentMap=new HashMap<String, Object>(); 
               	parentMap.put("name",parent.getName());
               	parentMap.put("id",parent.getId());
            	parentList.add(parentMap);
            	}
            		 
               
              		}
              		 
            		 
              	}
				
				map.put("data",dataMap);
				map.put("parent",parentList); 
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
   /**
    * 新增
    * @param ope
    * @return
    */
	@ApiOperation(value="新增菜单", notes="zt:true,false")
	@ApiImplicitParams({
	@ApiImplicitParam(paramType="query",name = "id", value = "id", required =false, dataType = "Integer"),
	@ApiImplicitParam(paramType="query",name = "name", value = "菜单名称", required =true, dataType = "String"),
	@ApiImplicitParam(paramType="query",name = "descript", value = "菜单描述", required =false, dataType = "String"),
	@ApiImplicitParam(paramType="query",name = "url", value = " 访问地址", required =false, dataType = "String"),
	@ApiImplicitParam(paramType="query",name = "pid", value = "父级id 没有传 0", required =true, dataType = "Integer"),
	@ApiImplicitParam(paramType="query",name = "level", value = "菜单等级 0 一级 1 二级 ..", required =true, dataType = "Integer")

	})
 @ResponseBody
 @PostMapping("insertPermission")
public  Map<String,Object>  insertPermission(Opepermission ope){
		Map<String,Object>  map=new HashMap<String, Object>(); 
		String zt="true";
		try {
			if(ope.getDescript()==null){
				ope.setDescript("");
			}
			if(ope.getLevel()==null){
				ope.setLevel(0);
				
			}
			if(ope.getName()==null){
				ope.setName("");
			}
			if(ope.getUrl()==null){
				ope.setUrl("");
				
			}
			if(ope.getPid()==null){
				ope.setPid(0);
			}
			if(!"".equals(ope.getName())){
			if(ope.getId()!=null && ope.getId()!=0){
				
				
				perDao.updatePermission(ope);
				
			}else{
				perDao.addNewPermission(ope);
			}
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
  /**
   * 根据id删除
   * @param id
   * @return
   */
	@ApiOperation(value="根据id删除菜单")
	@ApiImplicitParam(paramType="query",name = "ids", value = "ids", required =true, dataType = "String")
 @ResponseBody
 @PostMapping("deletePermissionById") 	
public  Map<String,Object>  deletePermissionById(String  ids){
		Map<String,Object>  map=new HashMap<String, Object>(); 
		String zt="true";
		try {
			
			if(ids!=null && !"".equals(ids)&& !"undefined".equals(ids)){
				ids=ids.substring(0,ids.length()-1);
				String[] arr=ids.split(",");
				for (String str:arr) {
					perDao.deletePermissionById(Integer.valueOf(str));
				}
				
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
 
	
	
	/**
	 * 根据level查询菜单
	 * @param level
	 * @return
	 */
	@ApiOperation(value="根据菜单等级查询父级菜单", notes="id descript:描述，"
			+ "name:代表 菜单名称 url：访问地址 ，pid：父菜单id,pName:父级菜单名称，level:菜单等级 0代表一级 1..")
	@ApiImplicitParam(paramType="query",name = "level", value = "菜单等级", required = false, dataType = "Integer")
 @ResponseBody
 @GetMapping("getPermissionListByLevel")
 public Map<String,Object>  getPermissionListByLevel(Integer level){
	 String zt="true";
	 Map<String,Object>  map=new HashMap<String, Object>(); 
	if(level!=null && level.intValue()!=0){
		
	
	 try {
		 List<Opepermission>  opeList=perDao.findPermissionBylevel(level-1);
		 List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();
    for (Opepermission ope:opeList) {
   	 Map<String,Object>  dataMap=new HashMap<String, Object>(); 
   	dataMap.put("name",ope.getName());
	dataMap.put("descript",ope.getDescript());
	dataMap.put("url",ope.getUrl());
	dataMap.put("pid",ope.getPid());
	dataMap.put("pName",ope.getpName());
	dataMap.put("level",ope.getLevel());
	dataMap.put("id",ope.getId());
	dataList.add(dataMap);
	}
		 
		map.put("data",dataList); 
		 
	} catch (Exception e) {
		e.printStackTrace();
		zt="false";	
	}
	}
	 
	 map.put("zt",zt);
	 
	 return map;
	 
 }
}
