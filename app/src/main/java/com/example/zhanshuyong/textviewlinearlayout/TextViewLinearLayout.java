package com.example.zhanshuyong.textviewlinearlayout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * @author zhanshuyong
 * @date 2017/12/27
 */

public class TextViewLinearLayout extends LinearLayout {

    TextView textView;
    View child2;
    /**
     * textView的高度
     */
    private int textViewHeight;
    /**
     * 子 View的高度
     */
    private int child2Height;

    public TextViewLinearLayout(Context context) {
        this(context, null);
    }

    public TextViewLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setOrientation(HORIZONTAL);

    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        textView = (TextView) getChildAt(0);
        child2 = getChildAt(1);
        LayoutParams textViewlp = (LayoutParams) textView.getLayoutParams();
        textViewlp.gravity = Gravity.CENTER_VERTICAL;
        textView.setLayoutParams(textViewlp);

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("azhansy", "onMeasure ----------");
        textViewHeight = textView.getMeasuredHeight();
        child2Height = child2.getMeasuredHeight();
        Log.d("azhansy", " child2： " + child2.getMeasuredWidth() + "      " + child2.getMeasuredHeight());

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("azhansy", "onSizeChanged" + " ----------" + "  child2Height: " + child2Height + "  textViewHeight:" + textViewHeight);

        int lineCount = textView.getLineCount();
        LayoutParams olp = (LayoutParams) child2.getLayoutParams();
        if (lineCount > 1) {
            olp.gravity = -1;

            if (child2Height > textViewHeight / lineCount) {

            } else {
                //这里计算还不太准确，
                int top = (int) (textViewHeight / lineCount / 2 - child2Height / 2 + textView.getBaseline() / 8);
                olp.setMargins(olp.leftMargin, top, olp.rightMargin, olp.bottomMargin);
                Log.d("azhansy", "count: " + lineCount + "     top: " + top + "     getBaseline: " + textView.getBaseline());
            }
            child2.setLayoutParams(olp);

        } else {
            olp.gravity = Gravity.CENTER_VERTICAL;
            child2.setLayoutParams(olp);
        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        Log.d("azhansy", "onLayout" + " ----------");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("azhansy", "onDraw" + " ----------");
    }
}
