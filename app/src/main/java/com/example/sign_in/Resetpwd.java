package com.example.sign_in;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Resetpwd extends AppCompatActivity {
    private EditText mPasswd_old;                        //密码编辑
    private EditText mPasswd_new;                        //密码编辑
    private EditText mPasswdCheck;                       //密码编辑
    private Button resetpwd_btn_sure;
    private String passwd_old,passwd_new,resetpwd_check;
    private String user_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.resetpwd);
        //设置此界面为竖屏
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        init();
    }

    private void init() {
        mPasswd_old = (EditText) findViewById(R.id.resetpwd_edit_pwd_old);
        mPasswd_new = (EditText) findViewById(R.id.resetpwd_edit_pwd_new);
        mPasswdCheck = (EditText) findViewById(R.id.resetpwd_edit_pwd_check);
        resetpwd_btn_sure = (Button) findViewById(R.id.resetpwd_btn_sure);
        Button resetpwd_btn_cancel = (Button) findViewById(R.id.resetpwd_btn_cancel);
        resetpwd_btn_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Resetpwd.this.finish();
            }
        });

        resetpwd_btn_sure.setOnClickListener(new View.OnClickListener() {

        @Override
            public void onClick(View v) {
                getEditString();
                if (TextUtils.isEmpty(passwd_old)) {
                    Toast.makeText(Resetpwd.this, "请输入原始密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (equals(readPasswd())) {
                    Toast.makeText(Resetpwd.this, "输入的密码与原始密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else if (equals(readPasswd())) {
                    Toast.makeText(Resetpwd.this, "输入的新密码与原始密码不能一致", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(passwd_new)) {
                    Toast.makeText(Resetpwd.this, "请输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(passwd_new)) {
                    Toast.makeText(Resetpwd.this, "请再次输入新密码", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!passwd_new.equals(mPasswdCheck)) {
                    Toast.makeText(Resetpwd.this, "两次输入的新密码不一致", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Toast.makeText(Resetpwd.this, "新密码设置成功", Toast.LENGTH_SHORT).show();
                    //修改登录成功时保存在SharedPreferences中的密码
                    resetpwd(passwd_new);
                    Intent intent = new Intent(Resetpwd.this, LoginActivity.class);
                    startActivity(intent);
                    UserActivity.instance.finish();//关闭设置页
                    Resetpwd.this.finish();//关闭当前页面
                }
            }
        });
    }

    private void getEditString(){

        String passwd_old=mPasswd_old.getText().toString().trim();
        String passwd_new=mPasswd_new.getText().toString().trim();
        String passwdCheck=mPasswdCheck.getText().toString().trim();
        }
    /**
     * 修改登录成功时保存在SharedPreferences中的密码
     */
    private void resetpwd(String newPsw) {
        SharedPreferences sp = getSharedPreferences("login", 0);
        SharedPreferences.Editor editor = sp.edit();//获取编辑器
        editor.putString("passwd","");//保存新密码
        editor.commit();//提交修改
    }

    /**
     * 从SharedPreferences中读取原始密码
     * 密码和用户名作为键值对保存到一起，所以通过用户名读取密码
     * 用户名从AnalysisUtils工具类获取
     */
    private String readPasswd() {
        SharedPreferences sp = getSharedPreferences("login", 0);
        String passwd = sp.getString("passwd", "");
        return passwd;
    }
}