package com.itmuch.cloud.data.operationpermission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.data.operationpermission.entity.PatientEvaluate;

@Mapper
public interface PatientEvaluateDao {
	/**
	 * 手术准入评估
	 * @param param
	 * @return
	 */
	List<PatientEvaluate>  findPatinetEvaluate(@Param("param")PatientEvaluate  param);
	
	/**
	 * 查询总数
	 * @param param
	 * @return
	 */
	Integer  getTotal(@Param("param")PatientEvaluate  param);
	/**
	 * 根据id查询
	 * @param oId
	 * @return
	 */
	PatientEvaluate  getPatinetEvaluateById(@Param("oId")Integer oId);
	/**
	 * 新增患者准入评估
	 * @param param
	 * @return
	 */
	Integer   insertPatinetEvaluate(@Param("param")PatientEvaluate  param);
	
	/**
	 * 更新
	 * @param param
	 * @return
	 */
	Integer   updatePatinetEvaluate(@Param("param")PatientEvaluate  param);
	
	
}
