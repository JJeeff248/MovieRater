/* Based on the ecs 100 template
 * Code for Movie Rater
 * Name: SACH
 * Date: 2/07/2020
 */


import ecs100.*;
import java.util.*;
import java.io.*;
import java.awt.Color;


/** 
 * Store all information about a movie
 * Title as String
 * Genre as String
 * Director as String
 * Rating as boolean
 */
public class Movie{
    
    // Create Fields
    private String title;
    private String genre;
    private String director;
    private String rating;
    
    public Movie(String userTitle, String userGenre, String userDirector, String userRating) {
        this.title = userTitle;
        this.genre = userGenre;
        this.director = userDirector;
        this.rating = userRating;
    }
    
    public String getTitle() {
        return this.title;
    }
    
    public String getGenre() {
        return this.genre;
    }
    
    public String getDirector() {
        return this.director;
    }
    
    public String getRating() {
        return this.rating;
    }
}

