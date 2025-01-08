package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller //gelen requestleri bu classta karşılayacak ve ilgili methodlar ile maplenerek cevaplayacak
@RequestMapping("students")// http:localhost:8080/SpringMvc/students/....
public class StudentController {

    @Autowired
    private IStudentService service;


 //http:localhost:8080/SpringMvc/students/hi + get - okuma
//http:localhost:8080/SpringMvc/students/hi + post - kayıt
 //http:localhost:8080/SpringMvc/students/hi + delete - silme


    //@RequestMapping("/hi")
    //Method: sadece ilgili olduğıu methoda bu url üzerinden istek(request) geleceğini söyler
    //Class:class içinde bulunan tüm methodlara bu url üzerinden istek(request) geleceğini söyler.
   @GetMapping("/hi")
    public ModelAndView sayHi (){
       //respone u hazrılamak

       ModelAndView maw = new ModelAndView();
       maw.addObject("message","Hi");
       maw.addObject("messagebody","I am a student management system");
       maw.setViewName("hi");
       return maw;



    }
    // 1 - tüm öğrencileri listeleme methodu
    //http:localhost:8080/SpringMvc/students/hi + get - okuma
@GetMapping
    public ModelAndView getStudents(){
    List<Student> allStudent = service.listAllStudents();

       ModelAndView maw = new ModelAndView();
       maw.addObject("studentList",allStudent);
       maw.setViewName("students");
       return maw;


}
    //2-öğrenciyi kaydetme
//request: http:localhost:8080/SpringMvc/students/new + get
//response: form göstermek
    @GetMapping("/new")
    public String sendForm(@ModelAttribute("student")Student student){
        return "studentForm";
    }
    //modeAttribute anatasyonu view katmanı ile controller arasında  modelin
    // transferini sağlar

    //2-b: öğrenciyi kaydetme
//request: http:localhost:8080/SpringMvc/students/saveStudent + POST
//response: Db'e öğrenciyi ekleme ve liste yonledirelim
    @PostMapping("/saveStudent")
    public String addStudent(@ModelAttribute("student")Student student){

        service.addOrUpdateStudent(student);

        return "redirect:/students";//http:localhost:8080/SpringMvc/students/ + GET
    }

}
