package com.lsb.portfolio.shopping_mall.controllers;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.vos.user.UserVo;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = "/")
@SessionAttributes(UserDto.CLASS_NAME)
public class RootController {
    @RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public String indexGet(@ModelAttribute(UserDto.CLASS_NAME) UserDto userDto, Model model){
//        if(userDto != null){
//            return "redirect:/";
//        }
        model.addAttribute("userDto", userDto);
        System.out.println("RootController, "+userDto);
        return "root/home";
    }

    @ModelAttribute(UserDto.CLASS_NAME)
    public UserVo userVo(){
        return null;
    }
}
