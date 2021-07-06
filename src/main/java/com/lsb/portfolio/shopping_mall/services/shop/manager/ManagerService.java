package com.lsb.portfolio.shopping_mall.services.shop.manager;

import com.lsb.portfolio.shopping_mall.dtos.shop.*;
import com.lsb.portfolio.shopping_mall.dtos.category.CategoryDto;
import com.lsb.portfolio.shopping_mall.dtos.category.SubCategoryDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.manager.*;
import com.lsb.portfolio.shopping_mall.interfaces.IProductIndex;
import com.lsb.portfolio.shopping_mall.models.shop.IShopModel;
import com.lsb.portfolio.shopping_mall.models.shop.manager.IManagerModel;
import com.lsb.portfolio.shopping_mall.services.Regex;
import com.lsb.portfolio.shopping_mall.services.shop.ShopService;
import com.lsb.portfolio.shopping_mall.utils.CryptoUtil;
import com.lsb.portfolio.shopping_mall.vos.shop.manager.*;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ManagerService extends Regex {
    private final IManagerModel managerModel;
    private final IShopModel shopModel;

    public static final int BOARDS_PER_PAGE = 10; //보드 페이지당
    public static final int PAGE_RANGE = 5;       //페이지 범위

    @Autowired
    public ManagerService(IManagerModel managerModel, IShopModel shopModel) {
        this.managerModel = managerModel;
        this.shopModel = shopModel;
    }


    public List<CategoryDto> getCategories(){
        return this.managerModel.selectCategories();
    }

    public List<SubCategoryDto> getSubCategories(){
        return this.managerModel.selectSubCategories();
    }


    public void create(UserDto userDto, ManagerProductCreateVo productCreateVo) throws IOException {
        if(productCreateVo == null){
            productCreateVo.setResult(ManagerProductCreateResult.FAILURE);
            return;
        }

        if(this.managerModel.selectCategoryCount(productCreateVo.getCategory()) == 0){
            productCreateVo.setResult(ManagerProductCreateResult.NO_CATEGORY);
            return;
        }

        if(!Regex.checkRegex(productCreateVo.getProductName(), Shop.PRODUCT_NAME)
            && !Regex.checkRegex(String.valueOf(productCreateVo.getProductPrice()), Shop.PRODUCT_PRICE)
            && !Regex.checkRegex(String.valueOf(productCreateVo.getProductPrice()), Shop.PRODUCT_CONTENT)){
            productCreateVo.setResult(ManagerProductCreateResult.NOT_REGEX);
            return;
        }

        JSONArray sizeArray = JSONArray.fromObject(productCreateVo.getSizes());
        JSONArray colorArray = JSONArray.fromObject(productCreateVo.getColors());

        for(int i = 0; i< sizeArray.size(); i++){
            if(sizeArray.get(i) == null && !Regex.checkRegex(String.valueOf(sizeArray.get(i)), Regex.Shop.PRODUCT_SIZE)){
                productCreateVo.setResult(ManagerProductCreateResult.VALUE_UNDEFINED);
                return;
            }
        }

        for(int i = 0; i< colorArray.size(); i++){
            if(colorArray.get(i) == null && !Regex.checkRegex(String.valueOf(colorArray.get(i)), Regex.Shop.PRODUCT_COLOR)){
                productCreateVo.setResult(ManagerProductCreateResult.VALUE_UNDEFINED);
                return;
            }
        }

        this.managerModel.insertProduct(
                userDto.getIndex(),
                productCreateVo.getProductName(),
                productCreateVo.getCategory(),
                productCreateVo.getProductPrice(),
                productCreateVo.getThumbnail().getBytes(),
                productCreateVo.getProductContent()
        );

        int productLastIndex = this.managerModel.selectLastInsertId();

        System.out.println("subCategory: "+productCreateVo.getSubCategory());
        if(productCreateVo.getSubCategory() != null){
            this.managerModel.insertSubProduct(
                    userDto.getIndex(),
                    productLastIndex,
                    productCreateVo.getSubCategory());
        }


        for(int i = 0; i< sizeArray.size(); i++){
            this.managerModel.insertSize(productLastIndex, String.valueOf(sizeArray.get(i)));
        }

        for(int i = 0; i< colorArray.size(); i++){
            this.managerModel.insertColor(productLastIndex, String.valueOf(colorArray.get(i)));
        }

        String optionStr = productCreateVo.getOptionDetail();
        JSONArray optionDetailArray = JSONArray.fromObject(optionStr); //직렬화 시켜 가져온 오브잭트 배열을 JSONArray 형식으로 바꿔준다.

        if(optionDetailArray == null){
            productCreateVo.setResult(ManagerProductCreateResult.FAILURE);
            return;
        }


        for (int i = 0; i<optionDetailArray.size(); i++){
            //JSONArray 형태의 값을 가져와 JSONObject로 풀어준다
            JSONObject obj = (JSONObject) optionDetailArray.get(i);
            if(obj.get("size") == null
                    && obj.get("color") == null
                    && obj.get("premium") == null
                    && obj.get("stock") == null){
                productCreateVo.setResult(ManagerProductCreateResult.FAILURE);
                return;
            }

            if(this.managerModel.selectSizeCount(productLastIndex, String.valueOf(obj.get("size"))) == 0
                  && this.managerModel.selectColorCount(productLastIndex, String.valueOf(obj.get("color"))) == 0
                  && !Regex.checkRegex(String.valueOf(obj.get("size")) , Shop.PRODUCT_SIZE)
                  && !Regex.checkRegex(String.valueOf(obj.get("color")), Shop.PRODUCT_COLOR)){
                productCreateVo.setResult(ManagerProductCreateResult.NO_SELECT_COUNT);
                return;
            }

            int sizeIndex = this.managerModel.selectLikeSizeIndex(productLastIndex, String.valueOf(obj.get("size")));
            int colorIndex = this.managerModel.selectLikeColorIndex(productLastIndex, String.valueOf(obj.get("color")));
            this.managerModel.insertOptionDetail(
                    sizeIndex,
                    colorIndex,
                    Integer.parseInt(String.valueOf(obj.get("premium"))),
                    Integer.parseInt(String.valueOf(obj.get("stock"))));
        }

        productCreateVo.setProductIndex(productLastIndex);
        productCreateVo.setResult(ManagerProductCreateResult.SUCCESS);
    }



    public void prepareEditing(ManagerProductPrepareEditVo prepareEditVo){
        ProductDto productDto = this.shopModel.selectByProductId(prepareEditVo.getProductIndex());

        if(productDto == null){
            prepareEditVo.setResult(ManagerProductPrepareEditResult.NO_SUCH_ARTICLE);
            return;
        }

        if(this.managerModel.selectBySubProductCount(prepareEditVo.getProductIndex()) != 0){
            ProductSubDto productSubDto = this.managerModel.selectBySubProductId(prepareEditVo.getProductIndex());
            prepareEditVo.setProductSubDto(productSubDto);
        }

        prepareEditVo.setProductDto(productDto);
        prepareEditVo.setResult(ManagerProductPrepareEditResult.SUCCESS);
    }



    public void editArticle(ManagerProductEditVo editVo) throws IOException{
        if(editVo == null){
            editVo.setResult(ManagerProductEditResult.FAILURE);
            return;
        }

        /*이미지 파일 있을때, 없을때*/
        if(editVo.getThumbnail() == null){
            this.managerModel.updateProduct(
                    editVo.getPrepareEditVo().getProductIndex(),
                    editVo.getUserDto().getIndex(),
                    editVo.getProductName(),
                    editVo.getCategory(),
                    editVo.getProductPrice(),
                    editVo.getProductContent()
            );
        }else{
            this.managerModel.updateProductAndImage(
                    editVo.getPrepareEditVo().getProductIndex(),
                    editVo.getUserDto().getIndex(),
                    editVo.getProductName(),
                    editVo.getCategory(),
                    editVo.getProductPrice(),
                    editVo.getThumbnail().getBytes(),
                    editVo.getProductContent()
            );
        }

        /*서브 카테고리*/
        if(editVo.getSubCategory() == null){
            this.managerModel.deleteSubProduct(
                    editVo.getPrepareEditVo().getProductIndex(),
                    editVo.getUserDto().getIndex());
        }

        //서브 카테고리 데이터가 이미 있으면 update, 없으면 insert
        if(editVo.getSubCategory() != null && this.managerModel.selectBySubProductCount(editVo.getPrepareEditVo().getProductIndex()) == 0){
            this.managerModel.insertSubProduct(
                    editVo.getUserDto().getIndex(),
                    editVo.getPrepareEditVo().getProductIndex(),
                    editVo.getSubCategory());
        }else{
            this.managerModel.updateSubProduct(
                    editVo.getPrepareEditVo().getProductIndex(),
                    editVo.getUserDto().getIndex(),
                    editVo.getSubCategory());
        }

        String optionStr = editVo.getOptionDetail();
        JSONArray optionDetailArray = JSONArray.fromObject(optionStr); //직렬화 시켜 가져온 오브잭트 배열을 JSONArray 형식으로 바꿔준다.

        for (int i = 0; i<optionDetailArray.size(); i++) {
            //JSONArray 형태의 값을 가져와 JSONObject로 풀어준다
            JSONObject obj = (JSONObject) optionDetailArray.get(i);
            if (obj.get("size") == null
                    && obj.get("color") == null
                    && obj.get("premium") == null
                    && obj.get("stock") == null) {
                editVo.setResult(ManagerProductEditResult.FAILURE);
                return;
            }

            int productIndex = editVo.getPrepareEditVo().getProductIndex();
            int sizeIndex = 0;
            int colorIndex = 0;
            JSONArray sizeArray = JSONArray.fromObject(editVo.getSizes());
            JSONArray colorArray = JSONArray.fromObject(editVo.getColors());

            switch (Integer.parseInt(String.valueOf(obj.get("type")))) {
                case 1: //insert
                case 3: //delete
                    System.out.println("size_1: "+String.valueOf(obj.get("size")));
                    System.out.println("color_1: "+String.valueOf(obj.get("color")));
                    List<ProductSizeDto> sizes = this.managerModel.selectSizes(productIndex);
                    List<ProductColorDto> colors = this.managerModel.selectColors(productIndex);

                    /*for (int j = 0; j < sizes.size(); j++){
                        if(obj.get("size").equals(sizes.get(i))){
                            editVo.setResult(ManagerProductEditResult.DUPLICATE_SIZE);
                            return;
                        }
                    }

                    for (int k = 0; k < colors.size(); k++){
                        if(obj.get("size").equals(colors.get(k))){
                            editVo.setResult(ManagerProductEditResult.DUPLICATE_COLOR);
                            return;
                        }
                    }*/

                    if(this.managerModel.selectSizeCount(productIndex, String.valueOf(obj.get("size"))) == 0
                            && this.managerModel.selectColorCount(productIndex, String.valueOf(obj.get("color"))) == 0){
                        for(Object size : sizeArray){//int j = 0; i< sizeArray.size(); i++
                            this.managerModel.insertSize(productIndex, String.valueOf(size));
                        }

                        for(Object color : colorArray){//int k = 0; i< colorArray.size(); i++
                            this.managerModel.insertColor(productIndex, String.valueOf(color));
                        }
                    }

                    System.out.println("size_2: "+String.valueOf(obj.get("size")));
                    System.out.println("color_2: "+String.valueOf(obj.get("color")));
                    sizeIndex = this.managerModel.selectLikeSizeIndex(productIndex, String.valueOf(obj.get("size")));
                    colorIndex = this.managerModel.selectLikeColorIndex(productIndex, String.valueOf(obj.get("color")));

                    if(Integer.parseInt(String.valueOf(obj.get("type"))) == 1) {
                        this.managerModel.insertOptionDetail(
                                sizeIndex,
                                colorIndex,
                                Integer.parseInt(String.valueOf(obj.get("premium"))),
                                Integer.parseInt(String.valueOf(obj.get("stock"))));

                    }

                    if(Integer.parseInt(String.valueOf(obj.get("type"))) == 3){
                        //this.managerModel.deleteOptionDetail(sizeIndex, colorIndex);
                        this.managerModel.deleteSize(sizeIndex);
                        this.managerModel.deleteColor(colorIndex);
                    }
                    break;
                case 2: //update
                    sizeIndex = this.managerModel.selectLikeSizeIndex(productIndex, String.valueOf(obj.get("size")));
                    colorIndex = this.managerModel.selectLikeColorIndex(productIndex, String.valueOf(obj.get("color")));
                    this.managerModel.updateOptionDetail(
                            sizeIndex,
                            colorIndex,
                            Integer.parseInt(String.valueOf(obj.get("premium"))),
                            Integer.parseInt(String.valueOf(obj.get("stock"))));
                    break;
                //case 3: //delete
                //    System.out.println("delete-size: "+(String) obj.get("size"));
                //    System.out.println("delete-color: "+(String) obj.get("color"));
                //    sizeIndex = this.managerModel.selectLikeSizeIndex(productIndex, (String) obj.get("size"));
                //    colorIndex = this.managerModel.selectLikeColorIndex(productIndex, (String) obj.get("color"));
                //    if(sizeIndex == 0 && colorIndex == 0){

                //    }
                //    this.managerModel.deleteOptionDetail(sizeIndex, colorIndex);
                //    break;
                default:
                    //잘못된 값 입력
                    editVo.setResult(ManagerProductEditResult.INVALID_VALUE);
                    break;
            }

        }
        editVo.setResult(ManagerProductEditResult.SUCCESS);
    }



    public List<ProductOptionDetailDto> getOptionDetails(IProductIndex productIndex){
        return this.managerModel.selectOptionDetails(productIndex.getProductIndex());
    }


//    public List<ProductSizeDto> getSizes(ManagerProductPrepareEditVo prepareEditVo){
//        return this.managerModel.selectSizes(prepareEditVo.getProductIndex());
//    }

    public List<ProductSizeDto> getSizes(IProductIndex productIndex){
        return this.managerModel.selectSizes(productIndex.getProductIndex());
    }

    public List<ProductColorDto> getColors(IProductIndex productIndex){
        return this.managerModel.selectColors(productIndex.getProductIndex());
    }


    public JSONObject uploadImage(MultipartFile multipartFile) throws IOException{
        String key = String.format("%s%f",
                new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()),
                Math.random());
        key = CryptoUtil.SHA1.hash(key);
        this.managerModel.insertImage(key, multipartFile.getBytes());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", "/shop/manager/get-image?key="+key);
        return jsonObject;
    }

    public void delete(ManagerProductDeleteVo deleteVo){
        if(deleteVo == null){
            deleteVo.setResult(ManagerProductDeleteResult.FAILURE);
            return;
        }

        this.managerModel.deleteProduct(
                deleteVo.getProductIndex(),
                deleteVo.getUserDto().getIndex());

        deleteVo.setResult(ManagerProductDeleteResult.SUCCESS);
    }

    public void adminProduct(AdminListVo adminListVo){
        if(adminListVo == null){
            adminListVo.setResult(AdminListResult.FAILURE);
            return;
        }

        if(this.managerModel.selectProductByMemberCount(adminListVo.getUserDto().getIndex()) == 0){
            adminListVo.setResult(AdminListResult.NO_SUCH_ARTICLE);
            return;
        }

        int productByLikeCountCount = this.managerModel.selectProductByMemberCount(adminListVo.getUserDto().getIndex());
        int maxPage = productByLikeCountCount / BOARDS_PER_PAGE + (productByLikeCountCount % BOARDS_PER_PAGE == 0 ? 0 : 1);
        int leftPage = Math.max(1, adminListVo.getPage() - PAGE_RANGE);
        int rightPage = Math.min(maxPage, adminListVo.getPage() + PAGE_RANGE);
        adminListVo.setMaxPage(maxPage);
        adminListVo.setLeftPage(leftPage);
        adminListVo.setRightPage(rightPage);

        ArrayList<ProductDto> productDtos = this.managerModel.selectProductByMember(
                adminListVo.getUserDto().getIndex(),
                ShopService.BOARDS_PER_PAGE * (adminListVo.getPage() - 1),
                BOARDS_PER_PAGE);

        adminListVo.setProductDtos(productDtos);
        adminListVo.setResult(AdminListResult.SUCCESS);
    }


    public byte[] getImage(String key){
        return this.managerModel.selectImage(key).getValue();
    }

    public byte[] getImageView(String productIndex){
        return this.managerModel.selectProductImage(productIndex).getValue();
    }
}
