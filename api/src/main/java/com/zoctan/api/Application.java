package com.zoctan.api;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.zoctan.api.core.ProjectConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author Zoctan
 * @date 2018/5/27
 */
@EnableEncryptableProperties
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@MapperScan(basePackages = ProjectConstant.MAPPER_PACKAGE)
public class Application extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(final SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(Application.class);
    }

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }
}
