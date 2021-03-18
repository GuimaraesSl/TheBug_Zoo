package com.example.thebug_zoo.components;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class CommonItemSpaceDecoration extends RecyclerView.ItemDecoration {

    private int mSpace = 0;
    private boolean mVerticalOrientation = true;

    public CommonItemSpaceDecoration(int space) {
        this.mSpace = space;
    }

    public CommonItemSpaceDecoration(int space, boolean verticalOrientation) {
        this.mSpace = space;
        this.mVerticalOrientation = verticalOrientation;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.top = SizeUtils.dp2px(view.getContext(), mSpace);
        if (mVerticalOrientation) {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.set(0, SizeUtils.dp2px(view.getContext(), mSpace), 0, SizeUtils.dp2px(view.getContext(), mSpace));
            } else {
                outRect.set(0, 0, 0, SizeUtils.dp2px(view.getContext(), mSpace));
            }
        } else {
            if (parent.getChildAdapterPosition(view) == 0) {
                outRect.set(SizeUtils.dp2px(view.getContext(), mSpace), 0, 0, 0);
            } else {
                outRect.set(SizeUtils.dp2px(view.getContext(), mSpace), 0, SizeUtils.dp2px(view.getContext(), mSpace), 0);
            }
        }
    }

}