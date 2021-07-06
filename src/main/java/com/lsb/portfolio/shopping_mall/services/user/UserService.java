package com.lsb.portfolio.shopping_mall.services.user;

import com.lsb.portfolio.shopping_mall.dtos.cart.CartTotalCountDto;
import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.models.user.IUserModel;
import com.lsb.portfolio.shopping_mall.enums.user.UserLoginResult;
import com.lsb.portfolio.shopping_mall.enums.user.UserRegisterResult;
import com.lsb.portfolio.shopping_mall.services.Regex;
import com.lsb.portfolio.shopping_mall.vos.user.UserLoginVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserRegisterVo;
import org.springframework.stereotype.Service;

@Service
public class UserService extends Regex {
    private final IUserModel userModel;

    public UserService(IUserModel userModel) {
        this.userModel = userModel;
    }

    public void login(UserLoginVo userLoginVo){
        UserDto userDto = this.userModel.selectUser(userLoginVo.getEmail(), userLoginVo.getPassword());
        CartTotalCountDto cartTotalCountDto = this.userModel.selectCartCountByMember(userLoginVo.getEmail(), userLoginVo.getPassword());

        if(this.userModel.selectLogin(userLoginVo) > 0){
            userLoginVo.setResult(UserLoginResult.SUCCESS);
            userLoginVo.setUserDto(userDto);
            userLoginVo.setCartTotalCountDto(cartTotalCountDto);
        }else{
            userLoginVo.setResult(UserLoginResult.FAILURE);
        }
    }

    public void register(UserRegisterVo userRegisterVo){
        if(userRegisterVo == null){
            userRegisterVo.setResult(UserRegisterResult.FAILURE);
            return;
        }

        if(this.userModel.selectEmailCount(userRegisterVo.getEmail()) > 0){
            userRegisterVo.setResult(UserRegisterResult.DUPLICATE_EMAIL);
        }else if(this.userModel.selectNicknameCount(userRegisterVo.getNickname()) > 0){
            userRegisterVo.setResult(UserRegisterResult.DUPLICATE_NICKNAME);
        }else if(this.userModel.selectContactCount(
                userRegisterVo.getContactFirst(),
                userRegisterVo.getContactSecond(),
                userRegisterVo.getContactThird()) > 0){
            userRegisterVo.setResult(UserRegisterResult.DUPLICATE_CONTACT);
        }else{
            this.userModel.insertUser(userRegisterVo);
            userRegisterVo.setResult(UserRegisterResult.SUCCESS);
        }
    }
}
