package com.example.wordroom;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = Words.class,version = 1,exportSchema = false)
public abstract class WordRoomDB extends RoomDatabase {


    private static WordRoomDB instance;
    public abstract WordsDao wordsDao();

    //singleton
    public static synchronized WordRoomDB getInstance(Context context){
        if(instance == null){
            instance= Room.databaseBuilder(context.getApplicationContext()
                    ,WordRoomDB.class,"word-database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    //callback
    private static RoomDatabase.Callback roomCallBack=new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDataAsyncTask(instance).execute();

        }
    };

    private static class PopulateDataAsyncTask extends AsyncTask<Void,Void,Void> {

        private WordsDao mWordsDao;
        PopulateDataAsyncTask(WordRoomDB db){
            mWordsDao=db.wordsDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            mWordsDao.insert(new Words("Book","book","noun"));
            mWordsDao.insert(new Words("Book","book","noun"));
            mWordsDao.insert(new Words("Book","book","noun"));
            return null;
        }
    }
}
