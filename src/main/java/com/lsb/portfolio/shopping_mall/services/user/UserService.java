package com.lsb.portfolio.shopping_mall.services.user;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.models.user.IUserModel;
import com.lsb.portfolio.shopping_mall.nums.UserLoginResult;
import com.lsb.portfolio.shopping_mall.nums.UserRegisterResult;
import com.lsb.portfolio.shopping_mall.vos.user.UserLoginVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserRegisterVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserVo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final IUserModel userModel;

    public UserService(IUserModel userModel) {
        this.userModel = userModel;
    }

    public void login(UserLoginVo userLoginVo){
        UserDto userDto = this.userModel.selectUser(userLoginVo.getEmail(), userLoginVo.getPassword());

        if(this.userModel.selectLogin(userLoginVo) > 0){
            userLoginVo.setUserLoginResult(UserLoginResult.SUCCESS);
            userLoginVo.setUserDto(userDto);
        }else{
            userLoginVo.setUserLoginResult(UserLoginResult.FAILURE);
        }
    }

    public void register(UserRegisterVo userRegisterVo){
        if(this.userModel.selectEmailCount(userRegisterVo.getEmail()) > 0){
            userRegisterVo.setUserRegisterResult(UserRegisterResult.DUPLICATE_EMAIL);
        }else if(this.userModel.selectNicknameCount(userRegisterVo.getNickname()) > 0){
            userRegisterVo.setUserRegisterResult(UserRegisterResult.DUPLICATE_NICKNAME);
        }else if(this.userModel.selectContactCount(userRegisterVo.getContact()) > 0){
            userRegisterVo.setUserRegisterResult(UserRegisterResult.DUPLICATE_CONTACT);
        }else{
            this.userModel.insertUser(userRegisterVo);
            userRegisterVo.setUserRegisterResult(UserRegisterResult.SUCCESS);
        }
    }
}
