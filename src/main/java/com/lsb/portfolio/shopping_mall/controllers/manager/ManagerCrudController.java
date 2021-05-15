package com.lsb.portfolio.shopping_mall.controllers.manager;

import com.lsb.portfolio.shopping_mall.dtos.category.CategoryDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.services.manager.ManagerService;
import com.lsb.portfolio.shopping_mall.vos.manager.ManagerProductCreateVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserVo;
import jdk.nashorn.internal.scripts.JO;

import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping(value = "/manager/board")
@SessionAttributes(UserDto.CLASS_NAME)
public class ManagerCrudController {
    private final ManagerService managerService;

    @Autowired
    public ManagerCrudController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String listGet(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            HttpServletResponse response,
            Model model){
        if(userDto == null){
            response.setStatus(404);
            return null;
        }
        return "manager/list";
    }

    @RequestMapping(
            value = "/create",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String createGet(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            HttpServletResponse response,
            Model model){
        if(userDto == null){
            response.setStatus(404);
            return null;
        }
        List<CategoryDto> categories = this.managerService.getCategories();
        model.addAttribute("categories", categories);
        return "manager/create";
    }

    @ResponseBody
    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPost(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            ManagerProductCreateVo productCreateVo,
            HttpServletResponse response) throws IOException {
        if(userDto == null){
            response.setStatus(404);
            return null;
        }
        System.out.println("post create()");
        this.managerService.create(userDto, productCreateVo);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", "test");
        return jsonObject.toString();
    }

    @ResponseBody
    @RequestMapping(
            value = "/add-image",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String addImagePost(
            HttpServletResponse response,
            MultipartFile upload,
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto) throws IOException{
        System.out.println("add image controller1");
        if(userDto == null || !userDto.isAdmin()){
            response.setStatus(404);
            return null;
        }
        System.out.println("add image controller2");
        System.out.println("fileName: "+upload.getOriginalFilename());

        JSONObject jsonObject = this.managerService.uploadImage(upload);
        return jsonObject.toString();
    }

    @ModelAttribute(UserDto.CLASS_NAME)
    public UserVo userVo(){
        return null;
    }
}
