package com.bytedance.rollcall.rollcall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytedance.rollcall.R;
import com.bytedance.rollcall.adapter.BaseAdapter;
import com.bytedance.rollcall.adapter.BaseViewHolder;
import com.bytedance.rollcall.adapter.OnItemClickListener;
import com.bytedance.rollcall.bean.Lesson;

import java.util.List;

public class RollCallAdapter extends BaseAdapter<BaseViewHolder, Lesson> {

    public RollCallAdapter(List<Lesson> data) {
        super(data);
    }

    public RollCallAdapter(List<Lesson> mData, OnItemClickListener onItemClickListener) {
        super(mData, onItemClickListener);
    }

    @Override
    public BaseViewHolder createVHAccordViewType(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.roll_call_classes_item_layout,
                parent, false);
        BaseViewHolder baseViewHolder = new BaseViewHolder(view);
        return baseViewHolder;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        Lesson lesson = getData().get(position);
        holder.setText(R.id.classes_tv, lesson.getName());
        holder.setText(R.id.time_tv, lesson.getTime());
        holder.setText(R.id.classroom_tv, lesson.getRoom());
        holder.setText(R.id.student_num_tv, String.valueOf(lesson.getStudNum()));
    }
}
