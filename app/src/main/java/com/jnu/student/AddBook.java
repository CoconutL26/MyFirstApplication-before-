package com.jnu.student;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class AddBook extends AppCompatActivity {
    private ImageView coverImageView;
    private EditText titleEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook_layout);

        // 初始化其他书籍信息的EditText
        coverImageView = findViewById(R.id.image_cover);
        titleEditText = findViewById(R.id.editText);

        // 设置添加按钮的点击事件
        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个包含书籍信息的Intent
                Intent resultIntent = new Intent();
                resultIntent.putExtra("title", titleEditText.getText().toString());

                // 设置结果码为RESULT_OK，表示成功添加书籍
                setResult(Activity.RESULT_OK, resultIntent);

                // 结束当前Activity，返回到上一个Activity
                finish();
            }
        });

        // 设置返回按钮的点击事件
        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
}
