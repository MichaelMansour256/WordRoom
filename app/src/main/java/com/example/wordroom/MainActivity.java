package com.example.wordroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private WordViewModel mWordViewModel;

    private RecyclerView mRecyclerView;
    private WordAdapter mWordAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton floatingActionButton=findViewById(R.id.btn_add_word);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,AddNewWordActivity.class);
                startActivityForResult(intent,1);
            }
        });
        RecyclerView recyclerView=findViewById(R.id.word_recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        mWordAdapter=new WordAdapter();
        recyclerView.setAdapter(mWordAdapter);

        mWordViewModel= ViewModelProviders.of(this).get(WordViewModel.class);
        mWordViewModel.getAllWords().observe(this, new Observer<List<Words>>() {
            @Override
            public void onChanged(List<Words> words) {
                //update ui -> recycler view
                mWordAdapter.setWords(words);
            }
        });
        mWordAdapter.onItemClickListener(new WordAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Words word) {
                Intent intent=new Intent(MainActivity.this,AddNewWordActivity.class);
                intent.putExtra(AddNewWordActivity.EXTRA_WORD,word.getWordName());
                intent.putExtra(AddNewWordActivity.EXTRA_MEANING,word.getWordMeaning());
                intent.putExtra(AddNewWordActivity.EXTRA_TYPE,word.getWordType());
                intent.putExtra(AddNewWordActivity.EXTRA_ID,word.getId());
                startActivity(intent);
            }
        });
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull @NotNull RecyclerView recyclerView, @NonNull @NotNull RecyclerView.ViewHolder viewHolder, @NonNull @NotNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull @NotNull RecyclerView.ViewHolder viewHolder, int direction) {
                //delete word
                Words word= mWordAdapter.getWordAt(viewHolder.getAdapterPosition());
                mWordViewModel.delete(word);

            }
        }).attachToRecyclerView(mRecyclerView);
    }
}