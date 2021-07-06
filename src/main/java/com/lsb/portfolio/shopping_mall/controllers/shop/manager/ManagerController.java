package com.lsb.portfolio.shopping_mall.controllers.shop.manager;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductColorDto;
import com.lsb.portfolio.shopping_mall.dtos.shop.ProductOptionDetailDto;
import com.lsb.portfolio.shopping_mall.dtos.shop.ProductSizeDto;
import com.lsb.portfolio.shopping_mall.dtos.category.CategoryDto;
import com.lsb.portfolio.shopping_mall.dtos.category.SubCategoryDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.manager.ManagerProductCreateResult;
import com.lsb.portfolio.shopping_mall.enums.shop.manager.ManagerProductEditResult;
import com.lsb.portfolio.shopping_mall.services.shop.manager.ManagerService;
import com.lsb.portfolio.shopping_mall.vos.shop.manager.*;
import com.lsb.portfolio.shopping_mall.vos.user.UserVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/shop/manager")
@SessionAttributes({UserDto.CLASS_NAME, UserDto.CART_TOTAL_COUNT_NAME})
public class ManagerController {
    private final ManagerService managerService;

    @Autowired
    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    /*상품 생성 GET*/
    @RequestMapping(
            value = "/create",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String createGet(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            Model model){
        if(userDto == null){
            return "user/user.login";
        }
        List<CategoryDto> categories = this.managerService.getCategories();
        List<SubCategoryDto> subCategories = this.managerService.getSubCategories();

        model.addAttribute("categories", categories);
        model.addAttribute("subCategories", subCategories);
        return "shop/manager/product.create";
    }

    /*상품 생성 POST*/
    @ResponseBody
    @RequestMapping(
            value = "/create",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String createPost(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            ManagerProductCreateVo productCreateVo) throws IOException {
        if(userDto == null){
            JSONObject loginJson = new JSONObject();
            productCreateVo.setResult(ManagerProductCreateResult.NO_LOGIN);
            loginJson.put("result", productCreateVo.getResultName().toLowerCase());
            return loginJson.toString();
        }

        this.managerService.create(userDto, productCreateVo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", productCreateVo.getResultName().toLowerCase());
        jsonObject.put("productIndex", productCreateVo.getProductIndex());
        return jsonObject.toString();
    }

    /*상품 수정 GET*/
    @RequestMapping(
            value = "/edit/{productIndex}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String editGet(
            @PathVariable("productIndex") int productIndex,
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            Model model){
        if(userDto == null || !userDto.isAdmin()){
            return "user/user.login";
        }
        ManagerProductPrepareEditVo prepareEditVo = new ManagerProductPrepareEditVo(productIndex);

        this.managerService.prepareEditing(prepareEditVo);

        List<CategoryDto> categories = this.managerService.getCategories();
        List<SubCategoryDto> subCategories = this.managerService.getSubCategories();
        List<ProductOptionDetailDto> optionDetails = this.managerService.getOptionDetails(prepareEditVo);
        List<ProductSizeDto> productSizes = this.managerService.getSizes(prepareEditVo);
        List<ProductColorDto> productColors = this.managerService.getColors(prepareEditVo);

        model.addAttribute("prepareEditVo", prepareEditVo);
        model.addAttribute("categories", categories);
        model.addAttribute("subCategories", subCategories);
        model.addAttribute("optionDetails", optionDetails);
        model.addAttribute("sizes", productSizes);
        model.addAttribute("colors", productColors);
        return "shop/manager/product.edit";
    }

    /*상품 수정 POST*/
    @ResponseBody
    @RequestMapping(
            value = "/edit/{productIndex}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String editPost(
            @PathVariable("productIndex") int productIndex,
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            ManagerProductEditVo editVo) throws IOException{
        if(userDto == null){
            JSONObject loginJson = new JSONObject();
            editVo.setResult(ManagerProductEditResult.NO_LOGIN);
            loginJson.put("result", editVo.getResultName().toLowerCase());
            return loginJson.toString();
        }

        ManagerProductPrepareEditVo prepareEditVo = new ManagerProductPrepareEditVo(productIndex);
        editVo.setPrepareEditVo(prepareEditVo);
        editVo.setUserDto(userDto);

        this.managerService.editArticle(editVo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", editVo.getResultName().toLowerCase());
        jsonObject.put("productIndex", editVo.getPrepareEditVo().getProductIndex());
        return jsonObject.toString();
    }

    /*이미지 추가*/
    @ResponseBody
    @RequestMapping(
            value = "/add-image",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String addImagePost(
            HttpServletResponse response,
            MultipartFile upload,
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto) throws IOException{
        if(userDto == null || !userDto.isAdmin()){
            response.setStatus(404);
            return null;
        }

        JSONObject jsonObject = this.managerService.uploadImage(upload);
        return jsonObject.toString();
    }

    /*상품 삭제 GET*/
    @RequestMapping(
            value = "/delete/{category}/{productIndex}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String deleteGet(
            @PathVariable("productIndex") int productIndex,
            @PathVariable("category") String category,
            @RequestParam("sub") Optional<String> subCategoryCode,
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            Model model){
        if(userDto == null){
            return "user/user.login";
        }
        String subCategory = subCategoryCode.orElse(null);
        ManagerProductDeleteVo deleteVo = new ManagerProductDeleteVo(productIndex);
        deleteVo.setUserDto(userDto);
        deleteVo.setCategory(category);
        deleteVo.setSubCategory(subCategory);

        this.managerService.delete(deleteVo);

        model.addAttribute("deleteVo", deleteVo);
        return !userDto.isAdmin() ? "shop/manager/product.delete" : "redirect:/shop";
    }

    /*관리지 페이지 GET*/
    @RequestMapping(
            value = {"/admin", "/admin/{page}"},
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String adminGet(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            @PathVariable("page") Optional<Integer> optionalPage,
            Model model){
        if(userDto == null){
            return "user/user.login";
        }
        int page = optionalPage.orElse(1);
        AdminListVo adminListVo = new AdminListVo(page);
        adminListVo.setUserDto(userDto);

        this.managerService.adminProduct(adminListVo);

        model.addAttribute("adminListVo", adminListVo);
        return "shop/manager/admin";
    }

    @RequestMapping(
            value = "/get-image",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public ResponseEntity<byte[]> getImageGet(String key){
        byte[] responseBytes = this.managerService.getImage(key);
        return new ResponseEntity<>(responseBytes, HttpStatus.OK);
    }

    @ModelAttribute(UserDto.CLASS_NAME)
    public UserVo userVo(){
        return null;
    }
}
