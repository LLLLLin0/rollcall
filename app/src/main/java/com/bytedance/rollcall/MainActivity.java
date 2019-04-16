package com.bytedance.rollcall;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bytedance.rollcall.lesson.LessonFragment;
import com.bytedance.rollcall.statis.StatisFragment;
import com.bytedance.rollcall.rollcall.RollCallFragment;

import org.litepal.tablemanager.Connector;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private View rollCall = null;
    private View lesson = null;
    private View statis = null;
    private TextView tvRollCall;
    private TextView tvLesson;
    private TextView tvStatics;
    private ImageView ivRollCall;
    private ImageView ivLesson;
    private ImageView ivStatics;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        StatusBarUtil.setTransparent(this);

        initView();
        initListener();
        initDataBase();
        showRollCallFragment();
    }

    private void initDataBase() {
        Connector.getDatabase();
    }

    private void initListener() {
        rollCall.setOnClickListener(this);
        lesson.setOnClickListener(this);
        statis.setOnClickListener(this);
    }

    private void initView() {
        rollCall = findViewById(R.id.roll_call);
        lesson = findViewById(R.id.lesson);
        statis = findViewById(R.id.statis);
        tvRollCall = findViewById(R.id.roll_call_tv);
        tvLesson = findViewById(R.id.lesson_tv);
        tvStatics = findViewById(R.id.statis_tv);
        ivRollCall = findViewById(R.id.roll_call_img);
        ivLesson = findViewById(R.id.lesson_img);
        ivStatics = findViewById(R.id.statis_img);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.roll_call:
                showRollCallFragment();
                break;
            case R.id.lesson:
                showClassManagerFragment();
                break;
            case R.id.statis:
                showPersonManagerFragment();
                break;
        }
    }

    private void showRollCallFragment() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment rollCall = manager.findFragmentByTag(RollCallFragment.class.getSimpleName());
        FragmentTransaction transaction = manager.beginTransaction();
        if (rollCall == null) {
            transaction.add(R.id.container, new RollCallFragment(),
                    RollCallFragment.class.getSimpleName());
        } else {
            if (rollCall.isHidden()) {
                transaction.show(rollCall);
            } else {
                return;
            }
        }

        Fragment classManager = manager.findFragmentByTag(LessonFragment.class.getSimpleName());
        Fragment personManager = manager.findFragmentByTag(StatisFragment.class.getSimpleName());
        if (classManager != null && !classManager.isHidden()) {
            transaction.hide(classManager);
        }
        if (personManager != null && !personManager.isHidden()) {
            transaction.hide(personManager);
        }
        transaction.commit();

        setStatus(true, false, false);
    }

    private void showClassManagerFragment() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment classManager = manager.findFragmentByTag(LessonFragment.class.getSimpleName());
        FragmentTransaction transaction = manager.beginTransaction();
        if (classManager == null) {
            transaction.add(R.id.container, new LessonFragment(),
                    LessonFragment.class.getSimpleName());
        } else {
            if (classManager.isHidden()) {
                transaction.show(classManager);
            } else {
                return;
            }
        }
        Fragment rollCall = manager.findFragmentByTag(RollCallFragment.class.getSimpleName());
        Fragment personManager = manager.findFragmentByTag(StatisFragment.class.getSimpleName());
        if (rollCall != null && !rollCall.isHidden()) {
            transaction.hide(rollCall);
        }
        if (personManager != null && !personManager.isHidden()) {
            transaction.hide(personManager);
        }
        transaction.commit();

        setStatus(false, true, false);
    }

    private void showPersonManagerFragment() {
        FragmentManager manager = getSupportFragmentManager();
        Fragment personManager = manager.findFragmentByTag(StatisFragment.class.getSimpleName());
        FragmentTransaction transaction = manager.beginTransaction();
        if (personManager == null) {
            transaction.add(R.id.container, new StatisFragment(),
                    StatisFragment.class.getSimpleName());
        } else {
            if (personManager.isHidden()) {
                transaction.show(personManager);
            } else {
                return;
            }
        }

        Fragment rollCall = manager.findFragmentByTag(RollCallFragment.class.getSimpleName());
        Fragment classManager = manager.findFragmentByTag(LessonFragment.class.getSimpleName());
        if (rollCall != null && !rollCall.isHidden()) {
            transaction.hide(rollCall);
        }
        if (classManager != null && !classManager.isHidden()) {
            transaction.hide(classManager);
        }
        transaction.commit();

        setStatus(false, false, true);
    }

    private void setStatus(boolean rollcall, boolean lesson, boolean statis) {
        if (rollcall) {
            tvRollCall.setTextColor(getResources().getColor(R.color.blue_bg));
            ivRollCall.setImageResource(R.drawable.rollcall_on);
        } else {
            tvRollCall.setTextColor(Color.BLACK);
            ivRollCall.setImageResource(R.drawable.rollcall_out);
        }

        if (lesson) {
            tvLesson.setTextColor(getResources().getColor(R.color.blue_bg));
            ivLesson.setImageResource(R.drawable.lesson_on);
        } else {
            tvLesson.setTextColor(Color.BLACK);
            ivLesson.setImageResource(R.drawable.lesson_out);
        }


        if (statis) {
            tvStatics.setTextColor(getResources().getColor(R.color.blue_bg));
            ivStatics.setImageResource(R.drawable.statis_on);
        } else {
            tvStatics.setTextColor(Color.BLACK);
            ivStatics.setImageResource(R.drawable.statis_out);
        }
    }
}
