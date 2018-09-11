package com.itmuch.cloud.data.Wap.data.Controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import scala.sys.process.ProcessBuilderImpl.Simple;

import com.itmuch.cloud.data.Wap.permission.dao.FeebackDao;
import com.itmuch.cloud.data.Wap.permission.dao.WapUserDao;
import com.itmuch.cloud.data.Wap.permission.entity.Feeback;
import com.itmuch.cloud.data.Wap.permission.entity.WapUser;
import com.itmuch.cloud.data.operationpermission.dao.LeaveMessageDao;
import com.itmuch.cloud.data.operationpermission.dao.OperationEducationDao;
import com.itmuch.cloud.data.operationpermission.dao.OperationFileDao;
import com.itmuch.cloud.data.operationpermission.dao.OperationManagerDao;
import com.itmuch.cloud.data.operationpermission.dao.PatientEvaluateDao;
import com.itmuch.cloud.data.operationpermission.entity.LeaveMessage;
import com.itmuch.cloud.data.operationpermission.entity.OperationEducation;
import com.itmuch.cloud.data.operationpermission.entity.OperationFile;
import com.itmuch.cloud.data.operationpermission.entity.OperationManager;
import com.itmuch.cloud.data.operationpermission.entity.PatientEvaluate;
@Api(tags="手术wap")
@RestController
@RequestMapping("/com/wap/")
@CrossOrigin
public class OperationWapApi {
	@Autowired
	private  OperationManagerDao  manDao;
	@Autowired
	private  OperationEducationDao  eduDao;
	@Autowired
	private  OperationFileDao     fileDao;
	@Autowired
	private  PatientEvaluateDao  pDao;
	@Autowired
	private  LeaveMessageDao   leaveDao;
	@Autowired
	private  WapUserDao  wapDao;
	@Autowired
	private FeebackDao  feeDao;
	/**
	 * 手机端Api
	 * 
	 */
 @ResponseBody
 @RequestMapping("getOperationManager")
public  Map<String,Object>  getOperationManager(HttpSession  session){
	 String zt="true";	
	 SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	 Map<String,Object>  dataMap=new HashMap<String, Object>();
     
	 try {
		 Object  obj=session.getAttribute("wapId");
		 Integer wapId=(Integer)obj;
		 WapUser  user=null;
		 if(wapId!=null){
		 user=wapDao.findById(wapId);	 
		 }
		 if(user!=null){
			 if(user.getPatientId()!=null && !"".equals(user.getPatientId())){
				 
		 Map<String,Object>  map=new HashMap<String, Object>();
			OperationManager  opeManger=manDao.getOperationManagerByPatientId(user.getPatientId());
			OperationEducation  edu=eduDao.findOperationEducationById(opeManger.getOperationId());
			map.put("patientName",opeManger.getPatientName());
			map.put("operationName",opeManger.getOperationName());
			map.put("operationDate",sdf.format(sdf.parse(opeManger.getOperationDate())));
			map.put("startDate",opeManger.getStartDate());
			map.put("endDate",opeManger.getEndDate());
			if(edu!=null){
				map.put("operationPlan",edu.getOperationPlan());	
			}else{
				map.put("operationPlan","");
			}
			
			dataMap.put("data",map);
			 }
		 }
	} catch (Exception e) {
		// TODO: handle exception
		e.printStackTrace();
		zt="false";
	}
	

	 dataMap.put("zt",zt);
	
	
	return dataMap;
 }	

 
 @ResponseBody
 @RequestMapping("getFileList")
 public   Map<String,Object>  getFileList(Integer opeId,Integer fileType){
	 Map<String,Object> map=new HashMap<String, Object>();
	 OperationManager  opeManger=manDao.getOperationManagerById(1);
	 String zt="true";
	
	 List<String> xgTypeList=new ArrayList<String>();
	 xgTypeList.add("mp4");
	 xgTypeList.add("rmvb");

	 try {
		 OperationFile  param=new OperationFile();
		if(fileType!=null){
			param.setOperationId(opeManger.getOperationId());
			param.setFileType(fileType);
			List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();
			List<OperationFile>  fileList=fileDao.getOperationFileByOperation(param);
			for (OperationFile file:fileList) {
				String   type="";
				 if(file.getFileName()!=null){	
					 type=file.getFileName().substring(file.getFileName().lastIndexOf(".")+1);

				 }
				 if("0".equals(String.valueOf(fileType))){
					 if(xgTypeList.contains(type)){
				 Map<String,Object> dataMap=new HashMap<String, Object>();
				 dataMap.put("fileName",file.getFileName());
				 dataMap.put("fId",file.getId());
				 if(file.getFileName()!=null){					 
					 dataMap.put("fileLx",file.getFileName().substring(file.getFileName().lastIndexOf(".")+1));
				 }else{
					 dataMap.put("fileLx","");	 
				 }
				 dataList.add(dataMap);
				 }
				 }else{
					 if(!xgTypeList.contains(type)){
						 Map<String,Object> dataMap=new HashMap<String, Object>();
						 dataMap.put("fileName",file.getFileName());
						 dataMap.put("fId",file.getId());
						 if(file.getFileName()!=null){					 
							 dataMap.put("fileLx",file.getFileName().substring(file.getFileName().lastIndexOf(".")+1));
						 }else{
							 dataMap.put("fileLx","");	 
						 }
						 dataList.add(dataMap);
						 }	 
					 
					 
					 
				 }
			}
			
			
			map.put("data",dataList);
			
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
 
 
 @ResponseBody
 @RequestMapping("getFileById")
 public  Map<String,Object> getFileById(Integer fileId){
	 String zt="true";
	 Map<String,Object>  map=new HashMap<String, Object>();
	 if(fileId!=null){
		 OperationFile file=fileDao.getOperationFileById(fileId);
		 if(file!=null){
		 Map<String,Object>  dataMap=new HashMap<String, Object>(); 
		 
		 dataMap.put("fileName",file.getFileName());
		 dataMap.put("fileUrl","http://www.jinghongda.cn/operationFile/"+file.getOperationId()+"/"+file.getFileType()+"/"+file.getFileName());
          map.put("data",dataMap);
          
		 }else{
			 
			 zt="false";
		 }
		 
	 }else{
		 
		 zt="false";
	 }
	 map.put("zt", zt);
	 return map;
	 
 }
 /**
  * 查询离院评估状态
  * @return
  */
 @ResponseBody
 @GetMapping("getOperationEvaluateSate")
 public   Map<String ,Object> getOperationEvaluateSate(HttpSession session){
	 Map<String,Object> map=new HashMap<String, Object>();
	 String zt="true";
	 try {
		 Object  obj=session.getAttribute("wapId");
		 Integer wapId=(Integer)obj;
		 WapUser  user=null;
		 if(wapId!=null){
		 user=wapDao.findById(wapId);	 
		 }
		 if(user!=null){
			 if(user.getPatientId()!=null && !"".equals(user.getPatientId())){
		 OperationManager  opeManger=manDao.getOperationManagerByPatientId(user.getPatientId());
		 
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		 Map<String,Object> dataMap=new HashMap<String, Object>();
         dataMap.put("evaluate",opeManger.getEvaluateState());
         if(opeManger.getEvaluateTime()!=null && !"".equals(opeManger.getEvaluateTime())){
        	 dataMap.put("evaluateTime",sdf.format(sdf.parse(opeManger.getEvaluateTime()))); 
         }else{
           	 dataMap.put("evaluateTime",""); 
         }
         dataMap.put("name",opeManger.getPatientName());
		 map.put("data",dataMap);
			 }
		 }
	} catch (Exception e) {

		e.printStackTrace();
		zt="false";
	}
	 
	 map.put("zt",zt);
	 return map;
 }
 @ApiOperation(value="手术留言")
 @ResponseBody
@PostMapping("getLeaveMessageList")
public  Map<String,Object> getLeaveMessageList(HttpSession session) throws ParseException{
	 Map<String,Object> map=new HashMap<String, Object>();
	
	 String zt="true";
	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
	 Map<String,Object> dataMap=new HashMap<String, Object>();
	 Object  obj=session.getAttribute("wapId");
	 Integer wapId=(Integer)obj;
	 WapUser  user=null;
	 if(wapId!=null){
	 user=wapDao.findById(wapId);	 
	 }
	 if(user!=null){
		 if(user.getPatientId()!=null && !"".equals(user.getPatientId())){
			 OperationManager  opeManger=manDao.getOperationManagerByPatientId(user.getPatientId());
		PatientEvaluate  eva=pDao.getPatinetEvaluateById(opeManger.getId());
		dataMap.put("operationName",opeManger.getOperationName());
		if(eva!=null){
			if(eva.getId()!=null){
				List<LeaveMessage>  leaveList=leaveDao.findLeaveMessageByEvaluateId(eva.getId(),0);
				List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
				for (LeaveMessage leave:leaveList) {
					 Map<String,Object> leaveMap=new HashMap<String, Object>();
					 leaveMap.put("id", leave.getId());
					 leaveMap.put("message", leave.getMessage());
	             if(leave.getMessageDate()!=null && !"".equals(leave.getMessageDate())){
	            	 leaveMap.put("messageDate",sdf.format(sdf.parse(leave.getMessageDate())));
	             }else{
	            	 leaveMap.put("messageDate","");

	             }
	             dataList.add(leaveMap);
				}
				dataMap.put("readMessage", dataList);
				
				List<LeaveMessage>  messList=leaveDao.findLeaveMessageByEvaluateId(eva.getId(),1);
				List<Map<String,Object>> readList=new ArrayList<Map<String,Object>>();
				for (LeaveMessage leave:messList) {
					 Map<String,Object> leaveMap=new HashMap<String, Object>();
					 leaveMap.put("id", leave.getId());
					 leaveMap.put("message", leave.getMessage());
	             if(leave.getMessageDate()!=null && !"".equals(leave.getMessageDate())){
	            	 leaveMap.put("messageDate",sdf.format(sdf.parse(leave.getMessageDate())));
	             }else{
	            	 leaveMap.put("message","");

	             }
	             readList.add(leaveMap);
				}
				dataMap.put("leaveMessage", readList);
			}	
			
		}
		 
		 map.put("data",dataMap);
		 leaveDao.updateReadState(eva.getId());
		 }}
	 map.put("zt",zt);
	 return map;
	 
 }
 /**
  * 查询用户信息
  */
 @ApiOperation(value="查询用户信息",notes="nickname:微信名称，headimgurl:头像，，isQua:是否认证  0 未认证  1 已认证 ")
 @ResponseBody
@PostMapping("getWapUserInfo")
 public Map<String,Object>  getWapUserInfo(HttpSession session){
    
	 Map<String,Object> map=new HashMap<String, Object>();
	 String zt="false";
	 Map<String,Object> dataMap=new HashMap<String, Object>();

 Object  obj=session.getAttribute("wapId");
 if(obj!=null){
	 Integer wapId=(Integer)obj;
	 WapUser  user=wapDao.findById(wapId);
	 if(user!=null){
		 dataMap.put("nickname",user.getUserName());
		 dataMap.put("headimgurl",user.getHeadimgurl());
		 dataMap.put("isQua",user.getIsQua());
		 map.put("data",dataMap);
		 zt="true";
	 }
	 
	 
 }
	 
	 
	 map.put("zt",zt);
return map;	 
 }
 

@ApiOperation(value="查询用户基本信息",notes="reallyName:姓名，tel 手机号")
@ResponseBody
@PostMapping("getUserBasicInfo")  
public  Map<String,Object>  getUserBasicInfo(HttpSession session){
	 String zt="false";
	 Map<String,Object>  map=new HashMap<String, Object>();
	 Map<String,Object> dataMap=new HashMap<String, Object>();
//;
	Object  obj=session.getAttribute("wapId");
	 if(obj!=null){
		 Integer wapId=(Integer)obj;
		 WapUser  user=wapDao.findById(wapId);
		 if(user!=null){
			 dataMap.put("reallyName",user.getReallyName());
			 dataMap.put("tel",user.getTelphone());
			 map.put("data",dataMap);
			 zt="true";
		 }

	
}
	 map.put("zt",zt);
	 return map;	 
} 


@ApiOperation(value="保存用户基本信息",notes="")
@ApiImplicitParams({
	@ApiImplicitParam(name = "reallyName", value = "姓名", required = true, dataType = "String"),
	@ApiImplicitParam(name = "tel", value = "手机号", required = false, dataType = "Integer")
	})
@ResponseBody
@PostMapping("saveUserBasicInfo")  
 public  Map<String,Object>  saveUserBasicInfo(HttpSession session,String reallyName,String tel){
	 String zt="true";
	 Map<String,Object>  map=new HashMap<String, Object>();
	 Object  obj=session.getAttribute("wapId");
	 Integer wapId=(Integer)obj;
	 WapUser  user=wapDao.findById(wapId);
	 if(user!=null){
		 if(user.getReallyName()==null){
			 user.setReallyName("");
		 }
		 if(user.getTelphone()==null){
			 user.setTelphone("");
		 }
		 
		 if(reallyName!=null && !"".equals(reallyName)){
			 user.setReallyName(reallyName);
		 }
		 if(tel!=null && !"".equals(tel)){
			 user.setTelphone(tel);
		 }
		 wapDao.updateUserBasicMessage(user);
		 
	 }
	 
	 
	 
	 
	
	map.put("zt", zt);
	return map;
}

@ApiOperation(value="保存问题反馈",notes="")
@ApiImplicitParam(name = "message", value = "问题", required = true, dataType = "String")
@ResponseBody
@PostMapping("saveFeeBack")  
public  Map<String,Object>  saveFeeBack(HttpSession session,String message){
 String zt="true";
 Map<String,Object>  map=new HashMap<String, Object>();
 Object  obj=session.getAttribute("wapId");
 Integer wapId=(Integer)obj;
  if(message!=null && !"".equals(message)){
	  Feeback  fee=new Feeback();
	  fee.setWapUserId(wapId);
	  fee.setMessage(message);
	  feeDao.insertMessage(fee);
  }
map.put("zt",zt);

return map;
}


@ApiOperation(value="查询未读信息",notes="")
@ResponseBody
@PostMapping("getReadCount")  
public  Map<String,Object>  getReadCount(Integer num){
	if(num==null){
		num=0;
	}
	 Map<String,Object> map=new HashMap<String, Object>();
	 String zt="true";
	 try {
		

	 OperationManager  opeManger=manDao.getOperationManagerById(1);
	 
	 
		PatientEvaluate  eva=pDao.getPatinetEvaluateById(opeManger.getId());
		Integer  count=leaveDao.getReadCount(eva.getId());
		if(num==1){
			map.put("count","");
		}else{
			map.put("count",count);
		}
		} catch (Exception e) {
			e.printStackTrace();
			zt="false";
		}

	return map;
}

@ApiOperation(value="查询未读信息,返回空字符串",notes="")
@ResponseBody
@PostMapping("getReadCountNull")  
public  Map<String,Object>  getReadCountNull(){
	
	 Map<String,Object> map=new HashMap<String, Object>();
	 String zt="true";
	 try {
		

	 
	 
		
		map.put("count","");
		} catch (Exception e) {
			e.printStackTrace();
			zt="false";
		}

	return map;
}

@ApiOperation(value="根据id查询留言",notes="")
@ApiImplicitParam(name = "id", value = "id", required = true, dataType = "Integer")
@PostMapping("getMessageById")  
public  Map<String,Object> getMessageById(Integer id){
	
	
	 Map<String,Object> map=new HashMap<String, Object>();
	 String zt="true";
	 try {
			SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");

		 if(id!=null){
			 LeaveMessage  leave=leaveDao.getMessageById(id);
			 if(leave!=null){
				 Map<String,Object> dataMap=new HashMap<String, Object>();	 
				 dataMap.put("message",leave.getMessage()); 
				 if(leave.getMessageDate()!=null && !"".equals(leave.getMessageDate())){
	            	 dataMap.put("messageDate",sdf.format(sdf.parse(leave.getMessageDate())));
	             }else{
	            	 dataMap.put("messageDate","");

	             }
				 map.put("data",dataMap);
				 }
			 
			 
		 }
		
		
		 
		 
	} catch (Exception e) {
		e.printStackTrace();
		zt="true";
	}
	map.put("zt",zt);
	
	return map;
}

}
