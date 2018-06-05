package com.example.niroshanchandrawijayakumar.tm.model;

import com.example.niroshanchandrawijayakumar.tm.db.entity.WebsiteEntity;

public class Website {
    public String id_website;
    public String website_name;
    public String visit_date;
    public String total_visits;

    public Website(WebsiteEntity websiteEntity){
        this.id_website = websiteEntity.idWebsite;
        this.website_name = websiteEntity.websiteName;
        this.visit_date = websiteEntity.visitDate;
        this.total_visits = websiteEntity.totalVisits + "";
    }

    public WebsiteEntity toWebsiteEntity(){
        int toalVisit = 0;
        try{
            toalVisit = Integer.parseInt(total_visits);
        }catch (NumberFormatException e) {}

        return new WebsiteEntity(id_website,website_name,visit_date,toalVisit);
    }
}
