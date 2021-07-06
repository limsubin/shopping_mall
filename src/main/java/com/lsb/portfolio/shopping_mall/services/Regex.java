package com.lsb.portfolio.shopping_mall.services;

public class Regex {
    // https://coding-factory.tistory.com/529

    public static class User{
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

    public static class Shop{
        public static final String PRODUCT_NAME = "\"^(?=.*\\\\d)(?=.*[~`!@#$%\\\\^&*()-+=])(?=.*[a-zA-Z]).{1,100}$\"";
        public static final String PRODUCT_PRICE = "^([0-9]{1,50})$";
        public static final String PRODUCT_CONTENT = "\"^(?=.*\\\\d)(?=.*[~`!@#$%\\\\^&*()-+=])(?=.*[a-zA-Z]).{1,100}$\"";
        public static final String PRODUCT_SIZE = "^([0-9a-zA-Z가-힣\\- ]{1,100})$";
        public static final String PRODUCT_COLOR = "^([0-9a-zA-Z가-힣\\- ]{1,100})$";
    }

    public static boolean checkRegex(String value, String regex){
        return value.matches(regex);
    }
}
