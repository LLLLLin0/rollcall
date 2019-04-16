package com.bytedance.rollcall.ui.popubwindow;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.bytedance.rollcall.R;

public class ProfilePopubWindow extends PopupWindow {

    private TextView take_photo;
    private TextView from_phone;
    private TextView cancel;

    private View view;

    public ProfilePopubWindow(Context mContext, OnClickListener itemsOnClick) {

        this.view = LayoutInflater.from(mContext).inflate(R.layout.profile_popub_layout, null);

        take_photo = view.findViewById(R.id.take_photo);
        from_phone = view.findViewById(R.id.from_phone);
        cancel = view.findViewById(R.id.cancel);

        // 取消按钮
        cancel.setOnClickListener((OnClickListener) v -> {
            dismiss();
        });
        // 设置按钮监听
        take_photo.setOnClickListener(itemsOnClick);
        from_phone.setOnClickListener(itemsOnClick);

        // 设置外部可点击
        this.setOutsideTouchable(true);
        // mMenuView添加OnTouchListener监听判断获取触屏位置如果在选择框外面则销毁弹出框
        this.view.setOnTouchListener((v, event) -> {

            int height = view.findViewById(R.id.popubwindow).getTop();

            int y = (int) event.getY();
            if (event.getAction() == MotionEvent.ACTION_UP) {
                if (y < height) {
                    dismiss();
                }
            }
            return true;
        });


        /* 设置弹出窗口特征 */
        // 设置视图
        this.setContentView(this.view);
        // 设置弹出窗体的宽和高
        this.setHeight(LinearLayout.LayoutParams.MATCH_PARENT);
        this.setWidth(LinearLayout.LayoutParams.MATCH_PARENT);

        // 设置弹出窗体可点击
        this.setFocusable(true);

        // 实例化一个ColorDrawable颜色为半透明
        ColorDrawable dw = new ColorDrawable(0xb0000000);
        // 设置弹出窗体的背景
        this.setBackgroundDrawable(dw);

        // 设置弹出窗体显示时的动画，从底部向上弹出
        this.setAnimationStyle(R.style.popub_anim);
    }
}
