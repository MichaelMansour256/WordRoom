package com.example.wordroom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WordViewModel extends AndroidViewModel {
    private WordRepo mWordRepo;
    private LiveData<List<Words>> mAllWords;
    public WordViewModel(@NonNull Application application) {
        super(application);
        mWordRepo=new WordRepo(application);
        mAllWords=mWordRepo.getAllWords();
    }

    public void insert(Words word){
        mWordRepo.insert(word);
    }
    public void delete(Words word){
        mWordRepo.delete(word);
    }
    public void update(Words word){
        mWordRepo.update(word);
    }
    public void deleteAll(){
        mWordRepo.deleteAll();
    }
    public LiveData<List<Words>> getAllWords(){
        return mAllWords;
    }
}
