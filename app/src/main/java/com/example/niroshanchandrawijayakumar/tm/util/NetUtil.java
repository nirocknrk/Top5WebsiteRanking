package com.example.niroshanchandrawijayakumar.tm.util;

import java.util.List;

import okhttp3.Authenticator;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import retrofit2.CallAdapter;
import retrofit2.Converter;
import retrofit2.Retrofit;

public class NetUtil {


    public static Retrofit getRetrofit(Retrofit.Builder retrofitBuilder,
                                                      String baseUrl,
                                                      List<Converter.Factory> converterFactoryList,
                                                      OkHttpClient.Builder okHttpClientBuilder,
                                                      List<Interceptor> listOfInterceptor,
                                                      CallAdapter.Factory callAdapterFactory,
                                                      Authenticator authenticator)  {
        OkHttpClient client = getOkHttpClent(okHttpClientBuilder,listOfInterceptor,authenticator);
        retrofitBuilder.baseUrl(baseUrl).client(client);

        for (Converter.Factory factory : converterFactoryList){
            retrofitBuilder.addConverterFactory(factory);
        }
        if(callAdapterFactory!=null){retrofitBuilder.addCallAdapterFactory(callAdapterFactory);}

        return retrofitBuilder.build();
    }

    public static OkHttpClient getOkHttpClent(OkHttpClient.Builder okHttpClientBuilder,
                                              List<Interceptor> listOfInterceptor,
                                              Authenticator authenticator){
        if ( !listOfInterceptor.isEmpty()){
            for(Interceptor interceptor : listOfInterceptor)
                okHttpClientBuilder.addInterceptor(interceptor);
        }
        if(authenticator!=null){okHttpClientBuilder.authenticator(authenticator);}
        return okHttpClientBuilder.build();
    }
}
