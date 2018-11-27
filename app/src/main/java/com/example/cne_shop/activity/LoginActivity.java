package com.example.cne_shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cne_shop.R;
import com.example.cne_shop.base.BaseActivity;
import com.example.cne_shop.contents.Contents;
import com.example.cne_shop.fragment.AccountPwdFragment;
import com.example.cne_shop.fragment.SmsFragment;

import butterknife.BindView;

/**
 * Created by 博 on 2017/8/25.
 */

public class LoginActivity extends BaseActivity {

    private TextView tv_Login;
    private TextView tv_Register;
    private ImageView iv_back;
    @BindView(R.id.tv_account_password)
    TextView tv_Account_password ;
    @BindView(R.id.view_account_password)
    View view_Account_password ;
    @BindView(R.id.tv_sms)
    TextView tv_Sms ;
    @BindView(R.id.view_sms)
    View view_Sms ;
    private FrameLayout mFrameLayout1;
    private AccountPwdFragment mAccountPwdFrag;
    private SmsFragment mSmsFrag;
    //private FragmentTransaction mFragTran;
    /*@BindView(R.id.toolBar)
    CnToolbar cnToolbar ;
    @BindView(R.id.button_log)
    Button login_button ;
    @BindView(R.id.forgetPass)
    TextView forget_button ;
    @BindView(R.id.register)
    TextView register_button ;
    @BindView(R.id.userId)
    MyEditText phone ;
    @BindView(R.id.password)
    MyEditText password ;
    OkhttpHelper okhttpHelper;*/


    @Override
    protected int getContentViewId() {
        return R.layout.activity_login;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initView() {
        tv_Login= (TextView)findViewById(R.id.title_content);
        tv_Register= (TextView)findViewById(R.id.right_text);
        iv_back= (ImageView)findViewById(R.id.left_text);
        tv_Login.setText(R.string.login);
        tv_Login.setVisibility(View.VISIBLE);
        tv_Register.setText(R.string.register);
        tv_Register.setVisibility(View.VISIBLE);
        tv_Account_password.setTextColor(getResources().getColor(R.color.look_detail_bg));
        view_Account_password.setVisibility(View.VISIBLE);
        mFrameLayout1 = (FrameLayout)findViewById(R.id.fl_content);


        mAccountPwdFrag = new AccountPwdFragment();
        mSmsFrag = new SmsFragment();
        FragmentTransaction mFragTran = getSupportFragmentManager().beginTransaction();
        mFragTran.add(R.id.fl_content, mAccountPwdFrag);
        mFragTran.add(R.id.fl_content, mSmsFrag);
        mFragTran.hide(mSmsFrag);
        mFragTran.commit();

        //View layout = getLayoutInflater().inflate(R.layout.header_title, null);
        /*mViewLayout = View.inflate(getApplicationContext(), R.layout.header_title, null);
        tv_Login= (TextView)mViewLayout.findViewById(R.id.title_content);
        tv_Register= (TextView)mViewLayout.findViewById(R.id.right_text);
        tv_Login.setText(R.string.login);
        tv_Login.setVisibility(View.VISIBLE);
        tv_Register.setText(R.string.register);
        tv_Register.setVisibility(View.VISIBLE);*/
        /*phone.setInputType(InputType.TYPE_CLASS_NUMBER); //输入类型
        phone.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)}); //最大输入长度
        password.setTransformationMethod(PasswordTransformationMethod.getInstance()); //设置为密码输入框
        okhttpHelper = OkhttpHelper.getOkhttpHelper() ;

        initPbtoolbar() ;
        Text() ;*/
    }

    @Override
    protected void addListener() {
        iv_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //finish();
                Log.d("----" , "register------------------------------") ;
                LoginActivity.this.startActivity(new Intent(LoginActivity.this , RegisterActivity.class));
            }
        });

        tv_Account_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_Account_password.setTextColor(getResources().getColor(R.color.look_detail_bg));
                view_Account_password.setVisibility(View.VISIBLE);
                tv_Sms.setTextColor(getResources().getColor(R.color.text_gray));
                view_Sms.setVisibility(View.GONE);
                FragmentTransaction mFragTran = getSupportFragmentManager().beginTransaction();
                mFragTran.show(mAccountPwdFrag)
                        .hide(mSmsFrag)
                        .commit();
                mAccountPwdFrag.setName(Contents.USERNAME);
            }
        });

        tv_Sms.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tv_Account_password.setTextColor(getResources().getColor(R.color.text_gray));
                view_Account_password.setVisibility(View.GONE);
                tv_Sms.setTextColor(getResources().getColor(R.color.look_detail_bg));
                view_Sms.setVisibility(View.VISIBLE);
                FragmentTransaction mFragTran = getSupportFragmentManager().beginTransaction();
                mFragTran.hide(mAccountPwdFrag)
                        .show(mSmsFrag)
                        .commit();
                mSmsFrag.setName(Contents.USERNAME);
            }
        });

        /*login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin(v) ;
            }
        });

        forget_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("----" , "register------------------------------") ;
                LoginActivity.this.startActivity(new Intent(LoginActivity.this , RegisterActivity.class));
            }
        });*/
    }

    @Override
    protected void beforLayout() {

    }

    private void closeKeyMode(){
        //InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
        //imm.hideSoftInputFromWindow(password.getWindowToken(),0);
    }


    private void initPbtoolbar(){

        /*cnToolbar.setLeftButtonIcOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                closeKeyMode();
                finish();
            }
        });*/
    }

    private void doLogin( View v ){

        /*String phoneNum = phone.getText().toString() ;

        if ( phoneNum == null ){
            Toast.makeText( v.getContext() , "请输入手机号码" , Toast.LENGTH_SHORT).show();
            return;
        }else if(phoneNum.length() != 11){
            Toast.makeText( v.getContext() , "请输入正确的手机号码" , Toast.LENGTH_SHORT).show();
            return;
        }

        String pwd = password.getText().toString() ;
        if (pwd == null){
            Toast.makeText( v.getContext() , "请输入密码" , Toast.LENGTH_SHORT).show() ;
            return;
        }

        String uri = Contents.API.LOGIN ;
        Map< String , String > params = new HashMap<String, String>() ;
        params.put("phone" , phoneNum ) ;
        params.put("password" , DESUtil.encode(Contents.DES_KEY , pwd)) ;

        okhttpHelper.doPost(uri, new loadingSpotsDialog<LoginRespMsg<User>>(LoginActivity.this ) {
            @Override
            public void onErroe(Response response, int responseCode, Exception e) throws IOException {
                this.closeSpotsDialog();
            }

            @Override
            public void callBackSucces(Response response, LoginRespMsg<User> userLoginRespMsg) throws IOException {
                this.closeSpotsDialog();

                if(userLoginRespMsg.getStatus() == 1){

                    MyApplication.getInstance().putUser(userLoginRespMsg.getData() , userLoginRespMsg.getTocken());
                    closeKeyMode() ;

                    if (null == MyApplication.getInstance().getIntent()){
                        setResult(RESULT_OK);
                        finish();
                    }else {
                        MyApplication.jumpToTargetoActivity(LoginActivity.this);
                        finish();
                    }

                }else {
                    showLoginErrorMsg() ;
                    phone.setText("");
                    password.setText("");
                }
            }
        }, params);*/
    }

    public void Text(){
        //phone.setText("15829238397");
        //password.setText("bb001314");
    }

    private void showLoginErrorMsg(){
        closeKeyMode();
        Toast.makeText(this, "密码错误" , Toast.LENGTH_LONG).show();
    }


}
