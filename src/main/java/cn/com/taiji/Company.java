package cn.com.taiji;

import javax.persistence.*;
import java.util.List;

/**
 * @Author: zxx
 * @Date: 2018/12/3 15:43
 * @Version 1.0
 */
@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;
    @Column(nullable = true, length = 20)
    private String name;
    @OneToMany(mappedBy = "company",cascade=CascadeType.ALL,fetch=FetchType.LAZY)
//    @JoinTable(name = "company_employee",joinColumns = @JoinColumn(name = "company_id"),
//            inverseJoinColumns = @JoinColumn(name = "employee_id"))
    private List<Employee> employeeList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    @Override
    public String toString() {
        return "Company{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", employeeList=" + employeeList +
                '}';
    }
}
