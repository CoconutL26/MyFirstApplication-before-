package com.jnu.student;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
//        TextView textView = findViewById(R.id.text_vciew_hellow_world);
//        textView.setText(getString(R.string.hello_world));
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        MyAdapter adapter = new MyAdapter(getListBooks());
        recyclerView.setAdapter(adapter);
    }

    public List<Book> getListBooks(){
        List<Book> BookList = new ArrayList<>();
        BookList.add(new Book("软件项目管理案例教程（第四版）",R.drawable.book_1));
        BookList.add(new Book("创新工程实践",R.drawable.book_2));
        BookList.add(new Book("信息安全教学基础（第二版）",R.drawable.book_3));
        return BookList;
    }



}



