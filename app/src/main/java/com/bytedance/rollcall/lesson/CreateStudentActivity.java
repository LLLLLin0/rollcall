package com.bytedance.rollcall.lesson;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bytedance.rollcall.BaseActivity;
import com.bytedance.rollcall.R;
import com.bytedance.rollcall.db.DBHelper;
import com.bytedance.rollcall.ui.popubwindow.ProfilePopubWindow;

public class CreateStudentActivity extends BaseActivity implements View.OnClickListener {

    private ImageView back;
    private TextView title;
    private TextView submit;
    private FrameLayout add_photo;
    private EditText editName;
    private EditText editClasses;
    private EditText editSex;
    private EditText editTel;

    public static void start(Context context) {
        Intent intent = new Intent(context, CreateStudentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_student_activity_layout);

        init();
    }

    private void init() {
        back = findViewById(R.id.back);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(this);
        title = findViewById(R.id.title);
        title.setText(R.string.create_student);
        submit = findViewById(R.id.submit);
        submit.setVisibility(View.VISIBLE);
        submit.setOnClickListener(this);
        add_photo = findViewById(R.id.add_photo);
        add_photo.setOnClickListener(this);

        editName = findViewById(R.id.edit_name);
        editSex = findViewById(R.id.edit_sex);
        editClasses = findViewById(R.id.edit_classes);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back:
                finish();
                break;
            case R.id.add_photo:
                ProfilePopubWindow profilePopubWindow = new ProfilePopubWindow(this, null);
                profilePopubWindow.showAtLocation(findViewById(R.id.main),
                        Gravity.CENTER, 0, 0);
                break;
            case R.id.submit:
                String name = editName.getText().toString();
                String sex = editSex.getText().toString();
                String classes = editClasses.getText().toString();
                DBHelper.insertStudent(name, sex, classes, null);
                Toast.makeText(this, R.string.success, Toast.LENGTH_SHORT).show();
                finish();
            default:
        }
    }
}
