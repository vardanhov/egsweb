package com.egswebapp.egsweb.configuration;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.exception.VelocityException;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.velocity.VelocityEngineFactoryBean;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import java.io.IOException;
import java.util.Properties;



@Configuration
public class BeanConfig {

    /**
     *  mapper facade bean
     */
    @Bean
    public MapperFacade mapper() {
        return new DefaultMapperFactory.Builder().build().getMapperFacade();
    }

    /**
     * BCrypt Password Encoder
     *
     */

    @Bean(value = "passwordEncoder")
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /*
     * Velocity configuration.
     */
    @Bean
    public VelocityEngine velocityEngine() throws VelocityException, IOException{

        VelocityEngineFactoryBean factory = new VelocityEngineFactoryBean();
        Properties props = new Properties();
        props.put("resource.loader", "class");
        props.put("class.resource.loader.class",
                "org.apache.velocity.runtime.resource.loader." +
                        "ClasspathResourceLoader");
        factory.setVelocityProperties(props);

        return factory.createVelocityEngine();
    }

    /**
     *  MultipartResolver bean
     */
    @Bean(name = "multipartResolver")
    public CommonsMultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
        multipartResolver.setMaxUploadSize(Long.MAX_VALUE);
        return multipartResolver;
    }

    /**
     * RestTemplate bean
     *
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
