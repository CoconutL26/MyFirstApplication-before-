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

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
    @NonNull
    private ArrayList<Book> bookArrayList;

    public MyAdapter(ArrayList<Book> bookArrayList) {
        this.bookArrayList = bookArrayList;
    }

    private int position;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
    @Override
    public MyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.itemlayout, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyAdapter.ViewHolder holder, int position) {
        Book book = bookArrayList.get(position);
        holder.bookCoverImageView.setImageResource(book.getCoverResource());
        holder.bookTitleTextView.setText(book.getTitle());
    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
            implements View.OnCreateContextMenuListener{
        public ImageView bookCoverImageView;
        public TextView bookTitleTextView;
        @Override
        public  void  onCreateContextMenu(ContextMenu menu,View v,
                                          ContextMenu.ContextMenuInfo menuInfo){
            menu.setHeaderTitle("具体操作");
            menu.add(0,0, this.getAdapterPosition(),"添加");
            menu.add(0,1, this.getAdapterPosition(),"删除");
            menu.add(0,2, this.getAdapterPosition(),"修改");
        }

        public ViewHolder(View itemView) {
            super(itemView);
            bookCoverImageView = itemView.findViewById(R.id.image_view_book_cover);
            bookTitleTextView = itemView.findViewById(R.id.text_view_book_title);
            itemView.setOnCreateContextMenuListener(this);
        }



    }
}
