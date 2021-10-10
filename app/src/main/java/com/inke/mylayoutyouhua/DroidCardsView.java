package com.inke.mylayoutyouhua;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DroidCardsView extends View {
    //图片与图片之间的间距
    private int mCardSpacing = 150;
    //图片与左侧距离的记录
    private int mCardLeft = 10;

    private List<DroidCard> mDroidCards = new ArrayList<>();

    private Paint paint = new Paint();

    public DroidCardsView(Context context) {
        super(context);
        initCards();
    }

    public DroidCardsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initCards();
    }

    /**
     * 初始化卡片集合
     */

    protected void initCards() {
        Resources res = getResources();
        mDroidCards.add(new DroidCard(res,R.drawable.ic_launcher_background, mCardLeft));

        mCardLeft+=mCardSpacing;
        mDroidCards.add(new DroidCard(res,R.drawable.ic_launcher_background, mCardLeft));

        mCardLeft+=mCardSpacing;
        mDroidCards.add(new DroidCard(res,R.drawable.ic_launcher_background, mCardLeft));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for(int i=0;i<mDroidCards.size()-1;i++) {
            drawDroidCard(canvas, mDroidCards, i);
        }

        drawLastDroidCard(canvas, mDroidCards.get(mDroidCards.size()-1));


//        for (DroidCard c : mDroidCards) {
//            drawDroidCard(canvas, c);
//        }

        invalidate();
    }

    private void drawDroidCard(Canvas canvas, List<DroidCard> mDroidCards, int i) {
        DroidCard c = mDroidCards.get(i);
        canvas.save();

        canvas.clipRect((float)c.x, 0f, (float)(mDroidCards.get(i+1).x), (float)c.height);

        canvas.drawBitmap(c.bitmap, c.x, 0f, paint);

        canvas.restore();
    }

    private void drawLastDroidCard(Canvas canvas, DroidCard c) {
        canvas.drawBitmap(c.bitmap, c.x, 0f, paint);
    }

    /**
     * 绘制DroidCard
     * @param canvas
     * @param c
     */
    private void drawDroidCard(Canvas canvas, DroidCard c) {
        canvas.drawBitmap(c.bitmap, c.x, 0f, paint);
    }
}
