package com.bytedance.rollcall.bean;

public class Student extends BaseBean {
    private int id;
    private String name;
    private String sex;
    private String classes;

    public Student() {

    }

    public Student(int id, String name, String sex, String classes) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.classes = classes;
    }

    public static Student convert(com.bytedance.rollcall.db.bean.Student studentBean) {
        Student student = new Student();
        student.setId(studentBean.getId());
        student.setName(studentBean.getName());
        student.setSex(studentBean.getSex());
        student.setClasses(studentBean.getClasses());
        return student;
    }

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

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

}
