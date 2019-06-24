package com.appdev.lib.widgets.title.adapter;

import android.content.Context;
import android.media.Image;
import android.text.TextUtils;
import android.widget.ImageView;

import androidx.core.content.res.ResourcesCompat;

import com.appdev.lib.widgets.R;
import com.appdev.lib.widgets.title.model.MoreAction;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class CommonTitleMoreAdapter extends BaseQuickAdapter<MoreAction,BaseViewHolder> {
    private int color;
    private Context mContext;

    public CommonTitleMoreAdapter(int layoutResId, int color, Context context) {
        super(layoutResId);
        this.color = color;
        this.mContext = context;
    }

    @Override
    protected void convert(BaseViewHolder helper, MoreAction item) {
        if(item.getIcon()!=-1){
            helper.setGone(R.id.iv_more,true);
            helper.setImageResource(R.id.iv_more,item.getIcon());
        }else {
            helper.setGone(R.id.iv_more,false);
        }
        if(!TextUtils.isEmpty(item.getName())){
            helper.setGone(R.id.tv_more,true);
            helper.setText(R.id.tv_more,item.getName());
        }else {
            helper.setGone(R.id.tv_more,false);
        }
        if(item.getColor()!=-1){
            ((ImageView)helper.getView(R.id.iv_more)).setColorFilter(
                    ResourcesCompat.getColor(mContext.getResources(),item.getColor(),null));
        }else {
            ((ImageView)helper.getView(R.id.iv_more)).setColorFilter(
                    ResourcesCompat.getColor(mContext.getResources(),color,null));
        }
    }
}
