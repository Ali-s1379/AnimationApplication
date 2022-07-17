package com.example.animationapplication;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class BoxDrawingView extends View {

    private Box currentBox;
    private Box currentBox2;
    private List<Box> boxList = new ArrayList<>();
    private List<Box> boxList2 = new ArrayList<>();
    private Paint boxColour;
    private Paint backgroundColour;
    private List<Float> pts = new ArrayList<>();
    private int mActivePointerId;

    public BoxDrawingView(Context context) {
        super(context);
    }

    public BoxDrawingView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        boxColour = new Paint();
        boxColour.setColor(getResources().getColor(R.color.box));
        backgroundColour = new Paint();
        backgroundColour.setColor(getResources().getColor(R.color.background));
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        PointF current = new PointF(event.getX(0), event.getY(0));

//        PointF current2 = new PointF(event.getX(event.getActionIndex()), event.getY(event.getActionIndex()));

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentBox = new Box(current);
                boxList.add(currentBox);
                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//                currentBox2 = new Box(current2);
//                boxList2.add(currentBox2);
//                break;
            case MotionEvent.ACTION_MOVE:
                if (currentBox != null) {
                    currentBox.setCurrent(current);
                    pts.add(currentBox.getCurrent().x);
                    pts.add(currentBox.getCurrent().y);
                    invalidate();
                }
//                if (currentBox2 != null) {
//                    currentBox2.setCurrent(current2);
//                    pts.add(currentBox2.getCurrent().x);
//                    pts.add(currentBox2.getCurrent().y);
//                    invalidate();
//                }
                break;
            case MotionEvent.ACTION_UP:
//                invalidate();
                currentBox = null;
                break;
            case MotionEvent.ACTION_POINTER_UP:
                currentBox2 = null;
                break;
            case MotionEvent.ACTION_CANCEL:
                break;

        }

        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawPaint(backgroundColour);

        for (Box box: boxList) {
            try {
                float left = Math.min(box.getOrigin().x, box.getCurrent().x);
                float top = Math.min(box.getOrigin().y, box.getCurrent().y);
                float right = Math.max(box.getOrigin().x, box.getCurrent().x);
                float bottom = Math.max(box.getOrigin().y, box.getCurrent().y);

                canvas.drawRect(left,top,right,bottom,boxColour);
            } catch (NullPointerException e) {
                e.printStackTrace();
            }

//            float[] points = new float[pts.size()];
//            for (int i = 0; i < pts.size();i++) {
//                points[i] = pts.get(i);
//            }
//            canvas.drawLines(points,boxColour);
//
//            float cx = (box.getOrigin().x + box.getCurrent().x) / 2;
//            float cy = (box.getOrigin().y + box.getCurrent().y) / 2;
//            float radius = (float) Math.sqrt(Math.pow(box.getOrigin().x - box.getCurrent().x,2) + Math.pow(box.getOrigin().y - box.getCurrent().y,2)) / 2;
//
//            canvas.drawCircle(cx, cy, radius, boxColour);
        }

    }
}

