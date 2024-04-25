package com.example.choose;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void button_Click(View view) {
        String str = "";
        // 获取性别
        RadioButton boy = findViewById(R.id.rdbBoy);
        if (boy.isChecked())
            str += "男\n";
        RadioButton girl = findViewById(R.id.rdbGirl);
        if (girl.isChecked())
            str += "女\n";
        // 获取门票种类
        RadioGroup type = findViewById(R.id.rgType);
        int selectedTypeId = type.getCheckedRadioButtonId();
        if (selectedTypeId == -1) {
            // 如果未选择票种，显示错误信息
            Toast.makeText(this, "請選取票種", Toast.LENGTH_SHORT).show();
            return;
        }

        String ticketType;
        if (selectedTypeId == R.id.rdbAdult)
            ticketType = "全票";
        else if (selectedTypeId == R.id.rdbChild)
            ticketType = "兒童票";
        else
            ticketType = "學生票";

        str += ticketType + "\n";

        // 获取购买张数
        EditText quantityEditText = findViewById(R.id.etTicketQuantity);
        String quantityStr = quantityEditText.getText().toString();

        if (quantityStr.isEmpty()) {
            // 如果购买张数为空，显示错误信息
            Toast.makeText(this, "請輸入購買張數", Toast.LENGTH_SHORT).show();
            return;
        }

        int quantity = Integer.parseInt(quantityStr);

        // 计算金额
        int price = calculatePrice(selectedTypeId, quantity);
        str += "購買張數：" + quantity + " 張\n";
        str += "金額：" + price + " 元";

        TextView output = findViewById(R.id.lblOutput);
        output.setText(str);
    }

    private int calculatePrice(int radioButtonId, int quantity) {
        int pricePerTicket = 0;
        if (radioButtonId == R.id.rdbAdult) {
            pricePerTicket = 500;
        } else if (radioButtonId == R.id.rdbChild) {
            pricePerTicket = 250;
        } else if (radioButtonId == R.id.rdbStudent) {
            pricePerTicket = 400;
        }
        return pricePerTicket * quantity;
    }

    public void button2_Click(View view) {
        // 获取lblOutput的文本
        TextView output = findViewById(R.id.lblOutput);
        String outputText = output.getText().toString();

        // 创建Intent对象，指向ShowChoose应用程序中的MainActivity
        Intent intent = new Intent();
        intent.setClassName("com.example.showchoose", "com.example.showchoose.MainActivity");

        // 将数据放入Intent中
        intent.putExtra("outputText", outputText);

        // 启动ShowChoose应用程序中的MainActivity并传递Intent
        startActivity(intent);
    }
}