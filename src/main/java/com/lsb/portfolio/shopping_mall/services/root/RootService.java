package com.lsb.portfolio.shopping_mall.services.root;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import com.lsb.portfolio.shopping_mall.enums.root.AllProductResult;
import com.lsb.portfolio.shopping_mall.models.root.IRootModel;
import com.lsb.portfolio.shopping_mall.services.shop.ShopService;
import com.lsb.portfolio.shopping_mall.vos.root.AllProductVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class RootService {
    private final IRootModel rootModel;

    public static final int BOARDS_PER_PAGE = 20; //보드 페이지당
    public static final int PAGE_RANGE = 5;       //페이지 범위

    @Autowired
    public RootService(IRootModel rootModel) {
        this.rootModel = rootModel;
    }

    public void getRoot(AllProductVo allProductVo){
        if(allProductVo == null){
            allProductVo.setResult(AllProductResult.FAILURE);
            return;
        }

        int allProductCount = this.rootModel.selectAllProductCount();

        // "boardCategoryCount / BOARDS_PER_PAGE"을 먼저 나눠서 페이지 10당 몇개 인지 계산
        // 그리고 10으로 나누었을때 나머지가 0이 아니라는 것은 10이 아닌 게시물이 몇개 있다는 뜻이니까 +1을 해준다
        int maxPage = allProductCount / BOARDS_PER_PAGE + (allProductCount % BOARDS_PER_PAGE == 0 ? 0 : 1);
        int leftPage = Math.max(1, allProductVo.getPage() - PAGE_RANGE);
        int rightPage = Math.min(maxPage, allProductVo.getPage() + PAGE_RANGE);
        allProductVo.setMaxPage(maxPage);
        allProductVo.setLeftPage(leftPage);
        allProductVo.setRightPage(rightPage);

        ArrayList<ProductDto> allProductArray = this.rootModel.selectAllProduct(
                ShopService.BOARDS_PER_PAGE * (allProductVo.getPage() - 1),
                BOARDS_PER_PAGE);

        ArrayList<String> memberNicknameArray = new ArrayList<>();
        for(ProductDto allProduct : allProductArray){
            memberNicknameArray.add(
                    this.rootModel.selectMemberNickname(
                            allProduct.getMemberIndex(),
                            allProduct.getIndex())
            );
        }

        allProductVo.setProductDtos(allProductArray);
        allProductVo.setNicknameArray(memberNicknameArray);
        allProductVo.setResult(AllProductResult.SUCCESS);
    }
}
