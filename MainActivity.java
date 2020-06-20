package com.example.two;//test

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {
    private Button btn;
    private TextView txt1,txt2;
    private TextView txt7;
    private EditText edit;
    /*声明变量*/



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt1=(TextView)findViewById(R.id.myTestView1);
        txt2=(TextView)findViewById(R.id.myTestView2);
        txt7=(TextView)findViewById(R.id.myTestView7);

        edit=(EditText)findViewById(R.id.myEditText);
        btn = (Button)findViewById(R.id.myButton1);
        btn.setOnClickListener(new btnclock());


    }
    class btnclock implements OnClickListener{
        public void onClick(View v){
            String passwd;
            passwd=edit.getText().toString();

            Intent intent=new Intent();
            intent.setClass(MainActivity.this, SecondActivity.class);
            EditText edit=(EditText)findViewById(R.id.myEditText);
            Bundle bundle=new Bundle();
            bundle.putString("text", edit.getText().toString());
            intent.putExtras(bundle);

            if(passwd.equals("123")) {
                startActivity(intent);
                txt7.setText("1");
            }
            else
                txt2.setText("签到码无效！！！");
        }
    }
}
//测试git;
