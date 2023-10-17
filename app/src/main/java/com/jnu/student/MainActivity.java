package com.jnu.student;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private ActivityResultLauncher<Intent> addBookLauncher;
    private ActivityResultLauncher<Intent> editBookLauncher;
    private List<Book> bookList = getListBooks();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
//        TextView textView = findViewById(R.id.text_vciew_hellow_world);
//        textView.setText(getString(R.string.hello_world));
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLongClickable(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new MyAdapter(bookList);
        recyclerView.setAdapter(adapter);

        registerForContextMenu(recyclerView);
        //添加书籍的启动器
        addBookLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        if (data != null) {
                            String title = data.getStringExtra("title");
                            bookList.add(new Book(title, R.drawable.book_2));
                            adapter.notifyItemInserted(bookList.size());
                        }
                    }
                    else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        //取消操作
                    }
                }
        );

        //修改书籍的启动器
        editBookLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    if (result.getResultCode() == Activity.RESULT_OK)
                    {
                        Intent data = result.getData();
                        if (data != null) {
                            String title = data.getStringExtra("title");
                            int coverResourceId = data.getIntExtra("cover",R.drawable.book_2);
                            int id = data.getIntExtra("id",0);
                            bookList.set(id, new Book(title, coverResourceId));
                            adapter.notifyItemChanged(id);
                        }
                    }
                    else if (result.getResultCode() == Activity.RESULT_CANCELED) {
                        //取消操作
                    }
                }
        );
    }

    @Override
    public boolean onContextItemSelected(MenuItem item){
//        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo) item.getMemuInfo();
        Toast.makeText(this,"clicked"+item.getOrder(),Toast.LENGTH_SHORT).show();
        switch (item.getItemId()){
            case 0:
                // add
                Intent addIntent = new Intent(MainActivity.this, AddBook.class);
                addBookLauncher.launch(addIntent);
                break;
            case 1:
                //delete
                bookList.remove(item.getOrder());
                adapter.notifyItemRemoved(item.getOrder());

                break;
            case 2:
                //edit
                Intent editIntent = new Intent(MainActivity.this, EditBook.class);
                editIntent.putExtra("id",item.getOrder());
                editIntent.putExtra("title", bookList.get(item.getOrder()).getTitle());
                editIntent.putExtra("cover", bookList.get(item.getOrder()).getCoverResource());
                editBookLauncher.launch(editIntent);
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }



    public List<Book> getListBooks(){
        List<Book> BookList = new ArrayList<>();
        BookList.add(new Book("软件项目管理案例教程（第四版）",R.drawable.book_1));
        BookList.add(new Book("创新工程实践",R.drawable.book_2));
        BookList.add(new Book("信息安全数学基础（第二版）",R.drawable.book_3));
        return BookList;
    }




}



