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
import com.itmuch.cloud.authorities.dao.OperoleDao;
import com.itmuch.cloud.authorities.entity.Opepermission;
import com.itmuch.cloud.authorities.entity.Operole;

@Api(tags="角色管理")
@Controller
@RequestMapping(value = "/com/role/")
@CrossOrigin
public class OpeRoleController {

	@Autowired
	private OperoleDao  roleDao;
	@Autowired
	private OpepermissionDao  perDao;
	/**
	 * 查询全部
	 * @param name
	 * @return
	 */
	@ApiOperation(value="查询全部角色", notes="total:总的行数  id descript:描述，"
			+ "name:代表 角色名称 ")
	@ApiImplicitParam(name = "name", value = "角色名称", required = false, dataType = "String")
 @ResponseBody
 @GetMapping("getRoleList")
 public Map<String,Object>  getRoleList(String name,Integer page,Integer size){
	 Operole  param=new  Operole();
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
		 List<Operole>  roleList=roleDao.getAllRoleByName(param);
		 List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();
    for (Operole ope:roleList) {
   	 Map<String,Object>  dataMap=new HashMap<String, Object>(); 
   	dataMap.put("name",ope.getName());
	dataMap.put("descript",ope.getDescript());
	dataMap.put("id",ope.getId());
	dataList.add(dataMap);
	}
		 Integer  total=roleDao.getRoleTotal(param);
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
	@ApiOperation(value="根据id查询角色", notes="total:总的行数  id descript:描述，"
			+ "name:代表 角色名称 ")
	@ApiImplicitParam(paramType="query",name = "id", value = "id", required = false, dataType = "Integer")
 @ResponseBody
 @GetMapping("getRoleById")
 public Map<String,Object>  getRoleById(Integer  id){
	 String zt="true";
	 Map<String,Object>  map=new HashMap<String, Object>(); 
	
	 try {
		 if(id!=null){
		Operole  role=roleDao.getRoleById(id);
   	 Map<String,Object>  dataMap=new HashMap<String, Object>(); 
   	dataMap.put("name",role.getName());
	dataMap.put("descript",role.getDescript());
	dataMap.put("id",role.getId());
		map.put("data",dataMap);
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
	    * 新增角色
	    * @param ope
	    * @return
	    */
		@ApiOperation(value="新增角色", notes="zt:true,false")
		@ApiImplicitParams({
		@ApiImplicitParam(paramType="query",name = "id", value = "id", required =false, dataType = "Integer"),
		@ApiImplicitParam(paramType="query",name = "name", value = "角色名称", required =true, dataType = "String"),
		@ApiImplicitParam(paramType="query",name = "descript", value = "角色描述", required =false, dataType = "String")
		})
	 @ResponseBody
	 @PostMapping("insertRole")	
public  Map<String,Object>	insertRole(Operole role){
			Map<String,Object>  map=new HashMap<String, Object>(); 
			String zt="true";
			try {
				if(role.getName()==null){
					role.setName("");
				}
				if(role.getDescript()==null){
					role.setDescript("");
				}
				if(role.getId()!=null &&  role.getId()!=0){
					roleDao.updateRole(role);
					
				}else{
					roleDao.insertRole(role);
					
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
		 @PostMapping("deleteRoleById") 	
		public  Map<String,Object>  deleteRoleById(String  ids){
				Map<String,Object>  map=new HashMap<String, Object>(); 
				String zt="true";
				try{
					if(ids!=null && !"".equals(ids)&& !"undefined".equals(ids)){
						ids=ids.substring(0,ids.length()-1);
						String[] arr=ids.split(",");
						for (String str:arr) {
                       roleDao.deleteRoleById(Integer.valueOf(str));
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
			/**
			 * 查询菜单树
			 * @return
			 */
   @ApiOperation(value="查询菜单树", notes="zt:true,false id label ")		
	@ResponseBody
	@PostMapping("getPermissionTree")
	public  Map<String,Object> getPermissionTree(){
		Map<String,Object>  map=new HashMap<String, Object>();
         String zt="true";
         try {
			
		
		List<Opepermission> oneOpeList=perDao.findPermissionBylevel(0);
		List<Map<String,Object>>  oneList=new ArrayList<Map<String,Object>>();
		for (Opepermission one:oneOpeList) {
			Map<String,Object> oneMap=new HashMap<String, Object>();
            oneMap.put("id",one.getId());
			oneMap.put("label",one.getName());
			List<Opepermission> twoOpeList=perDao.findChildPermission(1,one.getId());
			if(twoOpeList.size()>0){
			List<Map<String,Object>>  twoList=new ArrayList<Map<String,Object>>();
			for (Opepermission two:twoOpeList) {
				Map<String,Object> twoMap=new HashMap<String, Object>();
				twoMap.put("id",two.getId());
				twoMap.put("label",two.getName());
				List<Opepermission> threeOpeList=perDao.findChildPermission(2,two.getId());
				if(threeOpeList.size()>0){
					List<Map<String,Object>>  threeList=new ArrayList<Map<String,Object>>();

					for (Opepermission three:threeOpeList) {
						Map<String,Object> threeMap=new HashMap<String, Object>();
						threeMap.put("id",three.getId());
						threeMap.put("label",three.getName());
						threeList.add(threeMap);
					}
					twoMap.put("children", threeList);
				}
				twoList.add(twoMap);
			}
			oneMap.put("children",twoList);
			}
			oneList.add(oneMap);
			
			
		}
		
		map.put("data",oneList);
         } catch (Exception e) {
 			zt="false";
 		}
         map.put("zt",zt);
		return map;
		
	}
			
   @ApiOperation(value="新增角色与菜单之间的关系")
	@ApiImplicitParams({
	@ApiImplicitParam(paramType="query",name = "rid", value = "ids", required =true, dataType = "Integer"),
	@ApiImplicitParam(paramType="query",name = "ids", value = "ids", required =true, dataType = "String")
	})
@ResponseBody
@PostMapping("insertRoleAndPermission") 	
   public  Map<String,Object> insertRoleAndPermission(Integer rid,String ids){
	  
	   
	   Map<String,Object>  map=new HashMap<String, Object>();
       String zt="true";
       try {
		if(rid!=null && !"".equals(rid) && rid!=null && !"undefined".equals(ids)){
			roleDao.deleteRoleAndPermissionByRid(rid);

			ids=ids.substring(0,ids.length()-1);
			String[] arr=ids.split(",");
			for (String str:arr) {
				List<Opepermission> opeList=perDao.getPermissionAndRole(rid,Integer.parseInt(str));
				if(opeList.size()<=0){
					Opepermission  ope=perDao.findPermissionById(Integer.parseInt(str));	
					if(ope.getLevel()==2){
						roleDao.insertRoleAndPermission(rid,Integer.parseInt(str));
						Opepermission  twoOpe=perDao.findPermissionById(ope.getPid());	
						List<Opepermission> twoList=perDao.getPermissionAndRole(rid,twoOpe.getId());
						if(twoList.size()<=0){
							roleDao.insertRoleAndPermission(rid,twoOpe.getId());
							Opepermission oneOpe=perDao.findPermissionById(twoOpe.getPid());	
							List<Opepermission> oneList=perDao.getPermissionAndRole(rid,oneOpe.getId()); 
							if(oneList.size()<=0){
								roleDao.insertRoleAndPermission(rid,oneOpe.getId());
							}
							
							
							
						}
						
					}else if(ope.getLevel()==1){
						
						Opepermission  twoOpe=perDao.findPermissionById(Integer.parseInt(str));	
						List<Opepermission> twoList=perDao.getPermissionAndRole(rid,twoOpe.getId());
						if(twoList.size()<=0){
							roleDao.insertRoleAndPermission(rid,twoOpe.getId());
							Opepermission oneOpe=perDao.findPermissionById(twoOpe.getPid());	
							List<Opepermission> oneList=perDao.getPermissionAndRole(rid,oneOpe.getId()); 
							if(oneList.size()<=0){
								roleDao.insertRoleAndPermission(rid,oneOpe.getId());
							}
							
							
							
						}	
						
						
					}else{
						if(opeList.size()<=0){
							
						
								roleDao.insertRoleAndPermission(rid,Integer.parseInt(str));
						}
					}
					
				}
				
				
			}
		}
    	   
    	   
    	   
	} catch (Exception e) {
		e.printStackTrace();
		zt="false";
	}
       map.put("zt",zt);
       return map;
   }
   @ApiOperation(value="获取角色与菜单之间的关系")
	@ApiImplicitParam(paramType="query",name = "rid", value = "", required =true, dataType = "Integer")
  @ResponseBody
  @PostMapping("getChildPermission")
  public Map<String,Object>  getChildPermission(Integer rid){
	   Map<String,Object>  map=new HashMap<String, Object>();
       String zt="true";
       try {
		if(rid!=null){
			List<Opepermission> opeList=perDao.findPermissionAndRole(rid);
			List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();
			for (Opepermission ope:opeList) {
				Map<String,Object> dataMap=new HashMap<String, Object>();
				dataMap.put("id",ope.getId());
				dataMap.put("label",ope.getName());
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
