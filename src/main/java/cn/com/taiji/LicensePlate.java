package cn.com.taiji;

import javax.persistence.*;

/**
 * @Author: zxx
 * @Date: 2018/12/3 12:35
 * @Version 1.0
 */
@Entity
public class LicensePlate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "licensePlateNumber",nullable = true, length = 20)
    private String licensePlateNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    @Override
    public String toString() {
        return "LicensePlate{" +
                "id=" + id +
                ", licensePlateNumber='" + licensePlateNumber + '\'' +
                '}';
    }
}
