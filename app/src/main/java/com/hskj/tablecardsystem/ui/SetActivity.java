package com.hskj.tablecardsystem.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.hskj.tablecardsystem.R;
import com.hskj.tablecardsystem.control.CodeConstants;
import com.hskj.tablecardsystem.utils.SharedPreferenceTools;

public class SetActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText ipET,roomNumET,tableNumET,nameEditET,textSizeET,accountET,passwordET;
    private Button confirmBtn,cancleBtn,setBtn;
    private String ip,roomNum,tableNum,name,textSize,account,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // 去除通知栏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        // 去除标题栏
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_set);
        this.setFinishOnTouchOutside(false);//取消点击外面隐藏activity
        ipET = findViewById(R.id.service_ip_edit_text);
        accountET  = findViewById(R.id.account_edit_text);
        passwordET  = findViewById(R.id.password_edit_text);
        roomNumET  = findViewById(R.id.roomNum_edit_text);
        tableNumET  = findViewById(R.id.tableNum_edit_text);
        nameEditET  = findViewById(R.id.name_edit_text);
        textSizeET  = findViewById(R.id.size_edit_text);

        account = (String) SharedPreferenceTools.getValueFromSP(CodeConstants.SERVICE_ACCOUNT,"");
        password = (String) SharedPreferenceTools.getValueFromSP(CodeConstants.SERVICE_PASSWORD,"");
        ip = (String) SharedPreferenceTools.getValueFromSP(CodeConstants.SERVICE_IP,"");
        roomNum = (String) SharedPreferenceTools.getValueFromSP(CodeConstants.ROOM_NUM,"");
        tableNum = (String) SharedPreferenceTools.getValueFromSP(CodeConstants.TABLE_NUM,"");
        name = (String) SharedPreferenceTools.getValueFromSP(CodeConstants.PERSON_NAME,"");
        textSize = (String) SharedPreferenceTools.getValueFromSP(CodeConstants.TEXT_SIZE,"200");

        ipET.setText(ip);
        accountET.setText(account);
        passwordET.setText(password);
        roomNumET.setText(roomNum);
        tableNumET.setText(tableNum);
        nameEditET.setText(name);
        textSizeET.setText(textSize);

        cancleBtn  = findViewById(R.id.cancel_btn);
        confirmBtn  = findViewById(R.id.confirm_btn);
        setBtn  = findViewById(R.id.setting_btn);
        cancleBtn.setOnClickListener(this);
        confirmBtn.setOnClickListener(this);
        setBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.cancel_btn:
                finish();
                break;
            case R.id.confirm_btn:
                Intent intent = new Intent();
                if(TextUtils.isEmpty(ipET.getText())){
                    Toast.makeText(this,"请输入服务器IP",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(TextUtils.isEmpty(accountET.getText())){
                    Toast.makeText(this,"请输入服务器账号",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(TextUtils.isEmpty(passwordET.getText())){
                    Toast.makeText(this,"请输入服务器密码",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(TextUtils.isEmpty(roomNumET.getText())){
                    Toast.makeText(this,"请输入会议室编号",Toast.LENGTH_SHORT).show();
                    break;
                }
                if(TextUtils.isEmpty(tableNumET.getText())){
                    Toast.makeText(this,"请输入桌牌号",Toast.LENGTH_SHORT).show();
                    break;
                }

                SharedPreferenceTools.putValueIntoSP(CodeConstants.SERVICE_IP,ipET.getText());
                SharedPreferenceTools.putValueIntoSP(CodeConstants.SERVICE_ACCOUNT,accountET.getText());
                SharedPreferenceTools.putValueIntoSP(CodeConstants.SERVICE_PASSWORD,passwordET.getText());

                intent.putExtra("roomNumber",roomNumET.getText()+"");
                SharedPreferenceTools.putValueIntoSP(CodeConstants.ROOM_NUM,roomNumET.getText());

                intent.putExtra("tableNumber",tableNumET.getText()+"");
                SharedPreferenceTools.putValueIntoSP(CodeConstants.TABLE_NUM,tableNumET.getText());

                if(TextUtils.isEmpty(textSizeET.getText())){
                    intent.putExtra("textSize",200+"");//
                    SharedPreferenceTools.putValueIntoSP(CodeConstants.TEXT_SIZE,"200");
                }else{
                    intent.putExtra("textSize",textSizeET.getText()+"");
                    SharedPreferenceTools.putValueIntoSP(CodeConstants.TEXT_SIZE,textSizeET.getText());
                }
                intent.putExtra("name",nameEditET.getText()+"");
                SharedPreferenceTools.putValueIntoSP(CodeConstants.PERSON_NAME,nameEditET.getText());
                setResult(1,intent);
                finish();
                break;
            case R.id.setting_btn:
                Intent intent2  = new Intent(Settings.ACTION_SETTINGS);
                startActivity(intent2);
                break;
            default:
                break;
        }
    }
}
