package com.example.xiyan.testapplication.bean;

import com.supconit.azpt.db.annotation.Column;
import com.supconit.azpt.db.annotation.Table;

import java.util.Date;

/**
 * Created by xiyan on 16-4-12.
 */
@Table("Person")
public class Person {
    @Column(primaryKey = true, autoIncrement = true)
    private Integer id;

    @Column
    private String name;

    @Column
    private String age;

    @Column
    private Date birth;

    @Column
    private String sex;

    @Column
    private String other;

    public Person() {
        name="";
        age = "";
        sex = "";
        other ="";

    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }


    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }
}
