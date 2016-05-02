package com.ga;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.context.embedded.FilterRegistrationBean;
import org.springframework.boot.context.embedded.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.ga.filter.CORSFilter;



/**
 * The Class WebConfiguration. Web related configuration. This file is same as web.xml file
 */
@Configuration
public class WebConfiguration extends WebMvcConfigurerAdapter {

    /**
     * Filter login.
     * 
     * @return the filter registration bean
     */
    @Bean
    public FilterRegistrationBean filterLogin() {
        FilterRegistrationBean bean = new FilterRegistrationBean();
        bean.setFilter(new CORSFilter());
        bean.addUrlPatterns("/", "/*");
        return bean;
    }

    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("102400KB");
        factory.setMaxRequestSize("102400KB");
        return factory.createMultipartConfig();
    }

    // @Bean
    // InternalResourceViewResolver internalResourceViewResolver() {
    // InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    // viewResolver.setPrefix("/WEB-INF/views/");
    // viewResolver.setSuffix(".jsp");
    // return viewResolver;
    // }
}
