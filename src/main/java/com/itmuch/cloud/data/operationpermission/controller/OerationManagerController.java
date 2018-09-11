package com.itmuch.cloud.data.operationpermission.controller;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import scala.annotation.meta.field;
import scala.sys.process.ProcessBuilderImpl.Simple;

import com.itmuch.cloud.data.operationpermission.dao.OperationManagerDao;
import com.itmuch.cloud.data.operationpermission.entity.OperationManager;
import com.itmuch.cloud.data.operationpermission.entity.PreOperation;

@RestController
@RequestMapping(value = "/com/operationManager/")
@CrossOrigin
public class OerationManagerController {

	@Autowired
	private  OperationManagerDao  manDao;
	
	@ApiOperation(value="获取用户详细信息", notes="")
	@ApiImplicitParam(name = "sDate", value = "结束日期", required = true, dataType = "String")
	@GetMapping(path = "getOperationManagerList")
    public Map<String,Object> getOperationList(String sDate,String eDate,String pname,String opeName,String doctor, Integer page,Integer size) throws ParseException {
		Map<String,Object> map=new HashMap<String, Object>();
		String zt="true";
		Integer  total=0;
		if(page==null ){
			 page=1;
		 }
		if(size==null){
			size=10;
		}
		page=(page-1)*size;
		OperationManager param=new OperationManager();
		param.setPage(page);
		param.setSize(size);
		if(opeName!=null && !"".equals(opeName)){
			param.setOperationName("%"+opeName+"%");
			
		}
		if(doctor!=null &&!"".equals(doctor)){
			param.setChiefDoctor("%"+doctor+"%");
		}
		if(pname!=null && !"".equals(pname)){
			param.setPatientName("%"+pname+"%");
		}
		SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");
		if(sDate==null || "".equals(sDate)){			
			param.setsDate(sdf.format(sdf.parse("2018-7-24")));
		}
		if(eDate==null || "".equals(eDate)){			
			param.seteDate(sdf.format(sdf.parse("2018-7-24")));
		}
		try {
					
		List<OperationManager>  opeList=manDao.findOperationManagerByName(param);
		List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();

		for (OperationManager man:opeList) {
   
			 Map<String,Object> dataMap=new HashMap<String, Object>();
			 dataMap.put("id",man.getId());
			dataMap.put("chiefDoctor",man.getChiefDoctor());
			dataMap.put("operationName",man.getOperationName());
			dataMap.put("patientName",man.getPatientName());
			dataMap.put("operationRoom",man.getOperationRoom());
			if(man.getOperationDate()!=null && !"".equals(man.getOperationDate())){
				dataMap.put("operationDate",sdf.format(sdf.parse(man.getOperationDate())));
			}else{
				dataMap.put("operationDate","");

			}
				dataMap.put("operationTime",man.getStartDate()+"-"+man.getEndDate());
			
	    	 
			
		
			dataList.add(dataMap);
			 
			 
			 
			
		}
		map.put("data",dataList);
		total=manDao.getTotal(param);
		map.put("total",total);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			zt="false";
		}
		 map.put("zt",zt);
		 return map;
	}
	
	
	
}
