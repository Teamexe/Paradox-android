package com.exe.paradox.util;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;

/**
 * Created by shasha on 1/4/18.
 */

public class Typewriter extends android.support.v7.widget.AppCompatTextView {
    private CharSequence mText;
    private int mIndex;
    private long mDelay = 150; // in ms
    private Handler mHandler = new Handler();
    private Runnable characterAdder = new Runnable() {
        @Override
        public void run() {
            setText(mText.subSequence(0, mIndex++));
            if (mIndex <= mText.length()) {
                mHandler.postDelayed(characterAdder, mDelay);
            }
        }
    };

    public Typewriter(Context context) {
        super(context);
    }
    public Typewriter(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void animateText(CharSequence txt) {
        mText = txt;
        mIndex = 0;
        setText("");
        mHandler.removeCallbacks(characterAdder);
        mHandler.postDelayed(characterAdder, mDelay);
    }

    public void setCharacterDelay(long m) {
        mDelay = m;
    }
}