package cn.com.taiji;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        student.setName("yy");
        student.setSex("1");
        student.setCourseList(courseList);
        entityManager.persist(student);
        transaction.commit();
    }
    @Test
    public void insertStudent(){
        transaction.begin();
        Query query = entityManager.createQuery("SELECT c from Course as c");
        List<Course> courseList = query.getResultList();
        System.out.println(courseList);
        Student student = new Student();
        student.setName("yy");
        student.setSex("0");
        student.setCourseList(courseList);
        entityManager.persist(student);
        transaction.commit();
    }
    @Test
    public void update(){
        transaction.begin();
        Query query = entityManager.createQuery("update from Student set sex=:sex where id=:id");
        query.setParameter("sex","1");
        query.setParameter("id",5);
        query.executeUpdate();
        transaction.commit();
    }
    //更新
    @Test
    public void delete(){
        transaction.begin();
        Query query = entityManager.createQuery("SELECT s from Student as s where s.id=:id",Student.class);
        query.setParameter("id",1);
        Student student= (Student) query.getSingleResult();
        entityManager.remove(student);
        transaction.commit();
    }
    //试验criteria API
    @Test
    public void criteriaFind(){
        transaction.begin();
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> criteriaQuery = criteriaBuilder.createQuery(Student.class);
        Root<Student> studentRoot = criteriaQuery.from(Student.class);
        criteriaQuery.select(studentRoot).where(criteriaBuilder.equal(studentRoot.get("sex"),"0"));
        List<Student> studentList = entityManager.createQuery(criteriaQuery).getResultList();
        System.out.println(studentList);
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
