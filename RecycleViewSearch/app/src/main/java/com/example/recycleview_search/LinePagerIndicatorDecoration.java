package com.example.recycleview_search;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class LinePagerIndicatorDecoration extends RecyclerView.ItemDecoration {
    private final int colorActive = 0xFFFFFFFF;
    private final int colorInactive = 0x66FFFFFF;

    private final float DP;

    private final float indicatorHeight;
    private final float indicatorStrokeWidth;
    private final float indicatorItemLength;
    private final float indicatorItemPadding;

    private final Paint paint = new Paint();



    public LinePagerIndicatorDecoration(Context context) {
        DP = context.getResources().getDisplayMetrics().density;

        indicatorHeight = DP * 16;
        indicatorStrokeWidth = DP * 2;
        indicatorItemLength = DP * 16;
        indicatorItemPadding = DP * 4;

        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(indicatorStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
    }



    @Override
    public void onDrawOver(@NonNull Canvas canvas, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        int itemCount = parent.getAdapter().getItemCount();
        if (itemCount == 0) return;

        int totalWidth = (int) (indicatorItemLength * itemCount + indicatorItemPadding * (itemCount - 1));
        float startX = (parent.getWidth() - totalWidth) / 2f;
        float y = parent.getHeight() - indicatorHeight;

        drawInactiveIndicators(canvas, startX, y, itemCount);

        LinearLayoutManager layoutManager = (LinearLayoutManager) parent.getLayoutManager();
        int activePosition = layoutManager.findFirstVisibleItemPosition();
        if (activePosition == RecyclerView.NO_POSITION) return;

        View activeChild = layoutManager.findViewByPosition(activePosition);
        float progress = -activeChild.getLeft() / (float) activeChild.getWidth();
        drawActiveIndicator(canvas, startX, y, activePosition, progress);
    }

    private void drawInactiveIndicators(Canvas canvas, float startX, float y, int itemCount) {
        paint.setColor(colorInactive);
        for (int i = 0; i < itemCount; i++) {
            float x = startX + (indicatorItemLength + indicatorItemPadding) * i;
            canvas.drawLine(x, y, x + indicatorItemLength, y, paint);
        }
    }

    private void drawActiveIndicator(Canvas canvas, float startX, float y, int position, float progress) {
        paint.setColor(colorActive);
        float x = startX + (indicatorItemLength + indicatorItemPadding) * position;
        canvas.drawLine(x, y, x + indicatorItemLength, y, paint);
        if (progress > 0 && position < (int) (x / (indicatorItemLength + indicatorItemPadding))) {
            float nextX = x + indicatorItemLength + indicatorItemPadding;
            canvas.drawLine(nextX, y, nextX + indicatorItemLength * progress, y, paint);
        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        outRect.bottom = (int) indicatorHeight;
    }
}
