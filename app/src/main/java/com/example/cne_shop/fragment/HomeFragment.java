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

public class HomeFragment extends BaseFragment {

    private  CnToolbar cnToolbar ;
    private  RecyclerView recyclerView ;
    private List<ResyslerViewIndicator> mData;
    private List<MultipleItem> data;
    private int mDistanceY;

    @Override
    protected int getResRootViewId() {
        return R.layout.home_main_fragment;
    }

    @Override
    protected void init() {
        cnToolbar = (CnToolbar) mView.findViewById(R.id.toolBar);
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

        //展示
        data.add(new MultipleItem(MultipleItem.TYPE_22, R.mipmap.banner1, MultipleItem.TYPE_SPAN_SIZE_20));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //展示
        data.add(new MultipleItem(MultipleItem.TYPE_23, R.mipmap.ct, MultipleItem.TYPE_SPAN_SIZE_20));


        return data;
    }
}
