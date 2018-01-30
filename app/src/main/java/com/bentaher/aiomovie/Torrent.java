package com.bentaher.aiomovie;

import java.io.Serializable;

/**
 * Created by mocro on 28/01/2018.
 */

public class Torrent implements Serializable {

    public String title;
    public String resolution;
    public String size;
    public String sitelink;
    public String imagelink;
    public String magnetlink;

    //constructor om alle elemten informatie te verkrijgen.
    public Torrent(String title, String resolution, String size, String sitelink, String imagelink, String magnetlink){

        this.title = title;
        this.resolution = resolution;
        this.size = size;
        this.sitelink = sitelink;
        this.imagelink = imagelink;
        this.magnetlink = magnetlink;
    }

    public String getTitle(){
        return title;
    }
    public String getResolution(){
        return resolution;
    }
    public String getSize(){
        return size;
    }
    public String getSitelink(){
        return sitelink;
    }
    public String getImagelink(){
        return imagelink;
    }
    public String getMagnetlink(){
        return magnetlink;
    }


}
