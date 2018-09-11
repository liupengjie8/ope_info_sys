package com.itmuch.cloud.data.operationpermission.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.itmuch.cloud.data.operationpermission.entity.OperationFile;

@Mapper
public interface OperationFileDao {
     /**
      * 保存文件
      * @param param
      * @return
      */
	Integer  insertOperationFile(@Param("param")OperationFile param);
	/**
	 * 文件列表
	 * @param param
	 * @return
	 */
   List<OperationFile>  getOperationFileByOperationId(@Param("param")OperationFile param);
   /**
    * 根据id查询
    * @param id
    * @return
    */
   OperationFile  getOperationFileById(@Param("id")Integer  id);
   
   /**
    * 根据手术删除文件
    * @param param
    * @return
    */
   Integer   deleteOperationFileByOperationId(@Param("param")OperationFile param);
	
   /**
    * 删除文件
    * @param id
    * @return
    */
   Integer  deleteOperationFileById(@Param("id")Integer  id);
   /**
    * 根据文件地址删除文件
    * @param param
    * @return
    */
   Integer   deleteOperationFileByUrl(@Param("param")OperationFile param);
   
   List<OperationFile> getOperationFileByOperation(@Param("param")OperationFile param);
}
