package com.tpe.controller;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;
/*
1. @Controller Nedir?
Görevi: @Controller, bu sınıfın bir Controller (Denetleyici) olduğunu belirtir.
Bu, Spring MVC'de kullanılan bir özel sınıf türüdür ve amacı gelen HTTP isteklerini karşılamak,
bu isteklere yanıt oluşturmak ve gerekli yönlendirmeleri yapmaktır.
Nasıl Çalışır?
Kullanıcı bir tarayıcıdan bir URL'e (örneğin, http://localhost:8080/SpringMvc/students) istek gönderir.
Spring, bu isteği ilgili @Controller sınıfına yönlendirir.
Bu sınıf içindeki metotlardan biri, isteğin türüne ve URL'ine bağlı olarak çalıştırılır (örneğin,
GET isteği, POST isteği gibi).
Neden Kullanılır?
Separation of Concerns (Sorumlulukların Ayrımı): Controller, yalnızca kullanıcıdan gelen istekleri
işlemek ve doğru sayfayı veya yanıtı döndürmekle ilgilenir.
İş mantığı ve veri erişimi, farklı katmanlara (Service ve Repository gibi) taşınır.*/

//-------------------------------------------------------------------------------//

/*2. @RequestMapping Nedir?
Görevi: @RequestMapping, gelen bir HTTP isteğini (örneğin, GET, POST, DELETE) bir sınıfa veya metoda bağlar.
Sınıf Seviyesi Kullanım: Sınıftaki tüm metotlara ortak bir URL prefix’i ekler.
Metot Seviyesi Kullanım: Metodun hangi URL ve HTTP yöntemiyle eşleştirileceğini belirtir*/

@Controller//gelen request(istek)leri bu classta karşılanacak ve ilgili methodlarla maplenerek cevaplayacak
@RequestMapping("/students")//http:localhost:8080/SpringMvc/students/....
public class StudentController {

    @Autowired
    private IStudentService service;

    //NOT: controller response methodlar geriye sadece mav veya string döndürebilir.

    //http:localhost:8080/SpringMvc/students/hi + get--okuma
    //http:localhost:8080/SpringMvc/students/hi + post--kayıt
    //http:localhost:8080/SpringMvc/students/hi + delete--silme


    //@RequestMapping("/hi")
    //Method: sadece ilgili methoda bu url uzerinden istek(request) gelecegini soyler
    //Class : class icinde bulunan tüm methodlara bu url üzerinden istek(request) gelecegini soyler
    @GetMapping("/hi")
    public ModelAndView sayHi() {
        //response u hazırlayacak
        ModelAndView mav = new ModelAndView();
        mav.addObject("message", "Hi");
        mav.addObject("messagebody", "Ben Öğrenci Yönetim sistemiyim");
        mav.setViewName("hi");
        return mav;
        /*Amaç: Kullanıcıya bir mesaj göstermek.
ModelAndView: Hem veri (message, messagebody) hem de görüntülenecek sayfa (hi) bilgilerini içerir.
Sonuç: Kullanıcıya "Hi" ve "Ben Öğrenci Yönetim sistemiyim" mesajlarını gösterir.*/
    }

    //1-Tüm öğrencileri listeleme
    //http:localhost:8080/SpringMvc/students/ + get
    @GetMapping
    public ModelAndView getStudent() {
        List<Student> allStudent = service.listAllStudents();
        ModelAndView mav = new ModelAndView();
        mav.addObject("studentList", allStudent);
        mav.setViewName("students");
        return mav;
        /*Amaç: Tüm öğrencileri listelemek.
Nasıl Çalışır?:
Servis katmanından tüm öğrencileri alır: service.listAllStudents().
Bu listeyi (studentList) ModelAndView nesnesine ekler.
Görüntülenecek sayfa olarak students belirlenir.
Sonuç: students.jsp gibi bir sayfada tüm öğrenciler listelenir.
*/
    }

    //2-öğrenciyi kaydetme
    //request: http:localhost:8080/SpringMvc/students/new + get
    //response: form göstermek
    @GetMapping("/new")
    public String sendForm(@ModelAttribute("student") Student student) {
        return "studentForm";
        /*Amaç: Yeni bir öğrenci eklemek için bir form göstermek.
@ModelAttribute: View ile Controller arasında veri transferini sağlar.
Sonuç: studentForm.jsp gibi bir sayfa kullanıcıya gösterilir.*/
    }


    //2-b: öğrenciyi kaydetme
    //request: http:localhost:8080/SpringMvc/students/saveStudent + POST
    //response: Db'e öğrenciyi ekleme ve liste yonledirelim
    @PostMapping("/saveStudent")
    public String addStudent(@Valid @ModelAttribute("student") Student student, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "studentForm";
        }
        service.addOrUpdateStudent(student);
        return "redirect:/students";//http:localhost:8080/SpringMvc/students/ + GET
    }
        /*Amaç: Yeni bir öğrenci eklemek veya var olan bir öğrenciyi güncellemek.
@Valid: Student nesnesini doğrular (örneğin, ad boş bırakılamaz gibi kurallar).
BindingResult: Doğrulama sırasında oluşan hataları içerir.
Sonuç:
Hata varsa form yeniden gösterilir.
Hata yoksa öğrenci eklenir ve öğrenci listesi sayfasına yönlendirilir.*/


    //3. öğrenciyi güncelleme
    //request : http://localhost:8080/SpringMvc/students/update?id=3 + GET
    //response: update icin verilen id'deki bilgileri ile birlikte formu gösterme
    //idsi verilen öğrenciyi bulmalıyız
    @GetMapping("update")
    public ModelAndView sendFormUpdate(@RequestParam("id") Long identity) {

        Student foundStudent = service.findStudentById(identity);

        ModelAndView mav = new ModelAndView();
        mav.addObject("student", foundStudent);
        mav.setViewName("studentForm");
        return mav;
    }

    // 4- öğrenciyi silme
//request : http://localhost:8080/SpringMvc/students/delete/5 + GET
    @GetMapping("/delete/{id}")
    public String deleteStudent(@PathVariable("id") Long identity) {
        service.deleteStudent(identity);
        return "redirect:/students";
        //query param ve path param sadece 1 tane ise isim belirtmek opsiyonel

       /*Amaç: Belirli bir öğrenciyi güncellemek için form göstermek.
@RequestParam: URL'deki id parametresini alır.
Sonuç: İlgili öğrenci bilgileriyle birlikte studentForm.jsp
gibi bir sayfa kullanıcıya gösterilir.*/
    }
      //  @ExceptionHandler:try -catch bloğunun mantığıyla benzer çalışır
        @ExceptionHandler(StudentNotFoundException.class)
        public ModelAndView handleException (Exception ex){
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.addObject("message", ex.getMessage());
            modelAndView.setViewName("notFound");
            return modelAndView;



    }
}


/*Kodun Akışı
Öğrencileri Listeleme: /students → Tüm öğrenciler görüntülenir.
Yeni Öğrenci Ekleme:
Form: /students/new
Kaydetme: /students/saveStudent
Öğrenci Güncelleme:
Form: /students/update?id=3
Kaydetme: /students/saveStudent*/