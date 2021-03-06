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
import android.util.AttributeSet;
import android.widget.HorizontalScrollView;

import com.ijoic.translucent_layout.core.DrawerLayoutImpl;
import com.ijoic.translucent_layout.core.TranslucentKit;

/**
 * Translucent horizontal scroll view.
 *
 * @author VerstSiu verstsiu@126.com
 * @date 2017/09/23 19:49
 * @version 1.0
 */
public class TransHorizontalScrollView extends HorizontalScrollView implements DrawerLayoutImpl {

  private final TranslucentKit translucentKit;

  public TransHorizontalScrollView(Context context) {
    this(context, null);
  }

  public TransHorizontalScrollView(Context context, AttributeSet attrs) {
    this(context, attrs, 0);
  }

  public TransHorizontalScrollView(Context context, AttributeSet attrs, int defStyle) {
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
    translucentKit.onMeasureFrameLayout();
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);

    if (translucentKit.requiresAdjustMeasureHeight()) {
      super.setMeasuredDimension(getMeasuredWidth(), translucentKit.adjustMeasuredHeight(getMeasuredHeight()));
    }
  }

  @Override
  public void onDraw(Canvas c) {
    super.onDraw(c);
    translucentKit.onDraw(c);
  }
}
