package com.itmuch.cloud.authorities.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.itmuch.cloud.authorities.dao.OpepermissionDao;
import com.itmuch.cloud.authorities.dao.OpeuserDao;
import com.itmuch.cloud.authorities.entity.Opepermission;
import com.itmuch.cloud.authorities.entity.Opeuser;

public class CustomUserService implements UserDetailsService {
	 @Autowired
	    OpeuserDao userDao;
	    @Autowired
	    OpepermissionDao permissionDao;

	    public UserDetails loadUserByUsername(String username) {
	        Opeuser user = userDao.findByUserName(username);
	        if (user != null) {
	            List<Opepermission> permissions = permissionDao.findByAdminUserId(user.getId());
	            List<GrantedAuthority> grantedAuthorities = new ArrayList <>();
	            for (Opepermission permission : permissions) {
	                if (permission != null && permission.getName()!=null) {

	                GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
	                //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
	                grantedAuthorities.add(grantedAuthority);
	                }
	            }
	            return new User(user.getUserName(), user.getPassWord(), grantedAuthorities);
	        } else {
	            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
	        }
	    }

}
