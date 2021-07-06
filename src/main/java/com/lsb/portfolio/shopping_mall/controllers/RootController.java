package com.lsb.portfolio.shopping_mall.controllers;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.services.shop.manager.ManagerService;
import com.lsb.portfolio.shopping_mall.services.root.RootService;
import com.lsb.portfolio.shopping_mall.vos.root.AllProductVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping(value = "/")
@SessionAttributes(UserDto.CLASS_NAME)
public class RootController {
    private final RootService rootService;
    private final ManagerService managerService;

    @Autowired
    public RootController(RootService rootService, ManagerService managerService) {
        this.rootService = rootService;
        this.managerService = managerService;
    }

    @RequestMapping(
            value = {"/shop", "/shop/{page}"},
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String indexGet(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            @PathVariable("page") Optional<Integer> optionalPage,
            Model model){
        int page = optionalPage.orElse(1);
        AllProductVo allProductVo = new AllProductVo(page);
        allProductVo.setUserDto(userDto);

        this.rootService.getRoot(allProductVo);

        model.addAttribute("allProductVo", allProductVo);
        model.addAttribute("userDto", userDto);
        return "root/home";
    }

    /*@RequestMapping(
            value = {"/getImageView/{boardId}"},
            method = RequestMethod.GET,
            produces = MediaType.IMAGE_JPEG_VALUE)
    public ResponseEntity<byte[]> getImageView(@PathVariable("productIndex") String productIndex) {
        byte[] responseBytes = this.managerService.getImageView(productIndex);
        return new ResponseEntity<>(responseBytes, HttpStatus.OK);
    }*/

    @ModelAttribute(UserDto.CLASS_NAME)
    public UserVo userVo(){
        return null;
    }
}
