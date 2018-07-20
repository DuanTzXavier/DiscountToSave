package top.basking.discounttosave;

import android.graphics.Canvas;

/**
 * Created by tzduan on 2018/7/20.
 */

public interface Shadow {
    public void setParameter(ZDepthParam parameter, int left, int top, int right, int bottom, int shadowColor);
    public void onDraw(Canvas canvas);
}