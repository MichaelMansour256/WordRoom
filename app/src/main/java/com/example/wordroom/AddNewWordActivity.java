package com.example.wordroom;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewWordActivity extends AppCompatActivity {
    private EditText mEditTextWord;
    private EditText mEditTextMeaning;
    private EditText mEditTextType;
    public static final String EXTRA_ID="com.example.wordroom.extraid";
    public static final String EXTRA_WORD="com.example.wordroom.extraword";
    public static final String EXTRA_MEANING="com.example.wordroom.extrameaning";
    public static final String EXTRA_TYPE="com.example.wordroom.extratype";
    private boolean editMode;
    private int mId;
    AddNewWordViewModel mAddNewWordViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_word);
        mEditTextWord=findViewById(R.id.edit_text_word);
        mEditTextMeaning=findViewById(R.id.edit_text_meaning);
        mEditTextType=findViewById(R.id.edit_text_type);
        mAddNewWordViewModel= ViewModelProviders.of(this).get(AddNewWordViewModel.class);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_exit);
        Intent i = getIntent();
        if(i.hasExtra(EXTRA_ID)){
            //update
            setTitle("Edit Word");
            editMode=true;
            mId=i.getIntExtra(EXTRA_ID,-1);
            mEditTextWord.setText(i.getStringExtra(EXTRA_WORD));
            mEditTextMeaning.setText(i.getStringExtra(EXTRA_MEANING));
            mEditTextType.setText(i.getStringExtra(EXTRA_TYPE));

        }else{
            //insert
            setTitle("Add New Word");
            editMode=false;

        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater m = getMenuInflater();
        m.inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_word:
                saveWord();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }

    }
    void saveWord(){
        String word=mEditTextWord.getText().toString().trim();
        String meaning=mEditTextMeaning.getText().toString().trim();
        String type=mEditTextType.getText().toString().trim();
        Words wordObj=new Words(word,meaning,type);
        if (word.isEmpty() || meaning.isEmpty() ||type.isEmpty()) {
            Toast.makeText(AddNewWordActivity.this,"please fill all fields",Toast.LENGTH_LONG).show();
            return ;
        }
        if(editMode){
            wordObj.setId(mId);
            mAddNewWordViewModel.update(wordObj);
        }
        else{
            mAddNewWordViewModel.insert(wordObj);

        }
        finish();

    }
}