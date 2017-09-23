/*
 *
 *  Copyright(c) 2017 VerstSiu
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *  http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */

package com.ijoic.translucent_layout.core;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import com.ijoic.translucent_layout.R;

/**
 * Translucent kit.
 *
 * @author VerstSiu verstsiu@126.com
 * @date 2017/9/22 22:53
 * @version 1.0
 */
public class TranslucentKit implements DrawerLayoutImpl {

  /**
   * Drawer layout compat impl.
   */
  private interface DrawerLayoutCompatImpl {
    void configureApplyInsets(View drawerLayout);
    void dispatchChildInsets(View child, Object insets);
    void applyMarginInsets(ViewGroup.MarginLayoutParams lp, Object insets);
    int getTopInset(Object lastInsets);
    Drawable getDefaultStatusBarBackground(Context context);
  }

  /**
   * Drawer layout compat impl base.
   */
  private static class DrawerLayoutCompatImplBase implements DrawerLayoutCompatImpl {
    @Override
    public void configureApplyInsets(View drawerLayout) {
      // This space for rent
    }

    @Override
    public void dispatchChildInsets(View child, Object insets) {
      // This space for rent
    }

    @Override
    public void applyMarginInsets(ViewGroup.MarginLayoutParams lp, Object insets) {
      // This space for rent
    }

    @Override
    public int getTopInset(Object insets) {
      return 0;
    }

    @Override
    public Drawable getDefaultStatusBarBackground(Context context) {
      return null;
    }
  }


  /**
   * Drawer layout compat impl API 21.
   */
  private static class DrawerLayoutCompatImplApi21 implements DrawerLayoutCompatImpl {
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void configureApplyInsets(View drawerLayout) {
      DrawerLayoutCompatApi21.configureApplyInsets(drawerLayout);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void dispatchChildInsets(View child, Object insets) {
      DrawerLayoutCompatApi21.dispatchChildInsets(child, insets);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void applyMarginInsets(ViewGroup.MarginLayoutParams lp, Object insets) {
      DrawerLayoutCompatApi21.applyMarginInsets(lp, insets);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public int getTopInset(Object insets) {
      return DrawerLayoutCompatApi21.getTopInset(insets);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public Drawable getDefaultStatusBarBackground(Context context) {
      return DrawerLayoutCompatApi21.getDefaultStatusBarBackground(context);
    }
  }

  private static final DrawerLayoutCompatImpl IMPL;

  static {
    final int version = Build.VERSION.SDK_INT;
    if (version >= 21) {
      IMPL = new DrawerLayoutCompatImplApi21();
    } else {
      IMPL = new DrawerLayoutCompatImplBase();
    }
  }

  private final ViewGroup injectParent;
  private final boolean fitSystemEnabled;
  private final boolean forceInset;

  private Drawable mStatusBarBackground;

  private Object mLastInsets;
  private boolean mDrawStatusBarBackground;

  private boolean exactParentHeightInit;
  private int exactParentHeight;

  /**
   * Constructor.
   *
   * @param injectParent inject parent.
   * @param context context.
   */
  public TranslucentKit(@NonNull ViewGroup injectParent, @NonNull Context context, @Nullable AttributeSet attrs) {
    this.injectParent = injectParent;
    fitSystemEnabled = ViewCompat.getFitsSystemWindows(injectParent);

    if (fitSystemEnabled) {
      IMPL.configureApplyInsets(injectParent);
      mStatusBarBackground = IMPL.getDefaultStatusBarBackground(context);
      forceInset = readForceInset(context, attrs);
    } else {
      forceInset = false;
    }
  }

  @Override
  public void setChildInsets(Object insets, boolean draw) {
    mLastInsets = insets;
    mDrawStatusBarBackground = draw;
    injectParent.setWillNotDraw(!draw && injectParent.getBackground() == null);
    injectParent.requestLayout();
  }

  /**
   * Measure parent view as pure view group.
   */
  public void onMeasurePureViewGroup() {
    if (!fitSystemEnabled || mLastInsets == null) {
      return;
    }
    if (!exactParentHeightInit) {
      exactParentHeightInit = true;
      exactParentHeight = readParentHeight(injectParent, 0);
    }

    final int childCount = injectParent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = injectParent.getChildAt(i);

      if (child.getVisibility() == View.GONE) {
        continue;
      }

      final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();

      injectWindowInsets(child, lp);
    }
  }

  /**
   * Measure parent view as frame layout.
   */
  public void onMeasureFrameLayout() {
    if (!fitSystemEnabled || mLastInsets == null) {
      return;
    }
    if (!exactParentHeightInit) {
      exactParentHeightInit = true;
      exactParentHeight = readParentHeight(injectParent, 0);
    }

    final int childCount = injectParent.getChildCount();
    for (int i = 0; i < childCount; i++) {
      final View child = injectParent.getChildAt(i);

      if (child.getVisibility() == View.GONE) {
        continue;
      }

      if (ViewCompat.getFitsSystemWindows(child)) {
        IMPL.dispatchChildInsets(child, mLastInsets);
      }
    }
  }

  /**
   * Measure parent view as linear layout.
   *
   * @param layoutVertical layout vertical status.
   */
  public void onMeasureLinearLayout(boolean layoutVertical) {
    if (!fitSystemEnabled || mLastInsets == null) {
      return;
    }
    if (!exactParentHeightInit) {
      exactParentHeightInit = true;
      exactParentHeight = readParentHeight(injectParent, 0);
    }

    final int childCount = injectParent.getChildCount();
    int visibleChildIndex = 0;

    for (int i = 0; i < childCount; i++) {
      final View child = injectParent.getChildAt(i);

      if (child.getVisibility() == View.GONE) {
        continue;
      }

      final ViewGroup.MarginLayoutParams lp = (ViewGroup.MarginLayoutParams) child.getLayoutParams();

      if (!layoutVertical || visibleChildIndex == 0) {
        injectWindowInsets(child, lp);
      }

      ++visibleChildIndex;
    }
  }

  private void injectWindowInsets(@NonNull View child, @Nullable ViewGroup.MarginLayoutParams lp) {
    if (ViewCompat.getFitsSystemWindows(child)) {
      IMPL.dispatchChildInsets(child, mLastInsets);
    } else {
      IMPL.applyMarginInsets(lp, mLastInsets);
    }
  }

  /**
   * Returns <code>true</code> adjust measure height is needed.
   */
  public boolean requiresAdjustMeasureHeight() {
    return fitSystemEnabled && (
        forceInset || (exactParentHeight > 0 && injectParent.getMeasuredHeight() <= exactParentHeight)
    );
  }

  /**
   * Returns top inset.
   */
  public int getTopInset() {
    return IMPL.getTopInset(mLastInsets);
  }

  /**
   * Draw insets.
   *
   * @param c canvas.
   */
  public void onDraw(Canvas c) {
//    if (mDrawStatusBarBackground && mStatusBarBackground != null) {
//      final int inset = IMPL.getTopInset(mLastInsets);
//      if (inset > 0) {
//        mStatusBarBackground.setBounds(0, 0, getWidth(), inset);
//        mStatusBarBackground.draw(c);
//      }
//    }
  }

  private static int readParentHeight(@NonNull View injectView, int defValue) {
    ViewGroup.LayoutParams lp = injectView.getLayoutParams();

    if (lp == null) {
      return defValue;
    }
    int layoutHeight = lp.height;

    if (layoutHeight == ViewGroup.LayoutParams.WRAP_CONTENT
        || layoutHeight == ViewGroup.LayoutParams.MATCH_PARENT) {
      return defValue;
    }
    return layoutHeight;
  }

  private static boolean readForceInset(@NonNull Context context, AttributeSet attrs) {
    boolean forceInset = false;

    TypedArray a = context.obtainStyledAttributes(attrs, new int[] {
      R.attr.force_inset
    });

    forceInset = a.getBoolean(0, forceInset);

    a.recycle();
    return forceInset;
  }
}
