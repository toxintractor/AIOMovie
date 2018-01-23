package com.bentaher.aiomovie;

import java.util.ArrayList;

/**
 * Created by mocro on 22/01/2018.
 */

public class Movie {

    public String title;
    public String rating;
    public String genres;
    public String story;
    ArrayList<String> actors;

    //constructor om alle elemten informatie te verkrijgen.
    public Movie(String title, String rating, String genres, String story,
                 ArrayList<String> actors){

        this.title = title;
        this.rating = rating;
        this.genres = genres;
        this.story = story;
        this.actors = actors;
    }

    public String getTitle(){
        return title;
    }
    public String getRating(){
        return rating;
    }
    public String getGenres(){
        return genres;
    }
    public String getStory(){
        return story;
    }
    public ArrayList<String> getLuchtVochtigHeid(){
        return actors;
    }

}
