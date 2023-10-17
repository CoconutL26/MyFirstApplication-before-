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
    public static final int RESULT_CODE_SUCCESS = 666;
    private int position;
    private ImageView coverImageView;
    private EditText titleEditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addbook_layout);
        position= this.getIntent().getIntExtra("position",0);
        String title=this.getIntent().getStringExtra("title");
        // 初始化其他书籍信息的EditText
        EditText editTextTitle=findViewById(R.id.editText);
//        coverImageView = findViewById(R.id.image_cover);
//        titleEditText = findViewById(R.id.editText);
        if(null!=title)
        {
            editTextTitle.setText(title);
        }
        // 设置添加按钮的点击事件
        Button addButton = findViewById(R.id.button_add);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 创建一个包含书籍信息的Intent
                Intent intent = new Intent();
                Bundle bundle=new Bundle();
                bundle.putString("title",editTextTitle.getText().toString());
                bundle.putInt("position",position);

                intent.putExtras(bundle);
                setResult(RESULT_CODE_SUCCESS,intent);
                AddBook.this.finish();
            }
        });

        // 设置返回按钮的点击事件
        Button backButton = findViewById(R.id.button_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setResult(Activity.RESULT_CANCELED);
                finish();
            }
        });
    }
}
