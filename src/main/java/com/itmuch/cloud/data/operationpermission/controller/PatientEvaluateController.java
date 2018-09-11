package com.itmuch.cloud.data.operationpermission.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import scala.sys.process.ProcessBuilderImpl.Simple;

import com.itmuch.cloud.authorities.entity.Opeuser;
import com.itmuch.cloud.data.operationpermission.dao.LeaveMessageDao;
import com.itmuch.cloud.data.operationpermission.dao.PatientEvaluateDao;
import com.itmuch.cloud.data.operationpermission.entity.LeaveMessage;
import com.itmuch.cloud.data.operationpermission.entity.PatientEvaluate;

@Api(tags="患者准入评估")
@Controller
@RequestMapping(value = "/com/evaluate/")
@CrossOrigin
public class PatientEvaluateController {
	
	
	@Autowired
	private  PatientEvaluateDao  pDao;
	@Autowired
	private  LeaveMessageDao   leaveDao;
	
	@ApiOperation(value="查询全部 病人", notes="total:总的行数  id patientName:病人，"
			+ "age:年龄 "+"  operationName:手术名称")
	@ApiImplicitParams({
	@ApiImplicitParam(name = "name", value = "病人名称", required = false, dataType = "String"),
	@ApiImplicitParam(name = "operationType", value = "手术类型    0 可日间手术  1 不可日间手术  2 等待其他结果", required = false, dataType = "Integer")
	})
@ResponseBody
@PostMapping("getPatientEvaluateList")
public   Map<String,Object>  getPatientEvaluateList(String name,Integer page,Integer size,Integer operationType) throws ParseException{
	PatientEvaluate param=new PatientEvaluate();
	 String zt="true";
	 Map<String,Object>  map=new HashMap<String, Object>(); 
	 if(name!=null && !"".equals(name)){
		 param.setPatientName("%"+name+"%");
	 }
	 if(page==null ){
		 page=1;
	 }
	if(size==null){
		size=10;
	}
	page=(page-1)*size;
	param.setPage(page);
	param.setSize(size);
	param.setOperationType(operationType);
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");

	List<PatientEvaluate>  evaList=pDao.findPatinetEvaluate(param);
	 List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();
	    for (PatientEvaluate eva:evaList) {
	   	 Map<String,Object>  dataMap=new HashMap<String, Object>(); 
	   	dataMap.put("patientName",eva.getPatientName());
		dataMap.put("age",eva.getAge());
		dataMap.put("id",eva.getManagerId());
		dataMap.put("operationName",eva.getOperationName());
		if(eva.getOperationType()!=null){
		if(eva.getOperationType()==0){
			dataMap.put("operationType","可日间手术");	
		}else if(eva.getOperationType()==1){
			dataMap.put("operationType","不可日间手术");		
		}else if(eva.getOperationType()==2){
			dataMap.put("operationType","等待其他结果");		

		}else{
			dataMap.put("operationType","");		

		}
		}else{
			dataMap.put("operationType","");
		}
		if(eva.getEvaluateTime()!=null && !"".equals(eva.getEvaluateTime())){
			dataMap.put("evaluateTime",sdf.format(sdf.parse(eva.getEvaluateTime())));	
		}else{
			dataMap.put("evaluateTime","");	

		}
		dataList.add(dataMap);
		}
			 Integer  total=pDao.getTotal(param);
			 map.put("total",total);
			map.put("data",dataList); 
			 map.put("zt",zt);
			 
			 return map;	 
}
	
	@ApiOperation(value="根据id查询病人", notes="total:总的行数  id patientName:病人，"
			+ "age:年龄 "+"  operationName:手术名称  fastMessage:快速留言  "
			+"leaveMessage:留言列表   message：留言   messageDate：留言日期   operationType:手术类型")
	@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer")	
@ResponseBody
@PostMapping("getPatientEvaluateById")
	public   Map<String,Object>  getPatientEvaluateById(Integer id){
		
		 Map<String,Object>  map=new HashMap<String, Object>(); 
		 String zt="true";
		 try {
			
		if(id!=null){
	   	 Map<String,Object>  dataMap=new HashMap<String, Object>(); 
	   	PatientEvaluate eva=pDao.getPatinetEvaluateById(id);
		 
		 
		  	dataMap.put("patientName",eva.getPatientName());
			dataMap.put("age",eva.getAge());
			dataMap.put("id",eva.getManagerId());
			dataMap.put("operationType",eva.getOperationType());
			dataMap.put("operationName",eva.getOperationName());
			map.put("data",dataMap);
			List<Map<String,Object>> strList=new ArrayList<Map<String,Object>>();
		
			   	 Map<String,Object>  messMap1=new HashMap<String, Object>(); 
			   	messMap1.put("mid",1);
				messMap1.put("message","请于8月27日前挂号麻醉科，进行麻醉评估");
				strList.add(messMap1);
			    Map<String,Object>  messMap2=new HashMap<String, Object>(); 
			 	messMap2.put("mid",2);
			   	messMap2.put("message","请于8月27日前挂号心内科进行检查");
			   	strList.add(messMap2);
			    Map<String,Object>  messMap3=new HashMap<String, Object>(); 
			 	messMap3.put("mid",3);
			   	messMap3.put("message","您已通过准入评估，请持续关注手术安排");
			   	strList.add(messMap3);
			   	dataMap.put("fastMessage",strList);
			List<LeaveMessage>  messList=leaveDao.findLeaveMessage(eva.getId());
			SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");
			String dete="";
			List<Map<String,Object>>  messDataList=new ArrayList<Map<String,Object>>();
			for (LeaveMessage mess:messList) {
			   	 Map<String,Object>  messMap=new HashMap<String, Object>(); 
			   	messMap.put("message",mess.getMessage());
			   	if(mess.getMessageDate()!=null && !"".equals(mess.getMessageDate())){
			   		dete=sdf.format(sdf.parse(mess.getMessageDate()));
			   	}
				messMap.put("messageDate",dete);
				messDataList.add(messMap);
			}
			dataMap.put("leaveMessage",messDataList);
		}else{
			zt="false";
		}
		 } catch (Exception e) {
				e.printStackTrace();
				 zt="false";
			}
		 map.put("zt",zt);
		 return map;	
	}
	@ApiOperation(value="新增患者准入")
	@ApiImplicitParams({
	@ApiImplicitParam(paramType="query",name = "id", value = "患者id", required =true, dataType = "Integer"),
	@ApiImplicitParam(paramType="query",name = "fastMessage", value = "快速留言", required =true, dataType = "String"),
	@ApiImplicitParam(paramType="query",name = "message", value = "留言", required =true, dataType = "String")

	})
	@ResponseBody
	@PostMapping("insertPatientEvaluate")
		public   Map<String,Object> insertPatientEvaluate(Integer id,String fastMessage,Integer operationType,String message){
		Map<String,Object>  map=new HashMap<String, Object>(); 
		 String zt="true";
		try {
			
		
		String  str = (String) SecurityContextHolder.getContext().getAuthentication() .getPrincipal();
		
            String split = str.split("-")[1];
            split=split.replace("[","");
            split=split.replace("]","");
		
		 if(id!=null){
		 PatientEvaluate eva=pDao.getPatinetEvaluateById(id);
		 
		 if(eva.getId()==null){
			 PatientEvaluate param=new PatientEvaluate();	
			 param.setManagerId(id);
             param.setOperationType(operationType);
             param.setEvaluateUserType(0);
			 param.setEvaluateUser(Integer.parseInt(split));
			 pDao.insertPatinetEvaluate(eva);
		 }else{
		   eva.setOperationType(operationType);
		   pDao.updatePatinetEvaluate(eva);
			 
			 
		 }
			 PatientEvaluate evaluate=pDao.getPatinetEvaluateById(id);
             if(evaluate.getId()!=null){
            	 LeaveMessage leave=new LeaveMessage();
         		leave.setEvaluateId(evaluate.getId());
            	if(fastMessage!=null && !"".equals(fastMessage)){
            		fastMessage=fastMessage.substring(0,fastMessage.length()-1);
            		String[] strs=fastMessage.split(",");
            
            		for (String fast:strs) {
            		
						if("0".equals(fast)){
							leave.setMessage("请于8月27日前挂号麻醉科，进行麻醉评估");
						}else if("1".equals(fast)){
							leave.setMessage("请于8月27日前挂号心内科进行检查");

						}else  if("2".equals(fast)){
							leave.setMessage("您已通过准入评估，请持续关注手术安排");

							
						}
						
						insertMessage(leave);
						
					}
            		if(message!=null && !"".equals(message)){
            			leave.setMessage(message);
            			insertMessage(leave);
            			
            		}
            		
            	} 
            	 
            	 
            	 
             }
             List<LeaveMessage>  dataList=leaveDao.findLeaveMessage(eva.getId());
 			SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");
 			String dete="";
 			List<Map<String,Object>>  messDataList=new ArrayList<Map<String,Object>>();
 			for (LeaveMessage mess:dataList) {
 			   	 Map<String,Object>  messMap=new HashMap<String, Object>(); 
 			   	messMap.put("message",mess.getMessage());
 			   	if(mess.getMessageDate()!=null && !"".equals(mess.getMessageDate())){
 			   		dete=sdf.format(sdf.parse(mess.getMessageDate()));
 			   	}
 				messMap.put("messageDate",dete);
 				messDataList.add(messMap);
 			}
 			map.put("data",messDataList); 
			 
		 }else{
			 zt="false";
		 }
		} catch (Exception e) {
			zt="false";
			e.printStackTrace();
		}
		 map.put("zt",zt);
		 
		 return map;
	}
 private  Integer  insertMessage( LeaveMessage leave){
	 Integer  row=0;
	 if(leave.getMessage()!=null){
		 List<LeaveMessage> messList=leaveDao.getLeaveMessageByMessage(leave);
		 if(messList.size()>0){
			 leaveDao.updateMessageByMessage(leave.getMessage());
		 }else{
			 leaveDao.insertLeaveMessage(leave);
		 }
		 
		 
	 }
	 
	 return row;
 }	
	
	
	
}
