package com.example.cne_shop.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.example.cne_shop.R;
import com.example.cne_shop.activity.MultipleItem;
import com.example.cne_shop.activity.MultipleItemQuickAdapter;
import com.example.cne_shop.activity.MultipleItemShopAdapter;
import com.example.cne_shop.base.BaseFragment;
import com.example.cne_shop.base.ResyslerViewIndicator;
import com.example.cne_shop.bean.SliderIndicator;
import com.example.cne_shop.contents.Contents;
import com.example.cne_shop.okhttp.OkhttpHelper;
import com.example.cne_shop.okhttp.loadingSpotsDialog;
import com.example.cne_shop.widget.CnToolbar;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by 博 on 2017/8/21.
 */

public class ShopFragment extends BaseFragment {

    //private  CnToolbar cnToolbar ;
    private  RecyclerView recyclerView ;
    //private List<ResyslerViewIndicator> mData;
    private List<MultipleItem> data;
    //private int mDistanceY;

    @Override
    protected int getResRootViewId() {
        return R.layout.shop_main_fragment;
    }

    @Override
    protected void init() {
        //cnToolbar = (CnToolbar) mView.findViewById(R.id.toolBar);
        Init();

    }

    private void Init() {
        data = getMultipleItemData();
        recyclerView = (RecyclerView) mView.findViewById(R.id.resyclerView) ;
        final MultipleItemShopAdapter multipleItemAdapter = new MultipleItemShopAdapter(data, getActivity());
        final GridLayoutManager manager = new GridLayoutManager(getActivity(), MultipleItem.TYPE_SPAN_SIZE_20);
        recyclerView.setLayoutManager(manager);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        recyclerView.setAdapter(multipleItemAdapter);
        multipleItemAdapter.notifyDataSetChanged();
        recyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "position:" + Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

        });

    }

    private List<MultipleItem> getMultipleItemData() {
        List<MultipleItem> data = new ArrayList<>();

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //新货
        data.add(new MultipleItem(MultipleItem.TYPE_3, R.mipmap.shop1, MultipleItem.TYPE_SPAN_SIZE_20));

        //创意日常、摩登装扮、限量原作
        data.add(new MultipleItem(MultipleItem.TYPE_5, MultipleItem.TYPE_SPAN_SIZE_20));

        //有画之家、茶余饭后
        data.add(new MultipleItem(MultipleItem.TYPE_13, MultipleItem.TYPE_SPAN_SIZE_20));
        //data.add(new MultipleItem(MultipleItem.TYPE_6, R.mipmap.shop5, R.mipmap.shangpin4di, "把艺术带回家", MultipleItem.TYPE_SPAN_SIZE_10, "有画之家"));
        //data.add(new MultipleItem(MultipleItem.TYPE_6, R.mipmap.shop6, R.mipmap.shangpin4di, "让你尖叫的瓶瓶罐罐", MultipleItem.TYPE_SPAN_SIZE_10, "茶余饭后"));

        //查看更多艺术品
        //data.add(new MultipleItem(MultipleItem.TYPE_3, R.mipmap.look_more, MultipleItem.TYPE_SPAN_SIZE_20));
        data.add(new MultipleItem(MultipleItem.TYPE_14, R.mipmap.shop7, R.mipmap.bg_look, "xxx", MultipleItem.TYPE_SPAN_SIZE_20, "查看更多艺术品"));

        //浓情告白
        data.add(new MultipleItem(MultipleItem.TYPE_15, R.mipmap.shop_bj, R.mipmap.shop8, "爱就是给你一个温馨的家", MultipleItem.TYPE_SPAN_SIZE_20, "浓情告白"));

        //轻奢主义
        data.add(new MultipleItem(MultipleItem.TYPE_15, R.mipmap.shop_bj, R.mipmap.shop9, "兔女郎让你尽享时尚新生活", MultipleItem.TYPE_SPAN_SIZE_20, "轻奢主义"));

        //浓情告白
        data.add(new MultipleItem(MultipleItem.TYPE_15, R.mipmap.shop_bj, R.mipmap.shop10, "恋上红楼梦的精致品味", MultipleItem.TYPE_SPAN_SIZE_20, "最佳伴手礼"));

        //浓情告白
        data.add(new MultipleItem(MultipleItem.TYPE_15, R.mipmap.shop_bj, R.mipmap.shop11, "装扮全家的幸福与喜悦", MultipleItem.TYPE_SPAN_SIZE_20, "灵动空间"));

        //浓情告白
        data.add(new MultipleItem(MultipleItem.TYPE_15, R.mipmap.shop_bj, R.mipmap.shop12, "揭示家里与众不同的秘密", MultipleItem.TYPE_SPAN_SIZE_20, "戏如人生"));

        //浓情告白
        data.add(new MultipleItem(MultipleItem.TYPE_15, R.mipmap.shop_bj, R.mipmap.shop13, "不至于美术馆 绽放在生活中", MultipleItem.TYPE_SPAN_SIZE_20, "艺术&生活"));

        return data;
    }

}
