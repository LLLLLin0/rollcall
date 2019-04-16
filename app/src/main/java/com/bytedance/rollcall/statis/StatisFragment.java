package com.bytedance.rollcall.statis;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bytedance.rollcall.R;
import com.bytedance.rollcall.adapter.BaseAdapter;
import com.bytedance.rollcall.adapter.OnItemClickListener;
import com.bytedance.rollcall.bean.Lesson;
import com.bytedance.rollcall.db.DBHelper;

import java.util.ArrayList;
import java.util.List;

public class StatisFragment extends Fragment {

    private TextView title;
    private RecyclerView mRecyclerView;
    private StatisAdapter mAdapter;
    private static boolean isDataExpired = false;

    @Override
    public void onResume() {
        super.onResume();
        if (isDataExpired) {
            refreshList();
            setDataExpired(false);
        }
    }

    public static void setDataExpired(boolean expired) {
        isDataExpired = expired;
    }

    private void refreshList() {
        mAdapter.setData(getData());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.statics_fragment_layout, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        title = view.findViewById(R.id.title);
        title.setText(R.string.statics);
        mRecyclerView = view.findViewById(R.id.recycler);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mAdapter = new StatisAdapter(getData(), getListener());
        mRecyclerView.setAdapter(mAdapter);
    }

    private OnItemClickListener getListener() {
        return new OnItemClickListener() {
            @Override
            public void onClick(BaseAdapter baseAdapter, View view, int position) {
                //打开统计activity
                Lesson lesson = getData().get(position);
                LessonStaticsActivity.startRollCallActivity(getContext(), lesson);
            }

            @Override
            public void onChildClick(BaseAdapter baseAdapter, View view, int position) {

            }
        };
    }

    private List<Lesson> getData() {
        List<com.bytedance.rollcall.db.bean.Lesson> allLesson = DBHelper.selectAllLesson();
        List<Lesson> list = new ArrayList<>();
        for (com.bytedance.rollcall.db.bean.Lesson lessonBean : allLesson) {
            list.add(Lesson.convert(lessonBean));
        }
        return list;
    }
}
