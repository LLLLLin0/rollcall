package com.bytedance.rollcall.lesson;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bytedance.rollcall.R;
import com.bytedance.rollcall.adapter.BaseAdapter;
import com.bytedance.rollcall.adapter.OnItemClickListener;
import com.bytedance.rollcall.bean.Lesson;
import com.bytedance.rollcall.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class LessonFragment extends Fragment {

    private TextView title;
    private ImageView mImageView;
    private RecyclerView mRecyclerView;
    private LessonManageAdapter mLessonManageAdapter;
    private static boolean isDataExpired = false;

    @Override
    public void onResume() {
        super.onResume();
        if (isDataExpired) {
            refreshList();
            setDataExpired(false);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.lesson_fragment_layout, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        title = view.findViewById(R.id.title);
        title.setText(R.string.lesson);
        mImageView = view.findViewById(R.id.more);
        mImageView.setVisibility(View.VISIBLE);
        mImageView.setOnClickListener( v -> {
            PopupMenu popupMenu = new PopupMenu(getContext(), mImageView);
            MenuInflater inflater = popupMenu.getMenuInflater();
            inflater.inflate(R.menu.more, popupMenu.getMenu());
            popupMenu.show();
            MenuItem first = popupMenu.getMenu().findItem(R.id.create_lesson);
            first.setOnMenuItemClickListener(item -> {
                CreateLessonActivity.start(getContext());
                return true;
            });
            MenuItem second = popupMenu.getMenu().findItem(R.id.create_student);
            second.setOnMenuItemClickListener(item -> {
                CreateStudentActivity.start(getContext());
                return true;
            });
        });

        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onClick(BaseAdapter baseAdapter, View view, int position) {
                Lesson lesson = (Lesson) baseAdapter.getData().get(position);
                AddStudentActivity.start(getContext(), lesson);
            }

            @Override
            public void onChildClick(BaseAdapter baseAdapter, View view, int position) {

            }
        };
        mLessonManageAdapter = new LessonManageAdapter(getLessons(), onItemClickListener);
        mRecyclerView.setAdapter(mLessonManageAdapter);
    }

    private List<Lesson> getLessons() {
        List<com.bytedance.rollcall.db.bean.Lesson> lessons = DBHelper.selectAllLesson();
        if (lessons == null || lessons.size() == 0) {
            return null;
        }
        List<Lesson> list = new ArrayList<>();
        for (com.bytedance.rollcall.db.bean.Lesson lessonBean : lessons) {
            Lesson lesson = Lesson.convert(lessonBean);
            list.add(lesson);
        }
        return list;
    }

    private void refreshList() {
        mLessonManageAdapter.setData(getLessons());
    }

    public static boolean isDataExpired() {
        return isDataExpired;
    }

    public static void setDataExpired(boolean dataExpired) {
        isDataExpired = dataExpired;
    }
}
