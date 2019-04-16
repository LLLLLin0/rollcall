package com.bytedance.rollcall.rollcall;

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
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;

public class RollCallFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private RollCallAdapter mAdapter;
    private TextView mTitle;
    private TextView guideView;
    private String mDate;
    private static boolean isDataExpired = false;

    @Override
    public void onResume() {
        super.onResume();
        if (isDataExpired) {
            refreshList();
            setDataExpired(false);
        }
    }

    private void refreshList() {
        mAdapter.setData(getData());
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.roll_call_fragment_layout, container, false);
        init(view);
        return view;
    }

    private void init(View view) {
        guideView = view.findViewById(R.id.guide);
        mTitle = view.findViewById(R.id.title);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeZone(TimeZone.getTimeZone("GMT+8:00"));

        StringBuilder date = new StringBuilder();
        date.append(calendar.get(Calendar.YEAR)).append("-")
                .append(calendar.get(Calendar.MONTH)).append("-")
                .append(calendar.get(Calendar.DATE)).append("(")
                .append(getDayOfWeek(calendar.get(Calendar.DAY_OF_WEEK))).append(")");
        mDate = date.toString();
        mTitle.setText(mDate);

        mRecyclerView = view.findViewById(R.id.recycler);
        OnItemClickListener onItemClickListener = new OnItemClickListener() {
            @Override
            public void onClick(BaseAdapter baseAdapter, View view, int position) {
                Lesson lesson = (Lesson) baseAdapter.getData().get(position);
                RollCallActivity.startRollCallActivity(getContext(), lesson, mDate);
            }

            @Override
            public void onChildClick(BaseAdapter baseAdapter, View view, int position) {

            }
        };
        mAdapter = new RollCallAdapter(getData(), onItemClickListener);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);
        mRecyclerView.setVisibility(View.VISIBLE);
        guideView.setVisibility(View.GONE);
    }

    private List<Lesson> getData() {
        List<com.bytedance.rollcall.db.bean.Lesson> lessons = DBHelper.selectAllLesson();
        List<Lesson> list = new ArrayList<>();
        for (com.bytedance.rollcall.db.bean.Lesson lesson : lessons) {
            list.add(Lesson.convert(lesson));
        }
        return list;
    }

    private String getDayOfWeek(int i) {
        String day;
        switch (i) {
            case 1:
                day = "周一";
                break;
            case 2:
                day = "周二";
                break;
            case 3:
                day = "周三";
                break;
            case 4:
                day = "周四";
                break;
            case 5:
                day = "周五";
                break;
            case 6:
                day = "周六";
                break;
            case 7:
                day = "周日";
                break;
            default:
                day = "0";
        }
        return day;
    }

    public static void setDataExpired(boolean isDataExpired) {
        RollCallFragment.isDataExpired = isDataExpired;
    }
}
