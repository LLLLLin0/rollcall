package com.bytedance.rollcall.rollcall;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bytedance.rollcall.BaseActivity;
import com.bytedance.rollcall.R;
import com.bytedance.rollcall.adapter.BaseAdapter;
import com.bytedance.rollcall.adapter.OnItemClickListener;
import com.bytedance.rollcall.bean.Lesson;
import com.bytedance.rollcall.bean.Student;
import com.bytedance.rollcall.db.DBHelper;
import com.bytedance.rollcall.lesson.LessonFragment;
import com.bytedance.rollcall.statis.StatisFragment;

import java.util.ArrayList;
import java.util.List;

public class RollCallActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private TextView title;
    private TextView date;
    private TextView studentNum;
    private TextView time;

    private Lesson mLesson;
    private RecyclerView mRecyclerView;
    private RollCallStudentAdapter mAdapter;
    private boolean[] status;
    private int size;

    public static void startRollCallActivity(Context context, Lesson lesson, String date) {
        Intent intent = new Intent(context, RollCallActivity.class);
        intent.putExtra("data", lesson);
        intent.putExtra("date", date);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.roll_call_activity_layout);
        mLesson = (Lesson) getIntent().getSerializableExtra("data");
        init();
    }

    private void init() {
        back = findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        title = findViewById(R.id.title);
        title.setText(mLesson.getName());
        date = findViewById(R.id.date);
        date.setText(getIntent().getStringExtra("date"));
        time = findViewById(R.id.time);
        time.setText(mLesson.getTime());
        studentNum = findViewById(R.id.student_num);
        String s = (String) studentNum.getText();
        s = s.replace("num", String.valueOf(mLesson.getStudNum()));
        s = s.replace("rollcall", String.valueOf(0));
        studentNum.setText(s);

        mRecyclerView = findViewById(R.id.recycler);
        mAdapter = new RollCallStudentAdapter(getData(), getListener());
        if (getData() != null && getData().size() > 0) {
            status = new boolean[getData().size()];
            size = getData().size();
        }
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    private OnItemClickListener getListener() {
        return new OnItemClickListener() {
            @Override
            public void onClick(BaseAdapter baseAdapter, View view, int position) {
                if (position == size - 1) {
                    //提交
                    List<com.bytedance.rollcall.bean.Student> data = getData();
                    for (int i = 0; i < size - 1; i++) {
                        DBHelper.insertOneRollCall(mLesson.getId(), data.get(i).getId(),
                                status[i] ? 1 : 0);
                    }
                    DBHelper.updateLessonCallNum(mLesson.getId(), mLesson.getCallNum() + 1);
                    LessonFragment.setDataExpired(true);
                    RollCallFragment.setDataExpired(true);
                    StatisFragment.setDataExpired(true);
                    Toast.makeText(RollCallActivity.this, R.string.success, Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    CheckBox checkbox = view.findViewById(R.id.checkbox);
                    checkbox.setChecked(!checkbox.isChecked());
                    status[position] = checkbox.isChecked();
                }
            }

            @Override
            public void onChildClick(BaseAdapter baseAdapter, View view, int position) {

            }
        };
    }

    private List<Student> getData() {
        int lessonId = mLesson.getId();
        List<com.bytedance.rollcall.db.bean.Student> students = DBHelper
                .selectStudentsByLessonId(lessonId);
        List<Student> list = new ArrayList<>();
        for (com.bytedance.rollcall.db.bean.Student student : students) {
            list.add(Student.convert(student));
        }
        if (list.size() > 0) {
            list.add(new Student());
        }
        return list;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                this.finish();
                break;
            default:
        }
    }
}
