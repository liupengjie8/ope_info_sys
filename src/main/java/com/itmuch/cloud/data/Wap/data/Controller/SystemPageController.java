package com.itmuch.cloud.data.Wap.data.Controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.itmuch.cloud.authorities.entity.Opeuser;
import com.itmuch.cloud.data.Wap.permission.dao.WapUserDao;
import com.itmuch.cloud.data.Wap.permission.entity.WapUser;
@Controller
public class SystemPageController {
	@Autowired
	private  WapUserDao  wapDao;
	
	
	@RequestMapping("/")
    public String jhdHtml(HashMap<String, Object> map) {
       
        return "/jhd/index";
    }
	
	@RequestMapping("/jhd/about")
    public String jhdAboutHtml(HashMap<String, Object> map) {
       
        return "/jhd/about";
    }
	@RequestMapping("/jhd/content")
    public String contentHtml(HashMap<String, Object> map) {
       
        return "/jhd/content";
    }
	@RequestMapping("/jhd/news")
    public String newsHtml(HashMap<String, Object> map) {
       
        return "/jhd/news";
    }
	@RequestMapping("/jhd/product")
    public String productHtml(HashMap<String, Object> map) {
       
        return "/jhd/product";
    }
	@RequestMapping("/jhd/lianxi")
    public String lianxiHtml(HashMap<String, Object> map) {
       
        return "/jhd/lianxi";
    }
	@RequestMapping("/jhd/joinus")
    public String joinusHtml(HashMap<String, Object> map) {
       
        return "/jhd/joinus";
    }
	@RequestMapping("/jhd/product2")
    public String product2Html(HashMap<String, Object> map) {
       
        return "/jhd/product2";
    }
	@RequestMapping("/jhd/product3")
    public String product3Html(HashMap<String, Object> map) {
       
        return "/jhd/product3";
    }
	@RequestMapping("/jhd/product4")
    public String product4Html(HashMap<String, Object> map) {
       
        return "/jhd/product4";
    }
	@RequestMapping("/jhd/product5")
    public String product5Html(HashMap<String, Object> map) {
       
        return "/jhd/product5";
    }
	@RequestMapping("/jhd/product6")
    public String product6Html(HashMap<String, Object> map) {
       
        return "/jhd/product6";
    }
	/**
	 * pc端
	 * @param map
	 * @return
	 */
	@RequestMapping("dist")
    public String helloHtml(HashMap<String, Object> map) {
       
        return "pc";
    }
	@RequestMapping("wap")
    public String wapHtml(HashMap<String, Object> map,HttpSession session) {
		 session.setAttribute("wapId",5);
        return "wap";
    }
	@RequestMapping("/wxLogin")
    public String wxLoginHtml(HttpServletRequest req,Model model,HttpSession  session) {
		
		String nickname=req.getParameter("nickname");
       String headimgurl=req.getParameter("headimgurl");
       String isQua=req.getParameter("isQua");
		model.addAttribute("nickname",nickname);
	  model.addAttribute("headimgurl",headimgurl);
	  model.addAttribute("isQua",isQua);  
	  
        return "wxLogin";
    }
	@RequestMapping("pad")
    public String padHtml(HashMap<String, Object> map) {
       
        return "pad";
    }
	@RequestMapping("test")
    public String testHtml(HashMap<String, Object> map) {
       
        return "test";
    }
	
	
	@PostMapping("/api/login")
	public  String  getLogMess(HttpServletRequest  req, HttpServletResponse resp,RedirectAttributes attr) throws IOException, ServletException{
		InputStream inputStream= req.getInputStream();
        String reqJson=IOUtils.toString(inputStream,"utf-8");
        Opeuser user =new Opeuser();
        JSONObject jsonObject=JSONObject.fromObject(reqJson);
         user=(Opeuser)JSONObject.toBean(jsonObject,     
      		  Opeuser.class);
         req.setAttribute("name",user.getUserName()); 
         req.setAttribute("pass",user.getPassWord()); 
       
		return "forward:/login?name="+user.getUserName()+"&pass="+user.getPassWord();
	}
	
	
	
	
	
}
