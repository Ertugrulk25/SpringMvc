package com.tpe.repository;

import com.tpe.domain.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

//@Component
@Repository
public class StudentRepository implements IStudentRepository{

    @Autowired
    private SessionFactory sessionFactory;

    private Session session;

//1-b  tablodan tüm satırları getir.
    @Override
    public List<Student> findAll() {
        sessionFactory.openSession();
       List <Student> studentList= session.createQuery("FROM Student", Student.class).getResultList();

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

    }

    @Override
    public Optional<Student> findById(Long id) {
        return Optional.empty();
    }
}
