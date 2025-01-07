package com.tpe;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

//java tabanlı web uygulamaları geliştirmek için web xml dosyası ile config
// edilir,bu classı web xml yerine kullanıyoruz.

//AbstractAnnotationConfigDispatcherServletInitializer : DispatcherServlet
// konfigurasyonu ve başlatılması icin gerekli adımları gosterir.

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {


    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[]{
                RootConfig.class
        };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[]{
                WebMvcConfig.class
        };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{
                "/"
        };
    }
}
