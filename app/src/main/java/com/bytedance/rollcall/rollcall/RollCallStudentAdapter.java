package com.bytedance.rollcall.rollcall;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bytedance.rollcall.R;
import com.bytedance.rollcall.adapter.BaseAdapter;
import com.bytedance.rollcall.adapter.BaseViewHolder;
import com.bytedance.rollcall.adapter.OnItemClickListener;
import com.bytedance.rollcall.bean.Student;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class RollCallStudentAdapter extends BaseAdapter<BaseViewHolder, Student> {

    public RollCallStudentAdapter(List<Student> data) {
        super(data);
    }

    public RollCallStudentAdapter(List<Student> mData, OnItemClickListener onItemClickListener) {
        super(mData, onItemClickListener);
    }

    @Override
    public BaseViewHolder createVHAccordViewType(ViewGroup parent, int viewType) {
        View view;
        if (viewType == 1) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.roll_call_student_item_layout,
                    parent, false);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.submit_item_layout,
                    parent, false);
        }
        BaseViewHolder baseViewHolder = new BaseViewHolder(view);
        return baseViewHolder;
    }

    @Override
    public void convert(BaseViewHolder holder, int position) {
        if (position == getData().size() - 1) {
            return;
        }
        Student student = getData().get(position);
        holder.setText(R.id.name, student.getName());
        CircleImageView imageView = (CircleImageView) holder.getView(R.id.image);
        if ("男".equals(student.getSex())) {
            imageView.setImageResource(R.drawable.male_image);
        } else {
            imageView.setImageResource(R.drawable.female_image);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (getData() != null) {
            int size = getData().size();
            if (position != 0 && position == size - 1) {
                return 0;
            } else {
                return 1;
            }
        }
        return 1;
    }
}
