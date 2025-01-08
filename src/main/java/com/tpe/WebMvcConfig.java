package com.tpe;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

@Configuration
@ComponentScan("com.tpe")
@EnableWebMvc//Mvc yi aktifleştirir
public class WebMvcConfig implements WebMvcConfigurer {

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
//handlemaping: statik sayfa içeren bir istek geldiği zaman servlat a yönlendirmeden direkt kullanıcıya
//sorulmasını sağlar.


    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/statics/**").   //gelen istekleri sunma
                addResourceLocations("/resources/").
                setCachePeriod(0);

    }
}
