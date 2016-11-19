package com.kent.zhifoudaily.event;

import com.kent.zhifoudaily.entity.AttrsValueHolder;

public class ToggleNightMode {

    public ToggleNightMode(boolean isNightMode, AttrsValueHolder attrs) {
        this.isNightMode = isNightMode;
        this.attrs = attrs;
    }

    public boolean isNightMode;
    public AttrsValueHolder attrs;
}
