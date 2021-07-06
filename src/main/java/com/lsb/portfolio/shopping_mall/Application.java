package com.lsb.portfolio.shopping_mall;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@SpringBootApplication
public class Application {
//    public static final String CDN = "<link rel=\"shortcut icon\" href=\"#\">\n" +
//            "<link href=\"https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@300;400;500;700;900&display=swap\" rel=\"stylesheet\">\n" +
//            "<link rel=\"stylesheet\" href=\"https://pro.fontawesome.com/releases/v5.10.0/css/all.css\" integrity=\"sha384-AYmEC3Yw5cVb3ZcuHtOA93w35dYTsvhLPVnYs9eStHfGJvOvKxVfELGroGkvsg+p\" crossorigin=\"anonymous\"/>\n" +
//            "<link rel=\"stylesheet\" href=\"/resources/stylesheets/common.css\">";

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources("classpath:mappers/**Mapper.xml");
        bean.setDataSource(dataSource);
        bean.setMapperLocations(resources);
        return bean.getObject();
    }
}
