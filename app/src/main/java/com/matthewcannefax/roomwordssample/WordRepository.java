package com.matthewcannefax.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;


//A repository manages query threads and allows you to use multiple backends. In the most common
//example, the repository implements the logic for deciding whether to fetch data from a network or
//use results cached in the local database
public class WordRepository {

    //member variables
    private WordDao mWordDao;
    private LiveData<List<Word>> mAllWords;

    //constructor that gets a handle to the database and initializes the memeber values
    WordRepository(Application application){
        WordRoomDatabase db = WordRoomDatabase.getDatabase(application);
        mWordDao = db.wordDao();
        mAllWords = mWordDao.getAllWords();
    }

    //wrapper method that returns the cached words as LiveData. room executes all queries on a
    //separate thread. Observed LiveData notifies the observer when the data changes
    LiveData<List<Word>> getmAllWords(){
        return mAllWords;
    }

    //wrappr, use async to call insert on a non UI thread or the app will crash. Room ensures that no long running
    //operations run on the main thread
    public void insert(Word word){
        new insertAsyncTask(mWordDao).execute(word);
    }

    //inner class
    private static class insertAsyncTask extends AsyncTask<Word, Void, Void>{
        private WordDao mAsyncTaskDao;

        insertAsyncTask(WordDao dao){
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Word... words) {
            mAsyncTaskDao.insert(words[0]);
            return null;
        }
    }

}
