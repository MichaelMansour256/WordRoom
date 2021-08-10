package com.example.wordroom;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class WordRepo {
    private WordsDao mWordsDao;
    private LiveData<List<Words>> getAllWords;

    public WordRepo(Application app){
        WordRoomDB db=WordRoomDB.getInstance(app);
        mWordsDao=db.wordsDao();
        getAllWords=mWordsDao.getAllWords();
    }

    //operations

    //insert
    public void insert(Words word){
        new InsertAsyncTask(mWordsDao).execute(word);
    }
    //update
    public void update(Words word){
        new UpdateAsyncTask(mWordsDao).execute(word);
    }
    //delete
    public void delete(Words word){
        new DeleteAsyncTask(mWordsDao).execute(word);
    }
    //delete all
    public void deleteAll(){
        new DeleteAllAsyncTask(mWordsDao).execute();
    }
    //get all
    public LiveData<List<Words>> getAllWords(){
        return getAllWords;
    }

    private static class InsertAsyncTask extends AsyncTask<Words,Void,Void>{

        private WordsDao mWordsDao;
        public InsertAsyncTask(WordsDao wordsDao){
            mWordsDao=wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.insert(words[0]);
            return null;
        }
    }
    private static class DeleteAsyncTask extends AsyncTask<Words,Void,Void>{

        private WordsDao mWordsDao;
        public DeleteAsyncTask(WordsDao wordsDao){
            mWordsDao=wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.delete(words[0]);
            return null;
        }
    }
    private static class UpdateAsyncTask extends AsyncTask<Words,Void,Void>{

        private WordsDao mWordsDao;
        public UpdateAsyncTask(WordsDao wordsDao){
            mWordsDao=wordsDao;
        }
        @Override
        protected Void doInBackground(Words... words) {
            mWordsDao.update(words[0]);
            return null;
        }
    }

    private static class DeleteAllAsyncTask extends AsyncTask<Void,Void,Void>{

        private WordsDao mWordsDao;
        public DeleteAllAsyncTask(WordsDao wordsDao){
            mWordsDao=wordsDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.deleteAllWords();
            return null;
        }
    }
}
