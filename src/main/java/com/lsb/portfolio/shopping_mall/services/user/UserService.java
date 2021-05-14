package com.lsb.portfolio.shopping_mall.services.user;

import com.lsb.portfolio.shopping_mall.dtos.user.UserDto;
import com.lsb.portfolio.shopping_mall.models.user.IUserModel;
import com.lsb.portfolio.shopping_mall.enums.user.UserLoginResult;
import com.lsb.portfolio.shopping_mall.enums.user.UserRegisterResult;
import com.lsb.portfolio.shopping_mall.vos.user.UserLoginVo;
import com.lsb.portfolio.shopping_mall.vos.user.UserRegisterVo;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    // https://coding-factory.tistory.com/529
    public static class Regex{
        public static final String EMAIL = "^(?=.{8,50}$)([0-9a-z]([_]?[0-9a-z])*?)@([0-9a-z][0-9a-z\\-]*[0-9a-z]\\.)?([0-9a-z][0-9a-z\\-]*[0-9a-z])\\.([a-z]{2,15})(\\.[a-z]{2})?$";
        public static final String PASSWORD = "^(?=.*\\d)(?=.*[~`!@#$%\\^&*()-+=])(?=.*[a-zA-Z]).{6,20}$";
        public static final String NICKNAME = "^([0-9a-zA-Z가-힣]{2,10})$";
        public static final String NAME = "^([가-힣]{2,10})$";
        public static final String CONTACT_FIRST = "^(010|011|016|017)$";
        public static final String CONTACT_SECOND = "^([0-9]{4})$";
        public static final String CONTACT_THIRD = "^([0-9]{4})$";
        public static final String BIRTH_YEAR = "^([0-9]{4})$";
        public static final String BIRTH_MONTH = "^([0-9]{2})$";
        public static final String BIRTH_DAY = "^([0-9]{2})$";
        public static final String ADDRESS_POST = "^([0-9]{5})$";
        public static final String ADDRESS = "^([0-9a-zA-Z가-힣\\- ]{10,100})$";
        public static final String ADDRESS_DETAILS = "^([0-9a-zA-Z가-힣\\- ]{1,100})$";
    }

    private static boolean checkRegex(String value, String regex){
        return value.matches(regex);
    }

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
