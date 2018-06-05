package com.example.niroshanchandrawijayakumar.tm;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;
import android.util.Log;

import com.example.niroshanchandrawijayakumar.tm.db.entity.WebsiteEntity;
import com.example.niroshanchandrawijayakumar.tm.repo.Repository;

import java.util.List;

public class MainViewModel extends AndroidViewModel {
    private Repository repo;
    private LiveData<List<WebsiteEntity>> dataList;


    private MutableLiveData<String> errorData;

    public MainViewModel(@NonNull Application application) {
        super(application);

        errorData = new MutableLiveData();
        repo = new Repository(application);
        repo.fetchFromNet(throwable -> {
                     errorData.postValue("Error");
                    Log.e("Error","Fail fetching data",throwable);
                    throwable.printStackTrace();
        },
                () -> {
                    Log.i("TM","Completed");

        });
        dataList = repo.getAllWebsites();

    }

    public LiveData<List<WebsiteEntity>> getDataList(){ return dataList; }

    public LiveData<List<WebsiteEntity>> getTop5Data(){ return repo.getTop5Websites(); }

    public LiveData<String> listenError(){ return errorData; }

    public void  resetError(){  errorData.postValue(null); }

}
