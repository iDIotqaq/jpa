package cn.com.taiji;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zxx
 * @Date: 2018/12/3 16:37
 * @Version 1.0
 */
public class StudentTest {
    EntityManagerFactory factory;
    EntityManager entityManager;
    EntityTransaction transaction;
    @Before
    public void initEntityManagerFactory(){
        // 1. 创建EntityManagerFactory
        factory = Persistence.createEntityManagerFactory("jpa");
        // 2. 创建EntityManager
        entityManager = factory.createEntityManager();
        // 3.开启事务
        transaction = entityManager.getTransaction();

    }
    @Test
    public void insertTest(){
        transaction.begin();
        Course course1 = new Course();
        course1.setName("english");
        Course course2 = new Course();
        course2.setName("math");
        entityManager.persist(course1);
        entityManager.persist(course2);
        List<Course> courseList = new ArrayList<Course>();
        courseList.add(course1);
        courseList.add(course2);
        Student student = new Student();
        student.setName("yangye");
        student.setSex("1");
        student.setCourseList(courseList);
        entityManager.persist(student);
        transaction.commit();
    }
    @Test
    public void insertStudent(){
        transaction.begin();
        Query query = entityManager.createQuery("SELECT c from Course as c where c.id>:id");
        query.setParameter("id",0);
        List<Course> courseList = query.getResultList();
        Student student = new Student();
        student.setName("yangye");
        student.setSex("1");
        student.setCourseList(courseList);
        entityManager.persist(student);
        transaction.commit();
    }
    @Test
    public void delete(){
        transaction.begin();
        Query query = entityManager.createQuery("SELECT s from Student as s where s.id=:id");
        query.setParameter("id",1);
        List<Student> studentList= query.getResultList();
        for (Student student:studentList
             ) {
            entityManager.remove(student);
        }
        transaction.commit();
    }

    @After
    public void closeEntity(){

        // 6. 关闭EntityManager
        entityManager.close();
        // 7. 关闭EntityManagerFactory
        factory.close();
    }
}
