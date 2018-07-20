package top.basking.discounttosave;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.View;


public class ShadowView extends View {
    protected Shadow mShadow;
    protected ZDepthParam mZDepthParam;
    protected int mZDepthPaddingLeft;
    protected int mZDepthPaddingTop;
    protected int mZDepthPaddingRight;
    protected int mZDepthPaddingBottom;
    protected int mShadowColor = 0;

    protected ShadowView(Context context) {
        this(context, null);
        init();
    }

    protected ShadowView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
        init();
    }

    protected ShadowView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    protected void init() {
        setWillNotDraw(false);
        mShadow = new ShadowRect();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null);
    }

    protected void setZDepthPaddingLeft(int zDepthPaddingLeftValue) {
        ZDepth zDepth = getZDepthWithAttributeValue(zDepthPaddingLeftValue);
        mZDepthPaddingLeft = measureZDepthPadding(zDepth);
    }

    protected void setZDepthPaddingTop(int zDepthPaddingTopValue) {
        ZDepth zDepth = getZDepthWithAttributeValue(zDepthPaddingTopValue);
        mZDepthPaddingTop = measureZDepthPadding(zDepth);
    }

    protected void setZDepthPaddingRight(int zDepthPaddingRightValue) {
        ZDepth zDepth = getZDepthWithAttributeValue(zDepthPaddingRightValue);
        mZDepthPaddingRight = measureZDepthPadding(zDepth);
    }

    protected void setZDepthPaddingBottom(int zDepthPaddingBottomValue) {
        ZDepth zDepth = getZDepthWithAttributeValue(zDepthPaddingBottomValue);
        mZDepthPaddingBottom = measureZDepthPadding(zDepth);
    }

    protected int measureZDepthPadding(ZDepth zDepth) {
        float maxAboveBlurRadius = zDepth.getBlurTopShadowPx(getContext());
        float maxAboveOffset = zDepth.getOffsetYTopShadowPx(getContext());
        float maxBelowBlurRadius = zDepth.getBlurBottomShadowPx(getContext());
        float maxBelowOffset = zDepth.getOffsetYBottomShadowPx(getContext());

        float maxAboveSize = maxAboveBlurRadius + maxAboveOffset;
        float maxBelowSize = maxBelowBlurRadius + maxBelowOffset;

        return (int) Math.max(maxAboveSize, maxBelowSize);
    }

    protected int getZDepthPaddingLeft() {
        return mZDepthPaddingLeft;
    }

    protected int getZDepthPaddingTop() {
        return mZDepthPaddingTop;
    }

    protected int getZDepthPaddingRight() {
        return mZDepthPaddingRight;
    }

    protected int getZDepthPaddingBottom() {
        return mZDepthPaddingBottom;
    }

    protected void setZDepth(int zDepthValue) {
        ZDepth zDepth = getZDepthWithAttributeValue(zDepthValue);
        setZDepth(zDepth);
    }

    protected void setZDepth(ZDepth zDepth) {
        mZDepthParam = new ZDepthParam();
        mZDepthParam.initZDepth(getContext(), zDepth);
    }

    protected void setShadowColor(int shadowColor) {
        mShadowColor = shadowColor;
    }

    private ZDepth getZDepthWithAttributeValue(int zDepthValue) {
        switch (zDepthValue) {
            case 1:
                return ZDepth.Depth1;
            case 2:
                return ZDepth.Depth2;
            case 3:
                return ZDepth.Depth3;
            case 4:
                return ZDepth.Depth4;
            case 5:
                return ZDepth.Depth5;
            default:
                throw new IllegalArgumentException("unknown zDepth value.");
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int wSize = MeasureSpec.getSize(widthMeasureSpec);
        int hSize = MeasureSpec.getSize(heightMeasureSpec);

        int wMode = MeasureSpec.getMode(widthMeasureSpec);
        int hMode = MeasureSpec.getMode(heightMeasureSpec);

        switch (wMode) {
            case MeasureSpec.EXACTLY:
                // NOP
                break;

            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                wSize = 0;
                break;
        }

        switch (hMode) {
            case MeasureSpec.EXACTLY:
                // NOP
                break;

            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                hSize = 0;
                break;
        }

        super.onMeasure(
                MeasureSpec.makeMeasureSpec(wSize, wMode),
                MeasureSpec.makeMeasureSpec(hSize, hMode));
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        int parentWidth = (right - left);
        int parentHeight = (bottom - top);

        mShadow.setParameter(mZDepthParam,
                mZDepthPaddingLeft,
                mZDepthPaddingTop,
                parentWidth - mZDepthPaddingRight,
                parentHeight - mZDepthPaddingBottom, mShadowColor);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mShadow.onDraw(canvas);
    }
}