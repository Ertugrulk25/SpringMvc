package com.tpe.controller;

import com.tpe.domain.Course;
import com.tpe.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService service;


    //Task 1:tüm kursları listeleyelim
    //request:http://localhost:8080/SpringMVC/courses + GET
    //response:course-list sayfasında tablodaki tüm kursları listeleyelim
    @RequestMapping("/courses")
    @GetMapping
    public ModelAndView listCourses(){
        List<Course> courses=service.getAllCourse();

        ModelAndView mav=new ModelAndView();
        mav.addObject("courseList",courses);
        mav.setViewName("course-list");
        return mav;
    }
    //Task 2:yeni bir kurs için form gösterelim
    //request:http://localhost:8080/SpringMVC/add + GET
    //response:formu gönderip içinden verileri(model) alacağız.
    @RequestMapping("/add")
    @GetMapping
    public String sendCourseForm(@ModelAttribute("course") Course course){

        return "course-form";

    }

    //Task 3:yeni bir kursu tabloya ekleyelim
    //request:http://localhost:8080/SpringMVC/save + POST
    //response: yeni kurs bilgileri tabloya eklenir, course listesine yeniden yönlendirir
    @RequestMapping("/save")
    @PostMapping
    public String addCourse(@Valid @ModelAttribute("course") Course course, BindingResult bindingResult){
        if (bindingResult.hasErrors()){
            return "course-form";
        }
        service.saveOrUpdateCourse(course);
        return "redirect:/courses";
    }

    //Task 4:id ile bir kursu tabloda güncelleyelim
    //http://localhost:8080/SpringMvc/courses/edit?id=102 + GET
    //response: update formu içinde kursun bilgileri ile gösterilecek
    @RequestMapping("/courses/edit")
    @GetMapping
    public ModelAndView updateCourse(@RequestParam("id") Long identity){

        Course foundCourse=service.findCourseById(identity);

        ModelAndView mav=new ModelAndView("course-form");
        mav.addObject("course",foundCourse);
        return mav;
    }

    //Task 5:id ile bir kursu tablodan silelim
    //request: http://localhost:8080/SpringMvc/courses/delete?id=102 +GET
    //response: kursu silelim, kalan kursları listeleyelim
    @RequestMapping("/courses/delete")
    @GetMapping
    public String deleteCourse(@RequestParam("id") Long id){
        service.deleteCourse(id);
        return "redirect:/courses";
    }

    //Task 6:süresi n saatten fazla olan kursları listeleyelim
    //request:http://localhost:8080/SpringMVC/courses/10 + GET
    //response:course-list sayfasında tablodaki n saatten fazla olan kursları listeleyelim
    @RequestMapping("/courses/{duration}")
    @GetMapping
    public ModelAndView filterCoursesByDuration(@PathVariable("duration") int duration){
        List<Course> coursesByDuration=service.getCoursesByDuration(duration);
        ModelAndView mav=new ModelAndView("course-list");
        mav.addObject("courseList",coursesByDuration);
        return mav;
    }

    //PRACTICE:
    //Task 7:ismi Java olan kursları listeleyelim
    //request:http://localhost:8080/SpringMVC/courses/query?name=Java + GET
    //response:course-list sayfasında tablodaki ismi verilen kursları listeleyelim





}