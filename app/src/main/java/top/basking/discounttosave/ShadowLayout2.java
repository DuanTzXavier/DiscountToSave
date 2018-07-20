package top.basking.discounttosave;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by tzduan on 2018/7/20.
 */

public class ShadowLayout2 extends FrameLayout {

    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private RectF mRectF = new RectF();

    /**
     * 阴影的颜色, 需要带透明
     */
    private int mShadowColor = Color.argb(128, 249, 94, 94);

    /**
     * 阴影的大小范围 radius越大越模糊，越小越清晰
     */
    private float mShadowRadius = 30;

    /**
     * 阴影的宽度，比如底部的阴影，那就是底部阴影的高度
     */
    private float mShadowWidth = 25;

    /**
     * 阴影 x 轴的偏移量, 计算padding时需要计算在内
     */
    private float mShadowDx = 0;

    /**
     * 阴影 y 轴的偏移量，计算padding时需要计算在内，比如想底部的阴影多一些，这个设置值就可以了
     */
    private float mShadowDy = 10;

    public ShadowLayout2(Context context) {
        this(context, null);
    }

    public ShadowLayout2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ShadowLayout2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }


    /**
     * 为 ShadowLayout 设置 Padding 以为显示阴影留出空间
     */
    private void resetShadowPadding() {
        float rectLeft = 0;
        float rectTop = 0;
        float rectRight = 0;
        float rectBottom = 0;
        int paddingLeft = 0;
        int paddingTop = 0;
        int paddingRight = 0;
        int paddingBottom = 0;

        // todo 测试代码，待完善，暂时是右侧和底部的阴影
        rectRight = this.getWidth() - mShadowWidth - mShadowDx;
        paddingRight = (int) mShadowWidth + (int) mShadowDx;

//        rectLeft = this.getWidth() + mShadowWidth - mShadowDx;
        paddingLeft = (int) mShadowWidth + (int) mShadowDx;
        rectBottom = this.getHeight() - mShadowWidth - mShadowDy;
        paddingBottom = (int) mShadowWidth  + (int) mShadowDy;

        mRectF.left = rectLeft;
        mRectF.top = rectTop;
        mRectF.right = rectRight;
        mRectF.bottom = rectBottom;
        this.setPadding(paddingLeft, paddingTop, paddingRight, paddingBottom);
    }

    /**
     * 决定View在ViewGroup中的位置 , 此处left ，top...是相对于父视图
     */
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);

        resetShadowPadding();
    }

    /**
     * 决定View的大小
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    /**
     * 如何绘制这个View, 真正绘制阴影的方法
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawRect(mRectF, mPaint);
    }

    /**
     * 读取设置的阴影的属性
     *
     * @param attrs 从其中获取设置的值
     */
    private void init(AttributeSet attrs) {
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);  // 关闭硬件加速,setShadowLayer 才会有效
        this.setWillNotDraw(false);                    // 调用此方法后，才会执行 onDraw(Canvas) 方法
        mPaint.setAntiAlias(true);
        mPaint.setColor(Color.TRANSPARENT);
        mShadowColor = getContext().getResources().getColor(R.color.colorAccents);
        mPaint.setShadowLayer(mShadowRadius, mShadowDx, mShadowDy, mShadowColor);
    }

}
