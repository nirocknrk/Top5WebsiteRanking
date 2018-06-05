package com.example.niroshanchandrawijayakumar.tm.api;

import com.example.niroshanchandrawijayakumar.tm.model.Website;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Api {

    @GET("/all_data.php")
    Observable<List<Website>> getAllData() ;

    @GET("/filter.php/{email}")
    Observable<List<Website>> getAllData(@Query("start") String startDate , @Query("end") String endDate) ;
}
