package com.itmuch.cloud.data.operationpermission.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.data.operationpermission.entity.OperationEducation;

@Mapper
public interface OperationEducationDao {
    
	
	/**
	 *  根据id查询
	 * @param id
	 * @return
	 */
	OperationEducation findOperationEducationById(@Param("id")Integer id);
	/**
	 * 新增
	 * @param param
	 * @return
	 */
	Integer  insertOperationEducation(@Param("param")OperationEducation  param);
	/**
	 * 删除根据id
	 * @param id
	 * @return
	 */
	Integer  deleteOperationEducationById(@Param("id")Integer id);
	/**
	 * 更新
	 * @param param
	 * @return
	 */
	Integer  updateOperationEducation(@Param("param")OperationEducation  param);
	
}
