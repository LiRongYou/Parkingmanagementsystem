package com.example.usermanager.bean;

/**
 * @author 黎荣右
 * @date 2022/5/18 16:49
 */
public class Test {
    private String name;
    private String age;

    public Test(String name, String age) {
        this.name = name;
        this.age = age;
    }

    public Test() {
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

    @Override
    public String toString() {
        return "Test{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                '}';
    }
}
