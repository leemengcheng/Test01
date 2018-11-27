package com.example.cne_shop.activity;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Kevin on 2016/10/13.
 */

public class MyViewPagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<String> mImageArr;
    private String[] mTitles;

    public MyViewPagerAdapter(Context context, List<String> imageArr,String[] titles) {
        this.mContext = context;
        this.mImageArr = imageArr;
        this.mTitles = titles;
    }

    @Override
    public int getCount() {
        return mTitles.length;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ImageView imageView = new ImageView(mContext);
        position %= mImageArr.size();
        if(position<0){
            position = mImageArr.size()+position;
        }
        Picasso.with(mContext).load(mImageArr.get(position)).resize(719,411).into(imageView);
        container.addView(imageView);
        return imageView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object view) {
        container.removeView((View) view);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles[position];
    }
}
