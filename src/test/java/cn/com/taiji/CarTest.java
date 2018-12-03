package cn.com.taiji;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.*;

/**
 * Unit test for simple App.
 */
public class CarTest
{

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
    //增加
    @Test
    public void savePeopleTest(){
        // 3.开启事务
        transaction.begin();
       Car car = new Car();
       car.setManufacturer("bwm");
       car.setColor("red");
       LicensePlate licensePlate = new LicensePlate();
       licensePlate.setLicensePlateNumber("C444444");
       car.setLicensePlateID(licensePlate);
       entityManager.persist(car);
        // 5. 提交事务
        transaction.commit();
    }
    //查找
    @Test
    public void findOnePeopleTest(){
        // 3.开启事务
        transaction.begin();
        Car car = entityManager.find(Car.class,1);
        System.out.println(car);
        // 5. 提交事务
        transaction.commit();
    }
    //删除
    @Test
    public void deletePeopleTest(){
        // 3.开启事务
        transaction.begin();
        Car car = entityManager.find(Car.class,2);
        entityManager.remove(car);
        // 5. 提交事务
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
