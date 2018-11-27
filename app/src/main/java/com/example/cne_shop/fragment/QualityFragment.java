package com.example.cne_shop.fragment;


import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.cne_shop.R;
import com.example.cne_shop.activity.MultipleItem;
import com.example.cne_shop.activity.MultipleItemQuickAdapter;
import com.example.cne_shop.base.BaseFragment;
import com.example.cne_shop.base.ResyslerViewIndicator;
import com.example.cne_shop.widget.CnToolbar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 博 on 2017/8/21.
 */

public class QualityFragment extends BaseFragment {

    private  CnToolbar cnToolbar ;
    private  RecyclerView recyclerView ;
    private List<ResyslerViewIndicator> mData;
    private List<MultipleItem> data;
    private int mDistanceY;

    @Override
    protected int getResRootViewId() {
        return R.layout.quality_main_fragment;
    }

    @Override
    protected void init() {
        cnToolbar = (CnToolbar) mView.findViewById(R.id.toolBar);
        //initSlider() ;
        Init();
        //initRecyclerView() ;
    }

    private void Init() {
        data = getMultipleItemData();
        recyclerView = (RecyclerView) mView.findViewById(R.id.resyclerView) ;
        final MultipleItemQuickAdapter multipleItemAdapter = new MultipleItemQuickAdapter(data, getActivity());
        final GridLayoutManager manager = new GridLayoutManager(getActivity(), MultipleItem.TYPE_SPAN_SIZE_20);
        recyclerView.setLayoutManager(manager);
        multipleItemAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return data.get(position).getSpanSize();
            }
        });
        recyclerView.setAdapter(multipleItemAdapter);

        recyclerView.addOnItemTouchListener(new OnItemClickListener() {

            @Override
            public void onSimpleItemClick(BaseQuickAdapter adapter, View view, int position) {
                Toast.makeText(getActivity(), "position:" + Integer.toString(position), Toast.LENGTH_SHORT).show();
            }

        });
    }

    private List<MultipleItem> getMultipleItemData() {
        List<MultipleItem> data = new ArrayList<>();

        //头信息
        //data.add(new MultipleItem(MultipleItem.TYPE_0,InitData(), MultipleItem.TYPE_SPAN_SIZE_20));
        data.add(new MultipleItem(MultipleItem.TYPE_12,InitData00(), MultipleItem.TYPE_SPAN_SIZE_20));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //导购栏
        data.add(new MultipleItem(MultipleItem.TYPE_1, R.mipmap.chaoshi, R.mipmap.zt, "展厅", MultipleItem.TYPE_SPAN_SIZE_5));
        data.add(new MultipleItem(MultipleItem.TYPE_1, R.mipmap.fushi, R.mipmap.sjs, "设计师", MultipleItem.TYPE_SPAN_SIZE_5));
        data.add(new MultipleItem(MultipleItem.TYPE_1, R.mipmap.jiushui, R.mipmap.lc, "服务流程", MultipleItem.TYPE_SPAN_SIZE_5));
        data.add(new MultipleItem(MultipleItem.TYPE_1, R.mipmap.xinsou, R.mipmap.sc, "收藏", MultipleItem.TYPE_SPAN_SIZE_5));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //设计主题展示
        data.add(new MultipleItem(MultipleItem.TYPE_11, MultipleItem.TYPE_SPAN_SIZE_20));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        data.add(new MultipleItem(MultipleItem.TYPE_0,InitData(), MultipleItem.TYPE_SPAN_SIZE_20));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //智能创客
        data.add(new MultipleItem(MultipleItem.TYPE_8, MultipleItem.TYPE_SPAN_SIZE_20));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //智能创客
        /*for (int i = 1; i < 5; i++) {
            data.add(new MultipleItem(MultipleItem.TYPE_9, MultipleItem.TYPE_SPAN_SIZE_10));
        }*/
        data.add(new MultipleItem(MultipleItem.TYPE_9, R.mipmap.ck1, MultipleItem.TYPE_SPAN_SIZE_10));
        data.add(new MultipleItem(MultipleItem.TYPE_9, R.mipmap.ck2, MultipleItem.TYPE_SPAN_SIZE_10));
        data.add(new MultipleItem(MultipleItem.TYPE_9, R.mipmap.ck3, MultipleItem.TYPE_SPAN_SIZE_10));
        data.add(new MultipleItem(MultipleItem.TYPE_9, R.mipmap.ck4, MultipleItem.TYPE_SPAN_SIZE_10));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //案例展示
        data.add(new MultipleItem(MultipleItem.TYPE_21, MultipleItem.TYPE_SPAN_SIZE_20));

        //横分割线
        data.add(new MultipleItem(MultipleItem.TYPE_2, MultipleItem.TYPE_SPAN_SIZE_20));

        //案例展示
        /*for (int i = 1; i < 5; i++) {
            data.add(new MultipleItem(MultipleItem.TYPE_10, MultipleItem.TYPE_SPAN_SIZE_10));
        }*/
        data.add(new MultipleItem(MultipleItem.TYPE_10, R.mipmap.al1, MultipleItem.TYPE_SPAN_SIZE_10));
        data.add(new MultipleItem(MultipleItem.TYPE_10, R.mipmap.al2, MultipleItem.TYPE_SPAN_SIZE_10));
        data.add(new MultipleItem(MultipleItem.TYPE_10, R.mipmap.al3, MultipleItem.TYPE_SPAN_SIZE_10));
        data.add(new MultipleItem(MultipleItem.TYPE_10, R.mipmap.al4, MultipleItem.TYPE_SPAN_SIZE_10));

        return data;
    }

    private List<String> InitData() {
        List<String> urls = new ArrayList<>();
        urls.add("http://img4.imgtn.bdimg.com/it/u=335554504,46277580&fm=23&gp=0.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=3881482301,3131576041&fm=23&gp=0.jpg");
        urls.add("https://img.cniao5.com/5608f3b5Nc8d90151.jpg");
        return urls;
    }

    private List<String> InitData00() {
        List<String> urls = new ArrayList<>();
        urls.add("http://img4.imgtn.bdimg.com/it/u=335554504,46277580&fm=23&gp=0.jpg");
        urls.add("http://img2.imgtn.bdimg.com/it/u=3881482301,3131576041&fm=23&gp=0.jpg");
        return urls;
    }
}
