[![Android Arsenal](https://img.shields.io/badge/Android%20Arsenal-SimpleRatingView-brightgreen.svg?style=flat)](https://android-arsenal.com/details/1/1189)

SimpleRatingView
================

A rating toggle for Android which switches between 3 rating levels: positive, neutral, and negative.

![simpleratingview](https://cloud.githubusercontent.com/assets/2550945/5184090/c143e70e-74b4-11e4-8bfa-a7ec17416d05.gif)

# Usage
Since, SimpleRatingView is pushed to the Maven Central repository, all you have to do is to add the following line to your dependencies.

```
dependencies {
    compile 'com.github.xiprox.simpleratingview:library:+'
}
```

Once you have the library added as a dependency to your project, you can start with including the view in your layout:
```xml
<tr.xip.widget.simpleratingview.SimpleRatingView xmlns:srv="http://schemas.android.com/apk/res-auto"
    android:id="@+id/simple_rating_view"
    android:layout_width="wrap_content"
    android:layout_height="wrap_content" />
```
You can also customize SimpleRatingView by telling it to display the icons you want, in the color you want. The following xml attributes will allow you to set the icons for different rating levels.
```
    srv:srv_positiveIcon="@drawable/rating_positive"
    srv:srv_neutralIcon="@drawable/rating_neutral"
    srv:srv_negativeIcon="@drawable/rating_negative"
```
As for a custom color, you'll need to insert the following attribute:
```
    srv:srv_iconColor="@color/my_rating_icon_color"
```

On the Java side, all you have to do is catching rating changes from the view:
```java
mSimpleRatingView = (SimpleRatingView) findViewById(R.id.simple_rating_view);
mSimpleRatingView.setOnRatingChangedListener(new SimpleRatingView.OnRatingChangeListener() {
    @Override
    public void onRatingChanged(SimpleRatingView.Rating ratingType) {
        switch (ratingType) {
            case POSITIVE:
                // Do something
                break;
            case NEUTRAL:
                // Do something
                break;
            case NEGATIVE:
                // Do something
                break;
        }
    }
});
```
Of course, you can directly set the rating too.
```java
setSelectedRating(Rating rating)
```

And lastly, all customization can be applied programmatically too:
```java
setIconColor(int color)
setIconColor(Resources res, int color) // Set the icon color to a color resource
setPositiveIconResource(int res)
setPositiveIconDrawable(Drawable drawable)
setNeutralIconResource(int res)
setNeutralIconDrawable(Drawable drawable)
setNegativeIconResource(int res)
setNegativeIconDrawable(Drawable drawable)
```

# Sample App
<a href="https://play.google.com/store/apps/details?id=tr.xip.widget.simpleratingview.sample">
  <img alt="Get it on Google Play"
       src="https://developer.android.com/images/brand/en_generic_rgb_wo_45.png" />
</a>

# License
```
Copyright (C) 2014 Ihsan Isik

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
