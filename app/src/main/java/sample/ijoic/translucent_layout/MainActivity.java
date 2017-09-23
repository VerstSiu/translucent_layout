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

package sample.ijoic.translucent_layout;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.ijoic.translucent_layout.cases.DrawerTabHostActivity;
import sample.ijoic.translucent_layout.cases.FullPageActivity;
import sample.ijoic.translucent_layout.cases.ScrollHeaderActivity;
import sample.ijoic.translucent_layout.cases.SimpleTitleActivity;

/**
 * Main activity.
 *
 * @author VerstSiu verstsiu@126.com
 * @date 2017/9/23 10:25
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

  @OnClick(R.id.button_full_page)
  public void onCaseFullPage() {
    startActivity(new Intent(this, FullPageActivity.class));
  }

  @OnClick(R.id.button_simple_title)
  public void onCaseSimpleTitle() {
    startActivity(new Intent(this, SimpleTitleActivity.class));
  }

  @OnClick(R.id.button_scroll_header)
  public void displayHeaderBlockSample() {
    startActivity(new Intent(this, ScrollHeaderActivity.class));
  }

  @OnClick(R.id.button_drawer_tabhost)
  public void displayDrawerTabHost() {
    startActivity(new Intent(this, DrawerTabHostActivity.class));
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.act_main);
    ButterKnife.bind(this);
  }
}
