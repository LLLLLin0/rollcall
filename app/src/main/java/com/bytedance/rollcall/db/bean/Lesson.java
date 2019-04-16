package com.bytedance.rollcall.db.bean;

import org.litepal.crud.LitePalSupport;

public class Lesson extends LitePalSupport {
    private int id;
    private String name;
    private String time;
    private String room;
    private int studNum = 0;
    private int callNum = 0;

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

    public static class Builder {
        private Lesson mLesson;

        public Builder() {
            mLesson = new Lesson();
        }

        public Builder setName(String name) {
            mLesson.setName(name);
            return this;
        }

        public Builder setTime(String classTime) {
            mLesson.setTime(classTime);
            return this;
        }

        public Builder setRoom(String classRoom) {
            mLesson.setRoom(classRoom);
            return this;
        }

        public Lesson build() {
            return mLesson;
        }

    }

}
