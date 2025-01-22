package com.tpe.service;

import com.tpe.domain.Course;
import com.tpe.repository.IRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseService {

    @Autowired
    @Qualifier("courseRepository")
    private IRepository repository;

    //1-b
    public List<Course> getAllCourse(){
        return repository.findAll();
    }

    //3-b
    public void saveOrUpdateCourse(Course course) {
        repository.saveOrUpdate(course);
    }

    //4-b
    public Course findCourseById(Long identity) {

        return (Course) repository.findById(identity).get();
    }

    //5-b
    public void deleteCourse(Long id) {
        Course found=findCourseById(id);
        repository.delete(found);
    }

    //6-b
    public List<Course> getCoursesByDuration(int duration) {
        return repository.findAllByDuration(duration);
    }
}