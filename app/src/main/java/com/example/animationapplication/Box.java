package com.example.animationapplication;

import android.graphics.PointF;

public class Box {
    private PointF origin;
    private PointF current;

    public Box(PointF origin) {
        this.origin = origin;
    }

    public PointF getOrigin() {
        return origin;
    }

    public void setOrigin(PointF origin) {
        this.origin = origin;
    }

    public PointF getCurrent() {
        return current;
    }

    public void setCurrent(PointF current) {
        this.current = current;
    }
}
