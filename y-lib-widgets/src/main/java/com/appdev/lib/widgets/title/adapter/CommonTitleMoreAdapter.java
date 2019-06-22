package com.appdev.lib.widgets.title.adapter;

import android.text.TextUtils;

import com.appdev.lib.widgets.R;
import com.appdev.lib.widgets.title.model.MoreAction;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

public class CommonTitleMoreAdapter extends BaseQuickAdapter<MoreAction,BaseViewHolder> {

    public CommonTitleMoreAdapter(int layoutResId) {
        super(layoutResId);
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
    }
}
