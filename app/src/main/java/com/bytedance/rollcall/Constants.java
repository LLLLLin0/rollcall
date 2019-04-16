package com.bytedance.rollcall;

import android.support.annotation.StringDef;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface Constants {

    @StringDef({Sex.MAEL,Sex.FEMAEL})
    @Retention(RetentionPolicy.SOURCE)
    @interface Sex {
        public static final String MAEL = "男";
        public static final String FEMAEL = "女";
    }
}
