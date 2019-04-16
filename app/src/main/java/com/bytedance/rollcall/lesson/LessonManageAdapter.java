package com.bytedance.rollcall.lesson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytedance.rollcall.R;
import com.bytedance.rollcall.adapter.BaseAdapter;
import com.bytedance.rollcall.adapter.BaseViewHolder;
import com.bytedance.rollcall.adapter.OnItemClickListener;
import com.bytedance.rollcall.bean.Lesson;

import java.util.List;

public class LessonManageAdapter extends BaseAdapter<BaseViewHolder, Lesson> {

    public LessonManageAdapter(List<Lesson> data) {
        super(data);
    }

    public LessonManageAdapter(List<Lesson> mData, OnItemClickListener onItemClickListener) {
        super(mData, onItemClickListener);
    }

    @Override
    public BaseViewHolder createVHAccordViewType(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.lesson_manage_item_layout, parent, false);
        BaseViewHolder baseViewHolder = new BaseViewHolder(view);
        return baseViewHolder;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        Lesson lesson = getData().get(position);
        holder.setText(R.id.name, lesson.getName());
    }
}
