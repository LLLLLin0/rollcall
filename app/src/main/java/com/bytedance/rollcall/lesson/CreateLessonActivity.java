package com.bytedance.rollcall.lesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bytedance.rollcall.BaseActivity;
import com.bytedance.rollcall.R;
import com.bytedance.rollcall.db.DBHelper;
import com.bytedance.rollcall.rollcall.RollCallFragment;
import com.bytedance.rollcall.statis.StatisFragment;

public class CreateLessonActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private TextView title;
    private TextView submit;
    private EditText editName;
    private EditText editClassTime;
    private EditText editClassRoom;

    public static void start(Context context) {
        Intent intent = new Intent(context, CreateLessonActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_lesson_activity_layout);

        init();
    }

    private void init() {
        back = findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        title = findViewById(R.id.title);
        title.setText(R.string.create_lesson);
        submit = findViewById(R.id.submit);
        submit.setVisibility(View.VISIBLE);
        submit.setOnClickListener(this);

        editName = findViewById(R.id.edit_name);
        editClassTime = findViewById(R.id.edit_classtime);
        editClassRoom = findViewById(R.id.edit_classroom);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.submit:
                String name = editName.getText().toString();
                String time = editClassTime.getText().toString();
                String room = editClassRoom.getText().toString();
                DBHelper.insertLesson(name, time, room);
                LessonFragment.setDataExpired(true);
                RollCallFragment.setDataExpired(true);
                StatisFragment.setDataExpired(true);
                Toast.makeText(this, "提交成功", Toast.LENGTH_SHORT).show();
                finish();
                break;
            default:
        }
    }

    @Override
    public void finish() {
        super.finish();
    }
}
