package com.example.cne_shop.fragment;

import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.cne_shop.R;
import com.example.cne_shop.application.MyApplicationInfo;
import com.example.cne_shop.bean.UserInfo;
import com.example.cne_shop.contents.Contents;
import com.example.cne_shop.okhttp.OkhttpHelper;
import com.example.cne_shop.okhttp.loadingSpotsDialog;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by 博 on 2017/8/21.
 */

public class AccountPwdFragment extends Fragment  {

    private View mView;
    private EditText et_Phone_number ;
    private ImageView iv_Clear_phone ;
    private EditText et_Password ;
    private ImageView iv_Delete_password ;
    private ImageView iv_Show_hide_psw ;
    private boolean m_bShow = false;
    private boolean m_isPhone = false;
    private boolean m_isPwd = false;
    Button btn_Login ;
    OkhttpHelper okhttpHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_password_login, container, false);
        et_Phone_number = (EditText)mView.findViewById(R.id.phone_number);
        iv_Clear_phone = (ImageView)mView.findViewById(R.id.clear_phone);
        et_Password = (EditText)mView.findViewById(R.id.password);
        iv_Delete_password = (ImageView)mView.findViewById(R.id.delete_password);
        iv_Show_hide_psw = (ImageView)mView.findViewById(R.id.show_hide_psw);
        btn_Login = (Button)mView.findViewById(R.id.login);
        et_Phone_number.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        et_Password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        et_Phone_number.setText("13601948460");
        et_Password.setText("123456");
        et_Phone_number.setSelection(et_Phone_number.getText().length());
        btn_Login.setEnabled(true);
        okhttpHelper = OkhttpHelper.getOkhttpHelper() ;
        AddListener();
        return mView;
    }

    private void AddListener(){
        et_Phone_number.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s_Phone_number = et_Phone_number.getText().toString().trim();
                Contents.USERNAME = s_Phone_number;
                if(TextUtils.isEmpty(s_Phone_number)){
                    iv_Clear_phone.setVisibility(View.GONE);
                    m_isPhone = false;
                    if(!m_isPwd){
                        btn_Login.setEnabled(false);
                    }
                }
                else{
                    iv_Clear_phone.setVisibility(View.VISIBLE);
                    m_isPhone = true;
                    if(m_isPwd){
                        btn_Login.setEnabled(true);
                    }
                }
            }
        });

        et_Password.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s_Password = et_Password.getText().toString().trim();
                if(TextUtils.isEmpty(s_Password)){
                    iv_Delete_password.setVisibility(View.GONE);
                    m_isPwd = false;
                    if(!m_isPhone){
                        btn_Login.setEnabled(false);
                    }
                }
                else{
                    iv_Delete_password.setVisibility(View.VISIBLE);
                    m_isPwd = true;
                    if(m_isPhone){
                        btn_Login.setEnabled(true);
                    }
                }
            }
        });

        iv_Clear_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_Phone_number.setText("");
            }
        });

        iv_Delete_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_Password.setText("");
            }
        });

        iv_Show_hide_psw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(m_bShow){
                    iv_Show_hide_psw.setImageResource(R.drawable.icon_eye_disable);
                    et_Password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    m_bShow = false;
                }
                else{
                    iv_Show_hide_psw.setImageResource(R.drawable.icon_eye);
                    et_Password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    m_bShow = true;
                }

            }
        });

        btn_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DoLogin();
            }
        });

        et_Phone_number.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    String s_Phone_number = et_Phone_number.getText().toString().trim();
                    if(TextUtils.isEmpty(s_Phone_number)){
                        iv_Clear_phone.setVisibility(View.GONE);
                    }
                    else{
                        iv_Clear_phone.setVisibility(View.VISIBLE);
                    }
                } else {
                    // 此处为失去焦点时的处理内容
                    iv_Clear_phone.setVisibility(View.GONE);
                }
            }
        });

        et_Password.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    String s_Password = et_Password.getText().toString().trim();
                    if(TextUtils.isEmpty(s_Password)){
                        iv_Delete_password.setVisibility(View.GONE);
                    }
                    else{
                        iv_Delete_password.setVisibility(View.VISIBLE);
                    }
                } else {
                    // 此处为失去焦点时的处理内容
                    iv_Delete_password.setVisibility(View.GONE);
                }
            }
        });

    }

    public void setName(String name){
        et_Phone_number.setText(name);
        et_Phone_number.requestFocus();
        et_Phone_number.setSelection(et_Phone_number.getText().length());
    }

    private void DoLogin(){
        String s_Phone_number = et_Phone_number.getText().toString().trim();
        String s_Password = et_Password.getText().toString().trim();
        if(TextUtils.isEmpty(s_Phone_number)){
            Toast.makeText( getActivity() , "请输入您的账号" , Toast.LENGTH_SHORT).show();
            return;
        }
        else if(s_Phone_number.length() != 11){
            Toast.makeText( getActivity() , "请输入正确的账号" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(s_Password)){
            Toast.makeText( getActivity() , "请输入密码" , Toast.LENGTH_SHORT).show();
            return;
        }
        else if(s_Password.length() < 6){
            Toast.makeText( getActivity() , "请输入6位以上密码" , Toast.LENGTH_SHORT).show();
            return;
        }
        String uri = Contents.API.LOGIN ;
        Map< String , String > params = new HashMap<String, String>() ;
        params.put("username" , s_Phone_number ) ;
        params.put("password" , s_Password) ;
        params.put("loginType" , "" + 1) ;
        okhttpHelper.doPost(uri, new loadingSpotsDialog<String>(getActivity()) {
            @Override
            public void onErroe(Response response, int responseCode, Exception e) throws IOException {
                Log.d("----" , "----------------登录失败111--------------") ;
                this.closeSpotsDialog();
            }

            @Override
            public void callBackSucces(Response response, String Msg) throws IOException {
                this.closeSpotsDialog();
                Log.d("----" , "----------------登录222--------------") ;
                JSONObject object= JSONObject.parseObject(Msg);
                int resultCode=object.getInteger("resultCode");
                if(resultCode == 1000) {
                    String data=object.getString("data");
                    UserInfo objBean = JSONObject.parseObject(data, UserInfo.class);
                    Log.d("----" , "----------------登录成功444 --------------" + objBean.getUser_id()) ;
                    Log.d("----" , "----------------登录成功555 --------------" + objBean.getUt()) ;
                    MyApplicationInfo.getInstance().putUser(objBean , objBean.getUt());
                    closeKeyMode() ;
                    if (null == MyApplicationInfo.getInstance().getIntent()){
                        //setResult(RESULT_OK);
                        getActivity().finish();
                    }else {
                        MyApplicationInfo.jumpToTargeActivity(getActivity());
                        getActivity().finish();
                    }
                }
                else {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            showLoginErrorMsg() ;
                        }
                    });

                }
            }

            @Override
            public void onTokenError(Response response, int responseCode) {
                super.onTokenError(response, responseCode);
                Log.d("----" , "----------------登录失败333--------------") ;
                this.closeSpotsDialog();
            }
        } , params);
    }

    private void closeKeyMode(){
        InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        imm.hideSoftInputFromWindow(et_Password.getWindowToken(),0);
        imm.hideSoftInputFromWindow(et_Phone_number.getWindowToken(),0);
    }
    private void showLoginErrorMsg(){
        closeKeyMode();
        et_Password.setText("");
        Toast.makeText(getActivity(), "账号或者密码错误" , Toast.LENGTH_SHORT).show();
    }

}
