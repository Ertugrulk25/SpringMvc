package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.Optional;

//@Component
@Repository
public class StudentRepository implements IStudentRepository {

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

    //1-b:tablodan tüm satırları getiricez
    @Override
    public List<Student> findAll() {
        session=sessionFactory.openSession();
        List<Student>studentList=session.createQuery("from Student", Student.class).getResultList();

        session.close();
        return studentList;
    }

    //2-d
    @Override
    public void saveOrUpdate(Student student) {
        session=sessionFactory.openSession();
        Transaction trs= session.beginTransaction();
        session.saveOrUpdate(student);
        trs.commit();
        session.close();

    }

    @Override
    public void delete(Student student) {
        session=sessionFactory.openSession();
        Transaction trs= session.beginTransaction();
        session.delete(student);
        trs.commit();
        session.close();
    }

    //3-a
    @Override
    public Optional<Student> findById(Long id) {
        session =sessionFactory.openSession();
        Student student=session.get(Student.class,id);

        Optional<Student>optional=Optional.ofNullable(student);

        session.close();
        return optional;
    }

}
