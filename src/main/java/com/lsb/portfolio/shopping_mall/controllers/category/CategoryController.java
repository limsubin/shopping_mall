package com.lsb.portfolio.shopping_mall.controllers.category;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.services.category.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@RequestMapping(value = "/category")
@SessionAttributes(UserDto.CLASS_NAME)
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    //TODO 이미지 get 이해하기
    @RequestMapping(value = "/get-image", method = RequestMethod.GET, produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<byte[]> getImageGet(String key){
        byte[] responseBytes = this.categoryService.getImage(key);
        return new ResponseEntity<>(responseBytes, HttpStatus.OK);
    }
}
