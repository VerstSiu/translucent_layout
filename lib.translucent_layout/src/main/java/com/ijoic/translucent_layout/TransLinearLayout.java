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

package com.ijoic.translucent_layout;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.ijoic.translucent_layout.core.DrawerLayoutImpl;
import com.ijoic.translucent_layout.core.TranslucentKit;

/**
 * Translucent linear layout.
 *
 * @author VerstSiu verstsiu@126.com
 * @date 2017/09/23 10:15
 * @version 1.0
 */
public class TransLinearLayout extends LinearLayout implements DrawerLayoutImpl {

  private final TranslucentKit translucentKit;

  public TransLinearLayout(Context context) {
    this(context, null);
  }

  public TransLinearLayout(Context context, AttributeSet attrs) {
    super(context, attrs);
    translucentKit = new TranslucentKit(this, context, attrs);
  }

  public TransLinearLayout(Context context, AttributeSet attrs, int defStyle) {
    super(context, attrs, defStyle);
    translucentKit = new TranslucentKit(this, context, attrs);
  }

  @Override
  public void setChildInsets(Object insets, boolean draw) {
    translucentKit.setChildInsets(insets, draw);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    heightMeasureSpec = translucentKit.adjustHeightSpec(heightMeasureSpec);
    translucentKit.onMeasureLinearLayout(getOrientation() == VERTICAL);
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    if (translucentKit.requiresAdjustMeasureHeight()) {
      super.setMeasuredDimension(getMeasuredWidth(), translucentKit.adjustMeasuredHeight(getMeasuredHeight()));
    }
  }

  @NonNull
  private String mode2text(int mode) {
    switch (mode) {
      case MeasureSpec.AT_MOST:
        return "AT_MOST";

      case MeasureSpec.UNSPECIFIED:
        return "UNSPECIFIED";

      case MeasureSpec.EXACTLY:
        return "EXACTLY";

      default:
        return "NONE";
    }
  }

  @Override
  public void onDraw(Canvas c) {
    super.onDraw(c);
    translucentKit.onDraw(c);
  }
}
