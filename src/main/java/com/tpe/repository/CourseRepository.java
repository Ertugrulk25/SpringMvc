package com.tpe.repository;

import com.tpe.domain.Course;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class CourseRepository implements IRepository<Course,Long> {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    private Transaction transaction;

    //1-c
    @Override
    public List<Course> findAll() {
        session=sessionFactory.openSession();
        List<Course> courses=session.createQuery("FROM Course", Course.class).getResultList();
        session.close();

        return courses;
    }

    //3-c
    @Override
    public void saveOrUpdate(Course entity) {
        session=sessionFactory.openSession();
        transaction= session.beginTransaction();
        session.saveOrUpdate(entity);
        transaction.commit();
        session.close();
    }

    //5-c

    @Override
    public void delete(Course entity) {
        session=sessionFactory.openSession();
        transaction= session.beginTransaction();
        session.delete(entity);
        transaction.commit();
        session.close();
    }

    //4-c
    @Override
    public Optional<Course> findById(Long id) {
        session=sessionFactory.openSession();
        Course course=session.get(Course.class,id);
        session.close();

        Optional<Course> optional=Optional.ofNullable(course);//Attention

        return optional;
    }

    //6-c
    @Override
    public List<Course> findAllByDuration(int duration) {
        session=sessionFactory.openSession();
        List<Course> courses=session.createQuery("FROM Course c WHERE c.duration>"+duration, Course.class).getResultList();
        session.close();
        return courses;
    }
}