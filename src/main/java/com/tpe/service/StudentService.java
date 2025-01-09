package com.tpe.service;

import com.tpe.domain.Student;
import com.tpe.exception.StudentNotFoundException;
import com.tpe.repository.IStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
/*Spring uygulamalarında service katmanı, iş mantığının yer aldığı
yerdir ve genellikle veri erişim katmanı (repository) ile controller
 arasındaki köprü görevi görür. Buradaki kodun detaylarını açıklayalım:*/

@Service
public class StudentService implements IStudentService{

    @Autowired
    private IStudentRepository repository;

    //1-c
    @Override
    public List<Student> listAllStudents() {
        return repository.findAll();

    }

    //2-c
    @Override
    public void addOrUpdateStudent(Student student) {
        repository.saveOrUpdate(student);
        /*Amaç: Bu metot, bir öğrenciyi veritabanına eklemek veya mevcut bir öğrenciyi güncellemek için kullanılır.
repository.saveOrUpdate(student):
repository, veri erişim işlemlerini gerçekleştiren bir nesnedir.
saveOrUpdate metodu:
Eğer öğrenci veritabanında yoksa, yeni bir öğrenci olarak ekler.
Eğer öğrenci veritabanında zaten varsa, mevcut kaydı günceller.*/
    }
    //3-b
    @Override
    public Student findStudentById(Long id) {
        Student student=repository.findById(id).
                orElseThrow(()->new StudentNotFoundException("Student not Found By Id : "+id));
       /*Amaç: Bu metot, belirli bir id ile bir öğrenciyi bulur ve döndürür. Eğer öğrenci bulunamazsa özel bir hata fırlatır.
repository.findById(id):
findById metodu, verilen id'ye göre bir öğrenci arar.
Geriye bir Optional nesnesi döner.
Eğer öğrenci varsa, Optional içinde öğrenci nesnesi bulunur.
Eğer öğrenci yoksa, Optional boştur.
orElseThrow:
Eğer Optional boşsa, orElseThrow metodu bir hata fırlatır.
Burada, StudentNotFoundException özel bir istisna olarak kullanılmış.
Hata mesajı: "Student not Found By Id : " + id.*/
        return student;

    }

    @Override
    public void deleteStudent(Long id) {
        Student student = findStudentById(id);
        repository.delete(student);

    }
}
/*Spring Katmanları Arasında İlişki
Controller Katmanı: HTTP isteklerini karşılar ve service katmanını çağırır.
Service Katmanı: İş mantığını barındırır ve repository ile iletişim kurar.
Repository Katmanı: Veritabanı işlemlerini gerçekleştirir.*/

