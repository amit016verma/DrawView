package com.kimogi.drawview;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class DrawView extends View implements OnTouchListener {
    private List<Point> points = new ArrayList<Point>();
    private Paint paint;
    private Bitmap bitmap;
    private Canvas actualCanvas;

    private final int lineWidth = 2;
    private final int color = Color.BLUE;

    public DrawView(Context context) {
        super(context);
        init();
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public DrawView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    private void init() {
        setFocusable(true);
        setFocusableInTouchMode(true);

        this.setOnTouchListener(this);

        paint = new Paint();
        paint.setColor(color);
        paint.setStrokeWidth(lineWidth);
        paint.setAntiAlias(true);
    }

    @Override
    public void onDraw(Canvas canvas) {
        if (bitmap == null) {
            Bitmap immutableBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.transparent_bg);
            this.bitmap = immutableBitmap.copy(Bitmap.Config.ARGB_8888, true);
            actualCanvas = new Canvas(bitmap);
        }

        for (Point point : points) {
            if (points.indexOf(point) == 0) {
                actualCanvas.drawCircle(point.x, point.y, (int) (lineWidth / 2), paint);
            } else {
                Point last = points.get(points.indexOf(point) - 1);
                actualCanvas.drawLine(last.x, last.y, point.x, point.y, paint);
            }
        }
        canvas.drawBitmap(bitmap, 0, 0, paint);
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void clear() {
        bitmap = null;
        actualCanvas = null;
        points.clear();
        invalidate();
    }

    public boolean onTouch(View view, MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            points.clear();
        } else {
            Point point = new Point();
            point.x = event.getX();
            point.y = event.getY();
            points.add(point);
        }
        invalidate();
        return true;
    }
}

class Point {
    float x, y;

    @Override
    public String toString() {
        return x + ", " + y;
    }
}