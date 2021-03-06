package com.bentaher.aiomovie;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Youssef on 22/01/2018.
 */


public class Movie implements Serializable {

    //De data voor onze datastructuur.
    public String title;
    public String id;
    public String rating;
    public String imageLink;
    public String genres;
    public String story;
    public String year;
    String actors;

    //constructor om alle elemten informatie te verkrijgen.
    public Movie(String title, String id, String rating, String imageLink, String genres, String story, String year,
                 String actors){

        this.title = title;
        this.id = id;
        this.rating = rating;
        this.imageLink = imageLink;
        this.genres = genres;
        this.story = story;
        this.year = year;
        this.actors = actors;
    }

    public String getTitle(){
        return title;
    }
    public String getId(){
        return id;
    }
    public String getRating(){
        return rating;
    }
    public String getImageLink(){
        return imageLink;
    }
    public String getGenres(){
        return genres;
    }
    public String getStory(){
        return story;
    }
    public String getYear(){
        return year;
    }
    public String getActors(){
        return actors;
    }

}
