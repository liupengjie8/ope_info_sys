package com.itmuch.cloud.data.Wap.data.Controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itmuch.cloud.data.operationpermission.dao.OperationManagerDao;
import com.itmuch.cloud.data.operationpermission.dao.OutHospitalEvaluateDao;
import com.itmuch.cloud.data.operationpermission.entity.OperationManager;
import com.itmuch.cloud.data.operationpermission.entity.OutHospitalEvaluate;

@RestController
@RequestMapping("/com/pad/")
@CrossOrigin
public class OperationPadApi {
	
	@Autowired
	private OperationManagerDao manDao;
	@Autowired
	private  OutHospitalEvaluateDao outDao;
 /**
  * pad端
  */
	/**
	 * 离院评估手术列表
	 * @param pName
	 * @param evaluate
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping("getOperationManger")
	public  Map<String,Object> getOperationManger(String pName,Integer evaluate,Integer page,Integer size){
		Map<String,Object> map=new HashMap<String, Object>();
		String zt="true";
		Integer  total=0;
		if(page==null ){
			 page=1;
		 }
		if(size==null){
			size=5;
		}
		if(evaluate==null){
			evaluate=0;
		}
		
		page=(page-1)*size;
		OperationManager param=new OperationManager();
		param.setPage(page);
		param.setSize(size);
		param.setEvaluateState(evaluate);
		if(pName!=null && !"".equals(pName)){
			param.setPatientName("%"+pName+"%");
			
		}
		try {
			
		
		List<OperationManager> manList=manDao.getOperationManagerByEvaluateState(param);
		List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");

		for (OperationManager man:manList) {
   
			 Map<String,Object> dataMap=new HashMap<String, Object>();
			 dataMap.put("id",man.getId());
			//dataMap.put("chiefDoctor",man.getChiefDoctor());
			dataMap.put("operationName",man.getOperationName());
			dataMap.put("patientName",man.getPatientName());
			dataMap.put("evaluateState",man.getEvaluateState());
			//dataMap.put("operationRoom",man.getOperationRoom());
			if(man.getOperationDate()!=null && !"".equals(man.getOperationDate())){
				dataMap.put("operationDate",sdf.format(sdf.parse(man.getOperationDate())));
			}else{
				dataMap.put("operationDate","");

			}
				//dataMap.put("operationTime",man.getStartDate()+"-"+man.getEndDate());
			
	    	 

			dataList.add(dataMap);
			 
			 
			 
			
		}
		map.put("data",dataList);
		} catch (Exception e) {
			e.printStackTrace();
			zt="false";
		}
		 map.put("zt",zt);
		 return map;
	}  
	
	@RequestMapping("getOutHospitalEvaluate")
	public Map<String,Object> OutHospitalEvaluate(Integer mangerId){
		String zt="true";
		Map<String,Object> map=new HashMap<String, Object>();
        try {
			
		
		if(mangerId!=null){
			OutHospitalEvaluate evaluate= outDao.getOutHospitalByManngerId(mangerId);
			Map<String,Object> dataMap=new HashMap<String, Object>();
          if(evaluate!=null){
        	  dataMap.put("templature",evaluate.getTemplature());  
        	  dataMap.put("pulse",evaluate.getPulse());
        	  dataMap.put("breath",evaluate.getBreath());
        	  dataMap.put("life_status",evaluate.getLifeStatus());
        	  dataMap.put("action",evaluate.getAction());
        	  dataMap.put("pain",evaluate.getPain());
        	  dataMap.put("vomit",evaluate.getVomit());
        	  dataMap.put("bleed",evaluate.getBleed());
        	  dataMap.put("outHospital",evaluate.getOutHospital());
        	  dataMap.put("evaluateName",evaluate.getEvaluateName());
        	  dataMap.put("other",evaluate.getOther());
        	 
          }
          map.put("data",dataMap);
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
	                                                                            
	@RequestMapping("submitOutHospitalEvaluate")
	public Map<String,Object> submitOutHospitalEvaluate(OutHospitalEvaluate param,Integer subType){
		String zt="true";
		Map<String,Object> map=new HashMap<String, Object>();
		OperationManager man=new OperationManager();
		if(param.getManagerId()!=null && subType!=null){
			man.setId(param.getManagerId());
			OutHospitalEvaluate evaluate= outDao.getOutHospitalByManngerId(param.getManagerId());
			  man.setEvaluateState(subType);
          if(evaluate==null ){
        	
        	if(param.getTemplature()==null){
        		param.setTemplature("");
        	}
        	if(param.getPulse()==null){
        		param.setPulse("");
        	}
        	if(param.getBreath()==null){
        		param.setBreath("");
        	}
        	if(param.getLifeStatus()==null){
        		param.setLifeStatus(0);
        	}
        	if(param.getAction()==null){
        		param.setAction("");
        	}
        	if(param.getPain()==null){
        		param.setPain(0);
        	}
        	if(param.getVomit()==null){
        		param.setVomit(0);
        	}
        	if(param.getBleed()==null){
        		param.setBleed(0);
        	}
        	if(param.getOutHospital()==null){
        		param.setOutHospital(0);
        	}
        	if(param.getEvaluateName()==null){
        		param.setEvaluateName("");
        	}
        	if(param.getOther()==null){
        		param.setOther("");
        	}
        	 outDao.insertOutHospitalEvaluate(param);  
        	
        	  manDao.updateEvaluateState(man);
          }else{
          	if(param.getTemplature()!=null && !"".equals(param.getTemplature())){
          		evaluate.setTemplature("");
        	}
        	if(param.getPulse()!=null && !"".equals(param.getPulse())){
        		evaluate.setPulse("");
        	}
        	if(param.getBreath()!=null && !"".equals(param.getBreath())){
        		evaluate.setBreath("");
        	}
        	if(param.getLifeStatus()!=null && !"".equals(param.getLifeStatus())){
        		evaluate.setLifeStatus(0);
        	}
        	if(param.getAction()!=null && !"".equals(param.getAction())){
        		evaluate.setAction("");
        	}
        	if(param.getPain()!=null && !"".equals(param.getPain())){
        		evaluate.setPain(0);
        	}
        	if(param.getVomit()!=null && !"".equals(param.getVomit())){
        		evaluate.setVomit(0);
        	}
        	if(param.getBleed()!=null && !"".equals(param.getBleed())){
        		evaluate.setBleed(0);
        	}
        	if(param.getOutHospital()!=null && !"".equals(param.getOutHospital())){
        		evaluate.setOutHospital(0);
        	}
        	if(param.getEvaluateName()!=null && !"".equals(param.getEvaluateName())){
        		evaluate.setEvaluateName("");
        	}
        	if(param.getOther()!=null && !"".equals(param.getOther())){
        		evaluate.setOther("");
        	}  
        	 outDao.updateOutHospitalEvaluate(evaluate); 
        	  manDao.updateEvaluateState(man);
          }
       
		}else{
			zt="false";
		}
		map.put("zt",zt);
		 return map;
		
	}
	
	
	
}
