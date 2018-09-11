package com.itmuch.cloud.data.operationpermission.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.util.WebUtils;

import com.itmuch.cloud.data.operationpermission.dao.Icd9DictDao;
import com.itmuch.cloud.data.operationpermission.dao.OperationDao;
import com.itmuch.cloud.data.operationpermission.dao.OperationEducationDao;
import com.itmuch.cloud.data.operationpermission.dao.OperationFileDao;
import com.itmuch.cloud.data.operationpermission.entity.Icd9Dict;
import com.itmuch.cloud.data.operationpermission.entity.OperationEducation;
import com.itmuch.cloud.data.operationpermission.entity.OperationFile;
import com.itmuch.cloud.data.operationpermission.entity.PreOperation;



@Controller
@RequestMapping(value = "/com/operation/")
@CrossOrigin
public class OperationController {

	@Autowired
	private  OperationDao  opeDao;  
	@Autowired
	private  OperationEducationDao  eduDao;  
    @Autowired
    private   Icd9DictDao  icd9Dao;
	@Autowired
	private  OperationFileDao  fileDao;
	
	
	/**
	 * 查询手术列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "getOperationList")
    public Map<String,Object> getOperationList(String name,Integer page,Integer size) {
		Integer  total=0;
		if(page==null ){
			 page=1;
		 }
		if(size==null){
			size=10;
		}
		page=(page-1)*size;
		PreOperation param=new PreOperation();
		param.setPage(page);
		param.setSize(size);
		if(name!=null && !"".equals(name)){
		param.setOperationName("%"+name+"%");	
		
		}
		List<PreOperation> opeList=opeDao.findOperationByName(param);
		List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();
		for (PreOperation ope:opeList) {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("id",ope.getId());
			map.put("opeationName",ope.getOperationName());
			map.put("operationCode",ope.getOperationCode());
			map.put("operationLevel",ope.getOperationLevel());
			map.put("createDate",ope.getCreateDate());
			map.put("creator",ope.getCreator());
		dataList.add(map);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		total=opeDao.getTotal(param);
		map.put("totalDataNumber",total);
		map.put("tableData",dataList);
        
		return map;
    }
	
	
	/**
	 * 查询icd9的列表
	 * @param name
	 * @param page
	 * @param size
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "getIcd9ListByName")
    public  Map<String,Object>  getIcd9ListByName(String name,Integer page,Integer size) {
		Integer  total=0;
		if(page==null ){
			 page=1;
		 }
		if(size==null){
			size=10;
		}
		page=(page-1)*size;
		Icd9Dict  param=new Icd9Dict();
		param.setPage(page);
		param.setSize(size);
		if(name!=null && !"".equals(name)){
		param.setOperationName("%"+name+"%");	
		
		}
		List<Icd9Dict> icd9List=icd9Dao.findAllIcd9ByName(param);
		List<Map<String,Object>>  dataList=new ArrayList<Map<String,Object>>();
		for (Icd9Dict icdg:icd9List) {
			Map<String,Object> map=new HashMap<String, Object>();
			map.put("id",icdg.getId());
			map.put("opeationName",icdg.getOperationName());
			map.put("operationCode",icdg.getOperationCode());
			map.put("operationLevel",icdg.getOperationLevel());
			map.put("createDate",icdg.getCreateDate());
			map.put("stdIndicator",icdg.getStdIndicator());
			map.put("approved",icdg.getApproved());
			map.put("inputCode",icdg.getInputCode());
			map.put("bz",icdg.getBz());
		dataList.add(map);
		}
		Map<String, Object> map=new HashMap<String, Object>();
		total=icd9Dao.getTotal(param);
		map.put("totalDataNumber",total);
		map.put("tableData",dataList);
        
		return map;
		
		
    }
	/**
	 * 根据id删除手术
	 * @param ids
	 * @return
	 */
	@ResponseBody
	@GetMapping("deleteOperationById")
	public Map<String,Object> deleteOperationById(String ids){
		Integer row=0;
		String zt="false";
		try {
			
		
		if(ids!=null && !"".equals(ids)){
			ids=ids.substring(0,ids.length()-1);
			String[] arr=ids.split(",");
			for (String str:arr) {
				row=opeDao.deleteOperationById(Integer.valueOf(str));
			}
			if(row>0){
				zt="true";
			}
		}
		
		} catch (Exception e) {
			 zt="false";
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("zt", zt);
		return map;
	}
	
	
    /**
     * 新增手术
     * @param ids
     * @return
     */
	@ResponseBody
	@GetMapping(path = "inserOperation")
	public synchronized  Map<String,Object>  insertOperation(String ids){
		Integer row=0;
		String zt="false";
		try {
			
		
		if(ids!=null && !"".equals(ids)){
			ids=ids.substring(0,ids.length()-1);
			String[] idArr=ids.split(",");
			PreOperation  ope=new PreOperation();
			
			for (String arr:idArr) {
				Icd9Dict  icd9=icd9Dao.getIcd9ById(Integer.valueOf(arr));
			  ope.setOperationName(icd9.getOperationName());	
			  ope.setOperationCode(icd9.getOperationCode());
			  if(icd9.getOperationLevel()==null){
				  ope.setOperationLevel("");
			  }else{
				  ope.setOperationLevel(icd9.getOperationLevel());
				  
			  }
				ope.setCreator("ycm");
				Integer  cont=opeDao.getOperationByCode(icd9.getOperationCode());
				if(cont==0){
					row=opeDao.insertOperation(ope);	
				}else{
					row=1;
				}
				
				
			}
			
			if(row>0){
				zt="true";
			}
			
		}
		} catch (Exception e) {
			zt="false";
		}
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("zt", zt);
		return map;
	}
	/**
	 * 根据手术id查询宣教
	 * @param opeId
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "getOperationEducation")
	public   Map<String,Object> getOperationEducation(Integer opeId){
		OperationEducation  edu=eduDao.findOperationEducationById(opeId);
		PreOperation ope=opeDao.getOperationById(opeId);
		Map<String,Object> map=new HashMap<String, Object>();
    map.put("operationName",ope.getOperationName());
    map.put("opetionCode",ope.getOperationCode());
    map.put("operationId",opeId);
    if(edu!=null){
    	map.put("illGuid",edu.getIllGuid());
    	map.put("medicineGuid",edu.getMedicineGuid());
    	map.put("foodGuid",edu.getFoodGuid());
    	map.put("mindGuid",edu.getMindGuid());
    	map.put("operationPlan",edu.getOperationPlan());
    }else{
    	map.put("illGuid","");
    	map.put("medicineGuid","");
    	map.put("foodGuid","");
    	map.put("mindGuid","");
    	map.put("operationPlan","");
    	
    }
    OperationFile  param=new OperationFile();
	 param.setOperationId(Integer.valueOf(opeId));
	 param.setFileType(1);
    List<OperationFile> xgList=fileDao.getOperationFileByOperationId(param);
    param.setFileType(2);
    List<OperationFile> zdList=fileDao.getOperationFileByOperationId(param);
	List<Map<String,Object>> dataXgList=new ArrayList<Map<String,Object>>();
	for (OperationFile file:xgList) {
		Map<String,Object>  fileMap=new HashMap<String, Object>();
		fileMap.put("fileId",file.getId());
		fileMap.put("fileName",file.getFileName());
		fileMap.put("fileUrl",file.getFileUrl());
		fileMap.put("operationId",file.getOperationId());
		fileMap.put("fileType", file.getFileType());
		dataXgList.add(fileMap);
	}	
	map.put("xgFileList",dataXgList);
	List<Map<String,Object>> dataZdList=new ArrayList<Map<String,Object>>();
	for (OperationFile file:zdList) {
		Map<String,Object>  fileMap=new HashMap<String, Object>();
		fileMap.put("fileId",file.getId());
		fileMap.put("fileName",file.getFileName());
		fileMap.put("fileUrl",file.getFileUrl());
		fileMap.put("operationId",file.getOperationId());
		fileMap.put("fileType", file.getFileType());
		dataZdList.add(fileMap);
	}	
	map.put("zdFileList",dataZdList);
	return map;	
	}
	
	/**
	 * 保存宣教信息
	 * @param param
	 * @return
	 */
	@ResponseBody
	@GetMapping(path = "insertOperationEducation")
public  Map<String,Object>  insertOperationEducation(OperationEducation param){
		String zt="false";
		if(param.getoId()!=null){
			try {
				if(param.getIllGuid()==null){
					param.setIllGuid("");
				}
				if(param.getMedicineGuid()==null){
					param.setMedicineGuid("");
				}
               if(param.getFoodGuid()==null){
					param.setFoodGuid("");
				}
               if(param.getMindGuid()==null){
					param.setMindGuid("");
				}
               if(param.getOperationPlan()==null){
					param.setOperationPlan("");
				}
               OperationEducation ope=eduDao.findOperationEducationById(param.getoId());
               if(ope==null){
            	    Integer row=eduDao.insertOperationEducation(param);
                    if(row>0){
                  	  zt="true";
                    } 
            	   
               }else{
            	   
            	    Integer row=eduDao.updateOperationEducation(param);
                    if(row>0){
                  	  zt="true";
                    }
               }
               
               
               
          
               
               
			} catch (Exception e) {
				e.printStackTrace();
				zt="false";
			}
			
			
			
		}
		Map<String,Object> map=new HashMap<String, Object>();
					map.put("zt", zt);
					return map;	
	}	
	
	@ResponseBody
	@RequestMapping("uploadOperationFile")
	public Map<String,Object>  uploadOperationFile(HttpServletRequest request) throws IOException{
	MultipartHttpServletRequest mquest=WebUtils.getNativeRequest(request,MultipartHttpServletRequest.class);
	String	 path=request.getRealPath("operationFile");
	String  operationId=request.getParameter("opeId");
	String  type=request.getParameter("fileType");
	String zt="true";
	Map<String,Object> map=new HashMap<String, Object>();

	if(operationId!=null &&  !"null".equals(operationId) &&  !"undefined".equals(operationId)  && !"".equals(operationId) && type!=null && !"undefined".equals(type) && !"".equals(type)  && !"null".equals(type) ){
	
	List<MultipartFile> fileList=mquest.getFiles("file");
	
	 OutputStream os=null;
	 OperationFile  param=new OperationFile();
	 param.setOperationId(Integer.valueOf(operationId));
	 param.setFileType(Integer.valueOf(type));
	try {
		File f2=new File(path+"/"+operationId+"/"+type);
	/*	if(f2.exists()){
			deleteFile(f2);
			fileDao.deleteOperationFileByOperationId(param);
		}*/
		
	for (MultipartFile file:fileList) {
          
		System.out.println("path:"+file.getOriginalFilename());
			File f1=new File(path);
			if(!f1.exists()){
				f1.mkdir();
			}
			File f3=new File(f1.getPath()+"/"+operationId);
			if(!f3.exists()){
				f3.mkdir();
			}
			File f4=new File(f3.getPath()+"/"+type);
			if(!f4.exists()){
				f4.mkdir();
			}
			if(f4.exists()){
				
				File lastFile=new File(f4.getPath()+"/"+file.getOriginalFilename());
				if(lastFile.exists()){
					OperationFile ope=new OperationFile();
					ope.setOperationId(Integer.valueOf(operationId));
					ope.setFileType(Integer.parseInt(type));
					ope.setFileUrl(lastFile.getPath());
					fileDao.deleteOperationFileByUrl(ope);		
					lastFile.delete();
				
				}
				
			       lastFile.createNewFile();
			       System.out.println(lastFile.exists());
			        //使用输入流读取前台的file文件              

			        //循环读取输入流文件内容，通过输出流将内容写入新文件
			         os=new FileOutputStream(lastFile);  
			        os.write(file.getBytes());  
			    	if(os!=null){
			  		  os.close();

			  	}
				if(lastFile.exists()){
					param.setFileName(file.getOriginalFilename());
					param.setFileUrl(lastFile.getPath());
					fileDao.insertOperationFile(param);
				}
				 
				
				
			}
			
		
	}	
		
	List<OperationFile> fList=fileDao.getOperationFileByOperationId(param);
	List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
	for (OperationFile file:fList) {
		Map<String,Object>  fileMap=new HashMap<String, Object>();
		fileMap.put("fileId",file.getId());
		fileMap.put("fileName",file.getFileName());
		fileMap.put("fileUrl",file.getFileUrl());
		fileMap.put("operationId",file.getOperationId());
		fileMap.put("fileType", file.getFileType());
		dataList.add(fileMap);
	}
	map.put("data",dataList);
	} catch (Exception e) {
e.printStackTrace();
 zt="false";
}finally{
	if(os!=null){
		  os.close();

	}
}

	
	}else{
		 zt="false";
	}
		
		
		map.put("zt", zt);
		
		return map;	
	}
	
	@ResponseBody
@RequestMapping("deleteFileById")
public  Map<String,Object>  deleteFileById(Integer fId,Integer opeId,Integer fileType){
	String  zt="true"; 
	if(fId!=null){
		OperationFile file=fileDao.getOperationFileById(fId);
		if(file!=null){
			try {
				File delteFile=new File(file.getFileUrl());
				delteFile.delete();
				fileDao.deleteOperationFileById(fId);
			} catch (Exception e) {
				e.printStackTrace();
				zt="false";
			}
		
		}else{
			zt="false";
		}
		
	}else{
		zt="false";
	}
	Map<String,Object> map=new HashMap<String, Object>();

	OperationFile param=new OperationFile();
	param.setOperationId(opeId);
	param.setFileType(fileType);
	List<OperationFile> fList=fileDao.getOperationFileByOperationId(param);
	List<Map<String,Object>> dataList=new ArrayList<Map<String,Object>>();
	for (OperationFile file:fList) {
		Map<String,Object>  fileMap=new HashMap<String, Object>();
		fileMap.put("fileId",file.getId());
		fileMap.put("fileName",file.getFileName());
		fileMap.put("fileUrl",file.getFileUrl());
		fileMap.put("operationId",file.getOperationId());
		fileMap.put("fileType", file.getFileType());
		dataList.add(fileMap);
	}
	map.put("data",dataList);
	map.put("zt", zt);
	
	return map;	
}

@RequestMapping("downLoadOperationFile")	
public  void  downLoadOperationFile(HttpServletRequest req,HttpServletResponse res,Integer fId) throws IOException{
	
	if(fId!=null){
		OperationFile opeFile=fileDao.getOperationFileById(fId);
	File file=new File(opeFile.getFileUrl());

	OutputStream out = null;

	FileInputStream inputStream = null;
	try {
		String name = new String(file.getName().getBytes(),
				"ISO-8859-1");
		res.setContentType("application/force-download");

		res.setHeader("Content-Disposition", "attachment;filename="
				+ name);

		if (file.exists()) {
			inputStream = new FileInputStream(file);
			out = res.getOutputStream();
			int b = 0;
			byte[] buff = new byte[1024];
			while ((b = inputStream.read()) != -1) {

				out.write(b);
			}
			
		}

	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally{
		
if(inputStream!=null){
	inputStream.close();
}
if(out!=null){

	out.close();
	
	out.flush();
}
		
	}

	}
	
}	
	
	
	
	
	private boolean deleteFile(File file){
		if(file.isDirectory()){
			String[] childs=file.list();
			for (int i = 0; i < childs.length; i++) {
				boolean bool=deleteFile(new File(file.getPath()+"/"+childs[i]));
				
				
			}
			if(childs.length<1){
				file.delete();
			}
			
		}else{
			file.delete();
		}
		file.delete();
		return true;
	}	
	
	
	
}
