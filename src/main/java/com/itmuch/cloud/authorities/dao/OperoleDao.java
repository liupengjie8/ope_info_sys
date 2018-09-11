package com.itmuch.cloud.authorities.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.authorities.entity.Operole;

@Mapper
public interface OperoleDao {
    /**
     * 查询全部角色
     * @param param
     * @return
     */
	List<Operole>  getAllRoleByName(@Param("param")Operole param);
	/**
	 * 查询全部
	 * @return
	 */
	List<Operole>   findAllRole();
	/**
	 * 新增一个角色
	 * @param param
	 * @return
	 */
	Integer  insertRole(@Param("param")Operole param);
	/**
	 * 更新一个角色
	 * @param param
	 * @return
	 */
	Integer  updateRole(@Param("param")Operole param);
	/**
	 * 根据id删除角色
	 * @param rid
	 * @return
	 */
	Integer  deleteRoleById(@Param("rid")Integer rid);
	
	/**
	 * 根据id查询角色
	 * @param rid
	 * @return
	 */
   Operole  getRoleById(@Param("rid")Integer rid);
   
   /**
    * 保存角色总的记录数
    * @param param
    * @return
    */
   Integer   getRoleTotal(@Param("param")Operole param);
   /**
    * 保存角色与菜单之间的关系
    * 
    * @param rid
    * @param pid
    * @return
    */
   Integer   insertRoleAndPermission(@Param("rid")Integer rid,@Param("pid")Integer pid);
   
   /**
    * 删除
    * @param rid
    * @return
    */
   Integer  deleteRoleAndPermissionByRid(@Param("rid")Integer rid);
   
   
   /**
    * 获取用户与角色之间的关系
    * @param uid
    * @return
    */
   List<Operole>  getRoleAndUserByUid(@Param("uid")Integer uid);
    
}
