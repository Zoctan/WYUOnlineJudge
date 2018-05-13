package com.zoctan.api;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import com.zoctan.api.core.ProjectConstant;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import java.util.TimeZone;

/**
 * @author Zoctan
 */
@EnableEncryptableProperties
@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
@EnableScheduling
@MapperScan(basePackages = ProjectConstant.MAPPER_PACKAGE)
public class Application {
    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @PostConstruct
    void started() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"));
    }

    /**
     * 配置内置的servlet容器工厂为tomcat.
     *
     * @return EmbeddedServletContainerFactory
     */
    /*
    @Bean
    public EmbeddedServletContainerFactory servletContainer() {
        final TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
            @Override
            protected void postProcessContext(final Context context) {
                final SecurityConstraint securityConstraint = new SecurityConstraint();
                securityConstraint.setUserConstraint("CONFIDENTIAL");//confidential
                final SecurityCollection collection = new SecurityCollection();
                collection.addPattern("/*");
                securityConstraint.addCollection(collection);
                context.addConstraint(securityConstraint);
            }
        };
        // 添加连接配置，主要是http的配置信息
        tomcat.addAdditionalTomcatConnectors(this.httpConnector());
        return tomcat;
    }

    /**
     * 配置一个http连接信息
     *
     * @return Connector
     */
    /*
    @Bean
    public Connector httpConnector() {
        final Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
        connector.setScheme("http");
        connector.setPort(9090);
        connector.setSecure(false);
        connector.setRedirectPort(9089);
        return connector;
    }
    */
}
