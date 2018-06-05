package com.example.niroshanchandrawijayakumar.tm.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.niroshanchandrawijayakumar.tm.db.entity.WebsiteEntity;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface WebsiteDao {

    @Insert
    void insert(WebsiteEntity websiteEntity);

    @Query("DELETE FROM website_table")
    void deleteAll();

    @Query("SELECT * from website_table ORDER BY idWebsite ASC")
    Maybe<List<WebsiteEntity>> getAllWebsitesAsList();

    @Query("SELECT * from website_table")
    LiveData<List<WebsiteEntity>> getAllWebsites();

    @Query("SELECT * from website_table ORDER BY totalVisits DESC limit 5")
    LiveData<List<WebsiteEntity>> getTop5Websites();

}
