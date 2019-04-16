package com.bytedance.rollcall.lesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bytedance.rollcall.BaseActivity;
import com.bytedance.rollcall.R;
import com.bytedance.rollcall.bean.Lesson;
import com.bytedance.rollcall.datasource.MockData;
import com.bytedance.rollcall.db.DBHelper;
import com.bytedance.rollcall.rollcall.RollCallFragment;
import com.bytedance.rollcall.statis.StatisFragment;

public class AddStudentActivity extends BaseActivity implements View.OnClickListener {
    private ImageView back;
    private TextView title;
    private TextView submit;
    private TextView register;

    private Lesson mLesson;
    private RecyclerView mRecyclerView;
    private AddStudentAdapter mAdapter;
    private boolean[][] add = new boolean[10][10];

    public static void start(Context context, Lesson lesson) {
        Intent intent = new Intent(context, AddStudentActivity.class);
        intent.putExtra("data", lesson);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_student_activity_layout);
        init();
    }

    private void init() {
        back = findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        title = findViewById(R.id.title);
        title.setText(R.string.add_student);
        submit = findViewById(R.id.submit);
        submit.setVisibility(View.VISIBLE);
        submit.setOnClickListener(this);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);

        mLesson = (Lesson) getIntent().getSerializableExtra("data");

        mRecyclerView = findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new AddStudentAdapter(MockData.getAllClasses(), add);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.register:
                CreateStudentActivity.start(this);
                break;
            case R.id.submit:
                for (int j = 0; j < 10; j++) {
                    if (add[0][j]) {
                        int studId = mAdapter.getAdapterList().get(0).getData().get(j).getId();
                        DBHelper.insertRecord(mLesson.getId(), studId);
                    }
                }
                int num = DBHelper.countStudentByLessonId(mLesson.getId());
                DBHelper.updateLessonStudNum(mLesson.getId(), num);
                LessonFragment.setDataExpired(true);
                RollCallFragment.setDataExpired(true);
                StatisFragment.setDataExpired(true);
                Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
                finish();
                break;
        }
    }
}
