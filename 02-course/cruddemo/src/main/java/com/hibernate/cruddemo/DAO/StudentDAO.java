package com.hibernate.cruddemo.DAO;

import java.util.List;

import com.hibernate.cruddemo.entity.Student;

public interface StudentDAO {
    void save(Student student);

    void saveAll(Iterable<Student> students);
    
    Student findById(Integer id);

    List<Student> findAll();

    List<Student> findByLastName(String lastName);

    void update(Student student);

    void delete(Integer id);

    Integer deleteAll();
    
}
