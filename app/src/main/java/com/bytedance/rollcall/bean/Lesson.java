package com.bytedance.rollcall.bean;


import java.io.Serializable;

public class Lesson extends BaseBean implements Serializable {
    private int id;
    private String name;
    private String time;
    private String room;
    private int studNum;
    private int callNum;

    public Lesson() {

    }

    public Lesson(int id, String name, String time, String room, int studNum, int callNum) {
        this.id = id;
        this.name = name;
        this.time = time;
        this.room = room;
        this.studNum = studNum;
        this.callNum = callNum;
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

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getStudNum() {
        return studNum;
    }

    public void setStudNum(int studNum) {
        this.studNum = studNum;
    }

    public int getCallNum() {
        return callNum;
    }

    public void setCallNum(int callNum) {
        this.callNum = callNum;
    }

    @Override
    public String toString() {
        return "Lesson{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", time='" + time + '\'' +
                ", room='" + room + '\'' +
                ", studNum=" + studNum +
                ", callNum=" + callNum +
                '}';
    }

    public static Lesson convert(com.bytedance.rollcall.db.bean.Lesson lesson) {
        Lesson mLesson = new Lesson(lesson.getId(), lesson.getName(), lesson.getTime(),
                lesson.getRoom(), lesson.getStudNum(), lesson.getCallNum());
        return mLesson;
    }
}
