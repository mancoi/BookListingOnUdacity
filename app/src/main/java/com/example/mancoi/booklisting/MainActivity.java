<?xml version="1.0" encoding="utf-8"?>
<!-- Copyright (C) 2013 The Android Open Source Project

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
-->

<android.support.v7.widget.FitWindowsLinearLayout
    xmlns:android="http://schemas.android.com/apk/res/android"
    android:id="@+id/action_bar_root"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    android:orientation="vertical"
    android:fitsSystemWindows="true">

    <android.support.v7.widget.ViewStubCompat
        android:id="@+id/action_mode_bar_stub"
        android:inflatedId="@+id/action_mode_bar"
        android:layout="@layout/abc_action_mode_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content" />

    <include layout="@layout/abc_screen_content_include" />

</android.support.v7.widget.FitWindowsLinearLayout>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             <?xml version="1.0" encoding="utf-8"?>
<!--
/*
** Copyright 2014, The Android Open Source Project
**
** Licensed under the Apache License, Version 2.0 (the "License");
** you may not use this file except in compliance with the License.
** You may obtain a copy of the License at
**
**     http://www.apache.org/licenses/LICENSE-2.0
**
** Unless required by applicable law or agreed to in writing, software
** distributed under the License is distributed on an "AS IS" BASIS,
** WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
** See the License for the specific language governing permissions and
** limitations under the License.
*/

This is an optimized layout for a screen, with the minimum set of features
enabled.
-->

<android.support.v7.widget.FitWindowsFrameLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        android:id="@+id/action_bar_root"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true">

    <include layout="@layout/abc_screen_content_include" />

    <android.support.v7.widget.ViewStubCompat
            android:id="@+id/action_mode_bar_stub"
            android:inflatedId="@+id/action_mode_bar"
            android:layout="@layout/abc_action_mode_bar"
            android:layout_width="match_parent"
            android:layout_height="wrap_content" />

</android.support.v7.widget.FitWindowsFrameLayout>                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             <?xml version="1.0" encoding="utf-8"?>
<!-- Copyright (C) 2014 The Android Open Source Project

     Licensed under the Apache License, Version 2.0 (the "License");
     you may not use this file except in compliance with the License.
     You may obtain a copy of the License at

          http://www.apache.org/licenses/LICENSE-2.0

     Unless required by applicable law or agreed to in writing, software
     distributed under the License is distributed on an "AS IS" BASIS,
     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
     See the License for the specific language governing permissions and
     limitations under the License.
-->

<android.support.v7.widget.ActionBarOverlayLayout
        xmlns:android="http://schemas.android.com/apk/res/android"
        xmlns:app="http://schemas.android.com/apk/res-auto"
        android:id="@+id/decor_content_parent"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:fitsSystemWindows="true">

    <include layout="@layout/abc_screen_content_include"/>

    <android.support.v7.widget.ActionBarContainer
            android:id="@+id/action_bar_container"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentTop="true"
            style="?attr/actionBarStyle"
            android:touchscreenBlocksFocus="true"
            android:gravity="top">

        <android.support.v7.widget.Toolbar
                android:id="@+id/action_bar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                app:navigationContentDescription="@string/abc_action_bar_up_description"
                style="?attr/toolbarStyle"/>

        <android.support.v7.widget.ActionBarContextView
                android:id="@+id/action_context_bar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content"
                android:visibility="gone"
                android:theme="?attr/actionBarTheme"
                style="?attr/actionModeStyle"/>

    </android.support.v7.widget.ActionBarContainer>

</android.support.v7.widget.ActionBarOverlayLayout>
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    <?xml version="1.0" encoding="utf-8"?>
<!--
/*
 * Copyright (C) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
-->

<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
                android:layout_width="match_parent"
                android:layout_height="58dip"
                style="@style/RtlOverlay.Widget.AppCompat.Search.DropDown">

    <!-- Icons come first in the layout, since their placement doesn't depend on
         the placement of the text views. -->
    <ImageView
               android:id="@android:id/icon1"
               android:layout_width="@dimen/abc_dropdownitem_icon_width"
               android:layout_height="48dip"
               android:scaleType="centerInside"
               android:layout_alignParentTop="true"
               android:layout_alignParentBottom="true"
               android:visibility="invisible"
               style="@style/RtlOverlay.Widget.AppCompat.Search.DropDown.Icon1" />

    <ImageView
               android:id="@+id/edit_query"
               android:layout_width="48dip"
               android:layout_height="48dip"
               android:scaleType="centerInside"
               android:layout_alignParentTop="true"
               android:layout_alignParentBottom="true"
               android:background="?attr/selectableItemBackground"
               android:visibility="gone"
               style="@style/RtlOverlay.Widget.AppCompat.Search.DropDown.Query" />

    <ImageView
               android:id="@id/android:icon2"
               android:layout_width="48dip"
               android:layout_height="48dip"
               android:scaleType="centerInside"
               android:layout_alignWithParentIfMissing="true"
               android:layout_alignParentTop="true"
               android:layout_alignParentBottom="true"
               android:visibility="gone"
               style="@style/RtlOverlay.Widget.AppCompat.Search.DropDown.Icon2" />


    <!-- The subtitle comes before the title, since the height of the title depends on whether the
         subtitle is visible or gone. -->
    <TextView android:id="@android:id/text2"
              style="?android:attr/dropDownItemStyle"
              android:textAppearance="?attr/textAppearanceSearchResultSubtitle"
              android:singleLine="true"
              android:layout_width="match_par