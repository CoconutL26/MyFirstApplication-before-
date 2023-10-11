package com.jnu.student;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView textView1 = findViewById(R.id.textView1);
        TextView textView2 = findViewById(R.id.textView2);
        Button button = findViewById(R.id.button);
        textView1.setText(getString(R.string.hello_world));
        textView2.setText(getString(R.string.jnu));

        button.setOnClickListener(v -> {
            String text1 = textView1.getText().toString();
            String text2 = textView2.getText().toString();

            textView1.setText(text2);
            textView2.setText(text1);

            Toast.makeText(MainActivity.this, "交换成功", Toast.LENGTH_SHORT).show();

            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
            builder.setMessage("交换成功")
                    .setPositiveButton("确定", (dialog, id) -> dialog.dismiss());
            AlertDialog dialog = builder.create();
            dialog.show();
        });
    }
}
