package com.itmuch.cloud.data.operationpermission.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.data.operationpermission.entity.OutHospitalEvaluate;

@Mapper
public interface OutHospitalEvaluateDao {
   /**
    * 根据手术安排的id查询
    * @param manId
    * @return
    */
	OutHospitalEvaluate getOutHospitalByManngerId(@Param("manId")Integer manId);
	 
	/**
	 * 新增
	 * @param param
	 * @return
	 */
	Integer  insertOutHospitalEvaluate(@Param("param")OutHospitalEvaluate param);
	
	/**
	 * 更新
	 * @param param
	 * @return
	 */
	Integer   updateOutHospitalEvaluate(@Param("param")OutHospitalEvaluate param);
}
