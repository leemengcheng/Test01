package com.example.cne_shop.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.example.cne_shop.R;
import com.example.cne_shop.application.MyApplication;
import com.example.cne_shop.base.BaseActivity;
import com.example.cne_shop.bean.RegisterUser;
import com.example.cne_shop.bean.User;
import com.example.cne_shop.bean.VerifyMsg;
import com.example.cne_shop.bean.msg.LoginRespMsg;
import com.example.cne_shop.contents.Contents;
import com.example.cne_shop.okhttp.OkhttpHelper;
import com.example.cne_shop.okhttp.loadingSpotsDialog;
import com.example.cne_shop.utils.JsonUtil;
import com.example.cne_shop.widget.CnToolbar;
import com.example.cne_shop.widget.MyEditText;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import butterknife.BindView;


/**
 * Created by 博 on 2017/7/24.
 */

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.title_content)
    TextView tv_Register ;
    @BindView(R.id.tv_get_verification)
    TextView tv_GetVerify ;
    @BindView(R.id.phone_number)
    EditText et_Phone_number ;
    @BindView(R.id.delete_phone)
    ImageView iv_Delete_phone ;
    @BindView(R.id.verify_code)
    EditText et_Verify_code ;
    @BindView(R.id.delete_verify)
    ImageView iv_Delete_verify ;
    @BindView(R.id.password)
    EditText et_Password ;
    @BindView(R.id.delete_password)
    ImageView iv_Delete_password ;
    @BindView(R.id.show_hide_psw)
    ImageView iv_Show_hide_psw ;
    @BindView(R.id.btn_register)
    Button btn_Register ;
    @BindView(R.id.left_text)
    ImageView iv_Left_text ;
    private boolean m_bShow = false;
    private TimeCount mTime;
    OkhttpHelper okhttpHelper;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_register;
    }

    @Override
    protected void initView() {
        tv_Register.setText(R.string.register);
        et_Phone_number.setFilters(new InputFilter[]{new InputFilter.LengthFilter(11)});
        et_Verify_code.setFilters(new InputFilter[]{new InputFilter.LengthFilter(4)});
        et_Phone_number.setText("13601948460");
        et_Password.setFilters(new InputFilter[]{new InputFilter.LengthFilter(16)});
        mTime = new TimeCount(60000, 1000);
        okhttpHelper = OkhttpHelper.getOkhttpHelper() ;
    }

    @Override
    protected void addListener(){
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
                if(TextUtils.isEmpty(s_Phone_number)){
                    tv_GetVerify.setEnabled(false);
                    iv_Delete_phone.setVisibility(View.GONE);
                }
                else{
                    tv_GetVerify.setEnabled(true);
                    iv_Delete_phone.setVisibility(View.VISIBLE);
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
                }
                else{
                    iv_Delete_verify.setVisibility(View.VISIBLE);
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
                }
                else{
                    iv_Delete_password.setVisibility(View.VISIBLE);
                }
            }
        });

        iv_Left_text.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_Delete_phone.setOnClickListener(new View.OnClickListener() {
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

        btn_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Register();
            }
        });


        tv_GetVerify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getVerifyCode();
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
                        tv_GetVerify.setEnabled(false);
                        iv_Delete_phone.setVisibility(View.GONE);
                    }
                    else{
                        tv_GetVerify.setEnabled(true);
                        iv_Delete_phone.setVisibility(View.VISIBLE);
                    }
                } else {
                    // 此处为失去焦点时的处理内容
                    iv_Delete_phone.setVisibility(View.GONE);
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


    class TimeCount extends CountDownTimer {

        public TimeCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {
            tv_GetVerify.setEnabled(false);
            et_Phone_number.setEnabled(false);
            iv_Delete_phone.setVisibility(View.GONE);
            tv_GetVerify.setText("重发(" + millisUntilFinished / 1000 + ")");
        }

        @Override
        public void onFinish() {
            tv_GetVerify.setText("重新发送");
            tv_GetVerify.setEnabled(true);
            et_Phone_number.setEnabled(true);
            iv_Delete_phone.setVisibility(View.VISIBLE);
        }
    }

    @Override
    protected void beforLayout() {

    }

    private void Register(){
        String s_Phone_number = et_Phone_number.getText().toString().trim();
        String s_Verify_code = et_Verify_code.getText().toString().trim();
        String s_Password = et_Password.getText().toString().trim();
        if(TextUtils.isEmpty(s_Phone_number)){
            Toast.makeText( getApplicationContext() , "请输入手机号码" , Toast.LENGTH_SHORT).show();
            return;
        }
        else if(s_Phone_number.length() != 11){
            Toast.makeText( getApplicationContext() , "请输入正确的手机号码" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(s_Verify_code)){
            Toast.makeText( getApplicationContext() , "请输入验证码" , Toast.LENGTH_SHORT).show();
            return;
        }
        else if(s_Verify_code.length() != 4){
            Toast.makeText( getApplicationContext() , "请输入4位数验证码" , Toast.LENGTH_SHORT).show();
            return;
        }
        if(TextUtils.isEmpty(s_Password)){
            Toast.makeText( getApplicationContext() , "请输入密码" , Toast.LENGTH_SHORT).show();
            return;
        }
        else if(s_Password.length() < 6){
            Toast.makeText( getApplicationContext() , "请输入6位以上密码" , Toast.LENGTH_SHORT).show();
            return;
        }
        String uri = Contents.API.REGISTER ;
        Map< String , String > params = new HashMap<String, String>() ;
        params.put("mobile" , s_Phone_number ) ;
        params.put("verify_code" , s_Verify_code) ;
        params.put("password" , s_Password) ;
        okhttpHelper.doPost(uri, new loadingSpotsDialog<String>(this) {
            @Override
            public void onErroe(Response response, int responseCode, Exception e) throws IOException {
                Log.d("----" , "----------------注册失败111--------------") ;
                this.closeSpotsDialog();
            }

            @Override
            public void callBackSucces(Response response, String Msg) throws IOException {
                this.closeSpotsDialog();
                Log.d("----" , "----------------注册222--------------") ;
                JSONObject object= JSONObject.parseObject(Msg);
                int resultCode=object.getInteger("resultCode");
                if(resultCode == 1000) {
                    //String s_Message = Msg.getMessage();
                    Log.d("----" , "----------------注册成功--------------") ;
                    String data=object.getString("data");
                    RegisterUser objBean = JSONObject.parseObject(data, RegisterUser.class);
                    //JSONObject object1= JSON.parseObject(data);
                    Log.d("----" , "----------------注册成功000 --------------" + objBean.getId()) ;
                    Log.d("----" , "----------------注册成功000 --------------" + objBean.getUser_name()) ;
                    Log.d("----" , "----------------注册成功000 --------------" + objBean.getMobile()) ;
                }
            }

            @Override
            public void onTokenError(Response response, int responseCode) {
                super.onTokenError(response, responseCode);
                Log.d("----" , "----------------注册失败333--------------") ;
                this.closeSpotsDialog();
            }
        } , params);
    }

    private void getVerifyCode(){

        String s_Phone_number = et_Phone_number.getText().toString().trim();
        String jsonString = "{\"resultCode\":1000,\"message\":null,\"error\":null,\"data\":{\"id\":10000,\"User_name\":\"13601948460\",\"message\":null,\"mobile\":\"13601948460\"},\"dataReserve1\":null,\"dataReserve2\":null}";
        JSONObject object= JSONObject.parseObject(jsonString);
        String resultCode=object.getString("resultCode");
        String data=object.getString("data");
        Log.d("----" , "----------------获取验证码000 --------------" + data) ;
        //RegisterUser objBean = data.readObject(BeginEndBean.class);
        RegisterUser objBean = JSONObject.parseObject(data, RegisterUser.class);
        //JSONObject object1= JSON.parseObject(data);
        Log.d("----" , "----------------获取验证码000 --------------" + objBean.getId()) ;
        Log.d("----" , "----------------获取验证码000 --------------" + objBean.getUser_name()) ;
        Log.d("----" , "----------------获取验证码000 --------------" + objBean.getMobile()) ;
        Log.d("----" , "----------------获取验证码000 --------------" + objBean.getMobile222()) ;
        Log.d("----" , "----------------获取验证码000 --------------" + resultCode) ;

        String user_json = JSONObject.toJSONString(objBean);
        Log.d("----" , "----------------获取验证码test --------------" + user_json) ;
        String token = "";
        String token1 = JSONObject.parseObject(token, String.class);
        Log.d("----" , "----------------获取验证码test2 --------------" + token1) ;
        if(TextUtils.isEmpty(s_Phone_number)){
            Toast.makeText( getApplicationContext() , "请输入手机号码" , Toast.LENGTH_SHORT).show();
            return;
        }
        else if(s_Phone_number.length() != 11){
            Toast.makeText( getApplicationContext() , "请输入正确的手机号码" , Toast.LENGTH_SHORT).show();
            return;
        }
        String rule = "^1(3|5|7|8|4)\\d{9}" ;
        Pattern p = Pattern.compile(rule) ;
        Matcher m = p.matcher(s_Phone_number) ;

        if (!m.matches()){
            Toast.makeText(getApplicationContext() , "请输入正确的手机号码" , Toast.LENGTH_SHORT).show();
            return;
        }
        btn_Register.setEnabled(true);
        mTime.start();

        String uri = Contents.API.SMS ;
        Map< String , String > params = new HashMap<String, String>() ;
        params.put("mobile" , s_Phone_number ) ;
        params.put("flag" , ""+1) ;
        if(uri.length() > 0)
            return;
        okhttpHelper.doPost(uri, new loadingSpotsDialog<String>(this) {
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
}

