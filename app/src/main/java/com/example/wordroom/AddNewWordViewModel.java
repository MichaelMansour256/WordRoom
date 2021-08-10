package com.example.wordroom;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class AddNewWordViewModel extends AndroidViewModel {

    private WordRepo mWordRepo;

    public AddNewWordViewModel(@NonNull Application application) {
        super(application);
        mWordRepo=new WordRepo(application);

    }

    public void insert(Words word){
        mWordRepo.insert(word);
    }

    public void update(Words word){
        mWordRepo.update(word);
    }

}
