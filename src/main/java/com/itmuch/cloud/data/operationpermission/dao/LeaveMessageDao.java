package com.itmuch.cloud.data.operationpermission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.data.operationpermission.entity.LeaveMessage;

@Mapper
public interface LeaveMessageDao {

	/**
	 * 根据评估id查询评论信息
	 * @param id
	 * @return
	 */
	List<LeaveMessage>  findLeaveMessageByEvaluateId(@Param("eId")Integer eId,@Param("state")Integer state);
	
	/**
	 * 新增留言
	 * @param param
	 * @return
	 */
	Integer   insertLeaveMessage(@Param("param")LeaveMessage param);
	
	List<LeaveMessage>  getLeaveMessageByMessage(@Param("param")LeaveMessage param);
	
	Integer   updateMessageByMessage(@Param("message")String message);
	
	Integer   getReadCount(@Param("eId")Integer eId); 
	List<LeaveMessage>   findLeaveMessage(@Param("eId")Integer eId);
	
	Integer   updateReadState(@Param("eId")Integer eId);
	
	LeaveMessage  getMessageById(@Param("mId")Integer mId);
	
}
