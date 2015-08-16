/*
 * Copyright (C) 2014 Ihsan Isik
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

package tr.xip.widget.simpleratingview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

/**
 * @author Ihsan Isik
 *         <p>
 *         A rating selector view. Rating changes on each click, looping between neutral, positive,
 *         and negative.
 *         </p>
 */
public class SimpleRatingView extends ImageView {

    public enum Rating {
        NEGATIVE, NEUTRAL, POSITIVE
    }

    private Rating mSelectedRating = Rating.NEUTRAL;

    private OnRatingChangeListener mListener;

    private int mIconColor;
    private Drawable mPositiveIcon;
    private Drawable mNeutralIcon;
    private Drawable mNegativeIcon;

    public SimpleRatingView(Context context) {
        super(context);
    }

    public SimpleRatingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public SimpleRatingView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.SimpleRatingView, 0, 0);

        try {
            mIconColor = a.getColor(R.styleable.SimpleRatingView_srv_iconColor,
                    getResources().getColor(R.color.srv_icon_color_default));

            int positiveIconRes = a.getResourceId(R.styleable.SimpleRatingView_srv_positiveIcon,
                    R.drawable.ic_rating_positive);
            int neutralIconRes = a.getResourceId(R.styleable.SimpleRatingView_srv_neutralIcon,
                    R.drawable.ic_rating_neutral);
            int negativeIconRes = a.getResourceId(R.styleable.SimpleRatingView_srv_negativeIcon,
                    R.drawable.ic_rating_negative);

            setPositiveIconResource(positiveIconRes);
            setNeutralIconResource(neutralIconRes);
            setNegativeIconResource(negativeIconRes);
        } finally {
            a.recycle();
        }

        setImageResource(R.drawable.ic_rating_neutral);
    }

    /**
     * Sets the rating level and rating image.
     *
     * @param rating The rating level to be set to the view.
     */
    public void setSelectedRating(Rating rating) {
        mSelectedRating = rating;

        switch (rating) {
            case POSITIVE:
                setImageDrawable(mPositiveIcon);
                break;
            case NEUTRAL:
                setImageDrawable(mNeutralIcon);
                break;
            case NEGATIVE:
                setImageDrawable(mNegativeIcon);
                break;
        }
    }

    /**
     * Returns the currently selected rating level.
     *
     * @return Rating enum
     */
    public Rating getSelectedRating() {
        return mSelectedRating;
    }

    /**
     * Used to set the color of the rating icons/indicators.
     *
     * @param color int color value.
     */
    public void setIconColor(int color) {
        mIconColor = color;
        notifyIconColorChanged();
    }

    /**
     * Used to set the color of the rating icons/indicators.
     *
     * @param res   Resources
     * @param color int color resource.
     */
    public void setIconColor(Resources res, int color) {
        mIconColor = getResources().getColor(color);
        notifyIconColorChanged();
    }

    /**
     * Used to set the positive rating icon.
     *
     * @param res int icon resource.
     */
    public void setPositiveIconResource(int res) {
        mPositiveIcon = getResources().getDrawable(res);
        notifyIconChanged();
    }

    /**
     * Used to set the positive rating icon.
     *
     * @param drawable icon drawable.
     */
    public void setPositiveIconDrawable(Drawable drawable) {
        mPositiveIcon = drawable;
        notifyIconChanged();
    }

    /**
     * USed to set the neutral rating icon.
     *
     * @param res int icon resource.
     */
    public void setNeutralIconResource(int res) {
        mNeutralIcon = getResources().getDrawable(res);
        notifyIconChanged();
    }

    /**
     * Used to set the neutral rating icon.
     *
     * @param drawable icon drawable.
     */
    public void setNeutralIconDrawable(Drawable drawable) {
        mNeutralIcon = drawable;
        notifyIconChanged();
    }

    /**
     * Used to set the negative rating icon.
     *
     * @param res int icon resource.
     */
    public void setNegativeIconResource(int res) {
        mNegativeIcon = getResources().getDrawable(res);
        notifyIconChanged();
    }

    /**
     * Used to set the negative rating icon.
     *
     * @param drawable icon drawable.
     */
    public void setNegativeIconDrawable(Drawable drawable) {
        mNegativeIcon = drawable;
        notifyIconChanged();
    }

    /**
     * Used to attach a listener to the view.
     *
     * @param listener The listener to be attached.
     */
    public void setOnRatingChangedListener(OnRatingChangeListener listener) {
        mListener = listener;
        this.setOnClickListener(new MyOnClickListener());
    }

    @Override
    public void setImageResource(int resId) {
        super.setImageResource(resId);
        setColorFilter();
    }

    @Override
    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        setColorFilter();
    }

    private void setColorFilter() {
        setColorFilter(mIconColor, PorterDuff.Mode.SRC_ATOP);
    }

    public void notifyIconColorChanged() {
        setColorFilter();
    }

    public void notifyIconChanged() {
        setSelectedRating(getSelectedRating());
    }

    private class MyOnClickListener implements OnClickListener {

        @Override
        public void onClick(View view) {
            switch (mSelectedRating) {
                case NEUTRAL:
                    setSelectedRating(Rating.POSITIVE);
                    break;
                case POSITIVE:
                    setSelectedRating(Rating.NEGATIVE);
                    break;
                case NEGATIVE:
                    setSelectedRating(Rating.NEUTRAL);
            }

            if (mListener != null)
                mListener.onRatingChanged(mSelectedRating);
        }
    }

    public interface OnRatingChangeListener {
        public void onRatingChanged(Rating ratingType);
    }
}