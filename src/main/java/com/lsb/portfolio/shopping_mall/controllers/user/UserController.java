package com.lsb.portfolio.shopping_mall.controllers.user;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.user.UserLoginResult;
import com.lsb.portfolio.shopping_mall.enums.user.UserRegisterResult;
import com.lsb.portfolio.shopping_mall.services.user.UserService;
import com.lsb.portfolio.shopping_mall.vos.user.UserLoginVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserRegisterVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserVo;
import net.sf.json.JSON;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;


@Controller
@SessionAttributes({UserDto.CLASS_NAME, UserDto.CART_TOTAL_COUNT_NAME})
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
        if(userDto != null){
            return "root/home";
        }else{
            return "user/user.login";
        }
    }

    @ResponseBody
    @RequestMapping(
            value = "/login",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String loginPost(
            UserLoginVo userLoginVo,
            Model model,
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto){
        if(userDto != null){
            return "redirect:/";
        }

        this.userService.login(userLoginVo);
        model.addAttribute(UserDto.CLASS_NAME, userLoginVo.getUserDto());
        model.addAttribute(UserDto.CART_TOTAL_COUNT_NAME, userLoginVo.getCartTotalCountDto());
        model.addAttribute("userLoginVo", userLoginVo);
        System.out.println("result: "+ userLoginVo.getResultName().toLowerCase());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", userLoginVo.getResultName().toLowerCase());
        return jsonObject.toString();
    }

    @RequestMapping(
            value = "/logout",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String logout(SessionStatus sessionStatus){
        sessionStatus.setComplete();  //session.invalidate() : 지금 접속한 사용자의 세션 저장소를 초기화 한다.
        return "redirect:/shop";
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public String registerGet(@ModelAttribute(UserDto.CLASS_NAME) UserDto userDto){
        if(userDto != null){
            return "redirect:/";
        }
        return "user/user.register";
    }

    @RequestMapping(
            value = "/register",
            method = RequestMethod.POST,
            produces = MediaType.TEXT_HTML_VALUE
    )
    public String registerPost(
            UserRegisterVo userRegisterVo,
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            Model model){
        if(userDto != null){
            // 이미 로그인이 된상태라서 등록 못한다는 거 표시
            return "redirect:/";
        }
        this.userService.register(userRegisterVo);
        System.out.println("register-result: "+ userRegisterVo.getResult().name().toLowerCase());
        if(userRegisterVo.getResult() == UserRegisterResult.SUCCESS){
            return "user/user.register.success";
        }else{
            model.addAttribute("register", userRegisterVo);
            return userRegisterVo.getResult() == UserRegisterResult.SUCCESS ?
                    "user/user.login" :
                    "user/user.register";
        }
    }

    @RequestMapping(
            value = "/terms",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String termsGet(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            HttpServletRequest request,
            Model model){
        if(userDto != null){
            return "redirect:/";
        }else{
            model.addAttribute("strReferer", request.getHeader("referer"));
            //System.out.println("log : "+request.getHeader("referer"));
            return "user/user.terms";
        }
    }

    @ModelAttribute(UserDto.CLASS_NAME)
    public UserVo userVo(){
        return null;
    }
}
