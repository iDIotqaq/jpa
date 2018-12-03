package cn.com.taiji;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zxx
 * @Date: 2018/12/3 15:52
 * @Version 1.0
 */
public class CompanyTest {

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
        Employee employee1 = new Employee();
        employee1.setName("yangye");
        employee1.setAge(55);
        Employee employee2 = new Employee();
        employee2.setName("gouye");
        employee2.setAge(66);
        Company company = new Company();
        company.setName("taiji");
        entityManager.persist(company);
        employee1.setCompany(company);
        employee2.setCompany(company);
        entityManager.persist(employee1);
        entityManager.persist(employee2);
        transaction.commit();

    }
    @Test
    public void delete(){
        transaction.begin();
        Company company = entityManager.find(Company.class,2);
        entityManager.remove(company);
        transaction.commit();
    }
    @Test
    public void findCompany(){
        transaction.begin();
        Query query = entityManager.createQuery("select c from Company as c where id=:id ");
        query.setParameter("id",1);
        Company company =  (Company) query.getSingleResult();
        entityManager.remove(company);
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
