package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

//@Controller
@RestController
//Bu sınıfın bir RESTful web servisi olarak çalışacağını belirtir.
//Metotlardan dönen veriler, otomatik olarak JSON formatına dönüştürülerek istemciye iletilir.
@RequestMapping("/api")
//Sınıfın tüm HTTP endpoint'lerinin /api ile başlamasını sağlar.
//Örneğin, /all endpoint'i aslında /api/all URL'si olarak erişilebilir hale gelir.
public class StudentRestController {

    private final IStudentService service;

    @Autowired//sadece 1 constructor var ise  @Autowired kullanımı opsiyoneldir.
    public StudentRestController(IStudentService service) {
        this.service = service;
    }

    //1-Tüm öğrencileri listeleme
    //https://localhost:8080/api/all + GET
    @GetMapping("/all")
    public List<Student> allStudent(){
        return service.listAllStudents();//jackson tarafından JSON formatına dönüştürülür
    }

    //client:frontend,diğer bir yazılım

}