package com.itmuch.cloud.authorities.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.authorities.entity.Opeuser;

@Mapper
public interface OpeuserDao {
	/**
	 * 根据用户名称查询用户
	 * @param username
	 * @return
	 */
	Opeuser findByUserName(@Param("username")String username);
	/**
	 * 查询
	 * @param param
	 * @return
	 */
	List<Opeuser>  getAllUser(@Param("param")Opeuser param);
	
	/**
	 * 根据id查询
	 * @param uid
	 * @return
	 */
	Opeuser  getUserById(@Param("uid")Integer uid);
	
	/**
	 * 新增
	 * @param param
	 * @return
	 */
	Integer  insertUser(@Param("param")Opeuser param);
	/**
	 * 更新
	 * @param param
	 * @return
	 */
	Integer   updateUser(@Param("param")Opeuser param);
	
	/**
	 * 删除用户
	 * @param uid
	 * @return
	 */
	Integer  deleteUser(@Param("uid")Integer uid);
	
	/**
	 * 查询总的记录数
	 * @param param
	 * @return
	 */
	Integer   getTotal(@Param("param")Opeuser param);
	
	/**
	 * 根据id删除
	 * @param uid
	 * @return
	 */
	Integer  deleteUserAndRole(@Param("uid")Integer uid);
	
	/**
	 * 新增角色与用户之间的id
	 * @param uid
	 * @param rid
	 * @return
	 */
	Integer  insertUserAndRole(@Param("uid")Integer uid,@Param("rid")Integer rid);
	/**
	 *  根据姓名和密码查询用户
	 * @param username
	 * @param password
	 * @return
	 */
	Opeuser   findUserByNameAndPassWord(@Param("username")String username,@Param("password")String password);
}
