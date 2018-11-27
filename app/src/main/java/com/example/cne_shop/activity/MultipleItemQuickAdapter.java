package com.example.cne_shop.activity;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.cne_shop.R;

import java.util.List;

import me.weyye.library.colortrackview.ColorTrackTabLayout;

/**
 * Created by 李鹏 on 2017/2/23.
 */

public class MultipleItemQuickAdapter extends BaseMultiItemQuickAdapter<MultipleItem, BaseViewHolder> {

    private Context context;
    private ViewPager viewPager;
    ColorTrackTabLayout mTabChannel;
    private ImageView[] mImageView;
    private LinearLayout mIndicator;
    private MyViewPagerAdapter mAdapter;

    List<String> url00;
    private ImageView[] mImageView00;
    private ViewPager viewPager00;
    private LinearLayout mIndicator00;
    private MyViewPagerAdapter00 mAdapter00;

    List<String> url;
    private String[] titles;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 0) {
                viewPager00.setCurrentItem(viewPager00.getCurrentItem() + 1);
                handler.sendEmptyMessageDelayed(0, 2000);
            }
        }
    };

    public MultipleItemQuickAdapter(List<MultipleItem> data, Context context) {
        super(data);
        this.context = context;
        addItemType(MultipleItem.TYPE_0, R.layout.head);
        addItemType(MultipleItem.TYPE_1, R.layout.item_type1);
        addItemType(MultipleItem.TYPE_2, R.layout.item_type2_divider_h);
        addItemType(MultipleItem.TYPE_3, R.layout.item_type3);
        addItemType(MultipleItem.TYPE_4, R.layout.item_type4);
        addItemType(MultipleItem.TYPE_5, R.layout.item_type5);
        addItemType(MultipleItem.TYPE_6, R.layout.item_type6);
        addItemType(MultipleItem.TYPE_7, R.layout.item_type7);
        addItemType(MultipleItem.TYPE_8, R.layout.item_type8);
        addItemType(MultipleItem.TYPE_9, R.layout.item_type9);
        addItemType(MultipleItem.TYPE_10, R.layout.item_type9);
        addItemType(MultipleItem.TYPE_11, R.layout.item_type8);
        addItemType(MultipleItem.TYPE_12, R.layout.head00);
        addItemType(MultipleItem.TYPE_21, R.layout.item_type8);
    }

    @Override
    protected void convert(BaseViewHolder helper, MultipleItem item) {
        switch (helper.getItemViewType()) {
            case MultipleItem.TYPE_0:
                initViewPager(helper, item);
                /*if (!handler.hasMessages(0)) {
                    handler.sendEmptyMessage(0);
                }*/
                break;
            case MultipleItem.TYPE_1:
                helper.setText(R.id.item_type1_text, item.getContent());
                //helper.setImageResource(R.id.item_type1_img, item.getImage());
                helper.setBackgroundRes(R.id.item_type1_img, item.getBakground());
                break;
            case MultipleItem.TYPE_2:
                //  helper.setBackgroundRes(R.id.item_type2_divider_h,item.getBakground());
                break;
            case MultipleItem.TYPE_3:
                helper.setBackgroundRes(R.id.item_type3_img, item.getBakground());
                break;
            case MultipleItem.TYPE_4:
                helper.setText(R.id.item_type_4_text3, item.getContent());
                break;

            case MultipleItem.TYPE_9:
                //helper.setBackgroundRes(R.id.item_type9_img, item.getBakground());
                helper.setImageResource(R.id.item_type9_img, item.getBakground());
                //helper.setText(R.id.item_type_4_text3, item.getContent());
                break;

            case MultipleItem.TYPE_10:
                helper.setImageResource(R.id.item_type9_img, item.getBakground());
                break;

            case MultipleItem.TYPE_11:
                helper.setText(R.id.tv_name, "案例展示");
                break;

            case MultipleItem.TYPE_21:
                helper.setText(R.id.tv_name, "设计主题展示");
                break;

            case MultipleItem.TYPE_12:
                initViewPager00(helper, item);
                if (!handler.hasMessages(0)) {
                    handler.sendEmptyMessage(0);
                }
                break;

            case MultipleItem.TYPE_6:
                helper.setBackgroundRes(R.id.item_type6_ll, item.getBakground());
                helper.setText(R.id.item_type6_text1, item.gettitle());
                helper.setText(R.id.item_type6_text2, item.getContent());
                helper.setImageResource(R.id.item_type6_img, item.getImage());
                break;
        }
    }


    private void initViewPager(BaseViewHolder helper, MultipleItem item) {
        url = item.getList();
        mIndicator = (LinearLayout) helper.getView(R.id.indicator);
        viewPager = (ViewPager) helper.getView(R.id.head_viewPager);
        titles = new String[]{"中式", "美式","欧式"};
        mTabChannel = (ColorTrackTabLayout) helper.getView(R.id.tab_channel);
        mAdapter = new MyViewPagerAdapter(context, url, titles);
        viewPager.setAdapter(mAdapter);
        //viewPager.setCurrentItem(5000 * (url.size() + 1));
        viewPager.setCurrentItem(0);

        //mTabChannel.setSelectedTabIndicatorHeight(0);
        //mTabChannel.setTabPaddingLeftAndRight(10,10);
        //mTabChannel.setupWithViewPager(viewPager);
        mTabChannel.setupWithViewPager(viewPager);

        if (mImageView == null) {
            initIndicator();
        }

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIndicator(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initIndicator() {
        mImageView = new ImageView[url.size()];
        for (int i = 0; i < url.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.indicator_image, null);
            view.findViewById(R.id.indicator_iamge).setBackgroundResource(R.mipmap.hong_indicator);
            mImageView[i] = new ImageView(context);
            if (i == 0) {
                //mImageView[i].setBackgroundResource(R.mipmap.hong_indicator);
            } else {
                //mImageView[i].setBackgroundResource(R.mipmap.hui_indicator);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(20, 0, 0, 0);
                mImageView[i].setLayoutParams(layoutParams);
            }
            mIndicator.addView(mImageView[i]);
        }
    }

    private void setIndicator(int position) {
        position %= url.size();
        for (int i = 0; i < url.size(); i++) {
            /*mImageView[i].setBackgroundResource(R.mipmap.hong_indicator);
            if (position != i) {
                mImageView[i].setBackgroundResource(R.mipmap.hui_indicator);
            }*/
        }
    }


    private void initViewPager00(BaseViewHolder helper, MultipleItem item) {
        url00 = item.getList();
        mIndicator00 = (LinearLayout) helper.getView(R.id.indicator);
        viewPager00 = (ViewPager) helper.getView(R.id.head_viewPager);

        mAdapter00 = new MyViewPagerAdapter00(context, url00);
        viewPager00.setAdapter(mAdapter00);
        viewPager00.setCurrentItem(5000 * (url00.size() + 1));

        if (mImageView00 == null) {
            initIndicator00();
        }

        viewPager00.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                setIndicator00(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void initIndicator00() {
        mImageView00 = new ImageView[url00.size()];
        for (int i = 0; i < url00.size(); i++) {
            View view = LayoutInflater.from(context).inflate(R.layout.indicator_image, null);
            view.findViewById(R.id.indicator_iamge).setBackgroundResource(R.mipmap.hong_indicator);
            mImageView00[i] = new ImageView(context);
            if (i == 0) {
                mImageView00[i].setBackgroundResource(R.mipmap.hong_indicator);
            } else {
                mImageView00[i].setBackgroundResource(R.mipmap.hui_indicator);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                layoutParams.setMargins(20, 0, 0, 0);
                mImageView00[i].setLayoutParams(layoutParams);
            }
            mIndicator00.addView(mImageView00[i]);
        }
    }

    private void setIndicator00(int position) {
        position %= url00.size();
        for (int i = 0; i < url00.size(); i++) {
            mImageView00[i].setBackgroundResource(R.mipmap.hong_indicator);
            if (position != i) {
                mImageView00[i].setBackgroundResource(R.mipmap.hui_indicator);
            }
        }
    }
}
