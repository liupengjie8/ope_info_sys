package com.itmuch.cloud.data.operationpermission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.data.operationpermission.entity.OperationManager;

@Mapper
public interface OperationManagerDao {
    /**
     * 根据手术安排
     * @param param
     * @return
     */
	List<OperationManager>  findOperationManagerByName(@Param("param")OperationManager param);
	/**
	 * 查询总的记录数
	 * @param param
	 * @return
	 */
	Integer  getTotal(@Param("param")OperationManager param);
	
	/**
	 * 
	 * 根据id 查询
	 * @param id
	 * @return
	 */
	OperationManager  getOperationManagerById(@Param("id")Integer id);
	/**
	 * 根据用户id查询
	 * @param patientId
	 * @return
	 */
	OperationManager  getOperationManagerByPatientId(@Param("patientId")String patientId);

	
	/**
	 * 根据离院评估查询术日安排
	 * @param evaluateState
	 * @return
	 */
	List<OperationManager>       getOperationManagerByEvaluateState(@Param("param")OperationManager param);
	
	/**
	 *  更新评估状态
	 * @param param
	 * @return
	 */
	Integer  updateEvaluateState(@Param("param")OperationManager param);
}
