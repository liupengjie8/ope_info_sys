package com.itmuch.cloud.data.operationpermission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.data.operationpermission.entity.PreOperation;

@Mapper
public interface OperationDao {
   
	/**
	 * 条件分页查询
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	List<PreOperation> findOperationByName(@Param("param")PreOperation  param);
	
	/**
	 *   查询总数
	 * @param param
	 * @return
	 */
	Integer  getTotal(@Param("param")PreOperation  param);

	/**
	 * 新增
	 * @param param
	 * @return
	 */
     Integer  insertOperation(@Param("param")PreOperation  param);  
     
     /**
      * 删除手术根据id
      * @param id
      * @return
      */
     Integer  deleteOperationById(@Param("id")Integer id);
     
     /**
      * 根据编码验证
      * @param code
      * @return
      */
     Integer  getOperationByCode(@Param("code")String code);
     /**
      * 根据id查询
      * @param id
      * @return
      */
     PreOperation  getOperationById(@Param("id")Integer id);
}
