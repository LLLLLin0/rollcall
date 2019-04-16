package com.bytedance.rollcall.statis;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytedance.rollcall.R;
import com.bytedance.rollcall.adapter.BaseAdapter;
import com.bytedance.rollcall.adapter.BaseViewHolder;
import com.bytedance.rollcall.adapter.OnItemClickListener;
import com.bytedance.rollcall.bean.Lesson;
import com.bytedance.rollcall.bean.Student;
import com.bytedance.rollcall.db.DBHelper;
import com.bytedance.rollcall.db.bean.OneRollCall;

import java.util.List;

public class StaticsStudentAdapter extends BaseAdapter<BaseViewHolder, Student> {

    private Lesson mLesson;

    public StaticsStudentAdapter(List<Student> data, Lesson lesson) {
        super(data);
        mLesson = lesson;
    }

    public StaticsStudentAdapter(List<Student> mData, OnItemClickListener onItemClickListener) {
        super(mData, onItemClickListener);
    }

    @Override
    public BaseViewHolder createVHAccordViewType(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.statics_student_item_layout, parent, false);
        BaseViewHolder baseViewHolder = new BaseViewHolder(view);
        return baseViewHolder;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        Student student = getData().get(position);
        List<OneRollCall> oneRollCalls = DBHelper.selectOneRollCall(mLesson.getId(), student.getId());
        int on = 0;
        int out = 0;
        for (OneRollCall oneRollCall : oneRollCalls) {
            if (oneRollCall.getStatus() == 0) {
                out++;
            } else if (oneRollCall.getStatus() == 1) {
                on++;
            }
        }
        holder.setText(R.id.name, student.getName());
        holder.setText(R.id.on, String.valueOf(on));
        holder.setText(R.id.out, String.valueOf(out));
        int all = on + out;
        holder.setText(R.id.roll_call, String.valueOf(all));
        int rate = (int) (((float)on / all) * 100);
        holder.setText(R.id.rate, String.valueOf(rate) + '%');
        holder.setText(R.id.leave, "0");
    }
}
