package com.bytedance.rollcall.db.bean;

import org.litepal.crud.LitePalSupport;

public class Student extends LitePalSupport {
    private int id;
    private String name;
    private String sex;
    private String classes;
    private String tel;

    public Student() {
    }

    public Student(int id, String name, String classes, String sex, String telphone) {
        this.id = id;
        this.name = name;
        this.classes = classes;
        this.sex = sex;
        this.tel = telphone;
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

    public String getClasses() {
        return classes;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String telphone) {
        this.tel = telphone;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", classes='" + classes + '\'' +
                ", sex='" + sex + '\'' +
                ", telphone='" + tel + '\'' +
                '}';
    }

    public static class Builder {
        private Student mStudent;

        public Builder() {
            mStudent = new Student();
        }

        public Builder setName(String name) {
            mStudent.setName(name);
            return this;
        }

        public Builder setSex(String sex) {
            mStudent.setSex(sex);
            return this;
        }

        public Builder setClasses(String classes) {
            mStudent.setClasses(classes);
            return this;
        }

        public Builder setTel(String tel) {
            mStudent.setTel(tel);
            return this;
        }

        public Student build() {
            return mStudent;
        }
    }

}
