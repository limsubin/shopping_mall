package com.lsb.portfolio.shopping_mall.models.shop;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductDto;
import com.lsb.portfolio.shopping_mall.dtos.cart.CartDto;
import com.lsb.portfolio.shopping_mall.dtos.order.OrderAddressDto;
import com.lsb.portfolio.shopping_mall.dtos.order.OrderDto;
import com.lsb.portfolio.shopping_mall.dtos.payment.PaymentStatusDto;
import com.lsb.portfolio.shopping_mall.dtos.review.ReviewDto;
import com.lsb.portfolio.shopping_mall.vos.shop.CartVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface IShopModel {
    ArrayList<ProductDto> selectProducts(
            @Param("categoryCode") String categoryCode,
            @Param("offset") int offset,
            @Param("perPage") int perPage);

    ArrayList<ProductDto> selectSubProducts(
            @Param("categoryCode") String categoryCode,
            @Param("subCategoryCode") String subCategoryCode,
            @Param("offset") int offset,
            @Param("perPage") int perPage);

    int selectShopCategoryCount(
            @Param("categoryCode") String categoryCode);

    int selectShopSubCategoryCount(
            @Param("subCategoryCode") String subCategoryCode);

    ProductDto selectByProductId(
            @Param("productIndex") int index);

    void updateArticleViewed(
            @Param("productIndex") int index);

    void insertCart(CartVo cartVo);

    ArrayList<CartDto> selectCartProduct(
            @Param("memberIndex") int memberIndex);

    CartVo selectCartByCartIndex(
            @Param("memberIndex") int memberIndex,
            @Param("cartIndex") int cartIndex);

    void insertCart(
            @Param("productIndex") int productIndex,
            @Param("memberIndex") int memberIndex,
            @Param("productSizeIndex") String productSizeIndex,
            @Param("productColorIndex") String productColorIndex,
            @Param("cartCount") String cartCount,
            @Param("cartSubtotalPrice") String cartSubtotalPrice);

    int selectCartCount(
            @Param("productIndex") int productIndex,
            @Param("memberIndex") int memberIndex,
            @Param("productSizeIndex") String productSizeIndex,
            @Param("productColorIndex") String productColorIndex);

    void insertOrder(
            @Param("memberIndex") int memberIndex,
            @Param("addressPost") String addressPost,
            @Param("address") String address,
            @Param("addressDetail") String addressDetail);

    void insertOrderProduct(
            @Param("memberIndex") int memberIndex,
            @Param("orderIndex") int orderIndex,
            @Param("productIndex") int productIndex,
            @Param("productSizeIndex") String productSizeIndex,
            @Param("productColorIndex") String productColorIndex,
            @Param("cartCount") String cartCount,
            @Param("cartSubtotalPrice") String cartSubtotalPrice);

    void updateOrderTotalPrice(
            @Param("memberIndex") int memberIndex,
            @Param("orderIndex") int orderIndex,
            @Param("totalPrice") int totalPrice);

    ArrayList<OrderDto> selectOrderProduct(
            @Param("memberIndex") int memberIndex,
            @Param("orderIndex") int orderIndex);

    OrderDto selectOrderProductOne(
            @Param("memberIndex") int memberIndex,
            @Param("productIndex") int productIndex,
            @Param("orderIndex") int orderIndex);

    OrderAddressDto selectOrderAddress(
            @Param("memberIndex") int memberIndex,
            @Param("orderIndex") int orderIndex);

    void insertPayment(
            @Param("orderIndex") int orderIndex,
            @Param("paymentMethod") String paymentMethod);

    void updateOrderPaymentCompleted(
            @Param("orderIndex") int orderIndex,
            @Param("memberIndex") int memberIndex,
            @Param("addressPost") String addressPost,
            @Param("address") String address,
            @Param("addressDetail") String addressDetail);

    void deleteCart(
        @Param("productIndex") int productIndex,
        @Param("memberIndex") int memberIndex,
        @Param("sizeIndex") int sizeIndex,
        @Param("colorIndex") int colorIndex);

    List<Integer> selectOrderIndex(
            @Param("memberIndex") int memberIndex,
            @Param("productIndex") int productIndex);

    int selectOrderIndexCount(
            @Param("memberIndex") int memberIndex,
            @Param("productIndex") int productIndex);

/*    boolean selectPaymentState(
            @Param("memberIndex") int memberIndex,
            @Param("orderIndex") int orderIndex);*/

    ArrayList<PaymentStatusDto> selectPaymentState(
            @Param("memberIndex") int memberIndex,
            @Param("offset") int offset,
            @Param("perPage") int perPage);

    int selectPaymentStateByMemberCount(
            @Param("memberIndex") int memberIndex);

    void insertReview(
            @Param("productIndex") int productIndex,
            @Param("memberIndex") int memberIndex,
            @Param("orderIndex") int orderIndex,
            @Param("content") String content,
            @Param("ratingOptions") int ratingOptions);

    ArrayList<ReviewDto> selectMemberByReview(@Param("productIndex") int productIndex);

    int selectOrderIndexByReviewCompletedCount(
            @Param("memberIndex") int memberIndex,
            @Param("productIndex") int productIndex,
            @Param("orderIndex") int orderIndex);

    int selectReviewByMemberCount(
            @Param("memberIndex") int memberIndex,
            @Param("productIndex") int productIndex,
            @Param("orderIndex") int orderIndex);

    int selectArticleCountByContent(@Param("keyword") String keyword);

    ArrayList<ProductDto> selectArticlesByProductName(
            @Param("keyword") String keyword,
            @Param("offset") int offset,
            @Param("perPage") int perPage);

    void insertLike(
            @Param("memberIndex") int memberIndex,
            @Param("productIndex") int productIndex);

    void deleteLike(
            @Param("memberIndex") int memberIndex,
            @Param("productIndex") int productIndex);

    ArrayList<ProductDto> selectProductByLike(
            @Param("memberIndex") int memberIndex,
            @Param("offset") int offset,
            @Param("perPage") int perPage);

    int selectProductByLikeCount(
            @Param("memberIndex") int memberIndex);

   /* boolean selectIsLikeByMember(
            @Param("memberIndex") int memberIndex,
            @Param("productIndex") int productIndex);*/

    int selectIsLikeByMemberCount(
            @Param("memberIndex") int memberIndex,
            @Param("productIndex") int productIndex);

    void deleteSelectedCart(
            @Param("cartIndex") int cartIndex);

    void updateCartCount(
            @Param("memberIndex") int memberIndex,
            @Param("cartIndex") int cartIndex,
            @Param("cartCount") int cartCount);

    void deleteSelectedOrderProduct(
            @Param("orderIndex") int orderIndex,
            @Param("productIndex") int productIndex);

    void updateOrderCountByProduct(
            @Param("memberIndex") int memberIndex,
            @Param("orderIndex") int orderIndex,
            @Param("productIndex") int productIndex,
            @Param("orderCount") int orderCount);
}
