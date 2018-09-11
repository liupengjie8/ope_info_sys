package com.itmuch.cloud.data.Wap.permission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.data.Wap.permission.entity.Feeback;

@Mapper
public interface FeebackDao {
  /**
   * 查询列表
   * @param param
   * @return
   */
	List<Feeback>  findMessageList(@Param("param")Feeback param);
	
	/**
	 * 新增信息
	 * @param param
	 * @return
	 */
	Integer  insertMessage(@Param("param")Feeback param);
	/**
	 * 查询总数
	 * @param param
	 * @return
	 */
	Integer   getTotal(@Param("param")Feeback param);
	
}
