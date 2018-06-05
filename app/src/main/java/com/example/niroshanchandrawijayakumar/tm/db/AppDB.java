package com.example.niroshanchandrawijayakumar.tm.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.example.niroshanchandrawijayakumar.tm.db.dao.WebsiteDao;
import com.example.niroshanchandrawijayakumar.tm.db.entity.WebsiteEntity;

@Database(entities = {WebsiteEntity.class}, version = 1, exportSchema = false)
public abstract class AppDB extends RoomDatabase {

    private static AppDB INSTANCE;

    public static AppDB getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (AppDB.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context, AppDB.class, "APP_DB").build();
                }
            }
        }
        return INSTANCE;
    }

    public abstract WebsiteDao websiteDao();


}