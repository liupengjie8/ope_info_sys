package com.itmuch.cloud.data.Wap.permission.controller;

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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itmuch.cloud.data.Wap.permission.dao.FeebackDao;
import com.itmuch.cloud.data.Wap.permission.dao.WapUserDao;
import com.itmuch.cloud.data.Wap.permission.entity.Feeback;
import com.itmuch.cloud.data.Wap.permission.entity.WapUser;
@Api(tags="问题反馈")
@Controller
@RequestMapping("/com/fee/")
@CrossOrigin
public class FeebackController {

	
	@Autowired
	private  FeebackDao  feeDao;
	@Autowired
	private  WapUserDao  wapDao;
	

	@ApiOperation(value="查询留言列表",notes="")
	@ApiImplicitParams({
		@ApiImplicitParam(name = "sDate", value = "开始日期", required = false, dataType = "String"),
		@ApiImplicitParam(name = "eDate", value = "结束日期", required = false, dataType = "String"),
		@ApiImplicitParam(name = "page", value = "页数", required = false, dataType = "Integer"),
		@ApiImplicitParam(name = "size", value = "每页展示条数", required = false, dataType = "Integer")
		})
	@ResponseBody
	@PostMapping("getFeeBackList")
 public Map<String,Object>  getFeeBackList(String sDate,String eDate,Integer page,Integer size){
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
		Feeback  param=new Feeback();
		param.setPage(page);
		param.setSize(size);
		if(sDate!=null && !"".equals(sDate) && !"".equals(eDate) && eDate!=null){
			param.setsDate(sDate);
			param.seteDate(eDate);
			
		}
		try {
			
			SimpleDateFormat  sdf=new SimpleDateFormat("yyyy-MM-dd");

		List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
		List<Feeback> feeList=feeDao.findMessageList(param);
		for (Feeback back:feeList) {
			Map<String,Object> feeMap=new HashMap<String, Object>();
			if(back.getWapUserId()!=null){
				WapUser user=wapDao.findById(back.getWapUserId());
				feeMap.put("userName",user.getUserName());
				feeMap.put("headimgurl",user.getHeadimgurl());
			}
			if(back.getSubmitDate()!=null && !"".equals(back.getSubmitDate()) ){
				feeMap.put("messageDate",sdf.format(sdf.parse(back.getSubmitDate())));	
			}else{
				feeMap.put("messageDate","");
			}
			
			feeMap.put("message",back.getMessage());
			dataList.add(feeMap);
		}		
	map.put("data",dataList);
	total=feeDao.getTotal(param);
	map.put("total",total);	
		} catch (Exception e) {
			e.printStackTrace();
			zt="false";
		}	
		map.put("zt",zt);
	return map;	
	}
	
	
	
	
	
	
}

