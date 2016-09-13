package iii.org.tw.painter;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by user on 2016/9/13.
 */
public class MyView extends View {
    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //setOnClickListener(new myClickListener());

    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(4);
        canvas.drawLine(0,0,100,100,p);
    }

//    private class myClickListener implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//            Log.d("Abner","Click");
//        }
//    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float ex = event.getX() , ey = event.getY();
        Log.d("Abner","onTouchEvent 的 x = " + ex + "y = " + ey);
        //return super.onTouchEvent(event);
        return true;
    }


}