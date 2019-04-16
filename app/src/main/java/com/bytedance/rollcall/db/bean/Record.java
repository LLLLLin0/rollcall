package com.bytedance.rollcall.db.bean;

import org.litepal.crud.LitePalSupport;

public class Record extends LitePalSupport {
    private int id;
    private int lessonId;
    private int studId;

    public Record() {
    }

    public Record(int id, int lessonId, int studId) {
        this.id = id;
        this.lessonId = lessonId;
        this.studId = studId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLessonId() {
        return lessonId;
    }

    public void setLessonId(int lessonId) {
        this.lessonId = lessonId;
    }

    public int getStudId() {
        return studId;
    }

    public void setStudId(int studId) {
        this.studId = studId;
    }

    @Override
    public String toString() {
        return "Record{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", studentId=" + studId +
                '}';
    }

    public static class Builder {

        private Record mRecord;

        public Builder() {
            mRecord = new Record();
        }

        public Builder setLessonId(int lessonId) {
            mRecord.setLessonId(lessonId);
            return this;
        }

        public Builder setStudId(int studId) {
            mRecord.setStudId(studId);
            return this;
        }

        public Record build() {
            return mRecord;
        }
    }

}
