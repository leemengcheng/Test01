package com.example.cne_shop.fragment;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSONObject;
import com.example.cne_shop.R;
import com.example.cne_shop.contents.Contents;
import com.example.cne_shop.okhttp.OkhttpHelper;
import com.example.cne_shop.okhttp.loadingSpotsDialog;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 博 on 2017/8/21.
 */

public class SmsFragment extends Fragment  {

    private View mView;
    private TextView tv_GetVerify ;
    private ImageView iv_Delete_verify ;
    private EditText et_Verify_code ;
    private EditText et_Phone_number ;
    private ImageView iv_Clear_phone ;
    private boolean m_isPhone = false;
    private boolean m_isVerify = false;
    Button btn_Login ;
    private TimeCount mTime;
    OkhttpHelper okhttpHelper;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_sms, container, false);
        et_Phone_number = (EditText)mView.findViewById(R.id.phone_number);
        iv_Clear_phone = (ImageView)mView.findViewById(R.id.clear_phone);
        tv_GetVerify = (TextView)mView.findViewById(R.id.tv_get_verification);
        et_Verify_code = (EditText)mView.findViewById(R.id.password);
        iv_Delete_verify = (ImageView)mView.findViewById(R.id.delete_password);
        btn_Login = (Button)mView.findViewById(R.id.login);
        et_Phone_number.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        et_Verify_code.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        mTime = new TimeCount(60000, 1000);
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
                    tv_GetVerify.setEnabled(false);
                    iv_Clear_phone.setVisibility(View.GONE);
                    m_isPhone = false;
                    if(!m_isVerify){
                        btn_Login.setEnabled(false);
                    }
                }
                else{
                    tv_GetVerify.setEnabled(true);
                    iv_Clear_phone.setVisibility(View.VISIBLE);
                    m_isPhone = true;
                    if(m_isVerify){
                        btn_Login.setEnabled(true);
                    }
                }
            }
        });

        et_Verify_code.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String s_Verify_code = et_Verify_code.getText().toString().trim();
                if(TextUtils.isEmpty(s_Verify_code)){
                    iv_Delete_verify.setVisibility(View.GONE);
                    m_isVerify = false;
                    if(!m_isPhone){
                        btn_Login.setEnabled(false);
                    }
                }
                else{
                    iv_Delete_verify.setVisibility(View.VISIBLE);
                    m_isVerify = true;
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

        iv_Delete_verify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_Verify_code.setText("");
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

        et_Verify_code.setOnFocusChangeListener(new android.view.View.
                OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    // 此处为得到焦点时的处理内容
                    String s_Verify_code = et_Verify_code.getText().toString().trim();
                    if(TextUtils.isEmpty(s_Verify_code)){
                        iv_Delete_verify.setVisibility(View.GONE);
                    }
                    else{
                        iv_Delete_verify.setVisibility(View.VISIBLE);
                    }
                } else {
                    // 此处为失去焦点时的处理内容
                    iv_Delete_verify.setVisibility(View.GONE);
                }
            }
        });

        tv_GetVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVerifyCode();
            }
        });

    }


    private void getVerifyCode(){
        String s_Phone_number = et_Phone_number.getText().toString().trim();
        if(TextUtils.isEmpty(s_Phone_number)){
            Toast.makeText( getActivity() , "请输入您的账号" , Toast.LENGTH_SHORT).show();
            return;
        }
        else if(s_Phone_number.length() != 11){
            Toast.makeText( getActivity() , "请输入正确的账号" , Toast.LENGTH_SHORT).show();
            return;
        }
        String rule = "^1(3|5|7|8|4)\\d{9}" ;
        Pattern p = Pattern.compile(rule) ;
        Matcher m = p.matcher(s_Phone_number) ;

        if (!m.matches()){
            Toast.makeText(getActivity() , "请输入正确的账号" , Toast.LENGTH_SHORT).show();
            return;
        }
        mTime.start();

        String uri = Contents.API.SMS ;
        Map< String , String > params = new HashMap<String, String>() ;
        params.put("mobile" , s_Phone_number ) ;
        params.put("flag" , "" + 3) ;
        okhttpHelper.doPost(uri, new loadingSpotsDialog<String>(getActivity()) {
            @Override
            public void onErroe(Response response, int responseCode, Exception e) throws IOException {
                Log.d("----" , "----------------获取验证码111--------------") ;
                this.closeSpotsDialog();
            }

            @Override
            public void callBackSucces(Response response, String Msg) throws IOException {
                this.closeSpotsDialog();
                Log.d("----" , "----------------获取验证码222--------------") ;
                JSONObject object= JSONObject.parseObject(Msg);
                int resultCode=object.getInteger("resultCode");
                if(resultCode == 1000 ) {
                    //String s_Message = Msg.getMessage();
                    Log.d("----" , "----------------获取验证码成功--------------") ;
                }
            }

            @Override
            public void onTokenError(Response response, int responseCode) {
                super.onTokenError(response, responseCode);
                Log.d("----" , "----------------获取验证码333--------------") ;
                this.closeSpotsDialog();
            }
        } , params);
    }

    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_GetVerify.setEnabled(false);
            et_Phone_number.setEnabled(false);
            iv_Clear_phone.setVisibility(View.GONE);
            tv_GetVerify.setText("重发(" + millisUntilFinished / 1000 + ")");
        }

        @Override
        public void onFinish() {
            tv_GetVerify.setText("重新发送");
            tv_GetVerify.setEnabled(true);
            et_Phone_number.setEnabled(true);
            iv_Clear_phone.setVisibility(View.VISIBLE);
        }
    }

    public void setName(String name){
        et_Phone_number.setText(name);
        et_Phone_number.requestFocus();
        et_Phone_number.setSelection(et_Phone_number.getText().length());
    }

    private void DoLogin(){
        String s_Phone_number = et_Phone_number.getText().toString().trim();
        String s_Verify_code = et_Verify_code.getText().toString().trim();
        if(TextUtils.isEmpty(s_Phone_number)){
            Toast.makeText( getActivity() , "请输入您的账号" , Toast.LENGTH_SHORT).show();
            return;
        }
        else if(s_Phone_number.length() != 11){
            Toast.makeText( getActivity() , "请输入正确的账号" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(s_Verify_code)){
            Toast.makeText( getActivity() , "请输入验证码" , Toast.LENGTH_SHORT).show();
            return;
        }
        else if(s_Verify_code.length() != 4){
            Toast.makeText( getActivity() , "请输入4位验证码" , Toast.LENGTH_SHORT).show();
            return;
        }

        String uri = Contents.API.LOGIN ;
        Map< String , String > params = new HashMap<String, String>() ;
        params.put("username" , s_Phone_number ) ;
        params.put("password" , s_Verify_code) ;
        params.put("loginType" , "" + 2) ;
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
                    //String s_Message = Msg.getMessage();
                    Log.d("----" , "----------------登录成功--------------") ;
                    /*String data=object.getString("data");
                    RegisterUser objBean = JSONObject.parseObject(data, RegisterUser.class);
                    //JSONObject object1= JSON.parseObject(data);
                    Log.d("----" , "----------------登录成功000 --------------" + objBean.getId()) ;
                    Log.d("----" , "----------------登录成功000 --------------" + objBean.getUser_name()) ;
                    Log.d("----" , "----------------登录成功000 --------------" + objBean.getMobile()) ;*/
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

}
