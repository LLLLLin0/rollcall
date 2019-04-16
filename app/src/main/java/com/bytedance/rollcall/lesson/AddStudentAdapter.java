package com.bytedance.rollcall.lesson;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.bytedance.rollcall.R;
import com.bytedance.rollcall.adapter.BaseAdapter;
import com.bytedance.rollcall.adapter.BaseViewHolder;
import com.bytedance.rollcall.adapter.OnItemClickListener;
import com.bytedance.rollcall.db.DBHelper;
import com.bytedance.rollcall.db.bean.Student;
import com.bytedance.rollcall.util.L;

import java.util.ArrayList;
import java.util.List;

public class AddStudentAdapter extends BaseAdapter<BaseViewHolder, String> {

    private Context mContext;
    private boolean[][] add;
    private List<StudentAdapter> mAdapterList = new ArrayList<>();

    public AddStudentAdapter(List<String> data, boolean[][] add) {
        super(data);
        this.add = add;
    }

    public AddStudentAdapter(List<String> mData, OnItemClickListener onItemClickListener) {
        super(mData, onItemClickListener);
    }

    public List<StudentAdapter> getAdapterList() {
        return mAdapterList;
    }

    @Override
    public BaseViewHolder createVHAccordViewType(ViewGroup parent, int viewType) {
        mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(
                R.layout.add_student_item_layout, parent, false);
        BaseViewHolder baseViewHolder = new BaseViewHolder(view);
        return baseViewHolder;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        String classes = getData().get(position);
        holder.setText(R.id.classes_tv, classes);
        List<Student> students = DBHelper.selectStudentByClassesName(classes);
        if (students == null || students.size() == 0) {
            L.i("no student");
            return;
        }
        RecyclerView recycler = (RecyclerView) holder.getView(R.id.recycler);
        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        StudentAdapter studentAdapter = new StudentAdapter(convert(students), getListener(position));
        if (!mAdapterList.contains(studentAdapter)) {
            mAdapterList.add(studentAdapter);
        }
        recycler.setAdapter(studentAdapter);
    }

    private OnItemClickListener getListener(int parent) {
        return new OnItemClickListener() {
            @Override
            public void onClick(BaseAdapter baseAdapter, View view, int position) {
                CheckBox checkbox = view.findViewById(R.id.checkbox);
                checkbox.setChecked(!checkbox.isChecked());
                add[parent][position] = checkbox.isChecked();
            }

            @Override
            public void onChildClick(BaseAdapter baseAdapter, View view, int position) {

            }
        };
    }

    private List<com.bytedance.rollcall.bean.Student> convert(List<Student> students) {
        List<com.bytedance.rollcall.bean.Student> list = new ArrayList<>();
        for (Student studentBean : students) {
            com.bytedance.rollcall.bean.Student student = com.bytedance.rollcall.bean.Student.convert(studentBean);
            list.add(student);
        }
        return list;
    }

    class StudentAdapter extends BaseAdapter<BaseViewHolder, com.bytedance.rollcall.bean.Student> {

        public StudentAdapter(List<com.bytedance.rollcall.bean.Student> data) {
            super(data);
        }

        public StudentAdapter(List<com.bytedance.rollcall.bean.Student> mData, OnItemClickListener onItemClickListener) {
            super(mData, onItemClickListener);
        }

        @Override
        public BaseViewHolder createVHAccordViewType(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(mContext).inflate(
                    R.layout.student_item_layout, parent, false);
            BaseViewHolder baseViewHolder = new BaseViewHolder(view);
            return baseViewHolder;
        }

        @Override
        public void convert(BaseViewHolder holder, int position) {
            com.bytedance.rollcall.bean.Student student = getData().get(position);
            holder.setText(R.id.name, student.getName());
        }
    }
}
