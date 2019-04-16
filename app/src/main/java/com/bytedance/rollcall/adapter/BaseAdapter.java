package com.bytedance.rollcall.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.bytedance.rollcall.bean.BaseBean;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public abstract class BaseAdapter<V extends BaseViewHolder, T extends Object>
        extends RecyclerView.Adapter<V> {

    private List<T> mData;
    private OnItemClickListener mOnItemClickListener;

    public BaseAdapter(List<T> data) {
        this(data, null);
    }

    public BaseAdapter(List<T> mData, OnItemClickListener onItemClickListener) {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        this.mData = mData;
        mOnItemClickListener = onItemClickListener;
    }

    /**
     * 实现类中重写，根据T的类型渲染不同的layout
     */
    public abstract V createVHAccordViewType(ViewGroup parent, int viewType);

    /**
     * 实现类中重写，根据T的类型填充数据
     *
     * @param holder
     * @param position
     */
    public abstract void convert(V holder, int position);

    /**
     * 为每一个Item设置监听
     * 为Item中添加到HashSet的子项设置监听
     *
     * @param holder
     * @param position
     */
    private void bindViewClickListener(V holder, int position) {
        if (holder == null) {
            return;
        }
        View convertView = holder.convertView;
        if (convertView == null) {
            return;
        }
        if (mOnItemClickListener != null) {
            convertView.setOnClickListener(v -> {
                setOnItemClick(convertView, position);
            });
            HashSet<Integer> childClickViewIds = holder.getChildClickViewIds();
            for (Integer i : childClickViewIds) {
                View childView = holder.getView(i);
                childView.setOnClickListener(v -> {
                    setOnChildItemClick(childView, position);
                });
            }
        }
    }

    private void setOnChildItemClick(View childView, int position) {
        mOnItemClickListener.onChildClick(this, childView, position);
    }

    private void setOnItemClick(View view, int position) {
        mOnItemClickListener.onClick(this, view, position);
    }

    @Override
    public V onCreateViewHolder(ViewGroup parent, int viewType) {
        V viewHolder = createVHAccordViewType(parent, viewType);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(V holder, int position) {
        bindViewClickListener(holder, position);
        convert(holder, position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public List<T> getData() {
        return mData;
    }

    public void setData(List<T> data) {
        if (data != null) {
            mData.clear();
            mData.addAll(data);
            notifyDataSetChanged();
        }
    }
}
