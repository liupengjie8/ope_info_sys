package com.itmuch.cloud.authorities.config;


import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itmuch.cloud.authorities.dao.OpeuserDao;
import com.itmuch.cloud.authorities.entity.Opeuser;

import static java.util.Collections.emptyList;

/**
 * @author zhaoxinguo on 2017/9/13.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
     
    @Autowired
    private  OpeuserDao  userDao;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
       Opeuser user =userDao.findByUserName(username);
       
        if(user == null){
            throw new UsernameNotFoundException(username);
        }
        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add( new GrantedAuthorityImpl(user.getId()+""));

        return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassWord(), authorities);
    }

}
