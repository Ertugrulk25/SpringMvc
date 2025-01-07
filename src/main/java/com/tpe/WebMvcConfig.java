package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.tpe")
@EnableWebMvc//Mvc yi aktifleştirir
public class WebMvcConfig {

//wiev reslver

    @Bean
    public InternalResourceViewResolver resolver(){
        InternalResourceViewResolver resolver=new InternalResourceViewResolver();
        resolver.setPrefix("/WEB-INF/views/");//view dosyası nerede:/WEB-INF/views/students.jsp
        resolver.setSuffix(".jsp");
        resolver.setViewClass(JstlView.class);
        //JSTL: JavaStandartTabLibrary: JSP içinde daha az java kodu yazmamızı sağlar
        return resolver;
    }

}
