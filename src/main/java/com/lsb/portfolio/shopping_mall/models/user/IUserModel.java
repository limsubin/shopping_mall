package com.lsb.portfolio.shopping_mall.models.user;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.vos.user.UserLoginVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserRegisterVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface IUserModel {
    void insertUser(UserRegisterVo userRegisterVo);
    int selectEmailCount(String email);
    int selectNicknameCount(String nickname);
    int selectContactCount(String contact);

    UserDto selectUser(
            @Param("email") String email,
            @Param("password") String password);
    int selectLogin(UserLoginVo userLoginVo);
}
