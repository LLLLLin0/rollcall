package com.bytedance.rollcall.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.TextView;

import java.util.HashSet;
import java.util.LinkedHashSet;

public class BaseViewHolder extends RecyclerView.ViewHolder {

    public View convertView;
    private final SparseArray<View> views;
    protected final LinkedHashSet<Integer> childClickViewIds;

    public BaseViewHolder(View itemView) {
        super(itemView);
        convertView = itemView;
        this.views = new SparseArray<>();
        this.childClickViewIds = new LinkedHashSet<>();
    }

    /**
     * Item的子View从SparseArray中获取，避免重复findViewById()
     * @param viewId
     * @return
     */
    public View getView(int viewId) {
        View view = views.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            views.put(viewId, view);
        }
        return view;
    }

    public HashSet<Integer> getChildClickViewIds() {
        return childClickViewIds;
    }

    public void setText(int resId, String text) {
        TextView view = (TextView) getView(resId);
        view.setText(text);
    }
}
