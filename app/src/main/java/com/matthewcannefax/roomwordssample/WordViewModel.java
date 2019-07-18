package com.matthewcannefax.roomwordssample;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

//the role of the viewmodel is to provide data to the UI.
//A communication center between the repository and the UI
//Never pass context into the ViewModel
//Do not store Activity, Fragment or View instances or their Context in the ViewModel

public class WordViewModel extends AndroidViewModel {

    //member variable to hold reference to the Repository
    private WordRepository mRepository;

    //member variable to cache the list of words
    private LiveData<List<Word>> mAllWords;

    //constructor that gets a reference to the wordRepository and gets the list of all words from the Word Repository
    public WordViewModel(Application application){
        super(application);
        mRepository = new WordRepository(application);
        mAllWords = mRepository.getmAllWords();
    }

    LiveData<List<Word>> getAllWords(){return mAllWords;}

    //a getter method that gets all the words. this completely hides the implementation from the UI
    public void inset(Word word){
        mRepository.insert(word);
    }




}
