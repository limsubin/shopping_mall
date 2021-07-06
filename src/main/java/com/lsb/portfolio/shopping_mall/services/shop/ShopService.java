package com.lsb.portfolio.shopping_mall.services.shop;

import com.lsb.portfolio.shopping_mall.dtos.cart.CartTotalCountDto;
import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import com.lsb.portfolio.shopping_mall.dtos.shop.ProductSubDto;
import com.lsb.portfolio.shopping_mall.dtos.cart.CartDto;
import com.lsb.portfolio.shopping_mall.dtos.category.CategoryDto;
import com.lsb.portfolio.shopping_mall.dtos.category.SubCategoryDto;
import com.lsb.portfolio.shopping_mall.dtos.order.OrderAddressDto;
import com.lsb.portfolio.shopping_mall.dtos.order.OrderDto;
import com.lsb.portfolio.shopping_mall.dtos.payment.PaymentStatusDto;
import com.lsb.portfolio.shopping_mall.dtos.review.ReviewDto;
import com.lsb.portfolio.shopping_mall.enums.shop.*;
import com.lsb.portfolio.shopping_mall.enums.shop.search.SearchResult;
import com.lsb.portfolio.shopping_mall.enums.like.LikeResult;
import com.lsb.portfolio.shopping_mall.models.root.IRootModel;
import com.lsb.portfolio.shopping_mall.models.shop.IShopModel;
import com.lsb.portfolio.shopping_mall.models.shop.manager.IManagerModel;
import com.lsb.portfolio.shopping_mall.models.user.IUserModel;
import com.lsb.portfolio.shopping_mall.vos.shop.*;
import com.lsb.portfolio.shopping_mall.vos.search.SearchVo;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShopService {
    private final IShopModel shopModel;
    private final IUserModel userModel;
    private final IManagerModel managerModel;
    private final IRootModel rootModel;

    public static final int BOARDS_PER_PAGE = 10; //보드 페이지당
    public static final int PAGE_RANGE = 5;       //페이지 범위

    @Autowired
    public ShopService(IShopModel shopModel, IUserModel userModel, IManagerModel managerModel, IRootModel rootModel) {
        this.shopModel = shopModel;
        this.userModel = userModel;
        this.managerModel = managerModel;
        this.rootModel = rootModel;
    }

    public void getList(ProductListVo productListVo){
        if(this.shopModel.selectShopCategoryCount(productListVo.getCategoryCode()) == 0){
            productListVo.setResult(ProductListResult.FAILURE);
            return;
        }

        int shopCategoryCount = this.shopModel.selectShopCategoryCount(productListVo.getCategoryCode());

        if(productListVo.getSubCategoryCode() != null){
            shopCategoryCount = this.shopModel.selectShopSubCategoryCount(productListVo.getSubCategoryCode());
        }

        // "boardCategoryCount / BOARDS_PER_PAGE"을 먼저 나눠서 페이지 10당 몇개 인지 계산
        // 그리고 10으로 나누었을때 나머지가 0이 아니라는 것은 10이 아닌 게시물이 몇개 있다는 뜻이니까 +1을 해준다
        int maxPage = shopCategoryCount / BOARDS_PER_PAGE + (shopCategoryCount % BOARDS_PER_PAGE == 0 ? 0 : 1);
        int leftPage = Math.max(1, productListVo.getPage() - PAGE_RANGE);
        int rightPage = Math.min(maxPage, productListVo.getPage() + PAGE_RANGE);
        productListVo.setMaxPage(maxPage);
        productListVo.setLeftPage(leftPage);
        productListVo.setRightPage(rightPage);

        ArrayList<ProductDto> lists = this.shopModel.selectProducts(
                productListVo.getCategoryCode(),
                ShopService.BOARDS_PER_PAGE * (productListVo.getPage() - 1),
                BOARDS_PER_PAGE);

        List<CategoryDto> categoryDtos = this.managerModel.selectCategories();
        List<SubCategoryDto> subCategoryDtos = this.managerModel.selectSubCategories();
        productListVo.setCategoryDtos(categoryDtos);
        productListVo.setSubCategoryDtos(subCategoryDtos);

        if(productListVo.getSubCategoryCode() != null){
            lists = this.shopModel.selectSubProducts(
                    productListVo.getCategoryCode(),
                    productListVo.getSubCategoryCode(),
                    ShopService.BOARDS_PER_PAGE * (productListVo.getPage() - 1),
                    BOARDS_PER_PAGE);
        }

        ArrayList<String> memberNicknameArray = new ArrayList<>();
        for(ProductDto allProduct : lists){
            memberNicknameArray.add(
                    this.rootModel.selectMemberNickname(
                            allProduct.getMemberIndex(),
                            allProduct.getIndex())
            );
        }

        productListVo.setLists(lists);
        productListVo.setNicknameArray(memberNicknameArray);
        productListVo.setResult(ProductListResult.SUCCESS);
    }

    public void getRead(ProductReadVo productReadVo){
        ProductDto productDto = this.shopModel.selectByProductId(productReadVo.getProductIndex());
        ProductSubDto productSubDto = this.managerModel.selectBySubProductId(productReadVo.getProductIndex());
        if (productDto == null) {
            productReadVo.setResult(ProductReadResult.NO_SUCH_ARTICLE);
            return;
        }

        this.shopModel.updateArticleViewed(productReadVo.getProductIndex());
        productReadVo.setProductDto(productDto);
        productReadVo.setProductSubDto(productSubDto);
        productReadVo.setResult(ProductReadResult.SUCCESS);

        /*if(productReadVo.getUserDto() == null){
            productReadVo.setReviewResult(ReviewResult.NOT_LOGGED_IN);
            return;
        }

        if(this.boardModel.selectOrderIndexCount(
                productReadVo.getUserDto().getIndex(),
                productReadVo.getProductIndex()) == 0){
            productReadVo.setReviewResult(ReviewResult.NO_ORDER_HISTORY);
            return;
        }

        List<Integer> orderIndex = this.boardModel.selectOrderIndex(
                productReadVo.getUserDto().getIndex(),
                productReadVo.getProductIndex());
        //이미 리뷰 작성함
        if(this.boardModel.selectReviewByMemberCount(
                productReadVo.getUserDto().getIndex(),
                productReadVo.getProductIndex(),
                orderIndex) > 0){
            productReadVo.setReviewResult(ReviewResult.REVIEW_COMPLETED);
            return;
        }*/
    }

    public void getCart(CartPrepareVo cartPrepareVo){
        ArrayList<CartDto> cartDto = this.shopModel.selectCartProduct(cartPrepareVo.getUserDto().getIndex());
        if (cartDto == null) {
            cartPrepareVo.setResult(CartPrepareResult.NO_SUCH_ARTICLE);
            return;
        }

        cartPrepareVo.setCartDto(cartDto);
        cartPrepareVo.setResult(CartPrepareResult.SUCCESS);
    }

    public void cart(CartVo cartVo){
        if(cartVo == null){
            cartVo.setResult(CartResult.FAILURE);
            return;
        }

        if(cartVo.getUserDto() == null){
            cartVo.setResult(CartResult.NO_LOGIN);
            return;
        }

        JSONArray sizeArray = JSONArray.fromObject((cartVo.getProductSizeIndex()));
        JSONArray colorArray = JSONArray.fromObject((cartVo.getProductColorIndex()));
        JSONArray cartCountArray = JSONArray.fromObject((cartVo.getCartCount()));
        JSONArray cartSubtotalPriceArray = JSONArray.fromObject((cartVo.getCartSubtotalPrice()));

        /*이미 추가된 상품인지 확인*/
        for(int i = 0; i<sizeArray.size(); i++){
            if(this.shopModel.selectCartCount(
                    cartVo.getProductReadVo().getProductIndex(),
                    cartVo.getUserDto().getIndex(),
                    sizeArray.get(i).toString(),
                    colorArray.get(i).toString()) > 0){
                cartVo.setResult(CartResult.ALREADY_IN_CART);
                return;
            }
        }

        //result : [["111","112"],["81","82"],["1","1"],["100030","100503"]]
        /*JSONArray cartArray = new JSONArray();
        JSONObject cartObject = new JSONObject();
        // for문에서 sizeArray.size() 이외에 위에 JSONArray가 전부 같은 length인지 확인을 못하나?
        for(int i = 0; i<sizeArray.size(); i++){
            cartObject.put("sizeIndex", sizeArray.get(i));
            cartObject.put("colorIndex", colorArray.get(i));
            cartObject.put("cartCount", cartCountArray.get(i));
            cartObject.put("cartSubtotalPrice", cartSubtotalPriceArray.get(i));
            cartArray.add(cartObject);
        }*/

        /*장바구니 insert*/
        for(int i = 0; i<sizeArray.size(); i++){
            this.shopModel.insertCart(
                    cartVo.getProductReadVo().getProductIndex(),
                    cartVo.getUserDto().getIndex(),
                    sizeArray.get(i).toString(),
                    colorArray.get(i).toString(),
                    cartCountArray.get(i).toString(),
                    cartSubtotalPriceArray.get(i).toString());
        }

        CartTotalCountDto cartTotalCountDto = this.userModel.selectCartCountByMember(
                                                            cartVo.getUserDto().getEmail(),
                                                            cartVo.getUserDto().getPassword());

        cartVo.setResult(CartResult.SUCCESS);
        cartVo.setCartTotalCountDto(cartTotalCountDto);
    }

    public void buy(CartVo cartVo){
        if(cartVo == null){
            cartVo.setResult(CartResult.FAILURE);
            return;
        }

        JSONArray sizeArray = JSONArray.fromObject((cartVo.getProductSizeIndex()));
        JSONArray colorArray = JSONArray.fromObject((cartVo.getProductColorIndex()));
        JSONArray cartCountArray = JSONArray.fromObject((cartVo.getCartCount()));
        JSONArray cartSubtotalPriceArray = JSONArray.fromObject((cartVo.getCartSubtotalPrice()));

        for(int i = 0; i<sizeArray.size(); i++){
            if(sizeArray.get(i).equals("undefined")
                    && colorArray.get(i).equals("undefined")
                    && cartCountArray.get(i).equals("undefined")
                    && cartSubtotalPriceArray.get(i).equals("undefined")){
                cartVo.setResult(CartResult.VALUE_UNDEFINED);
                return;
            }
        }

/*        for(int i = 0; i<sizeArray.size(); i++){
            if(this.boardModel.selectCartCount(
                    cartVo.getProductReadVo().getProductIndex(),
                    cartVo.getUserDto().getIndex(),
                    sizeArray.get(i).toString(),
                    colorArray.get(i).toString()) > 0){
                cartVo.setResult(CartResult.ALREADY_IN_CART);
                return;
            }
        }*/

        //result : [["111","112"],["81","82"],["1","1"],["100030","100503"]]
        /*JSONArray cartArray = new JSONArray();
        JSONObject cartObject = new JSONObject();
        // for문에서 sizeArray.size() 이외에 위에 JSONArray가 전부 같은 length인지 확인을 못하나?
        for(int i = 0; i<sizeArray.size(); i++){
            cartObject.put("sizeIndex", sizeArray.get(i));
            cartObject.put("colorIndex", colorArray.get(i));
            cartObject.put("cartCount", cartCountArray.get(i));
            cartObject.put("cartSubtotalPrice", cartSubtotalPriceArray.get(i));
            cartArray.add(cartObject);
        }*/

        this.shopModel.insertOrder(
                cartVo.getUserDto().getIndex(),
                cartVo.getUserDto().getAddressPost(),
                cartVo.getUserDto().getAddress(),
                cartVo.getUserDto().getAddress_detail());


        int orderLastIndex = this.managerModel.selectLastInsertId();
        int orderTotalPrice = 0;
        for(int i = 0; i<sizeArray.size(); i++){
            this.shopModel.insertOrderProduct(
                    cartVo.getUserDto().getIndex(),
                    orderLastIndex,
                    cartVo.getProductReadVo().getProductIndex(),
                    sizeArray.get(i).toString(),
                    colorArray.get(i).toString(),
                    cartCountArray.get(i).toString(),
                    cartSubtotalPriceArray.get(i).toString());
            orderTotalPrice = orderTotalPrice +
                    (Integer.parseInt(cartCountArray.get(i).toString()) * Integer.parseInt(cartSubtotalPriceArray.get(i).toString()));
        }

        this.shopModel.updateOrderTotalPrice(
                cartVo.getUserDto().getIndex(),
                orderLastIndex,
                orderTotalPrice);

        cartVo.setOrderIndex(orderLastIndex);
        cartVo.setResult(CartResult.SUCCESS);
    }

    public void getOrder(OrderPrepareVo orderPrepareVo){
        if(orderPrepareVo == null){
            orderPrepareVo.setResult(OrderPrepareResult.FAILURE);
            return;
        }

        ArrayList<OrderDto> orderDtos = this.shopModel.selectOrderProduct(
                orderPrepareVo.getUserDto().getIndex(),
                orderPrepareVo.getOrderIndex());

        OrderAddressDto orderAddress = this.shopModel.selectOrderAddress(
                orderPrepareVo.getUserDto().getIndex(),
                orderPrepareVo.getOrderIndex());

        orderPrepareVo.setOrderDto(orderDtos);
        orderPrepareVo.setOrderAddressDto(orderAddress);
        orderPrepareVo.setResult(OrderPrepareResult.SUCCESS);
    }

    public void order(OrderVo orderVo){
        if(orderVo == null){
            orderVo.setResult(OrderResult.FAILURE);
            return;
        }

        /*
        * OK_1) [주문하기] 클릭시 바로 POST 로 값을 전달
        * OK_2) 그 전달값을 order에 insert
        * OK_3) 그 다음 cart에 있는 값을 order_product에 insert
        *    OK_3-1) List<cartVo>를 해서 가져오면 될듯
        * OK_4) order_product의 price를 전부 합해서 order의 totalPrice에 update
        *
        * OK_5) order, order_product 값을 전부 채웠으면 view order 페이지로 이동
        * OK_6) view order/{orderIndex} 페이지에서 주소 입력, 결재 수단 입력 하면 POST paymenet 로 값을 전달
        * OK_7) payment에 값 insert -> 결제 완료 페이지 띄우기
        * OK_8) 마이페이지에 paymenet join해서 결재 완료 페이지, 장바구니 페이지 띄우기
        * */

        /*주문서 값 저장*/
        this.shopModel.insertOrder(
                orderVo.getUserDto().getIndex(),
                orderVo.getUserDto().getAddressPost(),
                orderVo.getUserDto().getAddress(),
                orderVo.getUserDto().getAddress_detail());

        /*장바구니에 담긴 값 가져오기*/
        ArrayList<CartVo> carts = new ArrayList<>();
        JSONArray cartIndexArray = JSONArray.fromObject(orderVo.getCartIndex());
        JSONArray cartCountArray = JSONArray.fromObject(orderVo.getCartCount());

        if(cartIndexArray == null){
            orderVo.setResult(OrderResult.FAILURE);
            return;
        }

        if(cartCountArray == null){
            orderVo.setResult(OrderResult.FAILURE);
            return;
        }

        /*주문 전 장바구니에서 변경된 상품 개수 업데이트*/
        int index = 0;
        for (Object cartCount : cartCountArray){
            this.shopModel.updateCartCount(
                    orderVo.getUserDto().getIndex(),
                    Integer.parseInt(String.valueOf(cartIndexArray.get(index))),
                    Integer.parseInt(String.valueOf(cartCount))
            );
            index++;
        }

        for(Object cartIndex : cartIndexArray){
            System.out.println("cartIndex: "+Integer.parseInt(String.valueOf(cartIndex)));
            carts.add(
                    this.shopModel.selectCartByCartIndex(
                            orderVo.getUserDto().getIndex(),
                            Integer.parseInt(String.valueOf(cartIndex)))
            );
        }

        /*장바구니 값을 주문 상세에 저장*/
        int orderLastIndex = this.managerModel.selectLastInsertId();
        int orderTotalPrice = 0;
        for(CartVo cart : carts){
            this.shopModel.insertOrderProduct(
                    orderVo.getUserDto().getIndex(),
                    orderLastIndex,
                    cart.getProductIndex(),
                    cart.getProductSizeIndex(),
                    cart.getProductColorIndex(),
                    cart.getCartCount(),
                    cart.getCartSubtotalPrice());
            orderTotalPrice = orderTotalPrice +
                    (Integer.parseInt(cart.getCartSubtotalPrice()) * Integer.parseInt(cart.getCartCount()));
        }

        for(CartVo cart : carts){
            this.shopModel.deleteCart(
                    cart.getProductIndex(),
                    orderVo.getUserDto().getIndex(),
                    Integer.parseInt(cart.getProductSizeIndex()),
                    Integer.parseInt(cart.getProductColorIndex()));
        }

        this.shopModel.updateOrderTotalPrice(
                orderVo.getUserDto().getIndex(),
                orderLastIndex,
                orderTotalPrice);

        /*세션의 장바구니 개수 갱신*/
        CartTotalCountDto cartTotalCountDto = this.userModel.selectCartCountByMember(
                orderVo.getUserDto().getEmail(),
                orderVo.getUserDto().getPassword());

        orderVo.setResult(OrderResult.SUCCESS);
        orderVo.setOrderIndex(orderLastIndex);
        orderVo.setCartTotalCountDto(cartTotalCountDto);
    }

    public void payment(PaymentVo paymentVo){
        if(paymentVo == null){
            paymentVo.setResult(PaymentResult.FAILURE);
            return;
        }

        this.shopModel.insertPayment(
                paymentVo.getOrderIndex(),
                paymentVo.getPaymentMethod());

        JSONArray orderCountArray = JSONArray.fromObject(paymentVo.getOrderCount());
        JSONArray productIndexArray = JSONArray.fromObject(paymentVo.getProductIndex());
        int index = 0;
        for(Object orderCount : orderCountArray){
            this.shopModel.updateOrderCountByProduct(
                    paymentVo.getUserDto().getIndex(),
                    paymentVo.getOrderIndex(),
                    Integer.parseInt(String.valueOf(productIndexArray.get(index))),
                    Integer.parseInt(String.valueOf(orderCount))
            );
            index++;
        }

        /*주문 상품 리스트*/
        ArrayList<OrderDto> orderProudctDtos = this.shopModel.selectOrderProduct(
                paymentVo.getUserDto().getIndex(),
                paymentVo.getOrderIndex());

        /*결제 전에 주문 수량을 변경, 총 가격 재계산*/
        int orderProductTotalPrice = 0;
        for(OrderDto orderProduct : orderProudctDtos){
            orderProductTotalPrice = orderProductTotalPrice +
                    (orderProduct.getProductPrice() * orderProduct.getOrderCount());
        }

        if(orderProductTotalPrice == 0){
            paymentVo.setResult(PaymentResult.FAILURE);
            return;
        }

        /*주문서의 총 가격 업데이트*/
        this.shopModel.updateOrderTotalPrice(
                paymentVo.getUserDto().getIndex(),
                paymentVo.getOrderIndex(),
                orderProductTotalPrice);

        this.shopModel.updateOrderPaymentCompleted(
                paymentVo.getOrderIndex(),
                paymentVo.getUserDto().getIndex(),
                paymentVo.getAddressPost(),
                paymentVo.getAddress(),
                paymentVo.getAddressDetail());

        paymentVo.setResult(PaymentResult.SUCCESS);
    }

/*    public boolean getPaymentStatus(ProductReadVo productReadVo){
        if(productReadVo.getUserDto() == null){
            return false;
        }

        int orderIndex = this.boardModel.selectOrderIndex(
                productReadVo.getUserDto().getIndex(),
                productReadVo.getProductIndex());

        return this.boardModel.selectPaymentState(
                productReadVo.getUserDto().getIndex(),
                orderIndex);
    }*/

    public void review(ReviewVo reviewVo){
        if(reviewVo == null){
            reviewVo.setResult(ReviewResult.FAILURE);
            return;
        }

        /*주문 내역이 없을 때*/
        if(this.shopModel.selectOrderIndexCount(
                reviewVo.getUserDto().getIndex(),
                reviewVo.getProductIndex()) == 0){
            reviewVo.setResult(ReviewResult.NO_ORDER_HISTORY);
            return;
        }

        /*이미 리뷰를 작성했을 때*/
        List<Integer> orderIndexs = this.shopModel.selectOrderIndex(
                    reviewVo.getUserDto().getIndex(),
                    reviewVo.getProductIndex());

        int orderIndex = 0;
        for(int i = 0; i<orderIndexs.size(); i++){
            //주문 내역 만큼 리뷰 작성 완료 됐는지 유무
            if(this.shopModel.selectReviewByMemberCount(
                    reviewVo.getUserDto().getIndex(),
                    reviewVo.getProductIndex(),
                    orderIndexs.get(i)) == orderIndexs.size()){
                reviewVo.setResult(ReviewResult.REVIEW_COMPLETED);
                break;
            }

            System.out.println("orderIndex: "+orderIndex);
            System.out.println("reviewCount: "+this.shopModel.selectOrderIndexByReviewCompletedCount(
                    reviewVo.getUserDto().getIndex(),
                    reviewVo.getProductIndex(),
                    orderIndex));
            System.out.println("orderIndex"+orderIndex);

            //주문 내역 기준으로 아직 리뷰 작성 안한 개수
            if(this.shopModel.selectOrderIndexByReviewCompletedCount(
                    reviewVo.getUserDto().getIndex(),
                    reviewVo.getProductIndex(),
                    orderIndexs.get(i)) == 0){
                orderIndex = orderIndexs.get(i);
                break;
            }
        }

        System.out.println("orderIndex: "+orderIndex);

        if(orderIndex == 0){
            reviewVo.setResult(ReviewResult.REVIEW_COMPLETED);
            return;
        }

        this.shopModel.insertReview(
                reviewVo.getProductIndex(),
                reviewVo.getUserDto().getIndex(),
                orderIndex,
                reviewVo.getContent(),
                reviewVo.getRatingOptions());

        reviewVo.setResult(ReviewResult.SUCCESS);
    }

    public void paymentReview(PaymentReviewVo paymentReviewVo){
        if(paymentReviewVo == null){
            paymentReviewVo.setResult(PaymentReviewResult.FAILURE);
            return;
        }

        /*주문 내역이 없을 때*/
        if(this.shopModel.selectOrderIndexCount(
                paymentReviewVo.getUserDto().getIndex(),
                paymentReviewVo.getProductIndex()) == 0){
            paymentReviewVo.setResult(PaymentReviewResult.NO_ORDER_HISTORY);
            return;
        }

        /*이미 리뷰를 작성했을 때*/
        if(this.shopModel.selectReviewByMemberCount(
                paymentReviewVo.getUserDto().getIndex(),
                paymentReviewVo.getProductIndex(),
                paymentReviewVo.getOrderIndex()) != 0){
            paymentReviewVo.setResult(PaymentReviewResult.REVIEW_COMPLETED);
        }

        System.out.println("productIndex: "+paymentReviewVo.getProductIndex());
        System.out.println("orderIndex: "+paymentReviewVo.getOrderIndex());

        this.shopModel.insertReview(
                paymentReviewVo.getProductIndex(),
                paymentReviewVo.getUserDto().getIndex(),
                paymentReviewVo.getOrderIndex(),
                paymentReviewVo.getContent(),
                paymentReviewVo.getRatingOptions());

        paymentReviewVo.setResult(PaymentReviewResult.SUCCESS);
    }

    public ArrayList<ReviewDto> getReview(ProductReadVo productReadVo){
        return this.shopModel.selectMemberByReview(productReadVo.getProductIndex());
    }

    public void searchArticles(SearchVo searchVo){
        if(searchVo == null){
            searchVo.setResult(SearchResult.FAILURE);
            return;
        }

        int articleCount = this.shopModel.selectArticleCountByContent(searchVo.getKeyword());
        int maxPage = articleCount / BOARDS_PER_PAGE + (articleCount % BOARDS_PER_PAGE == 0 ? 0 : 1);
        int leftPage = Math.max(1, searchVo.getPage() - PAGE_RANGE);
        int rightPage = Math.min(maxPage, searchVo.getPage() + PAGE_RANGE);
        searchVo.setMaxPage(maxPage);
        searchVo.setLeftPage(leftPage);
        searchVo.setRightPage(rightPage);

        ArrayList<ProductDto> articles = this.shopModel.selectArticlesByProductName(
                searchVo.getKeyword(),
                ShopService.BOARDS_PER_PAGE * (searchVo.getPage() - 1),
                BOARDS_PER_PAGE);

        searchVo.setResult(SearchResult.SUCCESS);
        searchVo.setProductDtos(articles);
    }

    public void likeInsert(LikeVo likeVo){
        if(likeVo == null){
            likeVo.setResult(LikeResult.FAILURE);
            return;
        }

        this.shopModel.insertLike(
                likeVo.getUserDto().getIndex(),
                likeVo.getProductIndex());
        likeVo.setResult(LikeResult.SUCCESS);
    }

    public void likeDelete(LikeVo likeVo){
        if(likeVo == null){
            likeVo.setResult(LikeResult.FAILURE);
            return;
        }

        this.shopModel.deleteLike(
                likeVo.getUserDto().getIndex(),
                likeVo.getProductIndex());
        likeVo.setResult(LikeResult.SUCCESS);
    }

    public void getLike(LikePrepareVo likePrepareVo){
        if(likePrepareVo == null){
            likePrepareVo.setResult(LikePrepareResult.FAILURE);
            return;
        }

        int productByLikeCountCount = this.shopModel.selectProductByLikeCount(likePrepareVo.getUserDto().getIndex());
        int maxPage = productByLikeCountCount / BOARDS_PER_PAGE + (productByLikeCountCount % BOARDS_PER_PAGE == 0 ? 0 : 1);
        int leftPage = Math.max(1, likePrepareVo.getPage() - PAGE_RANGE);
        int rightPage = Math.min(maxPage, likePrepareVo.getPage() + PAGE_RANGE);
        likePrepareVo.setMaxPage(maxPage);
        likePrepareVo.setLeftPage(leftPage);
        likePrepareVo.setRightPage(rightPage);

        ArrayList<ProductDto> productDtos =
                this.shopModel.selectProductByLike(
                        likePrepareVo.getUserDto().getIndex(),
                        ShopService.BOARDS_PER_PAGE * (likePrepareVo.getPage() - 1),
                        BOARDS_PER_PAGE);

        likePrepareVo.setProductDtos(productDtos);
        likePrepareVo.setResult(LikePrepareResult.SUCCESS);
    }

    public Boolean getIsLikeByMember(ProductReadVo productReadVo){
        if(productReadVo == null){
            productReadVo.setResult(ProductReadResult.FAILURE);
            return false;
        }

        if(productReadVo.getUserDto() == null){
            return false;
        }

        if(this.shopModel.selectIsLikeByMemberCount(
                productReadVo.getUserDto().getIndex(),
                productReadVo.getProductIndex()) == 0){
            return false;
        }

        return true;
    }

    public void getPaymentStatus(PaymentStatusVo paymentStatusVo){
        if(paymentStatusVo == null){
            paymentStatusVo.setResult(PaymentStatusResult.FAILURE);
            return;
        }

        int productByLikeCountCount = this.shopModel.selectPaymentStateByMemberCount(paymentStatusVo.getUserDto().getIndex());
        int maxPage = productByLikeCountCount / BOARDS_PER_PAGE + (productByLikeCountCount % BOARDS_PER_PAGE == 0 ? 0 : 1);
        int leftPage = Math.max(1, paymentStatusVo.getPage() - PAGE_RANGE);
        int rightPage = Math.min(maxPage, paymentStatusVo.getPage() + PAGE_RANGE);
        paymentStatusVo.setMaxPage(maxPage);
        paymentStatusVo.setLeftPage(leftPage);
        paymentStatusVo.setRightPage(rightPage);

        ArrayList<PaymentStatusDto> paymentStatusDtos = this.shopModel.selectPaymentState(
                                                            paymentStatusVo.getUserDto().getIndex(),
                                                            ShopService.BOARDS_PER_PAGE * (paymentStatusVo.getPage() - 1),
                                                            BOARDS_PER_PAGE);


        ArrayList<Integer> reviewStatusArray = new ArrayList<>();
        for(PaymentStatusDto payment : paymentStatusDtos){
            reviewStatusArray.add(
                    this.shopModel.selectReviewByMemberCount(
                            paymentStatusVo.getUserDto().getIndex(),
                            payment.getProductIndex(),
                            payment.getOrderIndex())
            );
        }

        paymentStatusVo.setReviewStatusArray(reviewStatusArray);
        paymentStatusVo.setPaymentStatusDtos(paymentStatusDtos);
        paymentStatusVo.setResult(PaymentStatusResult.SUCCESS);
    }

    public void cartDelete(CartDeleteVo cartDeleteVo){
        if(cartDeleteVo == null){
            cartDeleteVo.setResult(CartDeleteResult.FAILURE);
            return;
        }

        JSONArray cartIndexArray = JSONArray.fromObject(cartDeleteVo.getCartIndexArray());

        /*선택된 장바구니 삭제*/
        for (Object cartIndex : cartIndexArray) {
            this.shopModel.deleteSelectedCart(Integer.parseInt(String.valueOf(cartIndex)));
        }

        /*세션의 장바구니 개수 갱신*/
        CartTotalCountDto cartTotalCountDto = this.userModel.selectCartCountByMember(
                                                        cartDeleteVo.getUserDto().getEmail(),
                                                        cartDeleteVo.getUserDto().getPassword());

        cartDeleteVo.setResult(CartDeleteResult.SUCCESS);
        cartDeleteVo.setCartTotalCountDto(cartTotalCountDto);
    }

    public void orderProductDelete(OrderProductDeleteVo orderProductDeleteVo){
        if(orderProductDeleteVo == null){
            orderProductDeleteVo.setResult(OrderProductDeleteResult.FAILURE);
            return;
        }

        this.shopModel.deleteSelectedOrderProduct(
                orderProductDeleteVo.getOrderIndex(),
                orderProductDeleteVo.getProductIndex());

        orderProductDeleteVo.setResult(OrderProductDeleteResult.SUCCESS);
    }

    public void getProductInfo(PaymentReviewPrepareVo paymentReviewPrepareVo){
        if(paymentReviewPrepareVo == null){
            paymentReviewPrepareVo.setResult(PaymentReviewPrepareResult.FAILURE);
            return;
        }
        OrderDto orderDto = this.shopModel.selectOrderProductOne(
                                            paymentReviewPrepareVo.getUserDto().getIndex(),
                                            paymentReviewPrepareVo.getProductIndex(),
                                            paymentReviewPrepareVo.getOrderIndex());
        paymentReviewPrepareVo.setOrderDto(orderDto);
        paymentReviewPrepareVo.setResult(PaymentReviewPrepareResult.SUCCESS);
    }
}
