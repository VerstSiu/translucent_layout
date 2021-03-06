
# Translucent Layout

[![](https://jitpack.io/v/VerstSiu/translucent_layout.svg)](https://jitpack.io/#VerstSiu/translucent_layout)

Translucent status bar effect for common layout without change activity theme.

## Preview

<img alt="main" src="screen_shots/main.jpg" width="135"/>&nbsp;
<img alt="main" src="screen_shots/full_page.jpg" width="135"/>&nbsp;
<img alt="main" src="screen_shots/simple_title.png" width="135"/>&nbsp;
<img alt="main" src="screen_shots/drawer_tabhost.jpg" width="135"/>

## Download

1. Add `jitpack` repository to all project's `build.gradle`.
    
    ```gradle

    allprojects {
      repositories {
        ...
        maven { url 'https://jitpack.io' }
      }
    }

    ```

2. Add dependency to app `build.gradle`.
    
    ```gradle

    dependencies {
      compile 'com.github.VerstSiu:lib.translucent_layout:1.2'
    }

    ```

## Get Start

1. Set `Activity` theme to `NoActionBar` and `StatusBarTransparent`.

    manifest:
    
    ```xml

    <application>

      <activity
        android:theme="AppTheme.NoActionBar.StatusBarTransparent"/>

    </application>

    ```

    styles:
    
    ```xml

    <style name="AppTheme.NoActionBar" parent="Theme.AppCompat.Light.NoActionBar"/>


    <style name="AppTheme.NoActionBar.StatusBarTransparent">
    </style>

    ```

    styles-v21:
    
    ```xml

    <style name="AppTheme.NoActionBar.StatusBarTransparent">
      <item name="android:statusBarColor">@android:color/transparent</item>
    </style>

    ```

2. Replace parent layout with `TransXXX`.
    
    ```xml

    <com.ijoic.translucent_layout.TransLinearLayout
      xmlns:android="http://schemas.android.com/apk/res/android"
      android:fitsSystemWindow="true">
    
      <com.ijoic.translucent_layout.TransLinearLayout
        android:fitsSystemWindow="true">
    
      </com.ijoic.translucent_layout.TransLinearLayout>

      ..

    </com.ijoic.translucent_layout.TransLinearLayout>

    ```

## Wrap Parent

Use extra layout to dispatch background translucent in those case:

1. Current view group or child, use vertical align related layout attributes.

2. `RelativeLayout`.

```xml

<com.ijoic.translucent_layout.TransLinearLayout
  android:layout_width="match_parent"
  android:layout_height="wrap_content"
  android:orientation="vertical"
  android:background="..">

  <RelativeLayout>

  ..

  </RelativeLayout>

</com.ijoic.translucent_layout.TransLinearLayout>

```

## Adjust Resize

Use `adjustResize` at the root layout when your activity's `windowSoftInputMode` settings with `adjustResize`.

```xml

<com.ijoic.translucent_layout.TransLinearLayout
  xmlns:app="http://schemas.android.com/apk/res-auto"
  android:fitsSystemWindows="true"
  app:adjustResize="true">

  ..

</com.ijoic.translucent_layout.TransLinearLayout>

```

## License

```

   Copyright 2017 VerstSiu

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.

```