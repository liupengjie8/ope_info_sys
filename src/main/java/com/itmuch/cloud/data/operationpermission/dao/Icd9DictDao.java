package com.itmuch.cloud.data.operationpermission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;


import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.data.operationpermission.entity.Icd9Dict;
import com.itmuch.cloud.data.operationpermission.entity.PreOperation;
@Mapper
public interface Icd9DictDao {
   
	/**
	 * 条件分页查询
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	List<Icd9Dict> findAllIcd9ByName(@Param("param")Icd9Dict  param);
	
	/**
	 *   查询总数
	 * @param param
	 * @return
	 */
	Integer  getTotal(@Param("param")Icd9Dict  param);
   /**
    * 根据id查询
    * @param id
    * @return
    */
	Icd9Dict  getIcd9ById(@Param("id")Integer id);
       
}
