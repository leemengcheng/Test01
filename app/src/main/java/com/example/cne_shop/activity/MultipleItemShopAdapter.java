package com.example.cne_shop.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cne_shop.R;

import java.util.List;

/**
 * Created by 李鹏 on 2017/2/23.
 */

public class MultipleItemShopAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private Context context;

    public MultipleItemShopAdapter(List<MultipleItem> data, Context context) {
        super(data);
        this.context = context;
        addItemType(MultipleItem.TYPE_2, R.layout.item_type2_divider_h);
        addItemType(MultipleItem.TYPE_3, R.layout.item_type3);
        addItemType(MultipleItem.TYPE_5, R.layout.item_type5);
        addItemType(MultipleItem.TYPE_6, R.layout.item_type6);
        addItemType(MultipleItem.TYPE_13, R.layout.item_type13);
        addItemType(MultipleItem.TYPE_14, R.layout.item_type14);
        addItemType(MultipleItem.TYPE_15, R.layout.item_type15);
        addItemType(MultipleItem.TYPE_22, R.layout.item_type22);
        addItemType(MultipleItem.TYPE_23, R.layout.item_type23);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TYPE_0:
                break;
            case MultipleItem.TYPE_1:
                break;
            case MultipleItem.TYPE_2:
                //  helper.setBackgroundRes(R.id.item_type2_divider_h,item.getBakground());
                break;
            case MultipleItem.TYPE_3:
                helper.setBackgroundRes(R.id.item_type3_img, item.getBakground());
                break;
            case MultipleItem.TYPE_5:
                helper.setOnClickListener(R.id.iv_shop1, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        System.out.println("ccc00088888");
                    }
                });
                break;
            case MultipleItem.TYPE_6:
                //helper.setBackgroundRes(R.id.item_type6_ll, item.getBakground());
                helper.setImageResource(R.id.item_type6_img, item.getImage());
                helper.setText(R.id.item_type6_text1, item.gettitle());
                helper.setText(R.id.item_type6_text2, item.getContent());
                break;
            case MultipleItem.TYPE_14:
                helper.setImageResource(R.id.item_type14_img, item.getBakground());
                helper.setImageResource(R.id.item_type14_img2, item.getImage());
                helper.setText(R.id.item_type14_text1, item.gettitle());
                break;

            case MultipleItem.TYPE_15:
                helper.setBackgroundRes(R.id.item_type_img, item.getBakground());
                helper.setImageResource(R.id.item_type_img1, item.getImage());
                helper.setText(R.id.item_type_text1, item.gettitle() + "\n\r-----");
                helper.setText(R.id.item_type_text3, item.getContent());
                break;

            case MultipleItem.TYPE_22:
                helper.setBackgroundRes(R.id.item_type_img, item.getBakground());
                break;
        }
    }
}
