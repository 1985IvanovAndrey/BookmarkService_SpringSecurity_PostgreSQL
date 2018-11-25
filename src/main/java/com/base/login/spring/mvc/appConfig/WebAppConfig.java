package com.base.login.spring.mvc.appConfig;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.Locale;

@Configuration //собственно эта аннотация и говорит о том, что данный класс является Java
@EnableWebMvc //эта аннотация разрешает нашему проекту использовать MVC;
@ComponentScan({"com.base.login.spring.mvc"}) //говорит, где искать компоненты проекта.
public class WebAppConfig implements WebMvcConfigurer {


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/")
                .setCachePeriod(Integer.valueOf("31556926"));
    }

    @Bean //указывает на то что это инициализация бина, и он будет создан с помощью DI.
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver resolver = new InternalResourceViewResolver();
        resolver.setViewClass(JstlView.class);
        resolver.setPrefix("/WEB-INF/view/");
        resolver.setSuffix(".jsp");
        return resolver;
    }
    /* Localization section is started */

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(localeChangeInterceptor());
//    }
//
//    @Bean
//    public LocaleChangeInterceptor localeChangeInterceptor() {
//        LocaleChangeInterceptor localeChangeInterceptor = new LocaleChangeInterceptor();
//        localeChangeInterceptor.setParamName("lang");
//        return localeChangeInterceptor;
//    }
//
//    @Bean(name = "localeResolver")
//    public LocaleResolver getLocaleResolver() {
//        CookieLocaleResolver cookieLocaleResolver = new CookieLocaleResolver();
//        cookieLocaleResolver.setDefaultLocale(new Locale("en_EN"));
//        return new CookieLocaleResolver();
//    }

        @Bean
    public ReloadableResourceBundleMessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:messages");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
//    @Bean(name = "messageSource")
//    public MessageSource messageSource() {
//        final ReloadableResourceBundleMessageSource ret = new ReloadableResourceBundleMessageSource();
//        ret.setBasename("classpath:messages");
//        ret.setDefaultEncoding("UTF-8");
//        return ret;
//    }
}