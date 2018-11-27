package com.example.cne_shop.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.cne_shop.R;
import com.example.cne_shop.activity.LoginActivity;
import com.example.cne_shop.activity.MyOrderActivity;
import com.example.cne_shop.activity.ShowConsigneeAdrActivity;
import com.example.cne_shop.application.MyApplication;
import com.example.cne_shop.application.MyApplicationInfo;
import com.example.cne_shop.base.BaseFragment;
import com.example.cne_shop.base.CircleImageView;
import com.example.cne_shop.bean.User;
import com.example.cne_shop.bean.UserInfo;
import com.example.cne_shop.widget.CnToolbar;

import butterknife.BindView;

/**
 * Created by 博 on 2017/8/21.
 */

public class MineFragment extends BaseFragment {
    private TextView tv_accountNick ;
    private TextView tv_account ;
    private CircleImageView iv_user_avatar ;
    private RelativeLayout rl_title_no_login;
    private RelativeLayout rl_title_login;
    private Button btn_login_register ;
    private TextView tv_my_count ;
    private TextView tv_shopping_cart ;
    private TextView tv_shopping_hint ;
    private TextView tv_my_order ;
    private RelativeLayout rl_my_sc;
    private RelativeLayout rl_my_dz;
    private RelativeLayout rl_my_kf;
    private RelativeLayout rl_my_xx;
    private RelativeLayout rl_my_sh;
    private RelativeLayout rl_my_sz;
    private RelativeLayout rl_my_gy;


    @Override
    protected int getResRootViewId() {
        return R.layout.fragment_me;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void init( ) {

        rl_title_no_login = (RelativeLayout)mView.findViewById(R.id.layout_title_no_login);
        rl_title_login = (RelativeLayout)mView.findViewById(R.id.layout_title_login);
        btn_login_register = (Button)mView.findViewById(R.id.login_register);
        tv_accountNick = (TextView)mView.findViewById(R.id.tv_accountNick);
        tv_account = (TextView)mView.findViewById(R.id.tv_account);
        iv_user_avatar = (CircleImageView)mView.findViewById(R.id.iv_user_avatar);

        tv_my_count = (TextView)mView.findViewById(R.id.tv_my_count);
        tv_shopping_cart = (TextView)mView.findViewById(R.id.tv_shopping_cart);
        tv_shopping_hint = (TextView)mView.findViewById(R.id.tv_shopping_hint);
        tv_my_order = (TextView)mView.findViewById(R.id.tv_my_order);
        rl_my_sc = (RelativeLayout)mView.findViewById(R.id.rl_my_sc);
        rl_my_dz = (RelativeLayout)mView.findViewById(R.id.rl_my_dz);
        rl_my_kf = (RelativeLayout)mView.findViewById(R.id.rl_my_kf);
        rl_my_xx = (RelativeLayout)mView.findViewById(R.id.rl_my_xx);
        rl_my_sh = (RelativeLayout)mView.findViewById(R.id.rl_my_sh);
        rl_my_sz = (RelativeLayout)mView.findViewById(R.id.rl_my_sz);
        rl_my_gy = (RelativeLayout)mView.findViewById(R.id.rl_my_gy);
        initUser();
        addListener() ;
        //rl_title_login.setVisibility(View.GONE);
        /*cnToolbar = (CnToolbar) mView.findViewById(R.id.toolBar) ;
        loginOut = (Button) mView.findViewById(R.id.loginOut) ;
        my_consignee = (TextView)mView.findViewById(R.id.my_consignee) ;
        my_favorite = (TextView) mView.findViewById(R.id.my_favorite);
        my_orderList = (TextView) mView.findViewById(R.id.my_list) ;

        initPbtoolbar() ;
        addListener() ;*/
    }

    private void initUser(){

        UserInfo user = MyApplicationInfo.getInstance().getUser() ;
        if(user != null){
            rl_title_no_login.setVisibility(View.GONE);
            rl_title_login.setVisibility(View.VISIBLE);
            iv_user_avatar.setImageResource(R.drawable.icon_header);
            tv_accountNick.setText("若一");
            tv_account.setText(user.getUser_name());
        }
        else{
            rl_title_no_login.setVisibility(View.VISIBLE);
            rl_title_login.setVisibility(View.GONE);
        }
        /*cnToolbar.setUserPhotoClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , LoginActivity.class) ;
                startActivityWithLogin(intent , true , MyApplication.START_FOR_RESULT);
            }
        });

        User user = MyApplication.getInstance().getUser() ;
        showUser(user) ;*/

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        //User user = MyApplication.getInstance().getUser() ;
        //showUser(user) ;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void showUser(UserInfo user){
        /*if (user != null){
            cnToolbar.setUserPhotoIcon(this.getActivity() , user.getLogo_url() , R.drawable.default_head );
            cnToolbar.setUserNameText(user.getUsername());
            loginOut.setVisibility(View.VISIBLE);
            cnToolbar.setUserClickable(false);

        }else {

            cnToolbar.setUserNameText("点击登录");
            cnToolbar.setUserPhotoIcon(getContext() , R.drawable.default_head);
            loginOut.setVisibility(View.GONE);
            cnToolbar.setUserClickable(true);
        }*/
    }

    private void addListener(){

        tv_accountNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        btn_login_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity() , LoginActivity.class) ;
                startActivityWithLogin(intent , true , MyApplication.START_FOR_RESULT);
                initUser();
            }
        });

        tv_my_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_shopping_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_my_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_my_sc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_my_dz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_my_kf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_my_xx.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_my_sh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_my_sz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rl_my_gy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        /*my_orderList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityWithLogin(new Intent(getContext() , MyOrderActivity.class) , true , MyApplication.START_NO_RESULT);
            }
        });

        loginOut.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onClick(View v) {
                loginOut() ;
            }
        });

        my_consignee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityWithLogin(new Intent(getContext() , ShowConsigneeAdrActivity.class ) , true , MyApplication.START_FOR_RESULT );
            }
        });*/
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void loginOut(){

        /*MyApplication.getInstance().clearUser();
        User user = MyApplication.getInstance().getUser() ;
        showUser(user) ;*/
    }
}
