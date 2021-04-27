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
public class PortfolioShoppingMallApplication {

    public static void main(String[] args) {
        SpringApplication.run(PortfolioShoppingMallApplication.class, args);
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
