package com.appdev.lib.widgets.button;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.DrawableRes;
import androidx.annotation.IntDef;
import androidx.annotation.StringRes;
import androidx.core.content.res.ResourcesCompat;

import com.appdev.lib.utils.DisplayUtils;
import com.appdev.lib.widgets.R;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import static com.appdev.lib.widgets.button.WEUIButton.ButtonType.TYPE_CUSTOM;
import static com.appdev.lib.widgets.button.WEUIButton.ButtonType.TYPE_DEFAULT;
import static com.appdev.lib.widgets.button.WEUIButton.ButtonType.TYPE_PRIMARY;
import static com.appdev.lib.widgets.button.WEUIButton.ButtonType.TYPE_WARN;

/**
 * WEUI风格的Button
 * @author 创建人 ：xiejiexin
 * @version 1.0
 * @package 包名 ：com.augurit.common.view.widget
 * @createTime 创建时间 ：2018/3/27
 * @modifyBy 修改人 ：xiejiexin
 * @modifyTime 修改时间 ：2018/3/27
 * @modifyMemo 修改备注：
 */

public class WEUIButton extends RelativeLayout{
    @IntDef({TYPE_PRIMARY, TYPE_DEFAULT, TYPE_WARN, TYPE_CUSTOM})
    @Retention(RetentionPolicy.SOURCE)
    public @interface ButtonType{
        // 按钮类型
        int TYPE_PRIMARY = 1;
        int TYPE_DEFAULT = 2;
        int TYPE_WARN = 3;
        int TYPE_CUSTOM = 4;
    }

    private final int TEXT_SIZE_DEFAULT = 18;

    // 按钮类型
    private int mType;
    // 按钮背景
    private int mBGNormal;
    // 加载中按钮背景
    private int mBGLoading;
    // 文字背景
    private int mBGTextNormal;
    // 加载中文字背景
    private int mBGTextLoading;
    // 是否在加载
    private boolean mIsLoading;
    // 按钮文字
    private CharSequence mText;
    // 加载中文字
    private CharSequence mLoadingText = null;

    private TextView mButtonTextView;
    private ProgressBar mProgressBar;

    public WEUIButton(Context context) {
        this(context, null);
    }

    public WEUIButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WEUIButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    /**
     * 初始化
     */
    private void init(AttributeSet attrs) {
        mType = ButtonType.TYPE_DEFAULT;
        int textSize = TEXT_SIZE_DEFAULT;
        boolean loading = false;
        boolean enabled = true;
        // xml属性读取
        if (attrs != null) {
            TypedArray ta = getContext().obtainStyledAttributes(attrs, R.styleable.WEUIButton);
            try {
                mType = ta.getInt(R.styleable.WEUIButton_buttonType, ButtonType.TYPE_DEFAULT);
                textSize = ta.getDimensionPixelSize(R.styleable.WEUIButton_textSize, -1);
                mText = ta.getText(R.styleable.WEUIButton_text);
                loading = ta.getBoolean(R.styleable.WEUIButton_loading, false);
                enabled = ta.getBoolean(R.styleable.WEUIButton_enabled, true);
                mLoadingText = ta.getText(R.styleable.WEUIButton_loadingText);
                mBGNormal = ta.getResourceId(R.styleable.WEUIButton_backgroundNormal, R.drawable.weui_bg_btn_default_normal);
                mBGLoading = ta.getResourceId(R.styleable.WEUIButton_backgroundLoading, R.drawable.weui_bg_btn_default_loading);
                mBGTextNormal = ta.getResourceId(R.styleable.WEUIButton_textNormal, R.drawable.weui_bg_btn_default_text_normal);
                mBGTextLoading = ta.getResourceId(R.styleable.WEUIButton_textLoading, R.drawable.weui_bg_btn_default_text_loading);
                if (mBGNormal != R.drawable.weui_bg_btn_default_normal
                        || mBGLoading != R.drawable.weui_bg_btn_default_loading
                        || mBGTextNormal != R.drawable.weui_bg_btn_default_text_normal
                        || mBGTextLoading != R.drawable.weui_bg_btn_default_text_loading) {
                    mType = ButtonType.TYPE_CUSTOM;
                }
            } finally {
                ta.recycle();
            }
        }
        setGravity(Gravity.CENTER);

        // 加载ProgressBar
        mProgressBar = new ProgressBar(getContext());
        int pbSize = textSize == -1? DisplayUtils.sp2px(getContext(), TEXT_SIZE_DEFAULT) : textSize;
        RelativeLayout.LayoutParams pbLp = new RelativeLayout.LayoutParams(pbSize, pbSize);
        int pbMargin = DisplayUtils.dip2px(getContext(), 4);
        pbLp.setMargins(pbMargin, pbMargin, pbMargin, pbMargin);
        pbLp.addRule(LEFT_OF, android.R.id.text1);
        mProgressBar.setLayoutParams(pbLp);
        mProgressBar.setIndeterminate(true);
        mProgressBar.setIndeterminateDrawable(ResourcesCompat.getDrawable(getResources(), R.drawable.weui_loading_rotate, null));

        // 按钮文字
        mButtonTextView = new TextView(getContext());
        mButtonTextView.setId(android.R.id.text1);
        if (textSize == -1) {
            mButtonTextView.setTextSize(TEXT_SIZE_DEFAULT);
        } else {
            mButtonTextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSize);
        }
        mButtonTextView.setText(mText);

        addView(mProgressBar);
        addView(mButtonTextView);

        // 应用按钮类型
        setType(mType);

        mProgressBar.setVisibility(GONE);
        setEnabled(enabled);
        if (loading) setLoading(true);
    }

    /**
     * 设置按钮类型
     */
    public void setType(@ButtonType int type) {
        mType = type;
        switch (mType) {
            case TYPE_PRIMARY:
                mBGNormal = R.drawable.weui_bg_btn_primary_normal;
                mBGLoading = R.drawable.weui_bg_btn_primary_loading;
                mBGTextNormal = R.drawable.weui_bg_btn_primary_text_normal;
                mBGTextLoading = R.drawable.weui_bg_btn_primary_text_loading;
                break;
            case TYPE_DEFAULT:
                mBGNormal = R.drawable.weui_bg_btn_default_normal;
                mBGLoading = R.drawable.weui_bg_btn_default_loading;
                mBGTextNormal = R.drawable.weui_bg_btn_default_text_normal;
                mBGTextLoading = R.drawable.weui_bg_btn_default_text_loading;
                break;
            case TYPE_WARN:
                mBGNormal = R.drawable.weui_bg_btn_warn_normal;
                mBGLoading = R.drawable.weui_bg_btn_warn_loading;
                mBGTextNormal = R.drawable.weui_bg_btn_warn_text_normal;
                mBGTextLoading = R.drawable.weui_bg_btn_warn_text_loading;
                break;
            case TYPE_CUSTOM:
                break;
            default:
                throw new IllegalArgumentException("错误的按钮类型");
        }
        applyType();
    }

    /**
     * 应用类型
     */
    private void applyType() {
        // 按钮背景
        setBackground(ResourcesCompat.getDrawable(getResources(), mBGNormal,null));
        // 文字颜色
        mButtonTextView.setTextColor(ResourcesCompat.getColorStateList(getResources(), mBGTextNormal,null));
        setLoading(mIsLoading);
    }

    /**
     * 设置加载状态，加载中按钮不能点击
     * @param isLoading 是否加载
     */
    public void setLoading(boolean isLoading) {
        if (isLoading) {
            setEnabled(false);
            setBackground(ResourcesCompat.getDrawable(getResources(), mBGLoading,null));
            mButtonTextView.setTextColor(ResourcesCompat.getColorStateList(getResources(), mBGTextLoading,null));
            mText = mButtonTextView.getText();
            if (mLoadingText != null) {
                mButtonTextView.setText(mLoadingText);
            }
            mProgressBar.setVisibility(VISIBLE);
        } else {
            setEnabled(true);
            setBackground(ResourcesCompat.getDrawable(getResources(), mBGNormal,null));
            mButtonTextView.setTextColor(ResourcesCompat.getColorStateList(getResources(), mBGTextNormal,null));
            mButtonTextView.setText(mText);
            mProgressBar.setVisibility(GONE);
        }
        mIsLoading = isLoading;
    }

    /**
     * 设置加载中显示文字
     * @param id resource id
     */
    public void setLoadingText(@StringRes int id) {
        setLoadingText(getResources().getString(id));
    }

    /**
     * 设置加载中显示文字
     * @param text 文字
     */
    public void setLoadingText(String text) {
        mLoadingText = text;
    }

    /**
     * 是否加载中
     * @return 是否加载中
     */
    public boolean isLoading() {
        return mIsLoading;
    }

    @Override
    public void setEnabled(boolean enabled) {
        super.setEnabled(enabled);
        setClickable(enabled);
        mButtonTextView.setEnabled(enabled);
    }

    /**
     * 设置按钮文字
     * @param resId 文字resId
     */
    public void setText(int resId) {
        mButtonTextView.setText(resId);
    }

    /**
     * 设置按钮文字
     * @param text 文字
     */
    public void setText(CharSequence text) {
        mButtonTextView.setText(text);
    }

    /**
     * 设置按钮文字大小
     * @param size 大小 in sp
     */
    public void setTextSize(float size) {
        mButtonTextView.setTextSize(size);
    }

    /**
     * 设置按钮文字大小
     * @param unit 数值单位
     * @param size 大小
     */
    public void setTextSize(int unit, float size) {
        mButtonTextView.setTextSize(unit, size);
    }

    /**
     * 设置按钮背景
     * @param bgNormal 默认背景
     * @param bgLoading 加载状态背景
     */
    public void setButtonBackgrounds(@DrawableRes int bgNormal, @DrawableRes int bgLoading) {
        mBGNormal = bgNormal;
        mBGLoading = bgLoading;
        applyType();
    }

    /**
     * 设置按钮文字颜色
     * @param textNormal 默认颜色
     * @param textLoading 加载状态颜色
     */
    public void setTextBackgrounds(@DrawableRes int textNormal, @DrawableRes int textLoading) {
        mBGTextNormal = textNormal;
        mBGTextLoading = textLoading;
        applyType();
    }
}
