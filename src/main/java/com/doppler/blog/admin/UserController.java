package com.doppler.blog.admin;

import com.doppler.blog.Service.UserService;
import com.doppler.blog.forms.UserForm;
import com.doppler.blog.models.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


@Controller
public class UserController {
    @Resource
    UserService userService;

    @RequestMapping(value = "login",method = GET)
    public String login() {
        return "admin/users/login";
    }


    @RequestMapping(value = "admin/user/profile",method = GET)
    public String profile(Model model){
        model.addAttribute("user",userService.getCurrentUser());
        return "admin/users/profile";
    }


    @RequestMapping(value = "admin/user/{username}",method = POST)
    public String updatePwd(@PathVariable String username, UserForm userForm,Model model){
        User user = userService.findByUsername(username);
        user.setEmail(userForm.getEmail());
        userService.changePassword(user,userForm.getPassword(),userForm.getNewPassword());
        return "redirect:profile";
    }
}