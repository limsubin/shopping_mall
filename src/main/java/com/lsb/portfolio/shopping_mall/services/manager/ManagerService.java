package com.lsb.portfolio.shopping_mall.services.manager;

import com.lsb.portfolio.shopping_mall.dtos.category.CategoryDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.manager.ManagerProductCreateResult;
import com.lsb.portfolio.shopping_mall.models.manager.IManagerModel;
import com.lsb.portfolio.shopping_mall.utils.CryptoUtil;
import com.lsb.portfolio.shopping_mall.vos.manager.ManagerProductCreateVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserVo;
import jdk.nashorn.internal.parser.JSONParser;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class ManagerService {
    private final IManagerModel managerModel;

    @Autowired
    public ManagerService(IManagerModel managerModel) {
        this.managerModel = managerModel;
    }

    public List<CategoryDto> getCategories(){
        return this.managerModel.selectCategories();
    }

    public void create(UserDto userDto, ManagerProductCreateVo productCreateVo) throws IOException {
        //TODO 그리고 서버에 들어갈때 null이 int에 들어갈려고 하면 먼저 막기
        //TODO 모든 insert에 들어가는 값이 제대로 있는지 정확한 값인지 확인을 한 다음 insert문 실행하기

        //selectCategoryCount 카테고리가 맞는지 확인, null이 있는지 확인
        if(productCreateVo == null){
            productCreateVo.setResult(ManagerProductCreateResult.FAILURE);
            return;
        }

        if(this.managerModel.selectCategoryCount(productCreateVo.getCategory()) == 0){
            productCreateVo.setResult(ManagerProductCreateResult.NO_CATEGORY);
            return;
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
        JSONArray sizeArray = JSONArray.fromObject(productCreateVo.getSizes());

        for(int i = 0; i< sizeArray.size(); i++){
            this.managerModel.insertSize(productLastIndex, (String) sizeArray.get(i));
        }

        JSONArray colorArray = JSONArray.fromObject(productCreateVo.getColors());
        for(int i = 0; i< colorArray.size(); i++){
            this.managerModel.insertColor(productLastIndex, (String) colorArray.get(i));
        }

        String optionStr = productCreateVo.getOptionDetail();
        //직렬화 시켜 가져온 오브잭트 배열을 JSONArray 형식으로 바꿔준다.
        JSONArray optionDetailArray = JSONArray.fromObject(optionStr);

        if(optionDetailArray == null){
            productCreateVo.setResult(ManagerProductCreateResult.FAILURE);
            return;
        }

        for (int i = 0; i<optionDetailArray.size(); i++){
            //JSONArray 형태의 값을 가져와 JSONObject로 풀어준다
            JSONObject obj = (JSONObject) optionDetailArray.get(i);
            if(this.managerModel.selectSizeCount(productLastIndex, (String) obj.get("size")) == 0
                && this.managerModel.selectColorCount(productLastIndex, (String) obj.get("color")) == 0){
                productCreateVo.setResult(ManagerProductCreateResult.NO_SELECT_COUNT);
                return;
            }

            System.out.println("productLastIndex: "+productLastIndex);
            System.out.println("size: "+obj.get("size"));
            System.out.println("color: "+obj.get("color"));
            int sizeIndex = this.managerModel.selectLikeSizeIndex(productLastIndex, (String) obj.get("size"));
            int colorIndex = this.managerModel.selectLikeColorIndex(productLastIndex, (String) obj.get("color"));
            this.managerModel.insertOptionDetail(
                    sizeIndex,
                    colorIndex,
                    Integer.parseInt(String.valueOf(obj.get("premium"))),
                    Integer.parseInt(String.valueOf(obj.get("stock"))));
        }

        productCreateVo.setResult(ManagerProductCreateResult.SUCCESS);
    }

    public JSONObject uploadImage(MultipartFile multipartFile) throws IOException{
        System.out.println("add image service1");
        String key = String.format("%s%f",
                new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()),
                Math.random());
        key = CryptoUtil.SHA1.hash(key);

        System.out.println("add image service2");
        this.managerModel.insertImage(key, multipartFile.getBytes());

        System.out.println("add image service3");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("url", "/category/get-image?key="+key);
        return jsonObject;
    }
}
