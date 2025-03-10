package com.start.bike.config;

import com.start.bike.interceptor.SqlLoggingInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
public class MyBatisConfig {

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory(DataSource dataSource, SqlLoggingInterceptor sqlLoggingInterceptor) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);

        // 设置Mapper XML文件的位置
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/*.xml"));

        sessionFactory.setPlugins(new Interceptor[]{sqlLoggingInterceptor});

        Properties myBatisProperties = new Properties();
        myBatisProperties.setProperty("mapUnderscoreToCamelCase", "true");
        sessionFactory.setConfigurationProperties(myBatisProperties);

        return sessionFactory;
    }
}