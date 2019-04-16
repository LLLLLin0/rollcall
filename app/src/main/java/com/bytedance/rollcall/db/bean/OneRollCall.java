package com.bytedance.rollcall.db.bean;

import org.litepal.crud.LitePalSupport;

import java.util.Date;

public class OneRollCall extends LitePalSupport {

    private int id;
    private int lessonId;
    private int studId;
    private int status;
    private Date time;
    private String remark;

    public OneRollCall() {

    }

    public OneRollCall(int id, int lessonId, int studId, int status, Date time, String remark) {
        this.id = id;
        this.lessonId = lessonId;
        this.studId = studId;
        this.status = status;
        this.time = time;
        this.remark = remark;
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

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public String toString() {
        return "OneRollCall{" +
                "id=" + id +
                ", lessonId=" + lessonId +
                ", studId=" + studId +
                ", status=" + status +
                ", time=" + time +
                ", remark='" + remark + '\'' +
                '}';
    }

    public static class Builder {
        private OneRollCall mOneRollCall;
        public Builder() {
            mOneRollCall = new OneRollCall();
        }

        public Builder setLessonId(int lessonId) {
            mOneRollCall.setLessonId(lessonId);
            return this;
        }

        public Builder setStudId(int studId) {
            mOneRollCall.setStudId(studId);
            return this;
        }

        public Builder setStatus(int status) {
            mOneRollCall.setStatus(status);
            return this;
        }

        public OneRollCall build() {
            return mOneRollCall;
        }
    }
}
