package com.itmuch.cloud.data.Wap.permission.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.data.Wap.permission.entity.WapUser;

@Mapper
public interface WapUserDao {
   /**
    *    根据姓名查询
    * @param username
    * @return
    */
	WapUser  findByUserName(@Param("username")String username);
	/**
	 * 新增
	 * @param param
	 * @return
	 */
	Integer  insertWapUser(@Param("param")WapUser  param);
	/**
	 * 根据openId查询
	 * @param openId
	 * @return
	 */
	WapUser  findByOpenId(@Param("openId")String openId);
	/**
	 * 根据id查询用户
	 * @param wapId
	 * @return
	 */
	WapUser  findById(@Param("wapId")Integer wapId);
	
	
	/**
	 * 保存用户基本信息
	 * @param param
	 * @return
	 */
	Integer  updateUserBasicMessage(@Param("param")WapUser  param);
	
}
