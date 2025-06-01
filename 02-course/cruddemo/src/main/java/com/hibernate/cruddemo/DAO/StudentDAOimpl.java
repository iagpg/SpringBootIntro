package com.hibernate.cruddemo.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.hibernate.cruddemo.entity.Student;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

@Repository
public class StudentDAOimpl implements StudentDAO {

    // this class is responsible for comunicating with the database
    // so then, we are doing a constructor injection of the entity manager to the studentDAOimpl class
    @Autowired
    private EntityManager entityManager;

    //this is a method of StudentDAO interface
    // we need to use the @Transactional annotation to tell spring that this method should be executed in a transaction
    @Override
    @Transactional
    public void save(Student student) {
        entityManager.persist(student);
    }

    @Override
    // same as here, we are modifying our database, so we need to use the @Transactional annotation
    @Transactional
    public void saveAll(Iterable<Student> students) {
        for (Student student : students) {
            entityManager.persist(student);
        }
    }

    @Override
    public Student findById(Integer id){
        return entityManager.find(Student.class, id);
    }

    @Override
    public List<Student> findAll(){
        TypedQuery<Student> query = entityManager.createQuery("FROM Student ORDER BY firstName", Student.class);
        query.setMaxResults(10);
        return query.getResultList();
    }

    @Override
    // this method are using setParameter to set the value of the lastNameVariable parameter in the query. this is a way to organize our code and avoid SQL injection attacks
    //this happens because the theLastName is not connected directly to the query, but instead is passed as a parameter to the query
    public List<Student> findByLastName(String theLastName) {
        TypedQuery<Student> query = entityManager.createQuery("From Student WHERE lastName = :lastNameVariable",Student.class);
        query.setParameter("lastNameVariable", theLastName);
        return query.getResultList();
    }


    @Override
    @Transactional
    public void update(Student student){
        // we are using the merge method to update the student object in the database
        // this method will return the updated student object
        entityManager.merge(student);
    }

    @Override
    @Transactional
    public void delete(Integer id) {
        // we cant remove directy the object student with the id because the entity manager needs to know the object before removing it
        // so we need to rescue the student object from the db first.
        Student student = entityManager.find(Student.class, id);
        if (student != null)
        entityManager.remove(student);
    }

    @Override
    @Transactional
    public Integer deleteAll(){
        Integer rowsDeleted = entityManager.createQuery("DELETE FROM Student").executeUpdate();
        return rowsDeleted;

    }
}