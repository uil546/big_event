package com.uil.big_event.config;


import com.uil.big_event.interceptors.LoginInterceptor;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.hibernate.validator.HibernateValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.SpringConstraintValidatorFactory;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private LoginInterceptor loginInterceptor;

    @Bean
    public Validator validator(AutowireCapableBeanFactory springFactory) {
        try (ValidatorFactory factory = Validation.byProvider(HibernateValidator.class)
                .configure()
                // 快速失败
                .failFast(true)
                // 解决 SpringBoot 依赖注入问题
                .constraintValidatorFactory(new SpringConstraintValidatorFactory(springFactory))
                .buildValidatorFactory()) {
            return factory.getValidator();
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //登录和注册不拦截
        registry.addInterceptor(loginInterceptor).excludePathPatterns("/user/login","/user/register");
    }
}
