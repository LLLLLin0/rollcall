package com.bytedance.rollcall.statis;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bytedance.rollcall.BaseActivity;
import com.bytedance.rollcall.R;
import com.bytedance.rollcall.bean.Lesson;
import com.bytedance.rollcall.db.DBHelper;
import com.bytedance.rollcall.db.bean.Student;

import java.util.ArrayList;
import java.util.List;

public class LessonStaticsActivity extends BaseActivity {

    private RecyclerView mRecyclerView;
    private StaticsStudentAdapter mAdapter;
    private Lesson mLesson;

    public static void startRollCallActivity(Context context, Lesson lesson) {
        Intent intent = new Intent(context, LessonStaticsActivity.class);
        intent.putExtra("data", lesson);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.statics_lesson_activity_layout);
        init();
    }

    private void init() {
        mLesson = (Lesson) getIntent().getSerializableExtra("data");
        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new StaticsStudentAdapter(getData(), mLesson);
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<com.bytedance.rollcall.bean.Student> getData() {
        List<Student> students = DBHelper.selectStudentsByLessonId(mLesson.getId());
        List<com.bytedance.rollcall.bean.Student> list = new ArrayList<>();
        for (Student student : students) {
            list.add(com.bytedance.rollcall.bean.Student.convert(student));
        }
        return list;
    }

}
