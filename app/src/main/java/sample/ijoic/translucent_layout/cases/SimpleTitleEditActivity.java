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

package sample.ijoic.translucent_layout.cases;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;
import sample.ijoic.translucent_layout.R;

/**
 * Simple title edit activity.
 *
 * @author VerstSiu verstsiu@126.com
 * @date 2017/10/19 20:07
 * @version 1.0
 */
public class SimpleTitleEditActivity extends AppCompatActivity {

  @OnClick(R.id.button_back)
  public void closePage() {
    finish();
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.act_cases_simple_title_edit);
    ButterKnife.bind(this);
  }
}
