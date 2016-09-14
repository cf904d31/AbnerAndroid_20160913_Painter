package iii.org.tw.painter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * Created by user on 2016/9/13.
 */
public class MyView extends View {
    private LinkedList<LinkedList<HashMap<String,Float>>> lines;
    private Resources res;
    private boolean isset;
    private int screenW , screenH;
    private Bitmap bmpBall , bmpBG;
    private Matrix matrix;

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
        lines = new LinkedList<>();
        res = context.getResources();
        matrix = new Matrix();

        //setOnClickListener(new myClickListener());

    }

    private void init() {

        screenW = getWidth();
        screenH = getHeight();
        Log.d("Abner","Width=" + screenW + "\t Height=" + screenH);
        //-----隨著螢幕大小而來改變圖片的縮放率
        float ballW = screenW/8f , ballH = ballW;



        bmpBG = BitmapFactory.decodeResource(res, R.drawable.bg);
        bmpBG = resizeBitmap(bmpBG, screenW,screenH);


        bmpBall = BitmapFactory.decodeResource(res, R.drawable.ball);
        bmpBall = resizeBitmap(bmpBall, ballW,ballH);
        //


        isset = true;
    }

    private Bitmap resizeBitmap (Bitmap src , float newW , float newH) {
        matrix.reset();
        matrix.postScale(newW/src.getWidth(),newH/src.getHeight());
        bmpBall = Bitmap.createBitmap(src,0,0,src.getWidth(),src.getHeight(),matrix, false);
        return bmpBall;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if(!isset) {init();}



        canvas.drawBitmap(bmpBG,0,0,null);

        canvas.drawBitmap(bmpBall,0,0,null);


        Paint p = new Paint();
        p.setColor(Color.BLUE);
        p.setStrokeWidth(4);





        for (LinkedList<HashMap<String,Float>> line : lines) {
            for (int i = 1; i < line.size(); i++) {
                canvas.drawLine(line.get(i - 1).get("x"), line.get(i - 1).get("y"), line.get(i).get("x"), line.get(i).get("y"), p);
            }
        }
        //canvas.drawLine(0,0,100,100,p);
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
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            doTouchDown(ex,ey);
            //Log.d("Abner", "Down 的 x = " + ex + "y = " + ey);
        } else if (event.getAction() == MotionEvent.ACTION_MOVE) {
            doTouchMove(ex,ey);
            //Log.d("Abner", "Move 的 x = " + ex + "y = " + ey);
        }


        //return super.onTouchEvent(event);
        return true;
    }

    private void doTouchDown(float x, float y) {
        LinkedList<HashMap<String,Float>> line = new LinkedList<>();
        lines.add(line);
        addPoint(x,y);
    }

    private void doTouchMove(float x, float y) {
        addPoint(x,y);
    }

    private void addPoint(float x, float y) {
        HashMap<String,Float> point = new HashMap<>();
        point.put("x",x);
        point.put("y",y);
        lines.getLast().add(point);
        invalidate();
    }


}
