package com.lsb.portfolio.shopping_mall.controllers.shop;

import com.lsb.portfolio.shopping_mall.dtos.shop.ProductColorDto;
import com.lsb.portfolio.shopping_mall.dtos.shop.ProductOptionDetailDto;
import com.lsb.portfolio.shopping_mall.dtos.shop.ProductSizeDto;
import com.lsb.portfolio.shopping_mall.dtos.review.ReviewDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.enums.shop.*;
import com.lsb.portfolio.shopping_mall.enums.like.LikeResult;
import com.lsb.portfolio.shopping_mall.vos.shop.ReviewVo;
import com.lsb.portfolio.shopping_mall.services.shop.ShopService;
import com.lsb.portfolio.shopping_mall.services.shop.manager.ManagerService;
import com.lsb.portfolio.shopping_mall.vos.shop.*;
import com.lsb.portfolio.shopping_mall.vos.search.SearchVo;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/shop")
@SessionAttributes({UserDto.CLASS_NAME, UserDto.CART_TOTAL_COUNT_NAME})
public class ShopController {
    private final ShopService shopService;
    private final ManagerService managerService;

    @Autowired
    public ShopController(ShopService shopService, ManagerService managerService) {
        this.shopService = shopService;
        this.managerService = managerService;
    }

    /*상품 목록 GET*/
    @RequestMapping(
            value = {"/list/{categoryCode}","list/{categoryCode}/{page}"},
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String listGet(
            Model model,
            @PathVariable("categoryCode") String categoryCode,
            @RequestParam("sub") Optional<String> subCategoryCode,
            @PathVariable("page") Optional<Integer> optionalPage,
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto){
        int page = optionalPage.orElse(1);
        String subCategory = subCategoryCode.orElse(null);
        ProductListVo productListVo = new ProductListVo(categoryCode, subCategory, page);
        
        this.shopService.getList(productListVo);
        
        model.addAttribute("listVo", productListVo);
        return "shop/list";
    }

    /*상품 상세페이지 GET*/
    @RequestMapping(
            value = "/read/{productIndex}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String readGet(
            @PathVariable("productIndex") int productIndex,
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            Model model){
        ProductReadVo productReadVo = new ProductReadVo(productIndex);
        productReadVo.setUserDto(userDto);
        this.shopService.getRead(productReadVo);

        List<ProductOptionDetailDto> optionDetails = this.managerService.getOptionDetails(productReadVo);
        List<ProductSizeDto> productSizes = this.managerService.getSizes(productReadVo);
        List<ProductColorDto> productColors = this.managerService.getColors(productReadVo);
        ArrayList<ReviewDto> reviews = this.shopService.getReview(productReadVo);
        Boolean isLike = this.shopService.getIsLikeByMember(productReadVo);

        model.addAttribute("readVo", productReadVo);
        model.addAttribute("sizes", productSizes);
        model.addAttribute("colors", productColors);
        model.addAttribute("optionDetails", optionDetails);
        model.addAttribute("reviews", reviews);
        model.addAttribute("isLike", isLike);
        return "shop/read";
    }

    /*장바구니 GET*/
    @RequestMapping(
            value = "/cart",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String cartGet(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            CartPrepareVo cartPrepareVo,
            Model model){
        if (userDto == null){
            return "user/user.login";
        }
        cartPrepareVo.setUserDto(userDto);
        this.shopService.getCart(cartPrepareVo);
        model.addAttribute("cartPrepareVo", cartPrepareVo);
        return "shop/cart";
    }

    /*장바구니 POST*/
    @ResponseBody
    @RequestMapping(
            value = "/cart/{productIndex}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String cartPost(
            @PathVariable("productIndex") int productIndex,
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            CartVo cartVo,
            Model model){
        if (userDto == null){
            JSONObject loginJson = new JSONObject();
            cartVo.setResult(CartResult.NO_LOGIN);
            loginJson.put("result", cartVo.getResultName().toLowerCase());
            return loginJson.toString();
        }
        ProductReadVo productReadVo = new ProductReadVo(productIndex);
        cartVo.setUserDto(userDto);
        cartVo.setProductReadVo(productReadVo);

        this.shopService.cart(cartVo);

        model.addAttribute("cartVo", cartVo);
        model.addAttribute(UserDto.CART_TOTAL_COUNT_NAME, cartVo.getCartTotalCountDto());   //장바구니 개수

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", cartVo.getResultName().toLowerCase());
        return jsonObject.toString();
    }

    /*주문 GET*/
    @RequestMapping(
            value = "/order/{orderIndex}",
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String orderGet(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            @PathVariable("orderIndex") int orderIndex,
            @RequestParam(value = "type", defaultValue = "") String type,
            Model model){
        if (userDto == null){
            return "user/user.login";
        }
        OrderPrepareVo orderPrepareVo = new OrderPrepareVo(orderIndex);
        orderPrepareVo.setUserDto(userDto);

        this.shopService.getOrder(orderPrepareVo);

        model.addAttribute("orderPrepareVo", orderPrepareVo);
        return type.equals("success") ? "shop/order.success" : "shop/order";
    }

    /*주문 POST*/
    @ResponseBody
    @RequestMapping(
            value = "/order",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String orderPost(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            OrderVo orderVo,
            Model model){
        if (userDto == null){
            JSONObject loginJson = new JSONObject();
            orderVo.setResult(OrderResult.NO_LOGIN);
            loginJson.put("result", orderVo.getResultName().toLowerCase());
            return loginJson.toString();
        }

        orderVo.setUserDto(userDto);

        System.out.println("cartArray: "+orderVo.getCartIndex());
        this.shopService.order(orderVo);
        model.addAttribute(UserDto.CART_TOTAL_COUNT_NAME, orderVo.getCartTotalCountDto());  //장바구니 개수

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", orderVo.getResultName().toLowerCase());
        jsonObject.put("orderIndex", orderVo.getOrderIndex());

        return jsonObject.toString();
    }

    /*바로 구매하기 POST*/
    @ResponseBody
    @RequestMapping(
            value = "/buy/{productIndex}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String orderPost(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            @PathVariable("productIndex") int productIndex,
            CartVo cartVo){
        if (userDto == null){
            JSONObject loginJson = new JSONObject();
            cartVo.setResult(CartResult.NO_LOGIN);
            loginJson.put("result", cartVo.getResultName().toLowerCase());
            return loginJson.toString();
        }
        ProductReadVo productReadVo = new ProductReadVo(productIndex);
        cartVo.setUserDto(userDto);
        cartVo.setProductReadVo(productReadVo);

        this.shopService.buy(cartVo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", cartVo.getResultName().toLowerCase());
        jsonObject.put("orderIndex", cartVo.getOrderIndex());

        return jsonObject.toString();
    }

    /*결제하기 POST*/
    @ResponseBody
    @RequestMapping(
            value = "/payment/{orderIndex}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String paymentPost(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            @PathVariable("orderIndex") int orderIndex,
            PaymentVo paymentVo){
        if (userDto == null){
            JSONObject loginJson = new JSONObject();
            paymentVo.setResult(PaymentResult.NO_LOGIN);
            loginJson.put("result", paymentVo.getResultName().toLowerCase());
            return loginJson.toString();
        }
        paymentVo.setUserDto(userDto);
        paymentVo.setOrderIndex(orderIndex);

        this.shopService.payment(paymentVo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", paymentVo.getResultName().toLowerCase());
        jsonObject.put("orderIndex", paymentVo.getOrderIndex());
        return jsonObject.toString();
    }

    /*주문 내역에서 리뷰 작성 POST*/
    @RequestMapping(
            value = "/paymentReview/{productIndex}/{orderIndex}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String paymentReviewGet(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            @PathVariable("productIndex") int productIndex,
            @PathVariable("orderIndex") int orderIndex,
            Model model){
        if(userDto == null){
            return "user/user.login";
        }
        PaymentReviewPrepareVo paymentReviewPrepareVo = new PaymentReviewPrepareVo(productIndex, orderIndex);
        paymentReviewPrepareVo.setUserDto(userDto);

        this.shopService.getProductInfo(paymentReviewPrepareVo);

        model.addAttribute("paymentReviewPrepareVo", paymentReviewPrepareVo);
        return "shop/review";
    }

    /*주문 내역에서 리뷰 작성 GET*/
    @ResponseBody
    @RequestMapping(
            value = "/paymentReview",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String paymentReviewPost(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            PaymentReviewVo paymentReviewVo){
        if(userDto == null){
            return "user/user.login";
        }
        paymentReviewVo.setUserDto(userDto);

        this.shopService.paymentReview(paymentReviewVo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", paymentReviewVo.getResultName().toLowerCase());
        return jsonObject.toString();
    }

    /*상품 상세페이지에서 리뷰 작성 POST*/
    @ResponseBody
    @RequestMapping(
            value = "/review/{productIndex}",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String reviewPost(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            @PathVariable("productIndex") int productIndex,
            ReviewVo reviewVo){
        if(userDto == null){
            JSONObject loginJson = new JSONObject();
            reviewVo.setResult(ReviewResult.NO_LOGIN);
            loginJson.put("result", reviewVo.getResultName().toLowerCase());
            return loginJson.toString();
        }
        reviewVo.setUserDto(userDto);
        reviewVo.setProductIndex(productIndex);

        this.shopService.review(reviewVo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", reviewVo.getResultName().toLowerCase());
        return jsonObject.toString();
    }

    /*검색 GET*/
    @RequestMapping(
            value = {"/search","/search/{page}"},
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String searchGet(
            Model model,
            @RequestParam("keyword") String keyword,
            @PathVariable("page") Optional<Integer> optionalPage) {
        int page = optionalPage.orElse(1);
        SearchVo searchVo = new SearchVo(keyword, page);

        this.shopService.searchArticles(searchVo);
        model.addAttribute("searchVo", searchVo);
        return "shop/search";
    }

    /*좋아요 GET*/
    @RequestMapping(
            value = {"/like","/like/{page}"},
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String likeGet(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            @PathVariable("page") Optional<Integer> optionalPage,
            Model model){
        if (userDto == null){
            return "user/user.login";
        }
        int page = optionalPage.orElse(1);
        LikePrepareVo likePrepareVo = new LikePrepareVo(page);
        likePrepareVo.setUserDto(userDto);

        this.shopService.getLike(likePrepareVo);

        model.addAttribute("likePrepareVo", likePrepareVo);
        return "shop/like";
    }

    /*좋아요 POST*/
    @ResponseBody
    @RequestMapping(
            value = "/like",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String likePost(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            LikeVo likeVo){
        if (userDto == null){
            JSONObject loginJson = new JSONObject();
            likeVo.setResult(LikeResult.NO_LOGIN);
            loginJson.put("result", likeVo.getResultName().toLowerCase());
            return loginJson.toString();
        }
        likeVo.setUserDto(userDto);
        if(likeVo.isLike()){
            this.shopService.likeInsert(likeVo);
        }else{
            this.shopService.likeDelete(likeVo);
        }

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", likeVo.getResultName().toLowerCase());
        return jsonObject.toString();
    }

    /*주문 내역 GET*/
    @RequestMapping(
            value = {"/paymentStatus", "/paymentStatus/{page}"},
            method = RequestMethod.GET,
            produces = MediaType.TEXT_HTML_VALUE)
    public String paymentStatusGet(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            @PathVariable("page") Optional<Integer> optionalPage,
            Model model){
        if(userDto == null){
            return "user/user.login";
        }
        int page = optionalPage.orElse(1);
        PaymentStatusVo paymentStatusVo = new PaymentStatusVo(page);
        paymentStatusVo.setUserDto(userDto);

        this.shopService.getPaymentStatus(paymentStatusVo);

        model.addAttribute("paymentStatusVo", paymentStatusVo);
        return "shop/paymentStatus";
    }

    /*카트 삭제 POST*/
    @ResponseBody
    @RequestMapping(
            value = "/cartDelete",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String cartDeletePost(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            CartDeleteVo cartDeleteVo,
            Model model){
        if (userDto == null){
            JSONObject loginJson = new JSONObject();
            cartDeleteVo.setResult(CartDeleteResult.NO_LOGIN);
            loginJson.put("result", cartDeleteVo.getResultName().toLowerCase());
            return loginJson.toString();
        }
        cartDeleteVo.setUserDto(userDto);

        this.shopService.cartDelete(cartDeleteVo);
        model.addAttribute(UserDto.CART_TOTAL_COUNT_NAME, cartDeleteVo.getCartTotalCountDto());

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", cartDeleteVo.getResultName().toLowerCase());
        return jsonObject.toString();
    }

    /*주문 상품 삭제*/
    @ResponseBody
    @RequestMapping(
            value = "/orderProductDelete",
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public String orderProductDeletePost(
            @ModelAttribute(UserDto.CLASS_NAME) UserDto userDto,
            OrderProductDeleteVo orderProductDeleteVo){
        if (userDto == null){
            JSONObject loginJson = new JSONObject();
            orderProductDeleteVo.setResult(OrderProductDeleteResult.NO_LOGIN);
            loginJson.put("result", orderProductDeleteVo.getResultName().toLowerCase());
            return loginJson.toString();
        }
        orderProductDeleteVo.setUserDto(userDto);

        this.shopService.orderProductDelete(orderProductDeleteVo);

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("result", orderProductDeleteVo.getResultName().toLowerCase());
        return jsonObject.toString();
    }

    @ModelAttribute(UserDto.CLASS_NAME)
    public UserDto userDto(){
        return null;
    }
}
