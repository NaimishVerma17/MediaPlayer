package com.example.itakg.musicapp.Views;

import android.content.Context;
import android.graphics.Typeface;
import android.support.annotation.ColorInt;
import android.support.v7.widget.AppCompatTextView;
import android.util.AttributeSet;

public class MyBoldTextView extends AppCompatTextView {
    private static final String BOLD_TEXT = "Fonts/Ubuntu-Bold.ttf";

    public MyBoldTextView(Context context) {
        super(context);
    }

    public MyBoldTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyBoldTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf.createFromAsset(getContext().getAssets(), BOLD_TEXT));
    }

    @Override
    public void setTextColor(@ColorInt int color) {
        super.setTextColor(color);
    }
}
