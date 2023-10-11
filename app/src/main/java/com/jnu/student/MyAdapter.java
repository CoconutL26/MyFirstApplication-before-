package com.jnu.student;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    @NonNull
    private List<Book> bookList;

    public MyAdapter(List<Book> bookList) {
        this.bookList = bookList;
    }

    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.bookCoverImageView.setImageResource(book.getCoverResource());
        holder.bookTitleTextView.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener{
        public ImageView bookCoverImageView;
        public TextView bookTitleTextView;
        @Override
        public  void  onCreateContextMenu(ContextMenu menu,View v,
                                          ContextMenu.ContextMenuInfo menuInfo){
            menu.setHeaderTitle("具体操作");
            menu.add(0,0, Menu.NONE,"添加");
            menu.add(0,1, Menu.NONE,"删除");
            menu.add(0,2, Menu.NONE,"修改");
        }

        public ViewHolder(View itemView) {
            super(itemView);
            bookCoverImageView = itemView.findViewById(R.id.image_view_book_cover);
            bookTitleTextView = itemView.findViewById(R.id.text_view_book_title);
        }



    }
}
