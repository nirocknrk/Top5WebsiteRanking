package com.example.niroshanchandrawijayakumar.tm.repo;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import com.example.niroshanchandrawijayakumar.tm.Dependencies.DependencyRegistry;
import com.example.niroshanchandrawijayakumar.tm.api.Api;
import com.example.niroshanchandrawijayakumar.tm.db.AppDB;
import com.example.niroshanchandrawijayakumar.tm.db.entity.WebsiteEntity;
import com.example.niroshanchandrawijayakumar.tm.model.Website;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class Repository {
    private AppDB db;
    private Api api;

    public Repository(Application application) {
        DependencyRegistry.shared.inject(this,application);
    }

    public LiveData<List<WebsiteEntity>> getAllWebsites() {
        return  db.websiteDao().getAllWebsites();
    }
    public LiveData<List<WebsiteEntity>> getTop5Websites() {
        return  db.websiteDao().getTop5Websites();
    }
//    public void getAllWebsitesAsList(Consumer<List<WebsiteEntity>> listConsumer) {
//        db.websiteDao().getAllWebsitesAsList()
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .doAfterSuccess(listConsumer)
//                .onErrorComplete()
//                .subscribe();
//    }

    public void insertAll(final List<Website> websiteList) {
        Completable.fromAction(()->insertWord(websiteList,true))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doFinally(() -> {
                })
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        Log.d("Repo","Added");
                    }

                    @Override
                    public void onComplete() {
                        Log.d("Repo","Completed");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d("Repo","Error");
                        e.printStackTrace();
                    }
                });
    }

    public Void insertWord(List<Website> websiteList,boolean isClearOld ) {
        if(isClearOld){
            db.websiteDao().deleteAll();
        }
        for (Website website : websiteList){
            db.websiteDao().insert(website.toWebsiteEntity());
        }
        Log.d("DB","Inserted in Thread"+Thread.currentThread().getName());
        return null;
    }

    public void fetchFromNet(Consumer<Throwable> onError,Action onCompleted){
        api.getAllData()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doAfterNext(websites -> {
                    insertAll(websites);onCompleted.run();})
                .doOnError(onError)
                .subscribe();
    }


    public void configureWith(AppDB db, Api api){
        this.db = db;
        this.api = api;
    }
}
