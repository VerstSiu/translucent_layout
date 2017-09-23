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
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.support.v7.app.AppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import sample.ijoic.translucent_layout.R;
import sample.ijoic.translucent_layout.drawer.FullPageFragment;
import sample.ijoic.translucent_layout.drawer.ScrollHeaderFragment;
import sample.ijoic.translucent_layout.drawer.SimpleTitleFragment;

/**
 * Drawer tab host activity.
 *
 * @author VerstSiu verstsiu@126.com
 * @date 2017/09/22 16:14
 * @version 1.0
 */
public class DrawerTabHostActivity extends AppCompatActivity {

  @BindView(android.R.id.tabhost)
  FragmentTabHost tabHost;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.act_cases_drawer_tab_host);
    ButterKnife.bind(this);

    tabHost.setup(this, getSupportFragmentManager(), android.R.id.tabcontent);
    addIabIndicator("home", FullPageFragment.class);
    addIabIndicator("news", SimpleTitleFragment.class);
    addIabIndicator("me", ScrollHeaderFragment.class);
  }

  private void addIabIndicator(@NonNull String indicatorKey, @NonNull Class<? extends Fragment> fragmentType) {
    tabHost.addTab(
      tabHost.newTabSpec(indicatorKey)
        .setIndicator(indicatorKey.toUpperCase()),
        fragmentType,
      null
    );
  }
}
