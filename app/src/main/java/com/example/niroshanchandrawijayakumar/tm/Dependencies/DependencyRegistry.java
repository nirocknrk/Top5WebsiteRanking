package com.example.niroshanchandrawijayakumar.tm.Dependencies;

import android.app.Application;

import com.example.niroshanchandrawijayakumar.tm.api.Api;
import com.example.niroshanchandrawijayakumar.tm.db.AppDB;
import com.example.niroshanchandrawijayakumar.tm.repo.Repository;
import com.example.niroshanchandrawijayakumar.tm.util.Config;
import com.example.niroshanchandrawijayakumar.tm.util.NetUtil;

import java.util.ArrayList;
import java.util.Arrays;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class DependencyRegistry {
    public static final DependencyRegistry shared = new DependencyRegistry();

    public void inject(Repository repository,Application application){
        repository.configureWith(getDb(application),getRetrofitForServiceApi().create(Api.class));
    }


    public Retrofit getRetrofitForServiceApi() {
       return NetUtil.getRetrofit(
               new Retrofit.Builder(),
                Config.BASE_URL,
               Arrays.asList(ScalarsConverterFactory.create(), GsonConverterFactory.create()),
                new OkHttpClient.Builder(),
               new ArrayList<Interceptor>(),
               RxJava2CallAdapterFactory.create(),
               null
        );
    }

    public AppDB getDb(Application application){
        return AppDB.getDatabase(application);
    }
}
