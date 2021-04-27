package com.lsb.portfolio.shopping_mall.controllers.user;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.nums.UserLoginResult;
import com.lsb.portfolio.shopping_mall.nums.UserRegisterResult;
import com.lsb.portfolio.shopping_mall.services.user.UserService;
import com.lsb.portfolio.shopping_mall.vos.user.UserLoginVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserRegisterVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserVo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;


@Controller
@SessionAttributes(UserDto.CLASS_NAME)
@RequestMapping(value = "/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String loginGet(UserLoginVo userLoginVo,
                           @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto){
        if(userDto == null){
            System.out.println("로그인으로");
            return "user/user.login";
        }else{
            System.out.println("홈으로");
            return "root/home";
        }
    }

    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE)
    public String loginPost(UserLoginVo userLoginVo, Model model,
                          @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto){
//        if(userDto != null){
//            return "redirect:/";
//        }

        this.userService.login(userLoginVo);
        model.addAttribute(UserDto.CLASS_NAME, userLoginVo.getUserDto());
        model.addAttribute("result", userLoginVo.getUserLoginResult());
        System.out.println("result: "+ userLoginVo.getUserLoginResult().name().toLowerCase());
        return "user/user.login";
    }



    @RequestMapping(value = "/register")
    public String register(
            UserRegisterVo userRegisterVo){
        this.userService.register(userRegisterVo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", userRegisterVo.getUserRegisterResult().name().toLowerCase());
        return jsonObject.toString(4);
    }

    @ModelAttribute(UserDto.CLASS_NAME)
    public UserVo userVo(){
        return null;
    }
}
