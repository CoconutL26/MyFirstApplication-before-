package com.jnu.student;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.jnu.student.data.DataSaver;
import com.jnu.student.Book;
import com.jnu.student.R;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LibraryListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LibraryListFragment extends Fragment {

    public static final int MENU_ID_ADD = 1;
    public static final int MENU_ID_UPDATE = 2;
    public static final int MENU_ID_DELETE = 3;
    private ArrayList<Book> books ;
    private LibraryListFragment.MyAdapter myAdapter;

    //添加书籍启动器
    private ActivityResultLauncher<Intent> addBookLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            ,result -> {
                if(null!=result){
                    Intent intent=result.getData();
                    if(result.getResultCode()==AddBook.RESULT_CODE_SUCCESS)
                    {
                        Bundle bundle=intent.getExtras();
                        String title= bundle.getString("title");
                        int position=bundle.getInt("position");
                        books.add(position, new Book(title,R.drawable.book_2) );
                        new DataSaver().Save(this.getContext(),books);
                        myAdapter.notifyItemInserted(position);
                    }
                }
            });
    //修改书籍启动器
    private ActivityResultLauncher<Intent> editBookLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult()
            ,result -> {
                if(null!=result){
                    Intent intent=result.getData();
                    if(result.getResultCode()==AddBook.RESULT_CODE_SUCCESS)
                    {
                        Bundle bundle=intent.getExtras();
                        String title= bundle.getString("title");
                        int position=bundle.getInt("position");
                        books.get(position).setTitle(title);
                        new DataSaver().Save(this.getContext(),books);
                        myAdapter.notifyItemChanged(position);
                    }
                }
            });
    public LibraryListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @return A new instance of fragment LibraryListFragment.
     */
    public static LibraryListFragment newInstance() {
        LibraryListFragment fragment = new LibraryListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_library_list, container, false);
        RecyclerView recyclerViewMain = rootView.findViewById(R.id.recyclerview_main);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this.getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMain.setLayoutManager(linearLayoutManager);

        DataSaver dataSaver = new DataSaver();
        books = dataSaver.Load(this.getContext());

        if (0 == books.size()) {
            books.add(new Book("软件项目管理案例教程（第四版）", R.drawable.book_1));
            books.add(new Book("创新工程实践", R.drawable.book_2));
            books.add(new Book("信息安全数学基础（第二版）", R.drawable.book_3));
        }
        myAdapter = new MyAdapter(books);
        recyclerViewMain.setAdapter(myAdapter);
        return rootView;
    }



    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ID_ADD:

                Intent intent = new Intent(this.getContext(), AddBook.class);
                intent.putExtra("position",item.getOrder());
                addBookLauncher.launch(intent);
                break;
            case MENU_ID_UPDATE:
                Intent intentUpdate = new Intent(this.getContext(), AddBook.class);
                Book Book= books.get(item.getOrder());
                intentUpdate.putExtra("name",Book.getTitle());
                intentUpdate.putExtra("position",item.getOrder());
                editBookLauncher.launch(intentUpdate);
                break;
            case MENU_ID_DELETE:
                AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
                builder.setTitle("Delete Data");
                builder.setMessage("Are you sure you want to delete this data?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        books.remove(item.getOrder());
                        new DataSaver().Save(LibraryListFragment.this.getContext(),books);
                        myAdapter.notifyItemRemoved(item.getOrder());
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                builder.create().show();
                break;

        }
        return super.onContextItemSelected(item);
    }

    public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        @NonNull
        private ArrayList<Book> bookArrayList;

        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {

            private final TextView bookTitleTextView;
            private final ImageView bookCoverImageView;

            public ViewHolder(View itemView) {
                super(itemView);
                bookCoverImageView = itemView.findViewById(R.id.image_view_book_cover);
                bookTitleTextView = itemView.findViewById(R.id.text_view_book_title);
                itemView.setOnCreateContextMenuListener(this);
            }

            public TextView getTextViewTitle() {
                return bookTitleTextView;
            }

            public ImageView getImageViewImage() {
                return bookCoverImageView;
            }

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0,MENU_ID_ADD,getAdapterPosition(),"添加 ");
                contextMenu.add(0,MENU_ID_UPDATE,getAdapterPosition(),"修改");
                contextMenu.add(0,MENU_ID_DELETE,getAdapterPosition(),"删除 ");
            }
        }
        public MyAdapter(ArrayList<Book> bookArrayList) {

            this.bookArrayList = bookArrayList;
        }
        // Create new views (invoked by the layout manager)
        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.itemlayout, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            viewHolder.getTextViewTitle().setText(bookArrayList.get(position).getTitle());
            viewHolder.getImageViewImage().setImageResource(bookArrayList.get(position).getCoverResource());
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return bookArrayList.size();
        }

    }

}