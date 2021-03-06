package com.doppler.blog.Service;

import com.doppler.blog.mappers.UserMapper;
import com.doppler.blog.models.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collections;

import static com.doppler.blog.GlobalConstants.UPDATE_PWD;
import static com.doppler.blog.GlobalConstants.UPDATE_USER_FAIL;
import static com.google.common.base.Preconditions.checkNotNull;
import static com.google.common.base.Preconditions.checkState;

/**
 * Created by doppler on 2016/5/21.
 */
@Service
public class UserService implements UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserService.class);
    @Resource
    UserMapper userMapper;

    @Bean
    private PasswordEncoder passwordEncoder(){
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder;
    }


    public User findByUsername(String username){
        return checkNotNull(userMapper.findByUsername(username),username + " Not Found");
    }


    public User getCurrentUser(){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth == null || auth instanceof AnonymousAuthenticationToken){
            return null;
        }
        String username = ((org.springframework.security.core.userdetails.User)auth.getPrincipal()).getUsername();
        return userMapper.findByUsername(username);
    }

    public void changePassword(User user,String password, String newPassword){
        if (passwordEncoder().matches(password,user.getPassword())) {
            user.setPassword(passwordEncoder().encode(newPassword));
            checkState(userMapper.updateUser(user) == 1,UPDATE_USER_FAIL.val());
            logger.info(UPDATE_PWD.val());
        }

    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("User Not Found");
        }
        return createSpringUser(user);
    }


    private org.springframework.security.core.userdetails.User createSpringUser(User user) {
        return new org.springframework.security.core.userdetails.User(
                user.getEmail(),
                user.getPassword(),
                Collections.singleton(createAuthority(user)));
    }


    private GrantedAuthority createAuthority(User user) {
        return new SimpleGrantedAuthority(user.getRole());
    }
}
