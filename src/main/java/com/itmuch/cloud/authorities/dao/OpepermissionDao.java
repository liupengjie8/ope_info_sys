package com.itmuch.cloud.authorities.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.authorities.entity.Opepermission;

@Mapper
public interface OpepermissionDao {

	/**
	 * 根据用户id查询资源
	 * @param userId
	 * @return
	 */
	List<Opepermission>  findByAdminUserId(@Param("userId")Integer userId);
	/**
	 * 查询全部
	 * @return
	 */
	List<Opepermission>  findAll();
	
	/**
	 * 新增
	 * @param param
	 * @return
	 */
	Integer  addNewPermission(@Param("param")Opepermission param);
	/**
	 * 更具id查询
	 * @param pid
	 * @return
	 */
	Opepermission  findPermissionById(@Param("pid")Integer pid);
	/**
	 * 根据等级查询
	 * @param level
	 * @return
	 */
	List<Opepermission> findPermissionBylevel(@Param("level")Integer level);
	
	/**
	 * 更新
	 * @param param
	 * @return
	 */
	Integer   updatePermission(@Param("param")Opepermission param);
	/**
	 * 查询全部
	 * @param param
	 * @return
	 */
	List<Opepermission>  findAllByPermission(@Param("param")Opepermission param);
	
	/**
	 * 根据id删除
	 * @param pid
	 * @return
	 */
	Integer   deletePermissionById(@Param("pid")Integer pid);
	/**
	 * 查询总的行数
	 * @param param
	 * @return
	 */
	Integer  getPermissionTotal(@Param("param")Opepermission param);
	
	/**
	 * 查询子菜单
	 * @param level
	 * @param pid
	 * @return
	 */
	List<Opepermission>  findChildPermission(@Param("level")Integer level,@Param("pid")Integer pid);
	/**
	 * 查询角色与菜单之间的关系
	 * @param rid
	 * @param pid
	 * @return
	 */
	List<Opepermission>  getPermissionAndRole(@Param("rid")Integer rid,@Param("pid")Integer pid);
	/**
	 * 更具角色获取树的叶子节点
	 * @param rid
	 * @return
	 */
	List<Opepermission> findPermissionAndRole(@Param("rid")Integer rid);
   
	
	List<Opepermission>  getPermissionByUser(@Param("uid")Integer uid,@Param("level")Integer level);
} 
