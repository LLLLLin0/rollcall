package com.bytedance.rollcall.db;

import com.bytedance.rollcall.db.bean.OneRollCall;
import com.bytedance.rollcall.db.bean.Record;
import com.bytedance.rollcall.db.bean.Lesson;
import com.bytedance.rollcall.db.bean.Student;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

public class DBHelper {

    /*
    * Lesson表CRUD
    * 1.获得所有课程列表（课程有id/name/time/room）
    * 2.增加一条课程记录
    * 3.删除一条课程记录（待定）
    * 4.更新课程的学生数或点名数
    * 5.根据课程名返回课程
    * */
    public static List<Lesson> selectAllLesson() {
        return LitePal.findAll(Lesson.class);
    }

    public static void insertLesson(String name, String time, String room) {
        Lesson lesson = new Lesson.Builder().setName(name)
                .setTime(time)
                .setRoom(room)
                .build();
        lesson.save();
    }

    public static Lesson selectLessonById(int id) {
        return LitePal.find(Lesson.class, id);
    }

    public static void updateLessonStudNum(int id, int studNum) {
        Lesson lesson = selectLessonById(id);
        lesson.setStudNum(studNum);
        lesson.save();
    }

    public static void updateLessonCallNum(int id, int callNum) {
        Lesson lesson = selectLessonById(id);
        lesson.setCallNum(callNum);
        lesson.save();
    }

    public static Lesson selectLessonByName(String name) {
        return LitePal.where("name = ?", name).findFirst(Lesson.class);
    }

    /*
    * Student表CRUD
    * 1.获得所有学生列表-用于课程添加学生
    * 2.根据id获得指定学生数据
    * 3.获得某个课程下的学生列表
    * 4.添加一条学生记录
    * 5.
    * */
    public static List<Student> selectAllStudent() {
        return LitePal.findAll(Student.class);
    }

    public static Student selectStudentById(int id) {
        return LitePal.find(Student.class, id);
    }

    public static List<Student> selectStudentsByLessonId(int lessonId) {
        List<Record> records = selectRecordByLessonId(lessonId);
        List<Student> list = new ArrayList<>();
        for (Record record : records) {
            list.add(selectStudentById(record.getStudId()));
        }
        return list;
    }

    public static void insertStudent(String name, String sex, String classes, String tel) {
        Student student = new Student.Builder().setName(name)
                .setSex(sex).setClasses(classes).setTel(tel).build();
        student.save();
    }

    public static List<Student> selectStudentByClassesName(String name) {
        return LitePal.where("classes = ?", name).find(Student.class);
    }

    /*
    * Record表CRUD
    * 1.根据lessonId返回相应的记录-课程下学生列表
    * 2.根据studId返回相应的记录-学生的课程列表
    * 3.查询一条Record记录是否存在*/

    public static List<Record> selectRecordByLessonId(int lessonId) {
        return LitePal.where("lessonid = ?", String.valueOf(lessonId))
                .find(Record.class);
    }

    public static List<Record> selectRecordByStudId(int studId) {
        return LitePal.where("studid = ?", String.valueOf(studId))
                .find(Record.class);
    }

    public static boolean isRecordExists(int lessonId, int studId) {
        Record first = LitePal.where("lessonid = ? and studid = ?",
                String.valueOf(lessonId), String.valueOf(studId)).findFirst(Record.class);
        return first == null ? false : true;
    }

    public static void insertRecord(int lessonId, int studId) {
        if (!isRecordExists(lessonId, studId)) {
            new Record.Builder().setLessonId(lessonId).setStudId(studId).build().save();
        }
    }

    public static int countStudentByLessonId(int lessonId) {
        return LitePal.where("lessonid = ?", String.valueOf(lessonId))
                .count(Record.class);
    }

    /*
    * OneRollCall表CRUD
    * 1.添加一条点名记录
    * 2.查询指定课程指定学生的点名记录*/

    public static void insertOneRollCall(int lessonId, int studId, int status) {
        new OneRollCall.Builder().setLessonId(lessonId).setStudId(studId)
                .setStatus(status).build().save();
    }

    public static List<OneRollCall> selectOneRollCall(int lessonId, int studId) {
        return LitePal.where("lessonid = ? and studid = ?",
                String.valueOf(lessonId), String.valueOf(studId)).find(OneRollCall.class);
    }

}
