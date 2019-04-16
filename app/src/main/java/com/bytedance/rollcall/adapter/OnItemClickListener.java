package com.bytedance.rollcall.adapter;

import android.view.View;

public interface OnItemClickListener {
    abstract void onClick(BaseAdapter baseAdapter, View view, int position);

    abstract void onChildClick(BaseAdapter baseAdapter, View view, int position);
}
