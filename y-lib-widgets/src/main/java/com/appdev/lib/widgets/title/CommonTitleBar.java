package com.appdev.lib.widgets.title;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.appdev.lib.utils.DisplayUtils;
import com.appdev.lib.widgets.R;
import com.appdev.lib.widgets.button.WEUIButton;
import com.appdev.lib.widgets.title.adapter.CommonTitleMoreAdapter;
import com.appdev.lib.widgets.title.listener.OnMoreActionItemClickListener;
import com.appdev.lib.widgets.title.model.MoreAction;

import java.util.List;

public class CommonTitleBar extends RelativeLayout {
    private String mTitleText;
    private int mBackImg;
    private String mBackText;
    private boolean mShowBack;
    private boolean mShowDivider;
    private boolean mShowRightBtn;
    private String mRightBtnText;
    private int mBackGroundColor;
    private int mTextAndIconColor;
    private int mBarStyle;
    private boolean immersiveStatusBar;

    private View vPlaceHolder;
    private LinearLayout llTitleBack;
    private ImageView ivBack;
    private TextView tvBack;
    private View vDividerBack;
    private TextView tvTitle;
    private RecyclerView mRecyclerView;
    private WEUIButton wbConfirm;

    private CommonTitleMoreAdapter mAdapter;

    public CommonTitleBar(Context context) {
        this(context,null);
    }

    public CommonTitleBar(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public CommonTitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray ta = null;
        try {
            ta = context.obtainStyledAttributes(attrs, R.styleable.CommonTitleBar,0,0);
            mTitleText = ta.getString(R.styleable.CommonTitleBar_titleText);
            mBackImg = ta.getInt(R.styleable.CommonTitleBar_backImg,-1);
            mBackText = ta.getString(R.styleable.CommonTitleBar_backText);
            mShowBack = ta.getBoolean(R.styleable.CommonTitleBar_showBack,true);
            mShowDivider = ta.getBoolean(R.styleable.CommonTitleBar_showDivider,false);
            mShowRightBtn = ta.getBoolean(R.styleable.CommonTitleBar_showRightBtn,false);
            mRightBtnText = ta.getString(R.styleable.CommonTitleBar_rightBtnText);
            mBackGroundColor = ta.getResourceId(R.styleable.CommonTitleBar_backgroundColor,R.color.appdev_primary_blue);
            mTextAndIconColor = ta.getResourceId(R.styleable.CommonTitleBar_textAndIconColor,R.color.appdev_white);
            mBarStyle = ta.getInt(R.styleable.CommonTitleBar_barStyle,1);
            immersiveStatusBar = ta.getBoolean(R.styleable.CommonTitleBar_immersiveStatusBar, false);
            if(mBarStyle==1){
                LayoutInflater.from(context).inflate(R.layout.layout_common_title_bar_center,this);
            }else if(mBarStyle==2){
                LayoutInflater.from(context).inflate(R.layout.layout_common_title_bar_left,this);
            }
            initView();
            afterInit();
        } finally {
            if (ta != null) {
                ta.recycle();
            }
        }
    }

    private void initView() {
        vPlaceHolder = findViewById(R.id.v_place_holder);
        llTitleBack = findViewById(R.id.ll_title_back);
        vDividerBack = findViewById(R.id.v_divider_back);
        ivBack = findViewById(R.id.iv_back);
        tvBack = findViewById(R.id.tv_back);
        tvTitle = findViewById(R.id.tv_title);
        mRecyclerView = findViewById(R.id.rv_recycler_view);
        wbConfirm = findViewById(R.id.wb_btn_confirm);
    }

    private void afterInit() {
        tvTitle.setText(mTitleText);
        if(mBackImg!=-1){
            ivBack.setImageDrawable(getResources().getDrawable(mBackImg));
        }
        llTitleBack.setOnClickListener(v -> goBack());
        showBackText(mBackText);
        showDivider(mShowDivider);
        showBack(mShowBack);
        showRightBtn(mShowRightBtn,mRightBtnText);
        setStyleColor(mBackGroundColor,mTextAndIconColor);
        setImmersiveStatusBar(immersiveStatusBar);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(
                getContext(), LinearLayoutManager.HORIZONTAL, true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mAdapter = new CommonTitleMoreAdapter(R.layout.item_title_more_action,mTextAndIconColor,getContext());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 默认返回
     */
    private void goBack() {
        ((Activity) getContext()).finish();
    }

    /**
     * 设置返回文字
     * @param charSequence
     */
    public void showBackText(CharSequence charSequence) {
        if(!TextUtils.isEmpty(charSequence)){
            tvBack.setVisibility(VISIBLE);
            tvBack.setText(charSequence);
        }else {
            tvBack.setVisibility(GONE);
        }
    }

    /**
     * 是否显示divider
     * @param mShowDivider
     */
    public void showDivider(boolean mShowDivider) {
        if(mShowDivider){
            vDividerBack.setVisibility(VISIBLE);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)tvTitle.getLayoutParams();
            layoutParams.leftMargin = DisplayUtils.dip2px(getContext(),10);
            tvTitle.setLayoutParams(layoutParams);
        }else {
            vDividerBack.setVisibility(GONE);
        }
    }

    /**
     * 显示返回按钮
     * @param mShowBack
     */
    public void showBack(boolean mShowBack) {
        if(mShowBack){
            llTitleBack.setVisibility(VISIBLE);
        }else {
            llTitleBack.setVisibility(GONE);
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams)tvTitle.getLayoutParams();
            layoutParams.leftMargin = DisplayUtils.dip2px(getContext(),12);
            tvTitle.setLayoutParams(layoutParams);
        }
    }

    /**
     * 显示右边按钮
     * @param mShowRightBtn
     * @param mRightBtnText
     */
    public void showRightBtn(boolean mShowRightBtn, CharSequence mRightBtnText) {
        if(mShowRightBtn){
            mRecyclerView.setVisibility(GONE);
            wbConfirm.setVisibility(VISIBLE);
        }else {
            mRecyclerView.setVisibility(VISIBLE);
            wbConfirm.setVisibility(GONE);
        }
        wbConfirm.setText(mRightBtnText);
    }

    /**
     * 设置TitleBar背景色和文字色
     * @param mBackGroundColor
     * @param mTextAndIconColor
     */
    public void setStyleColor(int mBackGroundColor, int mTextAndIconColor) {
        // 按钮背景
        setBackground(ResourcesCompat.getDrawable(getResources(), mBackGroundColor,null));
        tvTitle.setTextColor(ResourcesCompat.getColor(getResources(),mTextAndIconColor,null));
        ivBack.setColorFilter(ResourcesCompat.getColor(getResources(),mTextAndIconColor,null));
    }

    /**
     * 设置沉浸式
     * @param immersiveStatusBar
     */
    public void setImmersiveStatusBar(boolean immersiveStatusBar) {
        if (immersiveStatusBar) {
            int statusHeight = DisplayUtils.getStatusBarHeight(getContext());
            ViewGroup.LayoutParams layoutParams = vPlaceHolder.getLayoutParams();
            layoutParams.height = statusHeight;
            vPlaceHolder.setLayoutParams(layoutParams);
        }
    }

    /**
     * 设置返回按钮事件
     * @param listener 事件监听
     */
    public void setBackListener(OnClickListener listener) {
        llTitleBack.setOnClickListener(listener);
    }

    /**
     * 设置右边按钮点击事件
     * @param listener
     */
    public void setOnRightBtnClickListener(OnClickListener listener){
        wbConfirm.setOnClickListener(listener);
    }

    /**
     * 设置标题
     * @param titleText
     */
    public void setTitleText(CharSequence titleText){
        this.tvTitle.setText(titleText);
    }

    /**
     * 设置更多
     * @param moreActions
     */
    public void setActions(List<MoreAction> moreActions,OnMoreActionItemClickListener listener){
        this.mAdapter.setNewData(moreActions);
        this.mAdapter.setOnItemClickListener((adapter, view, position) -> {
            if(listener!=null){
                MoreAction moreAction = (MoreAction) adapter.getData().get(position);
                listener.OnMoreActionItemClick(moreAction,view,position);
            }
        });
    }
}
