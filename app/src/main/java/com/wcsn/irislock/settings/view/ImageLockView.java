package com.wcsn.irislock.settings.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

import com.wcsn.irislock.R;
import com.wcsn.irislock.settings.listener.OnPatternChangedListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by suiyue on 2016/8/18 0018.
 */
public class ImageLockView extends View {

    private static final String TAG = ImageLockView.class.getSimpleName();
    private static final int mPointNum = 3;

    private Point[][] mPoints = new Point[3][3];

    //是否初始化、开始绘制、结束
    private boolean mIsInit = false, isSelected, isFinish, movingNoPoint;
    private int mWidth;
    private int mHeight;
    private float offsetX;
    private float offsetY;



    private Bitmap mPointNormal = BitmapFactory.decodeResource(getResources(), R.drawable.point_normal);
    private Bitmap mPointSelected = BitmapFactory.decodeResource(getResources(), R.drawable.point_selected);
    private Bitmap mLine = BitmapFactory.decodeResource(getResources(), R.drawable.line);

    private float mBitmapR = mPointNormal.getWidth() >> 1;

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);


    //已选择点集合
    private List<Point> mPointList = new ArrayList<>();

    //手指滑动式屏幕上的坐标
    private float mMovingX;
    private float mMovingY;

    //上一次选中的点
    private Point mLastSelectedPoint;

    //保存设置的密码
    private String password;

    //画图监听
    private OnPatternChangedListener onPatternChangedListener;//画图监听器


    public ImageLockView(Context context) {
        super(context);
    }

    public ImageLockView(Context context, AttributeSet attrs) {
        super(context, attrs);
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        mWidth = metrics.widthPixels;
        mHeight = metrics.heightPixels;
        setPassword("");
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ImageLockView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        
        if (! mIsInit) {
            initPoints();
            mIsInit = true;
        }
        //画九宫格
        points2Canvas(canvas);

        //点与点之间的连线
        drawLinePoint2Point(canvas);

        //如果绘制没有结束
        if(isSelected) {
            //画最后一个点与鼠标之间的连线
            drawLinePoint2Mouse(canvas);
        }
    }

    private void initPoints() {

        int patternSpace;
        int paddingLeft, paddingTop;

        //横竖屏
        if (mWidth > mHeight) {
            patternSpace = mWidth / (mPointNum + 1);
            paddingLeft = patternSpace;
            paddingTop = (mHeight - (mPointNum - 1) * patternSpace) >> 1;
        } else {
            patternSpace = mWidth / (mPointNum + 1);
            paddingLeft = patternSpace;
            paddingTop = (mHeight - (mPointNum - 1) * patternSpace) >> 1;
        }

        for(int i=0; i<mPointNum; i++) {
            for(int j=0; j<mPointNum; j++) {
                //设置点的坐标和索引
                mPoints[i][j] = new Point(paddingLeft + j * patternSpace,
                        paddingTop + i * patternSpace);
                mPoints[i][j].setIndex(i * mPointNum + j);
            }
        }

    }

    private void points2Canvas(Canvas canvas) {

        Point p;
        int radius = mPointNormal.getWidth() >> 1;
        for(int i=0; i<mPointNum; i++) {
            for(int j=0; j<mPointNum; j++) {
                p = mPoints[i][j];
                if (p.status == Point.STATE_NORMAL) {
                    canvas.drawBitmap(mPointNormal, p.getX() - radius, p.getY() - radius, mPaint);
                } else  {
                    canvas.drawBitmap(mPointSelected, p.getX() - radius, p.getY() - radius, mPaint);
                }
            }
        }
    }

    /**
     * 画最后一个点和鼠标之间的连线
     * @param canvas 画布
     */
    private void drawLinePoint2Mouse(Canvas canvas) {
        if(null != mLastSelectedPoint)
            drawLine(canvas, mLastSelectedPoint, new Point(mMovingX, mMovingY));
    }

    /**
     * 画点到点之间的线
     * @param canvas 画布
     */
    private void drawLinePoint2Point(Canvas canvas) {
        int size = mPointList.size();
        Point start = null;
        if(size > 0) {
            start = mPointList.get(0);
            Point end;
            for (int i = 1; i < mPointList.size(); i++) {
                end = mPointList.get(i);
                drawLine(canvas, start, end);
                start = end;
            }
        }
        mLastSelectedPoint = start;
    }

    /**
     * 画线
     * @param canvas 画布
     * @param start 起始点
     * @param end 结束点
     */
    private void drawLine(Canvas canvas, Point start, Point end) {
        Matrix matrix = new Matrix();
        double dis = start.distance(end.getX(), end.getY());
        float degree = (float)start.degree(end.getX(), end.getY());
        canvas.rotate(degree, start.getX(), start.getY());//旋转画布
        matrix.setScale((float)dis/mLine.getWidth(), 1);//拉伸图片
        matrix.postTranslate(start.getX(), start.getY());
        canvas.drawBitmap(mLine, matrix, mPaint);
        canvas.rotate(-degree, start.getX(), start.getY());//将画布旋转回来
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        //获取屏幕上的坐标
        mMovingX = event.getX();
        mMovingY = event.getY();
        Point p;
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                reset();//重置所有点
                if(null != onPatternChangedListener) {
                    onPatternChangedListener.patternStart(true);
                }
                if(!isFinish) {
                    for(int i=0; i<mPointNum; i++) {
                        for(int j=0; j<mPointNum; j++) {
                            p = mPoints[i][j];
                            if(p.status != Point.STATE_SELECTED) {
                                if(p.intersect(mMovingX, mMovingY, mPointNormal.getWidth() >> 1)) {
                                    if(!mPointList.contains(p)) {
                                        p.status = Point.STATE_SELECTED;
                                        mPointList.add(p);
                                        mLastSelectedPoint = p;
                                        isSelected = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
                for(int i=0; i<mPointNum; i++) {
                    for(int j=0; j<mPointNum; j++) {
                        p = mPoints[i][j];
                        if(p.status != Point.STATE_SELECTED) {
                            if(p.intersect(mMovingX, mMovingY, mBitmapR)) {
                                //两点之间的点
                                if(null != mLastSelectedPoint) {
                                    int sumIndex = mLastSelectedPoint.getIndex() + p.getIndex();
                                    Point mid = mPoints[(sumIndex>>1)/mPointNum][(sumIndex>>1)%mPointNum];
                                    if(mid.intersect((mLastSelectedPoint.getX()+p.getX())/2,
                                            (mLastSelectedPoint.getY()+p.getY())/2, mBitmapR)) {
                                        mid.status = Point.STATE_SELECTED;
                                        mPointList.add(mid);
                                    }
                                }
                                p.status = Point.STATE_SELECTED;
                                mPointList.add(p);
                                mLastSelectedPoint = p;
                                isSelected = true;
                                break;
                            }
                        }
                    }
                }
                break;
            case MotionEvent.ACTION_UP:
                //停止绘制
                isSelected = false;
                isFinish = true;
                //停止绘制，检查点数是否满足要求
                checkPattern();
        }
        postInvalidate();
        return true;
    }

    /**
     * 停止绘制，检查点数是否满足要求
     */
    private void checkPattern() {
        if(mPointList.size() <= 1) {
            reset();
        } else {
            if(null != onPatternChangedListener) {
                String pw = "";
                for(Point p: mPointList) {
                    pw += p.getIndex();
                }
                setPassword(pw);
                onPatternChangedListener.patternChanged(pw);
            }
        }
    }

    public void reset() {

        for(Point p: mPointList) {
            p.status = Point.STATE_NORMAL;
        }
        mPointList.clear();
    }

    public void resetPoints() {
        reset();
        setPassword("");
        postInvalidate();
    }

    public void setOnPatternChangedListener(OnPatternChangedListener onPatternChangedListener) {
        if(null != onPatternChangedListener) {
            this.onPatternChangedListener = onPatternChangedListener;
        }
    }


    private boolean crossPoint(Point point) {
        if (mPointList.contains(point)) {
            return true;
        }
        return false;
    }

    public static class Point {

        public static int STATE_NORMAL = 0;
        public static int STATE_SELECTED = 1;

        private float x;//点中心点的x坐标
        private float y;//点中心点的y坐标


        private int index;//点的索引

        private int status;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
            this.status = STATE_NORMAL;
        }

        public float getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public float getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        public int getIndex() {
            return index;
        }

        public void setIndex(int index) {
            this.index = index;
        }

        /**
         * 屏幕上某点与该点之间的夹角
         * @param screenX x坐标
         * @param screenY y坐标
         * @return 夹角
         */
        public double degree(float screenX, float screenY) {
            float offsetX = screenX - x;
            float offsetY = screenY - y;
            double dis = distance(screenX, screenY);
            double rad;
            if(offsetX >= 0) {
                rad = Math.asin((offsetY) / (dis));
            } else {
                rad = - Math.asin((offsetY)/(dis)) + Math.PI;
            }
            return rad / Math.PI * 180;
        }

        /**
         * 屏幕上某点与该点之间的距离
         * @param screenX x坐标
         * @param screenY y坐标
         * @return 距离
         */
        public double distance(float screenX, float screenY) {
            return Math.sqrt(Math.pow(Math.abs(screenX - x), 2) + Math.pow(Math.abs(screenY - y), 2));
        }

        /**
         * 判断屏幕上的点是否在以该点为圆心的圆内
         * @param screenX x坐标
         * @param screenY y坐标
         * @param radius 半径
         * @return true表示在范围内，否则不在
         */
        public boolean intersect(float screenX, float screenY, float radius) {
            return radius > distance(screenX, screenY);
        }
    }

}
