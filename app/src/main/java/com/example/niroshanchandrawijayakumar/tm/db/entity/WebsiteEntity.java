package com.example.niroshanchandrawijayakumar.tm.db.entity;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;


@Entity(tableName = "website_table")
public class WebsiteEntity {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "idWebsite")
    public String idWebsite;

    @ColumnInfo(name = "websiteName")
    public String websiteName;

    @ColumnInfo(name = "visitDate")
    public String visitDate;

    @ColumnInfo(name = "totalVisits")
    public int totalVisits;

    public WebsiteEntity(String idWebsite,String websiteName,String visitDate,int totalVisits){
        this.idWebsite = idWebsite;
        this.websiteName = websiteName;
        this.visitDate = visitDate;
        this.totalVisits = totalVisits;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdWebsite() {
        return idWebsite;
    }

    public void setIdWebsite(String idWebsite) {
        this.idWebsite = idWebsite;
    }

    public String getWebsiteName() {
        return websiteName;
    }

    public void setWebsiteName(String websiteName) {
        this.websiteName = websiteName;
    }

    public String getVisitDate() {
        return visitDate;
    }

    public void setVisitDate(String visitDate) {
        this.visitDate = visitDate;
    }

    public int getTotalVisits() {
        return totalVisits;
    }

    public void setTotalVisits(int totalVisits) {
        this.totalVisits = totalVisits;
    }
}
